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

public class GoogleAppsScriptAPIThread {
    // Database configuration
    private static final String DB_URL = "jdbc:mysql://localhost:3306/newschema";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        try {
            // Start 16 threads
            for (int i = 0; i < 16; i++) {
                int threadNumber = i;

                new Thread(() -> {
                    try (Connection connection = createDatabaseConnection()) {
                        sendRequestAndInsertData(connection, threadNumber);
                    } catch (SQLException e) {
                        System.err.println("Error creating connection in thread " + threadNumber + ": " + e.getMessage());
                    }
                }).start();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection createDatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("JDBC Driver not found.", e);
        }
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private static void sendRequestAndInsertData(Connection connection, int threadNumber) {
        try {
            //test online tool here :
        	//https://reqbin.com/
        	// API URL
            String apiUrl = "https://script.google.com/macros/s/AKfycbxIXmBYhPIyq4fzxH9pv3UnYYn3sl757OgorHranr0B8fqVwI2MSaJ8xQrK4d7xN9oi/exec";
            int xNumberOfRecordRequired = 100;//number of last x records 
            // JSON payload with unique number
            String payload = "{\n" +
                    "    \"number\": " + threadNumber + ",\n" +
                    "    \"xNumberOfRecordRequired\":"+ xNumberOfRecordRequired+"\n" +
                    "}";
            System.out.println(payload);
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

            System.out.println("Thread " + threadNumber + " Response: " + response);

            // Parse the JSON response
            JSONArray jsonArray = new JSONArray(response);

            // Insert the data into the database
            insertDataIntoDatabase(connection, jsonArray, threadNumber);

        } catch (Exception e) {
            System.err.println("Error in thread " + threadNumber + ": " + e.getMessage());
        }
    }

    private static void insertDataIntoDatabase(Connection connection, JSONArray jsonArray, int threadNumber) {
        String insertQuery = "INSERT INTO JobDescriptions (DKEY, HashedJobDescription, EncryptedJobDescription, PlainTextJobDescription, AttachedEmails, ProfileID, Source, ScrappedDate, ScrappedVersion, TimeInputSystem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int dkeyCounter = threadNumber * 1000; // Ensure unique DKEYs for each thread

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
                    System.err.println("Error inserting row in thread " + threadNumber + ": " + e.getMessage());
                }
            }

            // Execute remaining batch
            preparedStatement.executeBatch();
            System.out.println("Thread " + threadNumber + ": Data inserted into the database successfully!");

        } catch (SQLException e) {
            System.err.println("Thread " + threadNumber + ": Error inserting data into the database: " + e.getMessage());
        }
    }
}
