// =========================
//  إعدادات المصدر (بدّل لاحقًا)
// =========================
const CONFIG = {
  DATA_URL: null,          // مثال: "data/sample-jobs.json" أو endpoint REST
  DATE_FIELD: 'postedAt',  // اسم عمود التاريخ في الداتا
  TIMEOUT_MS: 20000,
};

// مولد موك (7000 سطر) — يُستخدم إن لم يحدد DATA_URL
function generateMockJobs(n = 7000) {
  const now = new Date();
  const domains = ['company.com','example.io','startup.ai','bank.sa','retail.store','gov.sa','edu.org'];
  const tags = ['#hiring','#remote','#ksa','#java','#python','#sql','#entry','#senior','#hybrid','#fulltime'];
  const titles = ['Software Engineer','Data Analyst','Backend Developer','Frontend Developer','QA Engineer','DevOps','Full‑Stack','Product Manager','Support','BI Engineer'];
  const emails = ['jobs@company.com','careers@example.io','talent@startup.ai','hr@bank.sa'];

  // مواقع تقريبية: تركيز على السعودية + بعض العالم
  const regions = [
    {name:'KSA', lat:[16,32], lng:[34,56], weight:0.65},
    {name:'MENA', lat:[22,37], lng:[10,46], weight:0.15},
    {name:'EU', lat:[40,60], lng:[-10,30], weight:0.1},
    {name:'US', lat:[25,48], lng:[-125,-66], weight:0.1},
  ];
  const pickRegion = () => {
    const r = Math.random();
    let acc = 0;
    for (const reg of regions) {
      acc += reg.weight;
      if (r <= acc) return reg;
    }
    return regions[0];
  };

  const rows = [];
  for (let i = 0; i < n; i++) {
    const daysAgo = Math.floor(Math.random()*45); // آخر 45 يوم
    const dt = new Date(now.getTime() - daysAgo*86400000 - Math.random()*86400000);
    const d = domains[Math.floor(Math.random()*domains.length)];
    const t = titles[Math.floor(Math.random()*titles.length)];
    const tagCount = 1 + Math.floor(Math.random()*4);
    const hs = Array.from({length:tagCount},()=> tags[Math.floor(Math.random()*tags.length)])
                    .filter((v,idx,arr)=> arr.indexOf(v)===idx);
    const withEmail = Math.random() < 0.65; // 65% فيها إيميل
    const em = withEmail ? [emails[Math.floor(Math.random()*emails.length)]] : [];

    const reg = pickRegion();
    const lat = reg.lat[0] + Math.random()*(reg.lat[1]-reg.lat[0]);
    const lng = reg.lng[0] + Math.random()*(reg.lng[1]-reg.lng[0]);

    rows.push({
      id: i+1,
      title: t,
      domain: d,
      hashtags: hs,
      emails: em,
      postedAt: dt.toISOString(),
      description: `${t} at ${d}. We are ${hs.join(' ')}. Contact ${em[0] || '—'}`,
      location: { lat, lng }
    });
  }
  return rows;
}

// تحميل بيانات: إما من API أو موك
async function loadJobs(from, to) {
  const url = (CONFIG.DATA_URL || (document.getElementById('dataUrl')?.value || '').trim()) || null;
  const token = (document.getElementById('apiToken')?.value || '').trim();
  CONFIG.DATE_FIELD = (document.getElementById('dateField')?.value || CONFIG.DATE_FIELD);
  CONFIG.TIMEOUT_MS = +(document.getElementById('timeout')?.value || CONFIG.TIMEOUT_MS);

  if (!url) {
    // موك محلي
    return generateMockJobs(7000)
      .filter(r => inRange(r[CONFIG.DATE_FIELD], from, to))
      .sort((a,b)=> new Date(b[CONFIG.DATE_FIELD]) - new Date(a[CONFIG.DATE_FIELD]));
  }

  // لو عندك API حقيقي: نجلب مع مهلة
  const ctl = new AbortController();
  const tid = setTimeout(()=> ctl.abort(), CONFIG.TIMEOUT_MS);
  try {
    const res = await fetch(url, {
      headers: token ? { 'Authorization': token } : undefined,
      signal: ctl.signal,
    });
    clearTimeout(tid);
    if (!res.ok) throw new Error('HTTP '+res.status);
    const rows = await res.json();
    return rows
      .filter(r => inRange(r[CONFIG.DATE_FIELD], from, to))
      .sort((a,b)=> new Date(b[CONFIG.DATE_FIELD]) - new Date(a[CONFIG.DATE_FIELD]));
  } catch (e) {
    clearTimeout(tid);
    console.error('Fetch error', e);
    alert('تعذر جلب البيانات من المصدر المحدد. سيتم استخدام بيانات موك.');
    return generateMockJobs(7000)
      .filter(r => inRange(r[CONFIG.DATE_FIELD], from, to))
      .sort((a,b)=> new Date(b[CONFIG.DATE_FIELD]) - new Date(a[CONFIG.DATE_FIELD]));
  }
}

function inRange(iso, from, to){
  const d = new Date(iso);
  return (!from || d >= from) && (!to || d <= to);
}

// =============
//  واجهة المستخدم
// =============
const el = s=> document.querySelector(s);
const fmtInt = v=> new Intl.NumberFormat('ar-EG').format(Math.round(v||0));

const state = {
  rows: [],
  filtered: [],
  todayKey: null,
  charts: { trend:null, domains:null, tags:null, emails:null },
  page: 1, pageSize: 50,
  map: null, clusters: null
};

function initDates(){
  const to = new Date();
  const from = new Date(to.getTime() - 30*86400000);
  el('#fromDate').value = toInputDate(from);
  el('#toDate').value = toInputDate(to);
}
function toInputDate(d){
  const z = n=> String(n).padStart(2,'0');
  return `${d.getFullYear()}-${z(d.getMonth()+1)}-${z(d.getDate())}`;
}

function parseFilters(){
  const from = el('#fromDate').value ? new Date(el('#fromDate').value+'T00:00:00') : null;
  const to   = el('#toDate').value ? new Date(el('#toDate').value+'T23:59:59') : null;
  const domain = el('#domainFilter').value.trim().toLowerCase();
  const tags = el('#hashtagFilter').value.trim().toLowerCase().split(/\s+/).filter(Boolean);
  const q = el('#q').value.trim().toLowerCase();
  return { from, to, domain, tags, q };
}

function applyFilters(){
  const f = parseFilters();
  state.filtered = state.rows.filter(r => {
    const text = `${r.title} ${r.description} ${(r.emails||[]).join(' ')}`.toLowerCase();
    const domainOK = !f.domain || (r.domain||'').toLowerCase().includes(f.domain);
    const tagsOK = !f.tags.length || f.tags.every(t=> (r.hashtags||[]).some(h=> h.toLowerCase().includes(t.replace(/^#/,''))));
    const qOK = !f.q || text.includes(f.q);
    return domainOK && tagsOK && qOK;
  });
  state.page = 1;
  renderEverything();
}

function setKPIs(){
  const byDay = groupBy(state.filtered, r=> toInputDate(new Date(r[CONFIG.DATE_FIELD])));
  const today = toInputDate(new Date());
  el('#kpiToday').textContent = fmtInt(byDay[today]?.length || 0);

  // آخر 7 و 30 يوم في الفلتر
  const to = el('#toDate').value ? new Date(el('#toDate').value+'T23:59:59') : new Date();
  const d7 = new Date(to.getTime() - 6*86400000);
  const d30= new Date(to.getTime() - 29*86400000);
  el('#kpi7').textContent  = fmtInt(state.filtered.filter(r=> inRange(r[CONFIG.DATE_FIELD], d7, to)).length);
  el('#kpi30').textContent = fmtInt(state.filtered.filter(r=> inRange(r[CONFIG.DATE_FIELD], d30, to)).length);

  // دومينات فريدة
  const domains = new Set(state.filtered.map(r=> r.domain).filter(Boolean));
  el('#kpiDomains').textContent = fmtInt(domains.size);

  // متوسط/اليوم للفترة الحالية
  const f = parseFilters();
  const days = Math.max(1, Math.ceil(((f.to||new Date()) - (f.from||new Date()))/86400000) + 1);
  el('#kpiAvg').textContent = fmtInt(state.filtered.length / days);

  // وجود إيميل/هاشتاج
  el('#kpiWithEmail').textContent = fmtInt(state.filtered.filter(r=> (r.emails||[]).length).length);
  el('#kpiWithTags').textContent  = fmtInt(state.filtered.filter(r=> (r.hashtags||[]).length).length);

  el('#kpiLastIngest').textContent = new Date().toLocaleString('ar-EG');
}

function groupBy(arr, keyFn){
  return arr.reduce((m, r)=>{const k = keyFn(r); (m[k]||(m[k]=[])).push(r); return m;}, {});
}

function topN(mapOrArr, n=10){
  if (Array.isArray(mapOrArr)) {
    const m = new Map();
    mapOrArr.forEach(v=> m.set(v, (m.get(v)||0)+1));
    return [...m.entries()].sort((a,b)=> b[1]-a[1]).slice(0,n);
  }
  return [...mapOrArr.entries()].sort((a,b)=> b[1]-a[1]).slice(0,n);
}

function buildCharts(){
  // Trend line: jobs per day
  const byDay = groupBy(state.filtered, r=> toInputDate(new Date(r[CONFIG.DATE_FIELD])));
  const labels = Object.keys(byDay).sort();
  const series = labels.map(d=> byDay[d].length);
  mountChart('trend', labels, series);

  // Domains bar — Top10
  const doms = topN(state.filtered.map(r=> r.domain||'—'), 10);
  mountChart('domains', doms.map(([k])=>k), doms.map(([,v])=>v), 'bar');

  // Hashtags — Top15
  const allTags = state.filtered.flatMap(r=> (r.hashtags||[]).map(h=> h.startsWith('#')?h:`#${h}`));
  const tagsTop = topN(allTags, 15);
  mountChart('tags', tagsTop.map(([k])=>k), tagsTop.map(([,v])=>v), 'bar');

  // Emails quality — withEmail vs without
  const withEmail = state.filtered.filter(r=> (r.emails||[]).length).length;
  const without = state.filtered.length - withEmail;
  mountChart('emails', ['مع إيميل','بدون'], [withEmail, without], 'doughnut');
}

function mountChart(kind, labels, data, type='line'){
  const id = kind === 'trend' ? '#chartTrend'
           : kind === 'domains' ? '#chartDomains'
           : kind === 'tags' ? '#chartTags' : '#chartEmails';
  const ctx = el(id);
  if (!ctx) return;
  const prev = state.charts[kind];
  if (prev) prev.destroy();
  state.charts[kind] = new Chart(ctx, {
    type,
    data: { labels, datasets:[{ label: 'القيمة', data }] },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      plugins: { legend: { display: type!=='line' } },
      scales: (type==='line' || type==='bar') ? { y: { beginAtZero: true } } : {},
    }
  });
}

function renderTable(){
  const tbody = el('#tbl tbody');
  tbody.innerHTML = '';
  const page = state.page;
  const sz = state.pageSize;
  const start = (page-1)*sz;
  const rows = state.filtered.slice(start, start+sz);
  rows.forEach((r, i)=>{
    const loc = r.location ? `${r.location.lat.toFixed(2)}, ${r.location.lng.toFixed(2)}` : '—';
    const tr = document.createElement('tr');
    tr.innerHTML = `
      <td>${start+i+1}</td>
      <td>${new Date(r[CONFIG.DATE_FIELD]).toLocaleDateString('ar-EG')}</td>
      <td>${escapeHtml(r.title||'—')}</td>
      <td>${loc}</td>
      <td>${escapeHtml(r.domain||'—')}</td>
      <td>${(r.hashtags||[]).map(h=> `<span class="tag">${escapeHtml(h)}</span>`).join(' ') || '—'}</td>
      <td>${(r.emails||[]).join('<br>') || '—'}</td>
    `;
    tbody.appendChild(tr);
  });
  el('#countLabel').textContent = `عرض ${start+1}–${start+rows.length} من ${fmtInt(state.filtered.length)}`;
}

function escapeHtml(s){
  return String(s).replace(/[&<>\"']/g, m=> ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;','\'':'&#39;'}[m]));
}

function renderEverything(){
  setKPIs();
  buildCharts();
  renderTable();
  drawMap();
}

// تصدير
function exportCSV(){
  const header = ['id','date','title','lat','lng','domain','hashtags','emails'];
  const lines = [header.join(',')];
  state.filtered.forEach(r=>{
    const line = [
      r.id,
      new Date(r[CONFIG.DATE_FIELD]).toISOString(),
      csvSafe(r.title||''),
      r.location ? r.location.lat : '',
      r.location ? r.location.lng : '',
      csvSafe(r.domain||''),
      csvSafe((r.hashtags||[]).join(' ')),
      csvSafe((r.emails||[]).join(' '))
    ].join(',');
    lines.push(line);
  });
  const blob = new Blob([lines.join('\n')], {type:'text/csv;charset=utf-8'});
  downloadBlob(blob, 'jobs_export.csv');
}
function csvSafe(s){
  const v = String(s).replaceAll('"','""');
  return /[",\n]/.test(v) ? `"${v}"` : v;
}
function exportJSON(){
  const blob = new Blob([JSON.stringify(state.filtered,null,2)], {type:'application/json'});
  downloadBlob(blob, 'jobs_export.json');
}
function downloadBlob(blob, name){
  const a = document.createElement('a');
  a.href = URL.createObjectURL(blob); a.download = name; a.click();
  setTimeout(()=> URL.revokeObjectURL(a.href), 2000);
}

// تنقّل الجدول
function bindPaging(){
  el('#page').addEventListener('change', ()=>{
    state.page = Math.max(1, parseInt(el('#page').value||'1',10));
    renderTable();
  });
  el('#pageSize').addEventListener('change', ()=>{
    state.pageSize = parseInt(el('#pageSize').value, 10);
    state.page = 1; renderTable();
  });
  el('#btnPagePrev').addEventListener('click', ()=>{
    if (state.page>1){ state.page--; el('#page').value = state.page; renderTable(); }
  });
  el('#btnPageNext').addEventListener('click', ()=>{
    const maxPage = Math.max(1, Math.ceil(state.filtered.length/state.pageSize));
    if (state.page<maxPage){ state.page++; el('#page').value = state.page; renderTable(); }
  });
}

// التابات
function bindTabs(){
  document.querySelectorAll('.tabs .tab').forEach(btn=>{
    btn.addEventListener('click', ()=>{
      document.querySelectorAll('.tabs .tab').forEach(b=> b.classList.remove('active'));
      document.querySelectorAll('.panes .pane').forEach(p=> p.classList.remove('active'));
      btn.classList.add('active');
      const idx = Array.from(btn.parentElement.children).indexOf(btn);
      document.querySelectorAll('.panes .pane')[idx].classList.add('active');
      // إعادة رسم الرسم/الخريطة النشطة
      if (btn.dataset.tab === 'map') drawMap(true);
      else buildCharts();
    });
  });
}

// الأحداث الرئيسية
function bindUI(){
  el('#btnApply').addEventListener('click', applyFilters);
  el('#btnReset').addEventListener('click', ()=>{ initDates(); el('#domainFilter').value=''; el('#hashtagFilter').value=''; el('#q').value=''; applyFilters(); });
  el('#btnReload').addEventListener('click', async ()=>{ await boot(true); });
  el('#btnExportCSV').addEventListener('click', exportCSV);
  el('#btnExportJSON').addEventListener('click', exportJSON);
}

async function boot(forceReload=false){
  const { from, to } = parseFilters();
  if (forceReload || !state.rows.length){
    el('#kpiLastIngest').textContent = '… جار التحميل';
    state.rows = await loadJobs(from, to);
  }
  state.filtered = state.rows.slice();
  state.page = 1; el('#page').value = 1; state.pageSize = parseInt(el('#pageSize').value,10);
  renderEverything();
}

// ============
//   الخريطة
// ============
function drawMap(force) {
  const mapPaneActive = document.querySelector('.tabs .tab.active')?.dataset.tab === 'map';
  if (!mapPaneActive && !force) return;

  if (!state.map) {
    state.map = L.map('map', { zoomControl: true }).setView([23.8859, 45.0792], 5); // وسط السعودية
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 18,
      attribution: '&copy; OpenStreetMap contributors'
    }).addTo(state.map);
  }

  // أعد بناء التجمعات
  if (state.clusters) { state.map.removeLayer(state.clusters); }
  state.clusters = L.markerClusterGroup({ chunkedLoading: true });

  const withEmailLayer = L.layerGroup();
  const withoutEmailLayer = L.layerGroup();

  state.filtered.forEach(r => {
    if (!r.location) return;
    const hasEmail = (r.emails || []).length > 0;
    const color = hasEmail ? '#22c55e' : '#ef4444';

    const marker = L.circleMarker([r.location.lat, r.location.lng], {
      radius: 6, weight: 1, color: '#0a0a0a', fillColor: color, fillOpacity: 0.9
    });
    const html = `
      <div style="min-width:220px;direction:rtl">
        <div style="font-weight:700;margin-bottom:4px">${escapeHtml(r.title||'—')}</div>
        <div><b>التاريخ:</b> ${new Date(r[CONFIG.DATE_FIELD]).toLocaleString('ar-EG')}</div>
        <div><b>الدومين:</b> ${escapeHtml(r.domain||'—')}</div>
        <div><b>الهاشتاج:</b> ${(r.hashtags||[]).join(' ') || '—'}</div>
        <div><b>الإيميلات:</b> ${(r.emails||[]).join(', ') || '—'}</div>
        <div style="color:#9ca3af;margin-top:4px">${r.location.lat.toFixed(4)}, ${r.location.lng.toFixed(4)}</div>
      </div>`;
    marker.bindPopup(html);

    if (hasEmail) withEmailLayer.addLayer(marker);
    else withoutEmailLayer.addLayer(marker);
  });

  state.clusters.addLayer(withEmailLayer);
  state.clusters.addLayer(withoutEmailLayer);
  state.map.addLayer(state.clusters);

  // تحكم الطبقات
  const overlayMaps = {
    "مع إيميل": withEmailLayer,
    "بدون إيميل": withoutEmailLayer
  };
  // إزالة الضوابط السابقة إن وجدت
  if (state._layerControl) {
    state.map.removeControl(state._layerControl);
  }
  state._layerControl = L.control.layers(null, overlayMaps, { collapsed: false, position:'topright' }).addTo(state.map);

  // ضبط العرض على كل النقاط
  try {
    const bounds = state.clusters.getBounds();
    if (bounds.isValid()) state.map.fitBounds(bounds.pad(0.1));
  } catch (e) {}
}

// تشغيل
window.addEventListener('DOMContentLoaded', async ()=>{
  initDates(); bindUI(); bindTabs(); bindPaging();
  await boot(true);
});
