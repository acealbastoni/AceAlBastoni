package test;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class GoogleAppsScriptAPI {
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/newschema";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Connected to the database successfully!");

            // API URL
           // String apiUrl = "https://script.google.com/macros/s/AKfycbxWGIiTHY9YAs9sd7_rXK17nbo0QTTUBdqZAN7YOZGeAkjCIKWVHaR23Bxxfrn9adYz/exec";
            String apiUrl = "https://script.google.com/macros/s/AKfycbxIXmBYhPIyq4fzxH9pv3UnYYn3sl757OgorHranr0B8fqVwI2MSaJ8xQrK4d7xN9oi/exec";
            
            int xNumberOfRecordRequired = 1000;//number of last x records 
            // JSON payload with unique number
            String payload = "{\n" +
                    "    \"number\": " + 1 + ",\n" +
                    "    \"xNumberOfRecordRequired\":"+ xNumberOfRecordRequired+"\n" +
                    "}";

            // Open connection
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // Send request
            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes(StandardCharsets.UTF_8));
            }

            // Read response
            String response;
            try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8)) {
                response = scanner.useDelimiter("\\A").next();
            }
            conn.disconnect();

            System.out.println("Response: " + response);

            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(response);

            // Insert the data into the database
            insertDataIntoDatabase(connection, jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close database connection
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("Database connection closed.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void insertDataIntoDatabase(Connection connection, JSONArray jsonArray) {
        String insertQuery = "INSERT INTO JobDescriptions (DKEY, HashedJobDescription, EncryptedJobDescription, PlainTextJobDescription, AttachedEmails, ProfileID, Source, ScrappedDate, ScrappedVersion, TimeInputSystem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int dkeyCounter = 0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONArray record = jsonArray.getJSONArray(i);

                try {
                    // Set values for the PreparedStatement
                    preparedStatement.setInt(1, dkeyCounter++); // DKEY
                    preparedStatement.setString(2, record.getString(0)); // HashedJobDescription
                    preparedStatement.setString(3, record.getString(1)); // EncryptedJobDescription
                    preparedStatement.setString(4, record.getString(2)); // PlainTextJobDescription
                    preparedStatement.setString(5, record.getString(3)); // AttachedEmails
                    preparedStatement.setString(6, record.optString(4, null)); // ProfileID
                    preparedStatement.setString(7, record.optString(5, null)); // Source
                    preparedStatement.setString(8, record.optString(6, null)); // ScrappedDate
                    preparedStatement.setString(9, record.optString(7, null)); // ScrappedVersion
                    preparedStatement.setString(10, record.optString(8, null)); // TimeInputSystem

                    // Add to batch
                    preparedStatement.addBatch();

                    // Execute batch every 100 rows
                    if (i % 100 == 0) {
                        preparedStatement.executeBatch();
                    }
                } catch (SQLException e) {
                    System.err.println("Error inserting row " + i + ": " + e.getMessage());
                }
            }

            // Execute remaining batch
            preparedStatement.executeBatch();
            System.out.println("Data inserted into the database successfully!");

        } catch (SQLException e) {
            System.err.println("Error inserting data into the database: " + e.getMessage());
        }
    }
}
