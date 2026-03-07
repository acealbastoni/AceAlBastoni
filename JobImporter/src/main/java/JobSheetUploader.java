import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;

import utils.DBConnection;

public class JobSheetUploader {

    private static final String GOOGLE_SCRIPT_URL = //"https://script.google.com/macros/s/AKfycbwB_zSaXxm_S6Toh62RyE7Tog4lUDZZJSmh4XA5ebwHzsuJ8IKIcVmAA51HpiYgE1EzHA/exec";
    "https://script.google.com/macros/s/AKfycbw6ctohJP85I1Jeo6p5z2EWyiFBEdavgX_TcWvlZtho--6j1SPr-xxtwLDZsgiYErqsKQ/exec";
    		//"https://script.google.com/macros/s/AKfycbwvH4mqQiq1XBGn67Eh6sR-V7Bd9K5QkeqkaB1V8mTufUNbNU7uUVTS1qVfafbZkPby-g/exec";
//"https://script.google.com/macros/s/AKfycbzTwNxXWyUkCJydB1X5YOsKz_KxGByIj_a_gBfy3h2xfthmElFqVDnDIkhHoSzdKR4Ltg/exec";
    
 // Your regex (same logic as in JS)
    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("[.\\w-]+@([\\w-]+\\.)+[\\w-]+");

    public static List<String> extractEmails(String text) {
        if (text == null) return Collections.emptyList();

        Matcher m = EMAIL_PATTERN.matcher(text);
        LinkedHashSet<String> found = new LinkedHashSet<>();

        while (m.find()) {
            found.add(m.group());
        }
        return new ArrayList<>(found);
    }

    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
        System.setProperty("jdk.tls.client.protocols", "TLSv1.2,TLSv1.3");

        List<List<String>> jobsData = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection()) {
        	
        	//============================================================
        	//============================================================
        	//============================================================
        	//============================================================
        	
		
        	//============================================================
        	//============================================================
        	//============================================================
        	//====================Only Arabic Jobs =======================
        	
        	String sql = 
        			Constants._12_QueryOfSaudiRemoteJavaJobs;
        			//_2_QueryOfSaudiJavaJobs;
        			
        			//QueryOFSuadiArabicJobs;
        			//;
        			//Constants.QueryOFSuadiArabicJobs;
//        			"WITH last80k AS (\r\n"
//        			+ "    SELECT \r\n"
//        			+ "        Hashed_Job_Describtion,\r\n"
//        			+ "        Encrypted_Job_Description,\r\n"
//        			+ "        PlainText_Job_Description,\r\n"
//        			+ "        Attached_Emails,\r\n"
//        			+ "        Profile_ID,\r\n"
//        			+ "        Source,\r\n"
//        			+ "        Scrapped_Date,\r\n"
//        			+ "        Scrapped_Version,\r\n"
//        			+ "        Time_Input_The_System,\r\n"
//        			+ "        SQL_generated_HMD5,\r\n"
//        			+ "        dkey\r\n"
//        			+ "    FROM jobs\r\n"
//        			+ "    ORDER BY dkey DESC\r\n"
//        			+ "    LIMIT 480000\r\n"
//        			+ ")\r\n"
//        			+ "SELECT *\r\n"
//        			+ "FROM last80k\r\n"
//        			+ "WHERE\r\n"
//        			+ "    (\r\n"
//        			+ "        PlainText_Job_Description LIKE N'%الرياض%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '% جدة%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%مكة%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%تبوك%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%سعودى%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%سعودي%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%مكة%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%القصيم%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%أبها%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%رابغ%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%الدمام%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%الطائف%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%جازان%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%المدينة%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%Riyadh%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%Al Riyadh%'\r\n"
//        			+ "        OR PlainText_Job_Description LIKE '%Ar Riyadh%'\r\n"
//        			+ "   	   OR PlainText_Job_Description LIKE '%Java developer%'\r\n"
//        			+ "         OR PlainText_Job_Description LIKE '%kafka%'\r\n"
//        			+ "          OR PlainText_Job_Description LIKE '%sprinboot%'\r\n"
//        			+ "           OR PlainText_Job_Description LIKE '%azure%'\r\n"
//        			+ "            OR PlainText_Job_Description LIKE '%jeddah%'\r\n"
//        			+ "             OR PlainText_Job_Description LIKE '%mekkah%' "
//        			+ "    )\r\n"
//        			+ "    AND PlainText_Job_Description REGEXP '[؀-ۿ]'\r\n"
//        			+ "ORDER BY Scrapped_Date DESC;\r\n"
//        			+ "";
        	//============================================================
        	//============================================================
        	//============================================================
        	//============================================================
      	
////        	 String sql = " WITH last80k AS (\r\n"
////        	 		+ "    SELECT \r\n"
////        	 		+ "        Hashed_Job_Describtion, \r\n"
////        	 		+ "        Encrypted_Job_Description, \r\n"
////        	 		+ "        PlainText_Job_Description, \r\n"
////        	 		+ "        Attached_Emails, \r\n"
////        	 		+ "        Profile_ID, \r\n"
////        	 		+ "        Source, \r\n"
////        	 		+ "        Scrapped_Date, \r\n"
////        	 		+ "        Scrapped_Version, \r\n"
////        	 		+ "        Time_Input_The_System, \r\n"
////        	 		+ "        SQL_generated_HMD5, \r\n"
////        	 		+ "        dkey\r\n"
////        	 		+ "    FROM jobs\r\n"
////        	 		+ "    ORDER BY dkey DESC\r\n"
////        	 		+ "    LIMIT 90000\r\n"
////        	 		+ ")\r\n"
////        	 		+ "SELECT *\r\n"
////        	 		+ "FROM last80k\r\n"
////        	 		+ "WHERE\r\n"
////        	 		+ "(\r\n"
////        	 		+ "    /* ============================= */\r\n"
////        	 		+ "    /* 🇸🇦 السعودية (كل المدن) */\r\n"
////        	 		+ "    /* ============================= */\r\n"
////        	 		+ "    PlainText_Job_Description REGEXP\r\n"
////        	 		+ "    '(السعودية|المملكة|المدينة|نيوم|ينبع|نجران|أبها|عسير|مكة|الرياض|تبوك|القصيم|الخبر|الدمام|جدة|KSA|Saudi|Riyadh|Jeddah|Dammam|Khobar|Mecca|Medina|Yanbu|Neom|Tabuk|Qassim|Abha|Najran|Jizan|Hail|Al[- ]?Riyadh)'\r\n"
////        	 		+ ")\r\n"
////        	 		+ "AND\r\n"
////        	 		+ "(\r\n"
////        	 		+ "    /* ============================= */\r\n"
////        	 		+ "    /* 💻 Java / C# / Azure stack */\r\n"
////        	 		+ "    /* ============================= */\r\n"
////        	 		+ "    PlainText_Job_Description REGEXP\r\n"
////        	 		+ "    '(Java([^s]|$)|J2EE|Spring|Spring Boot|Hibernate|\r\n"
////        	 		+ "      C[#]|C Sharp|\\\\.NET|ASP\\\\.NET|ASP.NET Core|\r\n"
////        	 		+ "      Azure|Azure DevOps|AKS|App Service|\r\n"
////        	 		+ "      Microservices|REST API|Kafka)'\r\n"
////        	 		+ ")\r\n"
////        	 		+ "ORDER BY Scrapped_Date DESC;\r\n"
////        	 		+ " ";
        	
        	
        	//============================================================
        	//============================================================
        	//============================================================
        	//============================================================
        	
        	
        	
        	
        	
        	
        	
        	
        	

        	System.out.println(sql);
        	//System.exit(0);
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
int counter =0;
                while (rs.next()) {
                	counter++;
                	
                	String desc = rs.getString("PlainText_Job_Description");
                	List<String> emails = extractEmails(desc);


                	
                    List<String> row = new ArrayList<>();
                    row.add(rs.getString("Hashed_Job_Describtion"));
                    row.add("----------");
                    //row.add("█ █ 𝓐𝓬𝓮𝓐𝓵𝓑𝓪𝓼𝓽𝓸𝓷𝓲 █ █");
                    row.add(rs.getString("PlainText_Job_Description"));
                    //row.add(rs.getString("Attached_Emails"));
                    row.add(!emails.isEmpty() ? String.join(", ", emails) : "");
                    //row.add(rs.getString("PlainText_Job_Description"));
                    row.add(rs.getString("Profile_ID"));
                    row.add(rs.getString("Source"));
                    row.add(rs.getString("Scrapped_Date"));
                    row.add(rs.getString("Scrapped_Version"));
                    row.add(rs.getString("Time_Input_The_System"));
                    row.add(rs.getString("SQL_generated_HMD5"));
                    row.add(rs.getString("dkey"));
                    jobsData.add(row);
                }
            }

            if (jobsData.isEmpty()) {
                System.out.println("⚠️ No data found for scrapped_date = 2025-07-12");
                return;
            }

            System.out.println("✅ Rows to upload: " + jobsData.size());
//            boolean success = sendDataToGoogleSheet(jobsData);
//            if (success) {
//                System.out.println("✅ Data successfully uploaded to Google Sheet.");
//            } else {
//                System.out.println("❌ Failed to upload data to Google Sheet.");
//            }

			System.out.println("🧹 Resetting sheet before upload...");
			boolean resetOk = resetSheetOnGoogle();
			if (!resetOk) {
				System.out.println("❌ Reset failed. Stopping.");
				return;
			}

         // ====== BATCH UPLOAD LOGIC ======
            int batchSize = 300; // مهم جدًا — متزودوش عن 300 مع Apps Script
            int total = jobsData.size();
            int successCount = 0;

            for (int i = 0; i < total; i += batchSize) {

                int end = Math.min(i + batchSize, total);
                List<List<String>> batch = jobsData.subList(i, end);

                System.out.println("🚀 Uploading batch: " + (i / batchSize + 1)
                        + " | Rows: " + batch.size());

                boolean success = sendDataToGoogleSheet(batch);

                if (!success) {
                    System.out.println("❌ Batch failed. Stopping upload.");
                    break;
                }

                successCount += batch.size();

                try {
                    Thread.sleep(1500); // delay 1.5 ثانية بين كل batch
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.println("✅ Total uploaded rows: " + successCount);

            
            
            
            
            
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean sendDataToGoogleSheet(List<List<String>> data) {
        try {
            URL url = new URL(GOOGLE_SCRIPT_URL);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("POST");
//            conn.setRequestProperty("Content-Type", "application/json");
//            conn.setDoOutput(true);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60000);   // 60 ثانية
            conn.setReadTimeout(180000);     // 3 دقائق
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            Map<String, Object> requestData = new HashMap<>();
            requestData.put("action", "uploadJobs");
            requestData.put("data", data);


            String json = new Gson().toJson(requestData);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = conn.getResponseCode();
            System.out.println("🌐 HTTP Response Code: " + code);

            if (code == 200) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("📨 Response: " + response.toString());
                }
                return true;
            } else {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.err.println("❌ Error Response: " + response.toString());
                }
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    
    
    private static boolean resetSheetOnGoogle() {
        try {
            URL url = new URL(GOOGLE_SCRIPT_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(180000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            Map<String, Object> requestData = new HashMap<>();
            requestData.put("action", "resetJobs");

            String json = new Gson().toJson(requestData);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int code = conn.getResponseCode();
            System.out.println("🧹 Reset HTTP Code: " + code);
            return code == 200;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    
    
    
    
    
    
    
    
    
}
