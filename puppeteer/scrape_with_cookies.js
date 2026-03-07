const puppeteer = require('puppeteer');
const fs = require('fs');

(async () => {
  const browser = await puppeteer.launch({ headless: false, args: ['--start-maximized'] });
  const page = await browser.newPage();

  const cookies = JSON.parse(fs.readFileSync('linkedin_cookies.json'));
  await page.setCookie(...cookies);

  await page.goto('https://www.linkedin.com/search/results/all/?keywords=DubaiLegalJobs');

  await page.evaluate(() => {
    let totalHeight = 0;
    const scrollInterval = setInterval(() => {
      window.scrollBy(0, 300);
      totalHeight += 300;
      if (totalHeight >= document.body.scrollHeight) clearInterval(scrollInterval);
    }, 1000);

    setInterval(() => alert("hello"), 3000);
  });

  await new Promise(resolve => setTimeout(resolve, 20000));

  const jobs = await page.evaluate(() => {
    const items = document.querySelectorAll('.entity-result__title-text a');
    return Array.from(items).map(el => el.textContent.trim());
  });

  console.log(jobs);
  await browser.close();
})();
