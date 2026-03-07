const puppeteer = require('puppeteer');
const fs = require('fs');

(async () => {
  const browser = await puppeteer.launch({ headless: false, args: ['--start-maximized'] });
  const page = await browser.newPage();

  await page.goto('https://www.linkedin.com/login');

  console.log("🔐 سجل دخولك يدويًا الآن...");
  await new Promise(resolve => setTimeout(resolve, 60000));

  const cookies = await page.cookies();
  fs.writeFileSync('linkedin_cookies.json', JSON.stringify(cookies, null, 2));
  console.log("✅ تم حفظ الكوكيز");

  await browser.close();
})();
