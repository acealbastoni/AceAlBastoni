/**
 * Auto-generated SQL query catalog for the project.
 * Generated on 2026-01-06.
 *
 * Notes:
 * - These queries target MySQL 8+ (CTEs used in many queries).
 * - Keep this class as a pure constants holder (no logic).
 */
public final class Constants {

    private Constants() {} // يمنع إنشاء object

    public static final String QueryOFLast30000Jobs =
            "SELECT * FROM jobs ORDER BY dkey DESC LIMIT 30000";

    public static final String QueryOFLast80000Jobs =
            "SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000";

    public static final String QueryOFSaudiArabicJobs =
            "WITH last_jobs AS (\r\n"
            + "    SELECT\r\n"
            + "        Hashed_Job_Describtion,\r\n"
            + "        Encrypted_Job_Description,\r\n"
            + "        PlainText_Job_Description,\r\n"
            + "        Attached_Emails,\r\n"
            + "        Profile_ID,\r\n"
            + "        Source,\r\n"
            + "        Scrapped_Date,\r\n"
            + "        Scrapped_Version,\r\n"
            + "        Time_Input_The_System,\r\n"
            + "        SQL_generated_HMD5,\r\n"
            + "        dkey\r\n"
            + "    FROM jobs\r\n"
            + "    ORDER BY dkey DESC\r\n"
            + "    LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE\r\n"
            + "    PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "    AND PlainText_Job_Description REGEXP '[؀-ۿ]'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSuadiArabicJobs =
    		"WITH last_jobs AS (\r\n"
    				+ "    SELECT\r\n"
    				+ "        Hashed_Job_Describtion,\r\n"
    				+ "        Encrypted_Job_Description,\r\n"
    				+ "        PlainText_Job_Description,\r\n"
    				+ "        Attached_Emails,\r\n"
    				+ "        Profile_ID,\r\n"
    				+ "        Source,\r\n"
    				+ "        Scrapped_Date,\r\n"
    				+ "        Scrapped_Version,\r\n"
    				+ "        Time_Input_The_System,\r\n"
    				+ "        SQL_generated_HMD5,\r\n"
    				+ "        dkey\r\n"
    				+ "    FROM jobs\r\n"
    				+ "    ORDER BY dkey DESC\r\n"
    				+ "    LIMIT 80000\r\n"
    				+ ")\r\n"
    				+ "SELECT *\r\n"
    				+ "FROM last_jobs\r\n"
    				+ "WHERE\r\n"
    				+ "    PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
    				+ "    AND PlainText_Job_Description REGEXP '[؀-ۿ]'\r\n"
    				+ "    AND PlainText_Job_Description REGEXP '(?i)(\\bjava\\b|java\\s*developer|java\\s*engineer|java\\s*programmer|spring\\s*boot|j2ee|jakarta\\s*ee|jvm|microservices)'\r\n"
    				+ "ORDER BY Scrapped_Date DESC";

//            "WITH last_jobs AS (\r\n"
//            + "    SELECT\r\n"
//            + "        Hashed_Job_Describtion,\r\n"
//            + "        Encrypted_Job_Description,\r\n"
//            + "        PlainText_Job_Description,\r\n"
//            + "        Attached_Emails,\r\n"
//            + "        Profile_ID,\r\n"
//            + "        Source,\r\n"
//            + "        Scrapped_Date,\r\n"
//            + "        Scrapped_Version,\r\n"
//            + "        Time_Input_The_System,\r\n"
//            + "        SQL_generated_HMD5,\r\n"
//            + "        dkey\r\n"
//            + "    FROM jobs\r\n"
//            + "    ORDER BY dkey DESC\r\n"
//            + "    LIMIT 80000\r\n"
//            + ")\r\n"
//            + "SELECT *\r\n"
//            + "FROM last_jobs\r\n"
//            + "WHERE\r\n"
//            + "    PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
//            + "    AND PlainText_Job_Description REGEXP '[؀-ۿ]'\r\n"
//            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFAbha_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Abha|abha)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFAbha_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Abha|abha)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFAbha_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Abha|abha)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFCountAllJobs =
            "SELECT COUNT(*) AS total_jobs FROM jobs";

    public static final String QueryOFCountJobsArabic =
            "SELECT COUNT(*) AS arabic_jobs FROM jobs WHERE PlainText_Job_Description REGEXP '[؀-ۿ]'";

    public static final String QueryOFCountJobsByScrappedDate_Last30Days =
            "SELECT DATE(Scrapped_Date) AS day, COUNT(*) AS cnt\r\n"
            + "FROM jobs\r\n"
            + "WHERE Scrapped_Date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)\r\n"
            + "GROUP BY DATE(Scrapped_Date)\r\n"
            + "ORDER BY day DESC";

    public static final String QueryOFCountJobsByScrappedVersion_Last90Days =
            "SELECT Scrapped_Version, COUNT(*) AS cnt\r\n"
            + "FROM jobs\r\n"
            + "WHERE Scrapped_Date >= DATE_SUB(CURDATE(), INTERVAL 90 DAY)\r\n"
            + "GROUP BY Scrapped_Version\r\n"
            + "ORDER BY cnt DESC";

    public static final String QueryOFCountJobsBySource_Last30Days =
            "SELECT Source, COUNT(*) AS cnt\r\n"
            + "FROM jobs\r\n"
            + "WHERE Scrapped_Date >= DATE_SUB(CURDATE(), INTERVAL 30 DAY)\r\n"
            + "GROUP BY Source\r\n"
            + "ORDER BY cnt DESC";

    public static final String QueryOFCountJobsEnglish =
            "SELECT COUNT(*) AS english_jobs FROM jobs WHERE PlainText_Job_Description REGEXP '[A-Za-z]' AND PlainText_Job_Description NOT REGEXP '[؀-ۿ]'";

    public static final String QueryOFCountJobsWithEmails =
            "SELECT COUNT(*) AS jobs_with_emails FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> ''";

    public static final String QueryOFCountSaudiJobs_Last80000 =
            "WITH last_jobs AS (SELECT PlainText_Job_Description FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT COUNT(*) AS cnt\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'";

    public static final String QueryOFCountSaudiJobs_WithEmails_Last80000 =
            "WITH last_jobs AS (SELECT PlainText_Job_Description, Attached_Emails FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT COUNT(*) AS cnt\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND Attached_Emails IS NOT NULL AND Attached_Emails <> ''";

    public static final String QueryOFDammam_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Dammam|dammam)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFDammam_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Dammam|dammam)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFDammam_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Dammam|dammam)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFDeleteDuplicatesKeepMaxDkey_ByHMD5 =
            "DELETE j1\r\n"
            + "FROM jobs j1\r\n"
            + "JOIN jobs j2\r\n"
            + "  ON j1.SQL_generated_HMD5 = j2.SQL_generated_HMD5\r\n"
            + " AND j1.dkey < j2.dkey\r\n"
            + "WHERE j1.SQL_generated_HMD5 IS NOT NULL AND j1.SQL_generated_HMD5 <> ''";

    public static final String QueryOFDuplicatesByHashedJobDescription =
            "SELECT Hashed_Job_Describtion, COUNT(*) AS cnt\r\n"
            + "FROM jobs\r\n"
            + "WHERE Hashed_Job_Describtion IS NOT NULL AND Hashed_Job_Describtion <> ''\r\n"
            + "GROUP BY Hashed_Job_Describtion\r\n"
            + "HAVING COUNT(*) > 1\r\n"
            + "ORDER BY cnt DESC\r\n"
            + "LIMIT 500";

    public static final String QueryOFDuplicatesBySQLGeneratedHMD5 =
            "SELECT SQL_generated_HMD5, COUNT(*) AS cnt\r\n"
            + "FROM jobs\r\n"
            + "WHERE SQL_generated_HMD5 IS NOT NULL AND SQL_generated_HMD5 <> ''\r\n"
            + "GROUP BY SQL_generated_HMD5\r\n"
            + "HAVING COUNT(*) > 1\r\n"
            + "ORDER BY cnt DESC\r\n"
            + "LIMIT 500";

    public static final String QueryOFHail_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Hail|hail)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFHail_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Hail|hail)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFHail_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Hail|hail)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJeddah_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Jeddah|jeddah)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJeddah_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Jeddah|jeddah)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJeddah_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Jeddah|jeddah)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJizan_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Jizan|jizan)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJizan_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Jizan|jizan)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJizan_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Jizan|jizan)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningBanking_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Bank|Banking|Fintech|Payment|مدفوعات|بنك|مصرف)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningContract_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Contract|Freelance|Part[- ]?time|Temporary|دوام جزئي|عقد)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningEgypt_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Egypt|Cairo|Alexandria|مصر|القاهرة|الاسكندرية)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningFullTime_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Full[- ]?time|دوام كامل)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningGCC_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(GCC|Gulf|UAE|Dubai|Abu Dhabi|Qatar|Doha|Kuwait|Bahrain|Oman|الخليج)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningGovernment_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Government|Public Sector|Authority|وزارة|هيئة|حكومي)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningHealthcare_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Hospital|Healthcare|Clinic|Medical|مستشفى|صحة)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningInternship_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Intern|Internship|Co-op|Trainee|Training|متدرب|تدريب)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningRelocation_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Relocation|relocate|انتقال|نقل كفالة)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningSecurityClearance_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Security Clearance|Secret|Top Secret|تصريح امني)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMentioningVisa_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Visa|Sponsorship|Iqama|اقامة|كفالة)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsMissingEmailsButHasAtSign =
            "SELECT dkey, Source, Scrapped_Date, PlainText_Job_Description\r\n"
            + "FROM jobs\r\n"
            + "WHERE (Attached_Emails IS NULL OR Attached_Emails = '')\r\n"
            + "  AND PlainText_Job_Description LIKE '%@%'\r\n"
            + "ORDER BY dkey DESC\r\n"
            + "LIMIT 2000";

    public static final String QueryOFJobsMissingPlainText =
            "SELECT dkey, Source, Scrapped_Date\r\n"
            + "FROM jobs\r\n"
            + "WHERE PlainText_Job_Description IS NULL OR PlainText_Job_Description = ''\r\n"
            + "ORDER BY dkey DESC\r\n"
            + "LIMIT 2000";

    public static final String QueryOFJobsWithLinkedInUrls_Last50000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 50000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description LIKE '%linkedin.com%'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsWithPhoneNumbers_Last50000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 50000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '([0-9]{3}[- ]?[0-9]{3}[- ]?[0-9]{4,})'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobsWithWhatsApp_Last50000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 50000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(WhatsApp|واتساب|wa\\.me)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobs_CompanyEmailsOnly_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "  SELECT * FROM jobs\r\n"
            + "  WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> ''\r\n"
            + "  ORDER BY dkey DESC\r\n"
            + "  LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE Attached_Emails NOT REGEXP '@(gmail|hotmail|outlook|yahoo)\\.com'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobs_GmailOnly_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000) SELECT * FROM last_jobs WHERE Attached_Emails REGEXP '@gmail\\.com' ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobs_MultiTech_JavaCSharpAzure_WithEmails_Saudi_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails<>'' ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Azure|Azure DevOps|AKS|App Service|Functions)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobs_MultiTech_JavaOrCSharpOrAzure_Saudi_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Azure|Azure DevOps|AKS|App Service|Functions)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobs_OutlookOnly_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000) SELECT * FROM last_jobs WHERE Attached_Emails REGEXP '@(outlook|hotmail)\\.com' ORDER BY Scrapped_Date DESC";

    public static final String QueryOFJobs_YahooOnly_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000) SELECT * FROM last_jobs WHERE Attached_Emails REGEXP '@yahoo\\.com' ORDER BY Scrapped_Date DESC";

    public static final String QueryOFKhobar_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Khobar|khobar)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFKhobar_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Khobar|khobar)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFKhobar_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Khobar|khobar)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFLast1000Jobs =
            "SELECT * FROM jobs ORDER BY dkey DESC LIMIT 1000";

    public static final String QueryOFLast30000Jobs_WithEmails =
            "SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 30000";

    public static final String QueryOFLast50000Jobs_ArabicOnly =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 50000) SELECT * FROM last_jobs WHERE PlainText_Job_Description REGEXP '[؀-ۿ]' ORDER BY Scrapped_Date DESC";

    public static final String QueryOFLast50000Jobs_EnglishOnly =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 50000) SELECT * FROM last_jobs WHERE PlainText_Job_Description REGEXP '[A-Za-z]' AND PlainText_Job_Description NOT REGEXP '[؀-ۿ]' ORDER BY Scrapped_Date DESC";

    public static final String QueryOFLast80000Jobs_WithEmails_ArabicOnly =
            "WITH last_jobs AS (SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails<>'' ORDER BY dkey DESC LIMIT 80000) SELECT * FROM last_jobs WHERE PlainText_Job_Description REGEXP '[؀-ۿ]' ORDER BY Scrapped_Date DESC";

    public static final String QueryOFLast80000Jobs_WithEmails_EnglishOnly =
            "WITH last_jobs AS (SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails<>'' ORDER BY dkey DESC LIMIT 80000) SELECT * FROM last_jobs WHERE PlainText_Job_Description REGEXP '[A-Za-z]' AND PlainText_Job_Description NOT REGEXP '[؀-ۿ]' ORDER BY Scrapped_Date DESC";

    public static final String QueryOFMecca_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Mecca|mecca)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFMecca_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Mecca|mecca)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFMecca_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Mecca|mecca)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFMedina_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Medina|medina)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFMedina_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Medina|medina)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFMedina_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Medina|medina)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFNeom_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Neom|neom)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFNeom_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Neom|neom)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFNeom_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Neom|neom)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFQassim_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Qassim|qassim)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFQassim_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Qassim|qassim)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFQassim_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Qassim|qassim)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFRecentJobsSince30Days =
            "SELECT * FROM jobs WHERE Scrapped_Date >= DATE_SUB(NOW(), INTERVAL 30 DAY) ORDER BY Scrapped_Date DESC";

    public static final String QueryOFRecentJobsSince7Days =
            "SELECT * FROM jobs WHERE Scrapped_Date >= DATE_SUB(NOW(), INTERVAL 7 DAY) ORDER BY Scrapped_Date DESC";

    public static final String QueryOFRecentJobsSinceYesterday =
            "SELECT * FROM jobs WHERE Scrapped_Date >= DATE_SUB(NOW(), INTERVAL 1 DAY) ORDER BY Scrapped_Date DESC";

    public static final String QueryOFRiyadh_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Riyadh|riyadh)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFRiyadh_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Riyadh|riyadh)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFRiyadh_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Riyadh|riyadh)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudiJobs_Hybrid_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Hybrid|هجين)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudiJobs_Junior_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Junior|Jr\\.|Entry|Fresh|Graduate|حديث التخرج|مبتدئ|متدرب)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudiJobs_Onsite_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Onsite|On-site|حضوري|دوام كامل)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudiJobs_Remote_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Remote|Work from home|WFH|عن بُعد|عن بعد)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudiJobs_SalaryMention_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(SAR|ريال|salary|package|compensation)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudiJobs_Senior_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Senior|Sr\\.|Lead|Principal|Architect|خبير|اول)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_AWS_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(AWS|Amazon Web Services|EC2|S3|Lambda|ECS|EKS|CloudFormation)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_AWS_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(AWS|Amazon Web Services|EC2|S3|Lambda|ECS|EKS|CloudFormation)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_Azure_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_Azure_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_CSharpDotNet_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_CSharpDotNet_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_Data_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(SQL|MySQL|PostgreSQL|SQL Server|Oracle|ETL|Data Warehouse|Spark|Hadoop)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_Data_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(SQL|MySQL|PostgreSQL|SQL Server|Oracle|ETL|Data Warehouse|Spark|Hadoop)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_DevOps_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(DevOps|CI/CD|Jenkins|GitLab CI|Docker|Kubernetes|Helm|Terraform|Ansible)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_DevOps_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(DevOps|CI/CD|Jenkins|GitLab CI|Docker|Kubernetes|Helm|Terraform|Ansible)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_GCP_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(GCP|Google Cloud|GKE|BigQuery|Cloud Run|Pub/Sub)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_GCP_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(GCP|Google Cloud|GKE|BigQuery|Cloud Run|Pub/Sub)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_JavaScript_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(JavaScript|TypeScript|Node\\.js|React|Angular|Vue)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_JavaScript_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(JavaScript|TypeScript|Node\\.js|React|Angular|Vue)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_Java_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_Java_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_Python_Jobs_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Python|Django|Flask|FastAPI|Pandas|NumPy)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSaudi_Python_Jobs_WithEmails_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> '' ORDER BY dkey DESC LIMIT 80000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Python|Django|Flask|FastAPI|Pandas|NumPy)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFSpamLikePosts_Last80000 =
            "WITH last_jobs AS (SELECT * FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Earn\\s*\\$|Make\\s*Money|100%\\s*Guaranteed|عمل من المنزل بدون خبرة|ربح سريع)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFTabuk_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Tabuk|tabuk)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFTabuk_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Tabuk|tabuk)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFTabuk_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Tabuk|tabuk)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFTopEmailDomains_Last50000 =
            "WITH last_jobs AS (\r\n"
            + "  SELECT Attached_Emails\r\n"
            + "  FROM jobs\r\n"
            + "  WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> ''\r\n"
            + "  ORDER BY dkey DESC\r\n"
            + "  LIMIT 50000\r\n"
            + "),\r\n"
            + "first_email AS (\r\n"
            + "  SELECT TRIM(SUBSTRING_INDEX(Attached_Emails, ';', 1)) AS email\r\n"
            + "  FROM last_jobs\r\n"
            + ")\r\n"
            + "SELECT\r\n"
            + "  LOWER(SUBSTRING_INDEX(email, '@', -1)) AS domain,\r\n"
            + "  COUNT(*) AS cnt\r\n"
            + "FROM first_email\r\n"
            + "WHERE email LIKE '%@%'\r\n"
            + "GROUP BY LOWER(SUBSTRING_INDEX(email, '@', -1))\r\n"
            + "ORDER BY cnt DESC\r\n"
            + "LIMIT 200";

    public static final String QueryOFTopEmailDomains_Saudi_Last80000 =
            "WITH last_jobs AS (\r\n"
            + "  SELECT Attached_Emails, PlainText_Job_Description\r\n"
            + "  FROM jobs\r\n"
            + "  WHERE Attached_Emails IS NOT NULL AND Attached_Emails <> ''\r\n"
            + "  ORDER BY dkey DESC\r\n"
            + "  LIMIT 80000\r\n"
            + "),\r\n"
            + "first_email AS (\r\n"
            + "  SELECT\r\n"
            + "    TRIM(SUBSTRING_INDEX(Attached_Emails, ';', 1)) AS email,\r\n"
            + "    PlainText_Job_Description AS txt\r\n"
            + "  FROM last_jobs\r\n"
            + ")\r\n"
            + "SELECT\r\n"
            + "  LOWER(SUBSTRING_INDEX(email, '@', -1)) AS domain,\r\n"
            + "  COUNT(*) AS cnt\r\n"
            + "FROM first_email\r\n"
            + "WHERE email LIKE '%@%'\r\n"
            + "  AND txt REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "GROUP BY LOWER(SUBSTRING_INDEX(email, '@', -1))\r\n"
            + "ORDER BY cnt DESC\r\n"
            + "LIMIT 200";

    public static final String QueryOFTopHashtagsLikePattern_Last80000 =
            "WITH last_jobs AS (SELECT PlainText_Job_Description FROM jobs ORDER BY dkey DESC LIMIT 80000)\r\n"
            + "SELECT\r\n"
            + "  SUBSTRING_INDEX(SUBSTRING_INDEX(PlainText_Job_Description, '#', -1), ' ', 1) AS possible_tag\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description LIKE '%#%'\r\n"
            + "LIMIT 5000";

    public static final String QueryOFTopProfilesByJobCount_Last90Days =
            "SELECT Profile_ID, COUNT(*) AS cnt\r\n"
            + "FROM jobs\r\n"
            + "WHERE Scrapped_Date >= DATE_SUB(CURDATE(), INTERVAL 90 DAY)\r\n"
            + "  AND Profile_ID IS NOT NULL AND Profile_ID <> ''\r\n"
            + "GROUP BY Profile_ID\r\n"
            + "ORDER BY cnt DESC\r\n"
            + "LIMIT 200";

    public static final String QueryOFYanbu_Azure_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Yanbu|yanbu)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Azure|Azure DevOps|AKS|App Service|Functions|ARM|Bicep|Entra|Active Directory)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFYanbu_CSharpDotNet_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Yanbu|yanbu)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(C[#]|C Sharp|\\.NET|ASP\\.NET|ASP\\.NET Core|Entity Framework|EF Core)'\r\n"
            + "ORDER BY Scrapped_Date DESC";

    public static final String QueryOFYanbu_Java_SaudiJobs_Last60000 =
            "WITH last_jobs AS (\r\n"
            + "    SELECT * FROM jobs ORDER BY dkey DESC LIMIT 60000\r\n"
            + ")\r\n"
            + "SELECT *\r\n"
            + "FROM last_jobs\r\n"
            + "WHERE PlainText_Job_Description REGEXP '(Yanbu|yanbu)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Saudi|KSA|السعودية|المملكة)'\r\n"
            + "  AND PlainText_Job_Description REGEXP '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|Maven|Gradle|JDBC|JPA)'\r\n"
            + "ORDER BY Scrapped_Date DESC";
    
    
    
    
    
    
    

    // 1️⃣ Saudi + Java (Arabic OR English) WITH Arabic content
    public static final String _1_QueryOfSaudiJavaArabicJobs =
    "WITH last_jobs AS (\r\n"
    + "    SELECT\r\n"
    + "        Hashed_Job_Describtion,\r\n"
    + "        Encrypted_Job_Description,\r\n"
    + "        PlainText_Job_Description,\r\n"
    + "        Attached_Emails,\r\n"
    + "        Profile_ID,\r\n"
    + "        Source,\r\n"
    + "        Scrapped_Date,\r\n"
    + "        Scrapped_Version,\r\n"
    + "        Time_Input_The_System,\r\n"
    + "        SQL_generated_HMD5,\r\n"
    + "        dkey\r\n"
    + "    FROM jobs\r\n"
    + "    ORDER BY dkey DESC\r\n"
    + "    LIMIT 80000\r\n"
    + ")\r\n"
    + "SELECT *\r\n"
    + "FROM last_jobs\r\n"
    + "WHERE\r\n"
    + "    PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
    + "    AND PlainText_Job_Description REGEXP '[؀-ۿ]'\r\n"
    + "    AND PlainText_Job_Description REGEXP '(?i)(\\bjava\\b|java\\s*developer|java\\s*engineer|java\\s*programmer|spring\\s*boot|j2ee|jakarta\\s*ee|jvm|microservices|جافا|مطور\\s*جافا|مهندس\\s*جافا|مبرمج\\s*جافا|سبرنج|سبيرنج)'\r\n"
    + "ORDER BY Scrapped_Date DESC";

    // 2️⃣ Saudi + Java (Arabic OR English) WITHOUT Arabic requirement
    public static final String _2_QueryOfSaudiJavaJobs =
    "WITH last_jobs AS (\r\n"
    + "    SELECT\r\n"
    + "        Hashed_Job_Describtion,\r\n"
    + "        Encrypted_Job_Description,\r\n"
    + "        PlainText_Job_Description,\r\n"
    + "        Attached_Emails,\r\n"
    + "        Profile_ID,\r\n"
    + "        Source,\r\n"
    + "        Scrapped_Date,\r\n"
    + "        Scrapped_Version,\r\n"
    + "        Time_Input_The_System,\r\n"
    + "        SQL_generated_HMD5,\r\n"
    + "        dkey\r\n"
    + "    FROM jobs\r\n"
    + "    ORDER BY dkey DESC\r\n"
    + "    LIMIT 80000\r\n"
    + ")\r\n"
    + "SELECT *\r\n"
    + "FROM last_jobs\r\n"
    + "WHERE\r\n"
    + "    PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
    + "    AND PlainText_Job_Description REGEXP '(?i)(\\bjava\\b|java\\s*developer|java\\s*engineer|java\\s*programmer|spring\\s*boot|j2ee|jakarta\\s*ee|jvm|microservices|جافا|مطور\\s*جافا|مهندس\\s*جافا|مبرمج\\s*جافا|سبرنج|سبيرنج)'\r\n"
    + "ORDER BY Scrapped_Date DESC";

    // 3️⃣ Saudi + Java Backend ONLY (Exclude Android)
    public static final String _3_QueryOfSaudiJavaBackendJobs =
    "WITH last_jobs AS (\r\n"
    + "    SELECT\r\n"
    + "        Hashed_Job_Describtion,\r\n"
    + "        Encrypted_Job_Description,\r\n"
    + "        PlainText_Job_Description,\r\n"
    + "        Attached_Emails,\r\n"
    + "        Profile_ID,\r\n"
    + "        Source,\r\n"
    + "        Scrapped_Date,\r\n"
    + "        Scrapped_Version,\r\n"
    + "        Time_Input_The_System,\r\n"
    + "        SQL_generated_HMD5,\r\n"
    + "        dkey\r\n"
    + "    FROM jobs\r\n"
    + "    ORDER BY dkey DESC\r\n"
    + "    LIMIT 80000\r\n"
    + ")\r\n"
    + "SELECT *\r\n"
    + "FROM last_jobs\r\n"
    + "WHERE\r\n"
    + "    PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
    + "    AND PlainText_Job_Description REGEXP '(?i)(\\bjava\\b|spring\\s*boot|microservices|hibernate|jpa|j2ee|jakarta\\s*ee|kafka|docker)'\r\n"
    + "    AND PlainText_Job_Description NOT REGEXP '(?i)(android|android\\s*developer)'\r\n"
    + "ORDER BY Scrapped_Date DESC";

    // 4️⃣ Saudi + Senior / Lead Java
    public static final String _4_QueryOfSaudiSeniorJavaJobs =
    "WITH last_jobs AS (\r\n"
    + "    SELECT\r\n"
    + "        Hashed_Job_Describtion,\r\n"
    + "        Encrypted_Job_Description,\r\n"
    + "        PlainText_Job_Description,\r\n"
    + "        Attached_Emails,\r\n"
    + "        Profile_ID,\r\n"
    + "        Source,\r\n"
    + "        Scrapped_Date,\r\n"
    + "        Scrapped_Version,\r\n"
    + "        Time_Input_The_System,\r\n"
    + "        SQL_generated_HMD5,\r\n"
    + "        dkey\r\n"
    + "    FROM jobs\r\n"
    + "    ORDER BY dkey DESC\r\n"
    + "    LIMIT 80000\r\n"
    + ")\r\n"
    + "SELECT *\r\n"
    + "FROM last_jobs\r\n"
    + "WHERE\r\n"
    + "    PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
    + "    AND PlainText_Job_Description REGEXP '(?i)(\\bjava\\b|spring\\s*boot|microservices)'\r\n"
    + "    AND PlainText_Job_Description REGEXP '(?i)(senior|lead|principal|architect|خبير|قائد|رئيسي)'\r\n"
    + "ORDER BY Scrapped_Date DESC";

    // 5️⃣ Saudi + Java Remote / Hybrid
    public static final String _5_QueryOfSaudiRemoteJavaJobs =
    "WITH last_jobs AS (\r\n"
    + "    SELECT\r\n"
    + "        Hashed_Job_Describtion,\r\n"
    + "        Encrypted_Job_Description,\r\n"
    + "        PlainText_Job_Description,\r\n"
    + "        Attached_Emails,\r\n"
    + "        Profile_ID,\r\n"
    + "        Source,\r\n"
    + "        Scrapped_Date,\r\n"
    + "        Scrapped_Version,\r\n"
    + "        Time_Input_The_System,\r\n"
    + "        SQL_generated_HMD5,\r\n"
    + "        dkey\r\n"
    + "    FROM jobs\r\n"
    + "    ORDER BY dkey DESC\r\n"
    + "    LIMIT 80000\r\n"
    + ")\r\n"
    + "SELECT *\r\n"
    + "FROM last_jobs\r\n"
    + "WHERE\r\n"
    + "    PlainText_Job_Description REGEXP '(السعودية|المملكة|KSA|Saudi)'\r\n"
    + "    AND PlainText_Job_Description REGEXP '(?i)(\\bjava\\b|spring\\s*boot|microservices)'\r\n"
    + "    AND PlainText_Job_Description REGEXP '(?i)(remote|hybrid|work\\s*from\\s*home|عن\\s*بعد|هجين)'\r\n"
    + "ORDER BY Scrapped_Date DESC";

    
    
    
    
    
    public static final String _6_QueryOfSaudiRemoteJavaJobs = " WITH filtered_jobs AS (\n"
    		+ "    SELECT *\n"
    		+ "    FROM jobs\n"
    		+ "    WHERE\n"
    		+ "        PlainText_Job_Description REGEXP '(?i)(السعودية|المملكة|KSA|Saudi|Riyadh|Jeddah|Dammam|Khobar|Makkah|Medina|Abha|الرياض|جدة|الدمام|الخبر|مكة|المدينة|أبها)'\n"
    		+ "    AND\n"
    		+ "        PlainText_Job_Description REGEXP '(?i)(developer|programmer|software engineer|backend|frontend|full stack|java|spring|node|python|dotnet|\\.net|c#|react|angular|مطور|مبرمج|مهندس برمجيات|جافا)'\n"
    		+ "    ORDER BY dkey DESC\n"
    		+ "    LIMIT 80000\n"
    		+ ")\n"
    		+ "\n"
    		+ "SELECT \n"
    		+ "    * -- ,    (SELECT COUNT(*) FROM filtered_jobs) AS total_records\n"
    		+ "FROM filtered_jobs\n"
    		+ "ORDER BY Scrapped_Date DESC; ";
    
    
    
    
    public static final String _7_QueryOfSaudiRemoteJavaJobs = 
    		" SELECT *\n"
    		+ "FROM (\n"
    		+ "    SELECT\n"
    		+ "        Hashed_Job_Describtion,\n"
    		+ "        PlainText_Job_Description,\n"
    		+ "        Profile_ID,\n"
    		+ "        Source,\n"
    		+ "        Scrapped_Date,\n"
    		+ "        Scrapped_Version,\n"
    		+ "        Time_Input_The_System,\n"
    		+ "        SQL_generated_HMD5,\n"
    		+ "        dkey,\n"
    		+ "        Attached_Emails\n"
    		+ "    FROM jobs\n"
    		+ "    ORDER BY dkey DESC\n"
    		+ "    LIMIT 50000\n"
    		+ ") t\n"
    		+ "WHERE\n"
    		+ "    t.PlainText_Job_Description LIKE '%java%'\n"
    		+ "AND (\n"
    		+ "       t.PlainText_Job_Description LIKE '%Riyadh%'\n"
    		+ "    OR t.PlainText_Job_Description LIKE '%Jeddah%'\n"
    		+ "    OR t.PlainText_Job_Description LIKE '%Saudi%'\n"
    		+ "    OR t.PlainText_Job_Description LIKE '%السعودية%'\n"
    		+ "    OR t.PlainText_Job_Description LIKE '%الرياض%'\n"
    		+ "    OR t.PlainText_Job_Description LIKE '%جدة%'\n"
    		+ ")\n"
    		+ "LIMIT 1000;\n"
    		+ " ";
    

    public static final String _8_QueryOfSaudiRemoteJavaJobs =
            "SELECT *\n" +
            "FROM (\n" +
            "    SELECT\n" +
            "        Hashed_Job_Describtion,\n" +
            "        PlainText_Job_Description,\n" +
            "        Attached_Emails,\n" +
            "        Profile_ID,\n" +
            "        Source,\n" +
            "        Scrapped_Date,\n" +
            "        Scrapped_Version,\n" +
            "        Time_Input_The_System,\n" +
            "        SQL_generated_HMD5,\n" +
            "        dkey\n" +
            "    FROM jobs\n" +
            "    ORDER BY dkey DESC\n" +
            "    LIMIT 50000\n" +
            ") t\n" +
            "WHERE\n" +
            "    t.PlainText_Job_Description REGEXP '(?i)(\\\\bjava\\\\b(?!\\\\s*script)|spring\\\\s*boot|j2ee|jakarta\\\\s*ee|microservices|jvm|hibernate|maven|gradle|servlet|tomcat|kafka|rest\\\\s*api|backend\\\\s*developer|مطور\\\\s*جافا|مهندس\\\\s*جافا)'\n" +
            "AND\n" +
            "    t.PlainText_Job_Description REGEXP '(?i)(ksa|k\\\\.s\\\\.a\\\\.?|kingdom\\\\s*of\\\\s*saudi\\\\s*arabia|saudi\\\\s*arabia|saudi\\\\b|المملكة\\\\s*العربية\\\\s*السعودية|المملكة|السعودية|riyadh|ar\\\\s*riyadh|الرياض|jeddah|jeddah\\\\s*sa|جدة|dammam|الدمام|khobar|الخبر|dhahran|الظهران|jubail|الجبيل|mecca|makkah|مكة|medina|madinah|المدينة|taif|الطائف|abha|أبها|خميس\\\\s*مشيط|khamis\\\\s*mushait|jazan|جازان|najran|نجران|tabuk|تبوك|hail|حائل|al\\\\s*qas\\\\s*sim|qassim|القصيم|yanbu|ينبع|al\\\\s*ahsa|ahsa|الأحساء|neom|نيوم|eastern\\\\s*province|المنطقة\\\\s*الشرقية|giga\\\\s*project|vision\\\\s*2030|رؤية\\\\s*2030)'\n" +
            "LIMIT 1000;\n";

    
    public static final String _9_QueryOfSaudiRemoteJavaJobs =
    		" \n"
    		+ "SELECT *\n"
    		+ "FROM jobs\n"
    		+ "WHERE Scrapped_Date like '%20260217%' \n"
    		+ "\n"
    		+ "-- 1) سعودي أولاً\n"
    		+ "AND (\n"
    		+ "    LOWER(PlainText_Job_Description) REGEXP '((ksa)|saudi|saudi arabia|kingdom of saudi arabia|riyadh|jeddah|dammam|khobar|dhahran|jubail|makkah|mecca|madinah|medina|khamis|najran|jazan|tabuk|qassim|yanbu|eastern province)'\n"
    		+ "    OR LOWER(PlainText_Job_Description) REGEXP '(السعودية|المملكة|المملكة العربية السعودية|الرياض|جدة|الدمام|الخبر|الظهران|الجبيل|مكة|المدينة|الطائف|أبها|خميس مشيط|نجران|جازان|تبوك|حائل|القصيم|ينبع|الأحساء|نيوم|المنطقة الشرقية)'\n"
    		+ "    OR LOWER(Source) REGEXP '(ksa|saudi)'\n"
    		+ ")\n"
    		+ "\n"
    		+ "-- 2) ثم Java ومشتقاتها\n"
    		+ "AND (\n"
    		+ "    LOWER(PlainText_Job_Description) REGEXP '(java|spring|spring boot|j2ee|jakarta|jvm|hibernate|maven|gradle|servlet|tomcat|kafka|microservices|rest api|backend|api|integration|middleware)'\n"
    		+ "    OR LOWER(PlainText_Job_Description) REGEXP '(جافا|مطور جافا|مهندس جافا|مبرمج جافا|سبرنج|تكامل|تكاملات)'\n"
    		+ ")\n"
    		+ "\n"
    		+ "ORDER BY dkey DESC; ";
    

    

    public static final String _10_QueryOfSaudiRemoteJavaJobs =
            " \n"
            + "SELECT *\n"
            + "FROM jobs\n"
            + "WHERE Scrapped_Date IN ('20260215')\n"
            + "\n"
            + "-- 1) سعودي أولاً\n"
            + "AND (\n"
            + "    LOWER(PlainText_Job_Description) REGEXP '((ksa)|saudi|saudi arabia|kingdom of saudi arabia|riyadh|jeddah|dammam|khobar|dhahran|jubail|makkah|mecca|madinah|medina|khamis|najran|jazan|tabuk|qassim|yanbu|eastern province)'\n"
            + "    OR LOWER(PlainText_Job_Description) REGEXP '(السعودية|المملكة|المملكة العربية السعودية|الرياض|جدة|الدمام|الخبر|الظهران|الجبيل|مكة|المدينة|الطائف|أبها|خميس مشيط|نجران|جازان|تبوك|حائل|القصيم|ينبع|الأحساء|نيوم|المنطقة الشرقية)'\n"
            + "    OR LOWER(Source) REGEXP '(ksa|saudi)'\n"
            + ")\n"
            + "\n"
            + "-- 2) Java فقط (مع استبعاد JavaScript)\n"
            + "AND (\n"
            + "    LOWER(PlainText_Job_Description) REGEXP '(\\\\bjava\\\\b(?!\\\\s*script)|spring\\\\s*boot|spring\\\\s*framework|j2ee|jakarta\\\\s*ee|jvm|hibernate|maven|gradle|servlet|tomcat|kafka|microservices|rest\\\\s*api|backend\\\\s*developer|software\\\\s*engineer|integration\\\\s*developer|middleware|api\\\\s*developer|application\\\\s*support|technical\\\\s*support)'\n"
            + "    OR LOWER(PlainText_Job_Description) REGEXP '(جافا|مطور\\\\s*جافا|مهندس\\\\s*جافا|مبرمج\\\\s*جافا|سبرنج|مهندس\\\\s*برمجيات|مطور\\\\s*خلفية|تكامل|تكاملات|دعم\\\\s*تطبيقي|دعم\\\\s*فني)'\n"
            + ")\n"
            + "\n"
            + "ORDER BY dkey DESC;";

    
    
    
    
    public static final String _11_QueryOfSaudiRemoteJavaJobs =
            " \n"
            + "SELECT *\n"
            + "FROM jobs\n"
            + "WHERE Scrapped_Date IN ('20260217')\n"
            + "\n"
         
            + "-- 2) Java فقط (مع استبعاد JavaScript)\n"
            + "AND (\n"
            + "    LOWER(PlainText_Job_Description) REGEXP '(\\\\bjava\\\\b(?!\\\\s*script)|spring\\\\s*boot|spring\\\\s*framework|j2ee|jakarta\\\\s*ee|jvm|hibernate|maven|gradle|servlet|tomcat|kafka|microservices|rest\\\\s*api|backend\\\\s*developer|software\\\\s*engineer|integration\\\\s*developer|middleware|api\\\\s*developer|application\\\\s*support|technical\\\\s*support)'\n"
            + "    OR LOWER(PlainText_Job_Description) REGEXP '(جافا|مطور\\\\s*جافا|مهندس\\\\s*جافا|مبرمج\\\\s*جافا|سبرنج|مهندس\\\\s*برمجيات|مطور\\\\s*خلفية|تكامل|تكاملات|دعم\\\\s*تطبيقي|دعم\\\\s*فني)'\n"
            + ")\n"
            + "\n"
            + "ORDER BY dkey DESC;";
    
    
    public static final String _12_QueryOfSaudiRemoteJavaJobs =
//    	    "SELECT * " +
//    	    "FROM jobs " +
//    	    "WHERE Scrapped_Date >= (CURRENT_DATE - INTERVAL 4 DAY) " +
//    	    "  AND Scrapped_Date <  (CURRENT_DATE + INTERVAL 1 DAY) " +
//    	    "ORDER BY dkey DESC";
    " SELECT *\n"
    + "FROM jobs\n"
    + "WHERE Scrapped_Date >= '20260114' \n" //(CURRENT_DATE - INTERVAL 50 DAY)
    + "  AND Scrapped_Date <  (CURRENT_DATE + INTERVAL 1 DAY)\n"
    + "  AND LOWER(PlainText_job_Description) REGEXP\n"
    + "      'SQLSERVER|SQL|SQL SERVER|SpringBoot|Spring Boot|spring|REST APIs|REST API|Oracl|Integration|java|software engineer|software developer|java developer|python developer|backend developer|frontend developer|front end developer|full stack developer|web developer|application developer|android developer|ios developer|mobile developer|devops engineer|data engineer|qa engineer|test automation engineer|solutions architect'\n"
    + "  AND LOWER(PlainText_job_Description) REGEXP\n"
    + "      'saudi arabia|ksa|riyadh|jeddah|dammam|khobar|makkah|mecca|madinah|medina|jubail|yanbu|abha|taif|qassim|tabuk|najran|hail|jazan'\n"
    + "ORDER BY dkey DESC; ";
    
}
