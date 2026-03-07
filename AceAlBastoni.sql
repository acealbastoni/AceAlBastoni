-- █████████████████████████████████████████████████████████████████

SELECT 
    ProfileID AS Name,
    COUNT(ProfileID) AS MentionCount
FROM 
    newschema.jobdescriptions
    where 
--     (ProfileID like 'true' or ProfileID like '%true%' or ProfileID like 'true%' or ProfileID like '%true')

TimeInputSystem like '2025/01/16%'
GROUP BY 
    ProfileID
ORDER BY 
    MentionCount DESC;

-- █████████████████████████████████████████████████████████████████

SELECT * 
FROM newschema.jobdescriptions 
ORDER BY DKEY DESC 
LIMIT 100;


-- █████████████████████████████████████████████████████████████████


SELECT * FROM newschema.jobdescriptions order by TimeInputSystem desc LIMIT 100;


-- █████████████████████████████████████████████████████████████████


SELECT count(*)
FROM newschema.jobdescriptions
WHERE ProfileID like 'hashtag_%'
and TimeInputSystem like '2025/01/16%'
ORDER BY TimeInputSystem desc

-- █████████████████████████████████████████████████████████████████

SELECT TimeInputSystem, COUNT(*)
FROM `newschema`.`jobdescriptions`
GROUP BY TimeInputSystem
HAVING COUNT(*) > 1;

-- █████████████████████████████████████████████████████████████████

SELECT TimeInputSystem, COUNT(*) as cou
FROM `newschema`.`jobdescriptions`
GROUP BY TimeInputSystem
HAVING COUNT(*) > 1
order by cou desc;

-- █████████████████████████████████████████████████████████████████
-- SELECT DKEY, 
--        plaintextjobdescription, 
--        REGEXP_EXTRACT_ALL(plaintextjobdescription, '#[^ ]+ ') AS MatchedWords
-- FROM newschema.jobdescriptions
-- WHERE plaintextjobdescription REGEXP '#[^ ]+ '
-- ORDER BY DKEY DESC
-- LIMIT 100;

-- █████████████████████████████████████████████████████████████████

SELECT word AS wordStartingWithHash, 
       COUNT(*) AS countMentionedInTheLast50Records
FROM (
    SELECT DKEY, 
           REGEXP_SUBSTR(plaintextjobdescription, '#[^ ]+') AS word
    FROM newschema.jobdescriptions
    WHERE plaintextjobdescription REGEXP '#[^ ]+'  and TimeInputSystem like '2025/01/16%'
    ORDER BY DKEY DESC
    LIMIT 5000
) AS Words
GROUP BY word
ORDER BY countMentionedInTheLast50Records DESC;

https://www.linkedin.com/feed/hashtag/?keywords=

-- █████████████████████████████████████████████████████████████████

SELECT CONCAT('https://www.linkedin.com/feed/hashtag/?keywords=', SUBSTRING(word, 2)) AS hashtagLink,
       COUNT(*) AS countMentionedInTheLast50Records
FROM (
    SELECT DKEY, 
           REGEXP_SUBSTR(plaintextjobdescription, '#[^ ]+') AS word
    FROM newschema.jobdescriptions
    WHERE plaintextjobdescription REGEXP '#[^ ]+'
    ORDER BY DKEY DESC
    LIMIT 50000
) AS Words
GROUP BY word
ORDER BY countMentionedInTheLast50Records DESC;


-- █████████████████████████████████████████████████████████████████


SELECT 
    LEFT(HashedJobDescription, 1) AS StartingChar, -- استخراج أول حرف أو رقم
    COUNT(*) AS Count
FROM 
    newschema.jobdescriptions
WHERE 
    LEFT(HashedJobDescription, 1) IN ('0', '1', '2', '3', '4','5','6','7','8','9','a','b','c','d','e', 'f') -- الأحرف/الأرقام المطلوبة
GROUP BY 
    LEFT(HashedJobDescription, 1) 
ORDER BY 
    Count DESC; 

-- █████████████████████████████████████████████████████████████████

SELECT 
    ProfileID AS Name,
    COUNT(ProfileID) AS MentionCount
FROM 
    newschema.jobdescriptions
GROUP BY 
    ProfileID
ORDER BY 
    MentionCount DESC;

-- █████████████████████████████████████████████████████████████████


SELECT count(*) FROM newschema.jobdescriptions;


-- █████████████████████████████████████████████████████████████████

SELECT 
    ProfileID AS Name,
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/14%' THEN 1 END) AS '2025/01/14',
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/15%' THEN 1 END) AS '2025/01/15',
	COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/16%' THEN 1 END) AS '2025/01/16',
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/17%' THEN 1 END) AS '2025/01/17',
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/18%' THEN 1 END) AS '2025/01/18'
FROM 
    newschema.jobdescriptions
where ProfileID like 'hashtag_%'
GROUP BY 
    ProfileID
ORDER BY 
   name DESC;

-- █████████████████████████████████████████████████████████████████

SELECT 
    ProfileID AS Name,
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/14%' THEN 1 END) AS '2025/01/14',
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/15%' THEN 1 END) AS '2025/01/15',
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/16%' THEN 1 END) AS '2025/01/16',
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/17%' THEN 1 END) AS '2025/01/17',
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/18%' THEN 1 END) AS '2025/01/18'
FROM 
    newschema.jobdescriptions
WHERE 
    ProfileID LIKE 'hashtag_%'
GROUP BY 
    ProfileID
HAVING 
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/14%' THEN 1 END) > 5 AND
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/15%' THEN 1 END) > 5 AND
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/16%' THEN 1 END) > 5 AND
    COUNT(CASE WHEN TimeInputSystem LIKE '2025/01/17%' THEN 1 END) > 5
ORDER BY 
    Name DESC;


-- █████████████████████████████████████████████████████████████████

SELECT LEFT(TimeInputSystem, 10) AS DatePart, COUNT(*) AS Count
FROM newschema.jobdescriptions
WHERE LEFT(TimeInputSystem, 10) IN (
'2025/01/13',
'2025/01/14',
'2025/01/15',
'2025/01/16',
'2025/01/17',
'2025/01/18')
GROUP BY LEFT(TimeInputSystem, 10);
-- █████████████████████████████████████████████████████████████████
-- █████████████████████████████████████████████████████████████████
-- ██████████████████████ Loading ➜ InputFiles-Owner ███████████████████████████████████████████
SET GLOBAL sql_mode = '';
LOAD DATA INFILE 'C:\\ProgramData\\MySQL\\MySQL Server 8.0\\Uploads\\InputFiles-OwnerA.csv'
INTO TABLE inputfilesowner
CHARACTER SET latin1
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

SET GLOBAL sql_mode = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION';
-- █████████████████████████████████████████████████████████████████

-- █████████████████████████████████████████████████████████████████

-- █████████████████████████████████████████████████████████████████



-- ███████████████████████████ 
SELECT FILE_NAME, COUNT(*) 
FROM [InputFilesOwner]
GROUP BY FILE_NAME
HAVING COUNT(*) > 1;

--███████████████████████████ ??????????????????
Remove Duplicates (if any):

WITH CTE AS (
    SELECT 
        ROW_NUMBER() OVER (PARTITION BY FILE_ID ORDER BY (SELECT NULL)) AS RowNum
    FROM [InputFilesOwner]
)
DELETE FROM CTE
WHERE RowNum > 1;


--███████████████████████████ 
--simulate

WITH CTE AS (
    SELECT 
        FILE_ID,
        FILE_NAME,
        ROW_NUMBER() OVER (PARTITION BY FILE_ID ORDER BY FILE_NAME) AS RowNum
    FROM [InputFilesOwner]
)
SELECT *
FROM CTE
WHERE RowNum > 1;

-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- ███████████████████████████████ 22112025 Start █████████████████████████████████
https://chatgpt.com/c/6920aa73-99c0-8333-862e-a256cfae34c2
✅ الخطوة 1 — إنشاء جدول الباكب باسم ACE_JOBS

DROP TABLE IF EXISTS newschema.ACE_JOBS;

CREATE TABLE newschema.ACE_JOBS
LIKE newschema.jobs;

✅ الخطوة 2 — نسخ كل البيانات من jobs إلى ACE_JOBS
INSERT INTO newschema.ACE_JOBS
SELECT *
FROM newschema.jobs;


✅ الخطوة 3 — التأكد أن النسخة مكتملة
SELECT COUNT(*) AS original_count
FROM newschema.jobs;

SELECT COUNT(*) AS backup_count
FROM newschema.ACE_JOBS;


-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- STEP1 
🔵 خطوة 1 (معدّلة) – إنشاء ACE_JOBS_10K بدون الريكوردات اللي التاريخ فيها فاضي

DROP TABLE IF EXISTS newschema.ACE_JOBS_10K;

CREATE TABLE newschema.ACE_JOBS_10K AS
SELECT *
FROM (
    SELECT *
    FROM newschema.ACE_JOBS
    WHERE Time_Input_The_System IS NOT NULL
      AND Time_Input_The_System <> ''
      AND Time_Input_The_System LIKE '__%/__%/__% - __:__:__'
) AS t
ORDER BY STR_TO_DATE(Time_Input_The_System, '%Y/%m/%d - %H:%i:%s')
LIMIT 10000;

-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████

🟦 الخطوة 2 – تأكد من تحويل التاريخ من النص إلى DATETIME

SELECT 
    Time_Input_The_System,
    STR_TO_DATE(Time_Input_The_System, '%Y/%m/%d - %H:%i:%s') AS dt
FROM newschema.ACE_JOBS_10K
LIMIT 20;



-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
--STEP 3
--🟦 الخطوة 3 – إنشاء جدول إحصائيات الإيميلات 

DROP TABLE IF EXISTS newschema.ACE_JOBS_EMAIL_STATS;

CREATE TABLE newschema.ACE_JOBS_EMAIL_STATS (
    email          VARCHAR(255) NOT NULL,
    last_mentioned DATETIME     NOT NULL,
    mention_count  INT          NOT NULL,
    PRIMARY KEY (email)
);

-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- STEP 4 

SET PERSIST regexp_time_limit = 4000; 

INSERT INTO newschema.ACE_JOBS_EMAIL_STATS (email, last_mentioned, mention_count)
SELECT
    email,
    MAX(time_dt)  AS last_mentioned,
    COUNT(*)      AS mention_count
FROM (
    SELECT
        LOWER(
            REGEXP_SUBSTR(
                PlainText_JOB_DESCRIPTION,
                '[.0-9A-Za-z_-]+@([0-9A-Za-z_-]+[.])+[0-9A-Za-z_-]+'
            )
        ) AS email,
        STR_TO_DATE(Time_Input_The_System, '%Y/%m/%d - %H:%i:%s') AS time_dt
    FROM newschema.ACE_JOBS_10K
    WHERE PlainText_JOB_DESCRIPTION LIKE '%@%'
) AS t
WHERE email IS NOT NULL
  AND time_dt IS NOT NULL
GROUP BY email;
-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- STEP 5

🟦 الخطوة 5 – مراجعة النتيجة (Top الإيميلات)
SELECT *
FROM newschema.ACE_JOBS_EMAIL_STATS
ORDER BY mention_count DESC, last_mentioned DESC
LIMIT 50;

-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
--STEP 6
🟦 الخطوة 6 – مثال استخدام (ربط الإيميل بوظائفه من عينة الـ 10k)


SELECT 
    s.email,
    s.last_mentioned,
    s.mention_count,
    j.PlainText_JOB_DESCRIPTION
FROM newschema.ACE_JOBS_EMAIL_STATS s
JOIN newschema.ACE_JOBS_10K j
  ON j.PlainText_JOB_DESCRIPTION LIKE CONCAT('%', s.email, '%')
WHERE s.mention_count >= 3
LIMIT 50;


-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
1️⃣ إنشاء جدول الربط (وظيفة ← إيميل)




DROP TABLE IF EXISTS newschema.ACE_JOBS_10K_EMAILS;

CREATE TABLE newschema.ACE_JOBS_10K_EMAILS (
    dkey    INT          NOT NULL,   -- مفتاح الوظيفة
    email   VARCHAR(255) NOT NULL,   -- إيميل واحد في كل صف
    time_dt DATETIME     NOT NULL    -- التاريخ المحوَّل من Time_Input_The_System
);



-- ████████████████████████████████████████████████████████████████████████████████



INSERT INTO newschema.ACE_JOBS_10K_EMAILS (dkey, email, time_dt)
WITH RECURSIVE job_emails AS (
    -- الإيميل الأول
    SELECT
        j.dkey,
        STR_TO_DATE(j.Time_Input_The_System, '%Y/%m/%d - %H:%i:%s') AS time_dt,
        j.PlainText_JOB_Description AS full_text,
        1 AS occ,
        REGEXP_SUBSTR(
            j.PlainText_JOB_Description,
            '[.0-9A-Za-z_-]+@([0-9A-Za-z_-]+[.])+[0-9A-Za-z_-]+',
            1,
            1
        ) AS email
    FROM newschema.ACE_JOBS_10K j
    WHERE j.PlainText_JOB_Description LIKE '%@%'

    UNION ALL

    -- الإيميل الثاني + الثالث + الرابع ... إلخ
    SELECT
        job_id.dkey,
        job_id.time_dt,
        job_id.full_text,
        job_id.occ + 1 AS occ,
        REGEXP_SUBSTR(
            job_id.full_text,
            '[.0-9A-Za-z_-]+@([0-9A-Za-z_-]+[.])+[0-9A-Za-z_-]+',
            1,
            job_id.occ + 1
        ) AS email
    FROM job_emails AS job_id
    WHERE job_id.email IS NOT NULL
)
SELECT DISTINCT
    dkey,
    LOWER(email) AS email,
    time_dt
FROM job_emails
WHERE email IS NOT NULL;
-- █████████████████████████████████████████████████████████████████████████████████
-- ██████████████████████████████████████ACE_JOBS_10K_EMAILS ███████████████████████
🟦 الخطوة 2 – إنشاء جدول إحصائيات الإيميلات

2.1 احذف (لو موجود) ثم أنشئ الجدول

DROP TABLE IF EXISTS newschema.ACE_JOBS_EMAIL_STATS;

CREATE TABLE newschema.ACE_JOBS_EMAIL_STATS (
    email          VARCHAR(255) NOT NULL,
    last_mentioned DATETIME     NOT NULL,  -- أحدث تاريخ ظهر فيه الميل
    mention_count  INT          NOT NULL,  -- عدد الوظايف اللي اتذكر فيها الميل
    PRIMARY KEY (email)
);






2.2 املأ الجدول من ACE_JOBS_10K_EMAILS

INSERT INTO newschema.ACE_JOBS_EMAIL_STATS (email, last_mentioned, mention_count)
SELECT
    email,
    MAX(time_dt)            AS last_mentioned,
    COUNT(DISTINCT dkey)    AS mention_count
FROM newschema.ACE_JOBS_10K_EMAILS
GROUP BY email;

SELECT *
FROM newschema.ACE_JOBS_EMAIL_STATS
ORDER BY mention_count DESC, last_mentioned DESC
LIMIT 50;

-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████

🟦 الخطوة 3 – جلب N وظائف لكل إيميل (زي ما اتفقنا)

دلوقتي هنجيب مثلًا 3 وظائف لكل إيميل (تقدر تغيّر الرقم).


SET @N = 6;   -- غيّرها لأي رقم تحبه
WITH email_job_matches AS (
    SELECT
        s.email,
        s.last_mentioned,
        s.mention_count,
        j.dkey,
        j.PlainText_JOB_Description,
        ROW_NUMBER() OVER (
            PARTITION BY s.email
            ORDER BY ej.time_dt DESC
        ) AS rn
    FROM newschema.ACE_JOBS_EMAIL_STATS      s
    JOIN newschema.ACE_JOBS_10K_EMAILS       ej ON ej.email = s.email
    JOIN newschema.ACE_JOBS_10K              j  ON j.dkey  = ej.dkey
    -- عدّل الشرط ده على مزاجك:
    WHERE s.mention_count >= 3
)
SELECT *
FROM email_job_matches
WHERE rn <= @N 
ORDER BY mention_count,email, rn;

-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
تمام، هنستخدم نفس كويري N وظائف لكل إيميل لكن هنفلتر على دومين @intalio.com فقط 👌

✅ N وظائف لكل إيميل من دومين intalio.com


SET @N = 3;   -- غيّرها لأي رقم تحبه

WITH email_job_matches AS (
    SELECT
        s.email,
        s.last_mentioned,
        s.mention_count,
        j.dkey,
        j.PlainText_JOB_Description,
        ROW_NUMBER() OVER (
            PARTITION BY s.email
            ORDER BY ej.time_dt DESC
        ) AS rn
    FROM newschema.ACE_JOBS_EMAIL_STATS      s
    JOIN newschema.ACE_JOBS_10K_EMAILS       ej ON ej.email = s.email
    JOIN newschema.ACE_JOBS_10K              j  ON j.dkey  = ej.dkey
    WHERE SUBSTRING_INDEX(s.email, '@', -1) = 'intalio.com'   -- فلتر الدومين
)
SELECT *
FROM email_job_matches
WHERE rn <= @N                    -- N وظائف لكل إيميل
ORDER BY email, rn;


-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████

✅ N وظائف فقط لدومين معيّن (مثلاً intalio.com)

SET @domain = 'intalio.com';
SET @N      = 10;   -- غيّرها لأي عدد وظائف تحب

WITH domain_jobs AS (
    SELECT
        e.dkey,
        MAX(e.time_dt)        AS last_mentioned,        -- أحدث تاريخ للدومين في هذه الوظيفة
        COUNT(*)              AS domain_email_mentions  -- كام إيميل من هذا الدومين في نفس الوظيفة
    FROM newschema.ACE_JOBS_10K_EMAILS e
    WHERE SUBSTRING_INDEX(e.email, '@', -1) = @domain
    GROUP BY e.dkey
),
ranked AS (
    SELECT
        dj.dkey,
        dj.last_mentioned,
        dj.domain_email_mentions,
        j.PlainText_JOB_Description,
        ROW_NUMBER() OVER (
            ORDER BY dj.last_mentioned DESC
        ) AS rn
    FROM domain_jobs dj
    JOIN newschema.ACE_JOBS_10K j
      ON j.dkey = dj.dkey
)
SELECT
    dkey,
    last_mentioned,
    domain_email_mentions,
    PlainText_JOB_Description
FROM ranked
WHERE rn <= @N
ORDER BY rn;


-- ███████████████████████████████ 22112025 End ███████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████



SELECT 
    Country,
    COUNT(*) AS jobs_count
FROM (
    SELECT 
        CASE
            /* نفس الـ CASE بالظبط من الاستعلام اللي فوق */
            WHEN PlainText_Job_Description LIKE '%UAE%'
              OR PlainText_Job_Description LIKE '%Dubai%'
              OR PlainText_Job_Description LIKE '%Abu Dhabi%'
              OR PlainText_Job_Description LIKE '%Sharjah%'
              OR PlainText_Job_Description LIKE '%U.A.E%'
              OR PlainText_Job_Description LIKE '%United Arab%'
            THEN 'United Arab Emirates'

           WHEN PlainText_Job_Description LIKE '%Saudi%' 
    OR PlainText_Job_Description LIKE '%SAUDI%' 
    OR PlainText_Job_Description LIKE '%KSA%' 
    OR PlainText_Job_Description LIKE '%K.S.A%'
    OR PlainText_Job_Description LIKE '%Kingdom of Saudi%' 
    OR PlainText_Job_Description LIKE '%Kingdom of Saudi Arabia%' 
    OR PlainText_Job_Description LIKE '%Saudi Arabia%'
    OR PlainText_Job_Description LIKE '%SaudiArabia%'
    OR PlainText_Job_Description LIKE '%SA%'          -- بحذر، يستخدم فقط لو جوا كلام واضح
    OR PlainText_Job_Description LIKE '%SA-%'
    OR PlainText_Job_Description LIKE '%SA %'

    /* المدن الكبرى */
    OR PlainText_Job_Description LIKE '%Riyadh%' 
    OR PlainText_Job_Description LIKE '%Jeddah%' 
    OR PlainText_Job_Description LIKE '%Dammam%'
    OR PlainText_Job_Description LIKE '%Khobar%' 
    OR PlainText_Job_Description LIKE '%Al Khobar%'
    OR PlainText_Job_Description LIKE '%Mecca%' 
    OR PlainText_Job_Description LIKE '%Makkah%' 
    OR PlainText_Job_Description LIKE '%Medina%'
    OR PlainText_Job_Description LIKE '%Madinah%'
    OR PlainText_Job_Description LIKE '%Taif%' 
    OR PlainText_Job_Description LIKE '%Abha%' 
    OR PlainText_Job_Description LIKE '%Tabuk%' 
    OR PlainText_Job_Description LIKE '%Jazan%' 
    OR PlainText_Job_Description LIKE '%Jazan%' 
    OR PlainText_Job_Description LIKE '%Jubail%' 
    OR PlainText_Job_Description LIKE '%Yanbu%'
    OR PlainText_Job_Description LIKE '%Hail%' 
    OR PlainText_Job_Description LIKE '%Qassim%' 
    OR PlainText_Job_Description LIKE '%Qaseem%'
    OR PlainText_Job_Description LIKE '%Najran%'
    OR PlainText_Job_Description LIKE '%Buraidah%'

    /* طرق مكتوبة شائعة */
    OR PlainText_Job_Description LIKE '%Riyadh,%' 
    OR PlainText_Job_Description LIKE '%Riyadh-%'
    OR PlainText_Job_Description LIKE '%Jeddah-%'
    OR PlainText_Job_Description LIKE '%KSA-%'
    OR PlainText_Job_Description LIKE '%Saudi-%'
    OR PlainText_Job_Description LIKE '%Based in Saudi%'
    OR PlainText_Job_Description LIKE '%Saudi based%'
    OR PlainText_Job_Description LIKE '%Relocation to Saudi%'
    OR PlainText_Job_Description LIKE '%Working in Saudi%'
THEN 'Saudi Arabia'


         WHEN PlainText_Job_Description LIKE '%Qatar%'
    OR PlainText_Job_Description LIKE '%QATAR%'
    OR PlainText_Job_Description LIKE '%State of Qatar%'
    OR PlainText_Job_Description LIKE '%Qatari%' 
    OR PlainText_Job_Description LIKE '%QA-%'
    OR PlainText_Job_Description LIKE '%QA %'
    OR PlainText_Job_Description LIKE '%QAT%'      -- بحذر، فقط لو وارد
    OR PlainText_Job_Description LIKE '%Doha%'
    OR PlainText_Job_Description LIKE '%Doha,%'
    OR PlainText_Job_Description LIKE '%Doha-%'
    OR PlainText_Job_Description LIKE '%Al Wakrah%'
    OR PlainText_Job_Description LIKE '%Al-Wakrah%'
    OR PlainText_Job_Description LIKE '%Al Khor%'
    OR PlainText_Job_Description LIKE '%Al-Khor%'
    OR PlainText_Job_Description LIKE '%Lusail%'
    OR PlainText_Job_Description LIKE '%Education City%'
    OR PlainText_Job_Description LIKE '%Mesaieed%'
    OR PlainText_Job_Description LIKE '%Msheireb%'
    OR PlainText_Job_Description LIKE '%Umm Salal%'
    
    /* صيغ شائعة جداً */
    OR PlainText_Job_Description LIKE '%Doha based%'
    OR PlainText_Job_Description LIKE '%Doha-based%'
    OR PlainText_Job_Description LIKE '%Qatar based%'
    OR PlainText_Job_Description LIKE '%Qatar-based%'
    OR PlainText_Job_Description LIKE '%Relocation to Qatar%'
    OR PlainText_Job_Description LIKE '%Working in Qatar%'
    OR PlainText_Job_Description LIKE '%Hiring in Qatar%'
THEN 'Qatar'

          WHEN PlainText_Job_Description LIKE '%Egypt%' 
    OR PlainText_Job_Description LIKE '%EGYPT%' 
    OR PlainText_Job_Description LIKE '%EG%' 
    OR PlainText_Job_Description LIKE '%EG-%'
    OR PlainText_Job_Description LIKE '%EG %'
    OR PlainText_Job_Description LIKE '%Egyptian%' 
    OR PlainText_Job_Description LIKE '%Cairo%' 
    OR PlainText_Job_Description LIKE '%Cairo,%' 
    OR PlainText_Job_Description LIKE '%Cairo-%'
    OR PlainText_Job_Description LIKE '%Giza%' 
    OR PlainText_Job_Description LIKE '%Alexandria%' 
    OR PlainText_Job_Description LIKE '%Alex%' 
    OR PlainText_Job_Description LIKE '%Maadi%' 
    OR PlainText_Job_Description LIKE '%Nasr City%' 
    OR PlainText_Job_Description LIKE '%Heliopolis%' 
    OR PlainText_Job_Description LIKE '%6th of October%' 
    OR PlainText_Job_Description LIKE '%6 October%' 
    OR PlainText_Job_Description LIKE '%October City%' 
    OR PlainText_Job_Description LIKE '%New Cairo%' 
    OR PlainText_Job_Description LIKE '%Obour%' 
    OR PlainText_Job_Description LIKE '%Mansoura%' 
    OR PlainText_Job_Description LIKE '%Zagazig%'
    OR PlainText_Job_Description LIKE '%Banha%'
    OR PlainText_Job_Description LIKE '%Sharm%' 
    OR PlainText_Job_Description LIKE '%Hurghada%' 
    OR PlainText_Job_Description LIKE '%Aswan%' 
    OR PlainText_Job_Description LIKE '%Luxor%' 
    OR PlainText_Job_Description LIKE '%Tanta%' 
    OR PlainText_Job_Description LIKE '%Suez%' 
    OR PlainText_Job_Description LIKE '%Ismailia%'
    OR PlainText_Job_Description LIKE '%Port Said%'
    OR PlainText_Job_Description LIKE '%Damietta%'
THEN 'Egypt'

    WHEN PlainText_Job_Description LIKE '%Bahrain%'
    OR PlainText_Job_Description LIKE '%BAHRAIN%'
    OR PlainText_Job_Description LIKE '%Kingdom of Bahrain%'
    OR PlainText_Job_Description LIKE '%Bahraini%'
    OR PlainText_Job_Description LIKE '%BH-%'
    OR PlainText_Job_Description LIKE '%BH %'
    OR PlainText_Job_Description LIKE '%BHR%'

    /* المدن والمناطق الأكثر شيوعاً في البحرين */
    OR PlainText_Job_Description LIKE '%Manama%'        -- المنامة
    OR PlainText_Job_Description LIKE '%Al Manamah%'
    OR PlainText_Job_Description LIKE '%Muharraq%'      -- المحرق
    OR PlainText_Job_Description LIKE '%Isa Town%'      -- مدينة عيسى
    OR PlainText_Job_Description LIKE '%Hamad Town%'    -- مدينة حمد
    OR PlainText_Job_Description LIKE '%Riffa%'         -- الرفاع
    OR PlainText_Job_Description LIKE '%Juffair%'       -- الجفير
    OR PlainText_Job_Description LIKE '%Sitra%'         -- سترة
    OR PlainText_Job_Description LIKE '%Budaiya%'       -- البديع
    OR PlainText_Job_Description LIKE '%Seef%'          -- السيف
    OR PlainText_Job_Description LIKE '%Tubli%'         -- توبلي
    OR PlainText_Job_Description LIKE '%Zallaq%'        -- الزلاق
    OR PlainText_Job_Description LIKE "%A'ali%"         -- عالي
    OR PlainText_Job_Description LIKE '%Sanabis%'       -- سنابيس

    /* صيغ شائعة في الإعلانات */
    OR PlainText_Job_Description LIKE '%Bahrain-based%'
    OR PlainText_Job_Description LIKE '%Bahrain based%'
    OR PlainText_Job_Description LIKE '%Based in Bahrain%'
    OR PlainText_Job_Description LIKE '%Relocation to Bahrain%'
    OR PlainText_Job_Description LIKE '%Working in Bahrain%'
    OR PlainText_Job_Description LIKE '%Hiring in Bahrain%'
    THEN 'Bahrain'

            WHEN PlainText_Job_Description LIKE '%Kuwait%'
            OR PlainText_Job_Description LIKE '%KUWAIT%'
            OR PlainText_Job_Description LIKE '%State of Kuwait%'
            OR PlainText_Job_Description LIKE '%Kuwaiti%'
            OR PlainText_Job_Description LIKE '%KW-%'
            OR PlainText_Job_Description LIKE '%KW %'
            OR PlainText_Job_Description LIKE '%KWT%'
            
            /* المدن والمناطق المشهورة */
            OR PlainText_Job_Description LIKE '%Kuwait City%'
            OR PlainText_Job_Description LIKE '%Kuwait-City%'
            OR PlainText_Job_Description LIKE '%Hawalli%'
            OR PlainText_Job_Description LIKE '%Salmiya%'
            OR PlainText_Job_Description LIKE '%Salmiya%'
            OR PlainText_Job_Description LIKE '%Farwaniya%'
            OR PlainText_Job_Description LIKE '%Jahra%'
            OR PlainText_Job_Description LIKE '%Fahaheel%'
            OR PlainText_Job_Description LIKE '%Mangaf%'
            OR PlainText_Job_Description LIKE '%Mahboula%'
            OR PlainText_Job_Description LIKE '%Sharq%'
            OR PlainText_Job_Description LIKE '%Shuwaikh%'
            OR PlainText_Job_Description LIKE '%Sabah Al Salem%'
            OR PlainText_Job_Description LIKE '%Sabah%Salem%'
            
            /* صيغ مكتوبة شائعة */
            OR PlainText_Job_Description LIKE '%Kuwait-based%'
            OR PlainText_Job_Description LIKE '%Kuwait based%'
            OR PlainText_Job_Description LIKE '%Based in Kuwait%'
            OR PlainText_Job_Description LIKE '%Relocation to Kuwait%'
            OR PlainText_Job_Description LIKE '%Working in Kuwait%'
            OR PlainText_Job_Description LIKE '%Hiring in Kuwait%'
        THEN 'Kuwait'


            WHEN PlainText_Job_Description LIKE '%USA%'
            OR PlainText_Job_Description LIKE '%U.S.A%'
            OR PlainText_Job_Description LIKE '%United States%'
            OR PlainText_Job_Description LIKE '%United States of America%'
            OR PlainText_Job_Description LIKE '%America%'
            OR PlainText_Job_Description LIKE '%US-%'
            OR PlainText_Job_Description LIKE '%US %'
            OR PlainText_Job_Description LIKE '%U.S.%'
            OR PlainText_Job_Description LIKE '%American%'
            
            /* المدن الكبرى */
            OR PlainText_Job_Description LIKE '%New York%'
            OR PlainText_Job_Description LIKE '%NYC%'
            OR PlainText_Job_Description LIKE '%Los Angeles%'
            OR PlainText_Job_Description LIKE '%LA%'
            OR PlainText_Job_Description LIKE '%Chicago%'
            OR PlainText_Job_Description LIKE '%Houston%'
            OR PlainText_Job_Description LIKE '%Phoenix%'
            OR PlainText_Job_Description LIKE '%Philadelphia%'
            OR PlainText_Job_Description LIKE '%San Antonio%'
            OR PlainText_Job_Description LIKE '%San Diego%'
            OR PlainText_Job_Description LIKE '%Dallas%'
            OR PlainText_Job_Description LIKE '%San Jose%'
            OR PlainText_Job_Description LIKE '%Austin%'
            OR PlainText_Job_Description LIKE '%Jacksonville%'
            OR PlainText_Job_Description LIKE '%Columbus%'
            OR PlainText_Job_Description LIKE '%Charlotte%'
            OR PlainText_Job_Description LIKE '%Seattle%'
            OR PlainText_Job_Description LIKE '%Denver%'
            OR PlainText_Job_Description LIKE '%Boston%'
            OR PlainText_Job_Description LIKE '%San Francisco%'
            OR PlainText_Job_Description LIKE '%Washington%'
            OR PlainText_Job_Description LIKE '%Washington DC%'
            OR PlainText_Job_Description LIKE '%Baltimore%'
            OR PlainText_Job_Description LIKE '%Portland%'
            OR PlainText_Job_Description LIKE '%Las Vegas%'
            OR PlainText_Job_Description LIKE '%Miami%'
            OR PlainText_Job_Description LIKE '%Orlando%'
        
            /* ولايات (اختصارات أمريكية) */
            OR PlainText_Job_Description LIKE '%CA%'  -- California
            OR PlainText_Job_Description LIKE '%NY%'  -- New York
            OR PlainText_Job_Description LIKE '%TX%'  -- Texas
            OR PlainText_Job_Description LIKE '%FL%'  -- Florida
            OR PlainText_Job_Description LIKE '%IL%'  -- Illinois
            OR PlainText_Job_Description LIKE '%PA%'  -- Pennsylvania
            OR PlainText_Job_Description LIKE '%AZ%'  -- Arizona
            OR PlainText_Job_Description LIKE '%GA%'  -- Georgia
            OR PlainText_Job_Description LIKE '%NC%'  -- North Carolina
            OR PlainText_Job_Description LIKE '%MI%'  -- Michigan
            OR PlainText_Job_Description LIKE '%OH%'  -- Ohio
            OR PlainText_Job_Description LIKE '%WA%'  -- Washington State
            OR PlainText_Job_Description LIKE '%CO%'  -- Colorado
            OR PlainText_Job_Description LIKE '%MA%'  -- Massachusetts
            OR PlainText_Job_Description LIKE '%NV%'  -- Nevada
            OR PlainText_Job_Description LIKE '%VA%'  -- Virginia
            OR PlainText_Job_Description LIKE '%MD%'  -- Maryland
            OR PlainText_Job_Description LIKE '%NJ%'  -- New Jersey
            OR PlainText_Job_Description LIKE '%MN%'  -- Minnesota
            OR PlainText_Job_Description LIKE '%WI%'  -- Wisconsin
        
            /* صيغ شائعة */
            OR PlainText_Job_Description LIKE '%US-based%'
            OR PlainText_Job_Description LIKE '%USA-based%'
            OR PlainText_Job_Description LIKE '%Based in the US%'
            OR PlainText_Job_Description LIKE '%Based in USA%'
            OR PlainText_Job_Description LIKE '%Relocation to USA%'
            OR PlainText_Job_Description LIKE '%Work in USA%'
            OR PlainText_Job_Description LIKE '%Hiring in USA%'
        THEN 'USA'
        
        
            WHEN PlainText_Job_Description LIKE '%Oman%' OR PlainText_Job_Description LIKE '%Muscat%' THEN 'Oman'
            WHEN PlainText_Job_Description LIKE '%Jordan%' THEN 'Jordan'
            WHEN PlainText_Job_Description LIKE '%Lebanon%' THEN 'Lebanon'
            WHEN PlainText_Job_Description LIKE '%Turkey%' THEN 'Turkey'



            ELSE 'Unknown'
        END AS Country
    FROM (
        SELECT *
        FROM newschema.ace_jobs
        ORDER BY dkey DESC
        LIMIT 1000
    ) t
) x
GROUP BY Country
ORDER BY jobs_count DESC;

-- ████████████████████████████████████████████████████████████████████████████████


https://www.linkedin.com/search/results/content/?datePosted=%22past-24h%22&keywords=%23AgileConsultants&sid=Ggc



-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████
-- ████████████████████████████████████████████████████████████████████████████████






https://www.linkedin.com/search/results/content/?datePosted=%22past-24h%22&keywords=%23softwarejobs&sid=Ggc


https://www.linkedin.com/search/results/content/?datePosted=%22past-24h%22&keywords=%23microservicessoftwarejobs&sid=Ggc






#backenddeveloper
#careers
#developerjobs
#engineeringjobs
#jobopportunity
#jobs
#jobsearch
#microservices
#remotejobs
#techcareers







https://docs.google.com/spreadsheets/d/1WSFshTf5lRj41HwPuw1cvhuBBqbMmfB_4-m17E0-rFE/edit?gid=124934924#gid=124934924
https://script.googleusercontent.com/macros/echo?user_content_key=AehSKLikpg5r43tBa7feY4oK4fJCmIzWT0Pl8sZloSbF4PayW-AkNa_caZrPuRobD-xLj4yywhLAk3jRTPvoMcn91DKM4DwjDJBLRob6gJOuj-6BQDSN_BEESvOTArnQ1poCTXJHpX9AUXvph4x4PMXOPvMvmm8-sJxUN6e5ymfQrbPhjti1uwxEZLkNpiz3toFh2xCLgGgYg_nN6FAKkH3qQ7Af0Q9oILqyBdm-kDTLIG2dT4lAklIC9mnOu8ieb1Vb3G3PBrSu8EdSRjvV62b7KvpM9v2Enw&lib=M29tBGHDC2EytwnORiJ7kfFlAWfUvf5oC
https://script.googleusercontent.com/macros/echo?user_content_key=AehSKLjb5xwa41aOONZ_wszWumE2WpiRD1R2lyWGC3Ou371K78vHDL0wF5BH6oEqdtlZxPhI-xJ2Jzc2P1IHyYiX1PACd7jl50EpPCWcrHN1IWjqhPQGAzsw56w7JJQriIxG-s5inOmRcxhdB5WV418gKeItzyZNttYM8GTaux6K7C1pyRQTJPrdHLdFS-a0IidsoV0KBV_2-h23PuALfZ2lj8wOZ36FNMRkubORtwf4_67lIAINYyLVcOJVa3hIj_hNuAiU3ahD08eNHydElvLS9WfXLDwWOQ&lib=M29tBGHDC2EytwnORiJ7kfFlAWfUvf5oC
https://script.googleusercontent.com/macros/echo?user_content_key=AehSKLgfsZpaghZ6hcHQyd_exUiUExTY4dRWQw4s_MlkG7Gwk2CjyfeTUgiS-YO9NOz5ECJr5ZhhNBvVvMHw0nRDlKrlldw5-rBL8t149T6dso1GbdJmDshWpNZ1P5JGUVLA9oQPk8cW6s-wWOrmfKRjTfTWdb6ClkaY6QDap7eX1hP9VHrMD39Jwje5_rkSDNYBjcS8w_I-kLgI4ehOiA3m8bcU4gSWZCK1-L7jkyDtGk3EHX8yvHp58qA5lclpZVmjeEFjeYDJM38gyvwJ4XrwFn03tKJAyQ&lib=M29tBGHDC2EytwnORiJ7kfFlAWfUvf5oC
https://acealbastoni.github.io/#/page1
https://chatgpt.com/c/690ea6bc-b2e4-8333-aedb-a3e6cadc7d70
https://acealbastoni.github.io/#/page2
file:///C:/root/pages/removeme/page2.html
file:///C:/root/pages/removeme/New%20folder%20(3)/index.html#/home
file:///C:/root/pages/removeme/New%20folder%20(3)/index.html#/home
file:///C:/rclone/rclone-v1.66.0-windows-amd64/New%20folder/modular-dashboard.html#home
file:///C:/root/pages/removeme/page2.html
https://chatgpt.com/c/68fb721e-e594-832b-8312-c6a3dda7555a
https://chatgpt.com/c/692945d1-2efc-8328-9e32-46444ffeb6d6
file:///C:/root/pages/removeme/index.html#/page2
https://chatgpt.com/c/692945d1-2efc-8328-9e32-46444ffeb6d6
file:///C:/root/pages/removeme/pag4/jobs.html
https://www.youtube.com/results?search_query=%D9%88%D9%84%D9%8A%D8%B3%D8%AA+%D8%A8%D8%B1%D9%88%D8%A7%D8%AC%D8%B9+%D9%88%D9%84%D9%83%D9%86+
https://www.youtube.com/watch?v=dhnCeD-9QIM&list=RDPS5c1NTSODQ&index=27
https://www.youtube.com/watch?v=VMMyOxRVox4
https://apap.ahlamontada.com/t6486-topic
file:///C:/root/pages/removeme/pag4/New%20folder/New%20folder/jobs-dashboard.html
https://docs.google.com/spreadsheets/d/12DOdU_mhknF4v4c6I5Q0w779zrCbiuLwzb_B3EaSFOg/edit?gid=931349507#gid=931349507
https://chatgpt.com/c/6929fab3-8130-8328-ac55-57a4b70df1d9
https://script.google.com/macros/s/AKfycbyin6nA9tDwOkhDtl9h4WyTvdT6nvcY91yXfQmPzbXYcvUs1ASqLCnke93vNVHN_bVNTQ/execexec?key=447e152f-143f-4195-80fd-42b87d40af46-1764452322847&page=1&size=10
https://claude.ai/chat/4df37edd-6511-4819-aed7-d953d6daa229
https://script.googleusercontent.com/macros/echo?user_content_key=AehSKLiOtctalJQvyJfT61tkcObszd3lMeIKlcEnPui48lS-QY_mWPcZ8_XtK3q_sWTrf5w0BYhLxDDwukZTf0pd7kkFHYwqhgLVCCd6btKxUz5rtQpA_W5SQyVRBdnENZTEvsGRcuGMX-tyIo3ZPpd4wTS7ZJN0_xNx3EGZNihP6YFgw5pfO_5ZK8mDC7xEZAv7exXdL5PZxgpHgqiOuHQV1eMPAtU2D7XYtNAT5_bxqx8Pp2VhNiBjoD8SBpEuuiCrMinnZO2zzdjZ3RxgTCoqK46H45wc568Lg2ANEWw1g7kiL3YEDDyKwvxwc3m2TxrLVUlse1YqUtyN0BcI810781L6wUfZ0PEReUqUg9xnsYnGObpXx5aG8nh5KpVd0tN0qW3yM8nw&lib=MV-pt8QWof49hSAYiyxBSdvv1O5OqMya3
file:///C:/ffffff/hfghgf/xxxxxxxxxxxxxx/jobs-dashboard.html
file:///C:/ffffff/hfghgf/xxxxxxxxxxxxxx/jobs-dashboard.html
file:///C:/ffffff/hfghgf/New%20folder%20(2)/pages/1-jobs/index.html
file:///C:/ffffff/hfghgf/New%20folder%20(2)/pages/1-jobs/index.html
https://www.linkedin.com/search/results/all/?keywords=%23ohtl&origin=HASH_TAG_FROM_FEED
https://www.linkedin.com/search/results/all/?keywords=%23cairo&origin=HASH_TAG_FROM_FEED
https://www.linkedin.com/search/results/all/?keywords=%23cairojobs&origin=HASH_TAG_FROM_FEED
https://www.linkedin.com/search/results/all/?keywords=%23staffarabia&origin=HASH_TAG_FROM_FEED
https://www.linkedin.com/search/results/all/?keywords=%23powerbi&origin=HASH_TAG_FROM_FEED
https://www.linkedin.com/search/results/all/?keywords=%23copilot&origin=HASH_TAG_FROM_FEED
https://www.linkedin.com/search/results/all/?keywords=%23freshershiring&origin=HASH_TAG_FROM_FEED
https://www.linkedin.com/search/results/all/?keywords=%23kubernetes&origin=HASH_TAG_FROM_FEED
https://www.youtube.com/watch?v=LDoxFzKoeh8&list=RD6qRD6UnP5QE&index=27
https://www.linkedin.com/feed/?doFeedRefresh=true&nis=true
https://www.linkedin.com/search/results/all/?keywords=%23infrastructure&origin=HASH_TAG_FROM_FEED&sid=mFy
https://www.linkedin.com/search/results/all/?keywords=%23doha&origin=HASH_TAG_FROM_FEED
chrome://settings/
chrome://extensions/
https://www.linkedin.com/feed/






file:///C:/ffffff/hfghgf/jobhub-ksa/search.html
file:///C:/ffffff/hfghgf/linkedoff/index.html
file:///C:/ffffff/hfghgf/22/ch/template.html
file:///C:/ffffff/hfghgf/linkedoff-final/index.html#
file:///C:/ffffff/hfghgf/22/linkedoff/index.html#
file:///D:/sss/New%20Text%20Document.html
https://claude.ai/chat/87ba740e-0a9c-4ece-8eab-97c8a7c3981a
https://chatgpt.com/c/69330cd1-32f0-8326-8c53-123784129368
https://claude.ai/chat/d5fb6813-624d-4f61-b6d3-1452ea11ee2b
https://docs.google.com/spreadsheets/d/1WSFshTf5lRj41HwPuw1cvhuBBqbMmfB_4-m17E0-rFE/edit?pli=1&gid=124934924#gid=124934924
https://docs.google.com/spreadsheets/d/12DOdU_mhknF4v4c6I5Q0w779zrCbiuLwzb_B3EaSFOg/edit?gid=931349507#gid=931349507
https://script.google.com/u/0/home/projects/17JRkpBZNrlskAbRhdt3f0bIyuml4O2YLlhVRnQmwBy6NFVFhCOqm5gtn/edit
https://acealbastoni.github.io/#/page1
https://claude.ai/chat/dd6e75df-4cf7-40e3-9e72-704347fd6893
https://claude.ai/chat/bebc3092-0aa1-4d61-8489-ffee8ec33f50
https://claude.ai/chat/93972ae6-f47d-4d7b-b19e-093580be7049
https://claude.ai/chat/d5fb6813-624d-4f61-b6d3-1452ea11ee2b
https://claude.ai/chat/8d8e19d2-1af6-4c8b-8a27-40b524de1fc9
















