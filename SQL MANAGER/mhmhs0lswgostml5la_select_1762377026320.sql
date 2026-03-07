-- Title: أخر30 مرة تم تسجل وظايف
-- Description: كود بيجيب أخر 30 يوم تم تسجل وظايف فيهم وبيتجاهل الايام التى لم تتم اضافة وظايف
-- Category: select
-- Date: ١٤ جمادى الأولى ١٤٤٧ هـ في ١١:١٠ م
-- ID: mhmhs0lswgostml5la

SET @d = DATE_FORMAT(CURDATE(), '%Y%m%d');   -- مثال ناتج: 20250918
SET @n = 30;

SELECT
  F.Scrapped_Date,
  COUNT(*) AS job_count
FROM jobs AS F
WHERE F.Scrapped_Date >= DATE_FORMAT(DATE_SUB(STR_TO_DATE(@d, '%Y%m%d'), INTERVAL @n DAY), '%Y%m%d')
  AND F.Scrapped_Date <= DATE_FORMAT(STR_TO_DATE(@d, '%Y%m%d'), '%Y%m%d')
GROUP BY F.Scrapped_Date
ORDER BY F.Scrapped_Date DESC;