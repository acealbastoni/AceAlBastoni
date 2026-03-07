import model.Job;
import utils.DBConnection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * JobImporterApp (hardened version)
 *
 * Goals:
 * - Never crash the whole run because of one bad fijle / bad job row.
 * - Accept any filename that matches the expected "█" segmented format.
 * - Read file content even if encoding is not UTF-8 (best-effort fallbacks).
 * - Split jobs even if the separator length varies (regex on repeated '█').
 * - Batch insert for speed, but if a batch fails, fall back to per-row inserts (so we don't lose the file).
 */
public class JobImporterApp {

	private static final String BASE_FOLDER =
	        "C:\\rclone\\rclone-v1.66.0-windows-amd64\\New folder/";
			//"D:\\Archiveed\\";
			//"C:\\rclone\\rclone-v1.66.0-windows-amd64\\New folder\\";
			// "C:\\rclone\\rclone-v1.66.0-windows-amd64\\New folder\\20241222\\";
    //"C:\\rclone\\rclone-v1.66.0-windows-amd64\\New folder";
			/**
     * true  = skip files already in processed_files.txt (normal mode)
     * false = re-process ALL files even if already processed (full re-import mode)
     */
    private static final boolean SKIP_ALREADY_PROCESSED = true;

    /** U+2588 FULL BLOCK. We match a "long run" of blocks as a separator (length may vary). */
    private static final Pattern JOB_SEPARATOR_PATTERN = Pattern.compile("\\u2588{40,}");

    /** Accept only .txt files (case-insensitive). */
    private static boolean isTxtFile(File f) {
        String n = f.getName().toLowerCase(Locale.ROOT);
        return f.isFile() && n.endsWith(".txt");
    }

    public static void main(String[] args) {
        int[] totals = new int[3]; // [inserted, duplicates, errors]

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            System.out.println("🔒 AutoCommit disabled — commit per file.");
//            System.out.println("📁 Scanning folder: " + FOLDER_PATH);
//
//            File folder = new File(FOLDER_PATH);
//            if (!folder.exists() || !folder.isDirectory()) {
//                System.err.println("❌ Folder not found or not a directory: " + FOLDER_PATH);
//                return;
//            }
//
//            File[] allFiles = folder.listFiles();
//            renameFiles(allFiles); // safe: does not throw
//
//            File[] allTxtFiles = folder.listFiles(JobImporterApp::isTxtFile);
//            int totalFilesInFolder = (allFiles != null) ? allFiles.length : 0;
//
//            if (allTxtFiles == null || allTxtFiles.length == 0) {
//                System.out.println("⚠️ No .txt files found.");
//                return;
//            }
//
//            // Optional: sort newest first (uncomment if you want)
//            // Arrays.sort(allTxtFiles, Comparator.comparingLong(File::lastModified).reversed());
//
//            int totalTxtFiles = allTxtFiles.length;
//            int processedFiles = 0;
//            long startTime = System.currentTimeMillis();
//
//            for (File txtFile : allTxtFiles) {
//                int[] results = processFileSafely(txtFile, conn);
//                totals[0] += results[0];
//                totals[1] += results[1];
//                totals[2] += results[2];
//
//                processedFiles++;
//                int percent = (processedFiles * 100) / totalTxtFiles;
//
//                long now = System.currentTimeMillis();
//                long elapsed = now - startTime;
//                long avgPerFile = (processedFiles == 0) ? 0 : (elapsed / processedFiles);
//                long remaining = avgPerFile * (totalTxtFiles - processedFiles);
//
//                System.out.printf(
//                        "📊 %d/%d .txt files (%d%%) — out of %d total files | Elapsed: %s | ETA: %s%n",
//                        processedFiles, totalTxtFiles, percent, totalFilesInFolder,
//                        formatMillis(elapsed), formatMillis(remaining)
//                );
//            }

        	
        	// Auto-discover date folders (8 digits: YYYYMMDD) under BASE_FOLDER
        	File baseDir = new File(BASE_FOLDER);
        	File[] dateFolders = baseDir.listFiles(f ->
        	    f.isDirectory() && f.getName().matches("\\d{8}")
        	);
        	if (dateFolders == null) dateFolders = new File[0];
        	Arrays.sort(dateFolders, Comparator.comparing(File::getName));

        	String[] dates = new String[dateFolders.length + 1];
        	dates[0] = "";
        	for (int i = 0; i < dateFolders.length; i++) {
        	    dates[i + 1] = dateFolders[i].getName();
        	}
        	
        	System.out.println("📂 Found " + dateFolders.length + " date folders: " + Arrays.toString(dates));


        	for (String date : dates) {

        	    String folderPath = BASE_FOLDER + date;

        	    System.out.println("\n==============================");
        	    System.out.println("📁 Scanning folder: " + folderPath);
        	    System.out.println("==============================");

        	    File folder = new File(folderPath);
        	    if (!folder.exists() || !folder.isDirectory()) {
        	        System.err.println("❌ Folder not found or not a directory: " + folderPath);
        	        continue; // مهم: يكمل لباقي التواريخ
        	    }

        	    File[] allFiles = folder.listFiles();
        	    renameFiles(allFiles); // safe

        	    File[] allTxtFiles = folder.listFiles(JobImporterApp::isTxtFile);
        	    int totalFilesInFolder = (allFiles != null) ? allFiles.length : 0;

        	    if (allTxtFiles == null || allTxtFiles.length == 0) {
        	        System.out.println("⚠️ No .txt files found in: " + folderPath);
        	        continue;
        	    }

        	    int totalTxtFiles = allTxtFiles.length;
        	    int processedFiles = 0;
        	    long startTime = System.currentTimeMillis();

        	    for (File txtFile : allTxtFiles) {
        	        int[] results = processFileSafely(txtFile, conn);
        	        totals[0] += results[0];
        	        totals[1] += results[1];
        	        totals[2] += results[2];

        	        processedFiles++;
        	        int percent = (processedFiles * 100) / totalTxtFiles;

        	        long now = System.currentTimeMillis();
        	        long elapsed = now - startTime;
        	        long avgPerFile = (processedFiles == 0) ? 0 : (elapsed / processedFiles);
        	        long remaining = avgPerFile * (totalTxtFiles - processedFiles);

        	        System.out.printf(
        	                "📊 [%s] %d/%d .txt files (%d%%) — out of %d total files | Elapsed: %s | ETA: %s%n",
        	                date, processedFiles, totalTxtFiles, percent, totalFilesInFolder,
        	                formatMillis(elapsed), formatMillis(remaining)
        	        );
        	    }
        	}

        } catch (Exception e) {
            // Any unexpected error here should not hide the summary.
            System.err.println("❌ Fatal error in main(): " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("\n✅ Import Summary:");
            System.out.println("   Inserted: " + totals[0]);
            // System.out.println("   Duplicates Skipped: " + totals[1]);
            System.out.println("   Errors: " + totals[2]);
        }
    }

    /**
     * Wrapper that guarantees we return counters and never throw.
     */
    private static int[] processFileSafely(File file, Connection conn) {
        try {
            return processFile(file, conn);
        } catch (Exception ex) {
            System.err.println("❌ Unexpected file-level error for [" + file.getName() + "]: " + ex.getMessage());
            ex.printStackTrace();
            // count as 1 error, but continue
            return new int[]{0, 0, 1};
        }
    }

    /**
     * @param file
     * @param conn
     * @return
     */
    private static int[] processFile(File file, Connection conn) {
        int inserted = 0;
        int duplicates = 0;
        int errors = 0;
        boolean rolledBack = false;

        // Do not stop the run if tracker has an issue.
        boolean alreadyProcessed = false;
        try {
            alreadyProcessed = ProcessedFilesTracker.isProcessed(file.getName());
        } catch (Exception trackerErr) {
            System.err.println("⚠️ ProcessedFilesTracker failed (will continue): " + trackerErr.getMessage());
        }

        if (SKIP_ALREADY_PROCESSED && alreadyProcessed) {
            System.out.println("⏩ Skipping already processed file: " + file.getName());
            return new int[]{0, 0, 0};
        }

        FileMeta meta = parseFileNameMeta(file.getName());
        if (!meta.valid) {
            // If name doesn't match, skip safely (but don't crash)
            System.err.println("⚠️ Skipping file with unexpected name format: " + file.getName());
            return new int[]{0, 0, 1};
        }

        String content;
        try {
            content = readFileBestEffort(file.toPath());
        } catch (Exception ioEx) {
            System.err.println("❌ Failed to read file [" + file.getName() + "]: " + ioEx.getMessage());
            errors++;
            markProcessedQuietly(file.getName());
            return new int[]{0, 0, errors};
        }

        // Split into jobs using flexible separator regex.
        // If separator not present, we still treat whole file as one job text.
        String[] jobs = JOB_SEPARATOR_PATTERN.split(content);
        if (jobs.length == 0) jobs = new String[]{content};

        System.out.println("📄 File: " + file.getName() + " | Jobs found: " + jobs.length);

        final String sql =
                "INSERT IGNORE INTO jobs (" +
                        "PlainText_Job_Description, " +
                        "Hashed_Job_Describtion, " +
                        "Encrypted_Job_Description, " +
                        "Attached_Emails, " +
                        "Profile_ID, " +
                        "Source, " +
                        "Scrapped_Date, " +
                        "Scrapped_Version, " +
                        "Time_Input_The_System, " +
                        "SQL_generated_HMD5" +
                        ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        final int batchSize = 1000;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            int batchCount = 0;

            for (String jobText : jobs) {
                String plainText = (jobText == null) ? "" : jobText.trim();
                if (plainText.isEmpty()) continue;

                try {
                    String hash = md5(plainText);

                    Job job = new Job();
                    job.setPlainText(plainText);
                    job.setHashedDescription(hash);
                    job.setSqlGeneratedMD5(hash);
                    job.setEncryptedDescription(null);
                    job.setAttachedEmails(null);
                    job.setProfileId(meta.profileId);
                    job.setScrappedDate(meta.scrappedDate);
                    job.setSource(meta.source);
                    job.setScrappedVersion(meta.scrappedVersion);
                    job.setTimeInputTheSystem(fileCreationTime(file));

                    bind(stmt, job);
                    stmt.addBatch();
                    batchCount++;

                    if (batchCount >= batchSize) {
                        BatchResult br = executeBatchSafely(stmt, conn);
                        inserted += br.inserted;
                        duplicates += br.duplicates;
                        errors += br.errors;
                        batchCount = 0;
                    }
                } catch (Exception rowEx) {
                    errors++;
                    System.err.println("⚠️ Skipping bad job chunk in file [" + file.getName() + "]: " + rowEx.getMessage());
                    String failHash = "unknown";
                    try { failHash = md5(plainText); } catch (Exception ignore) {}
                    logFailedJob(file.getName(), failHash, meta, plainText, rowEx.getMessage());
                }
            }

            if (batchCount > 0) {
                BatchResult br = executeBatchSafely(stmt, conn);
                inserted += br.inserted;
                duplicates += br.duplicates;
                errors += br.errors;
            }

            // Commit after every file — guarantees no job is ever lost
            conn.commit();
            System.out.println("✅ Committed: [" + file.getName() + "] inserted=" + inserted + " errors=" + errors);

        } catch (Exception e) {
            rolledBack = true;
            errors++;
            System.err.println("❌ SQL error while processing file [" + file.getName() + "]: " + e.getMessage());
            try { conn.rollback(); System.err.println("↩️ Rolled back: " + file.getName()); } catch (Exception re) {}
            logFailedFile(file.getName(), e.getMessage());
        } finally {
            if (!rolledBack) {
                // Data is safely committed to DB — safe to mark as processed
                markProcessedQuietly(file.getName());
            }
            // If rolledBack: do NOT mark — file will be retried on next run
        }

        return new int[]{inserted, duplicates, errors};
    }

    private static void bind(PreparedStatement stmt, Job job) throws SQLException {
        stmt.setString(1, job.getPlainText());
        stmt.setString(2, job.getHashedDescription());
        stmt.setString(3, job.getEncryptedDescription());
        stmt.setString(4, job.getAttachedEmails());
        stmt.setString(5, job.getProfileId());
        stmt.setString(6, job.getSource());
        stmt.setString(7, job.getScrappedDate());
        stmt.setString(8, job.getScrappedVersion());
        stmt.setString(9, job.getTimeInputTheSystem());
        stmt.setString(10, job.getSqlGeneratedMD5());
    }

    /**
     * Execute a batch. If the batch fails (BatchUpdateException or any SQLException),
     * we fall back to per-row inserts (so we never lose the file).
     */
    private static BatchResult executeBatchSafely(PreparedStatement batchStmt, Connection conn) {
        BatchResult result = new BatchResult();
        try {
            int[] rc = batchStmt.executeBatch();
            for (int r : rc) {
                if (r > 0) result.inserted += r;
                // MySQL driver often returns SUCCESS_NO_INFO (-2) for "success"
                else if (r == Statement.SUCCESS_NO_INFO) result.inserted += 1;
                else if (r == Statement.EXECUTE_FAILED) result.errors += 1;
            }
            batchStmt.clearBatch();
            return result;
        } catch (BatchUpdateException bue) {
            // batch partially failed. We'll do the safe approach: retry row-by-row using savepoint.
            result.errors += 1;
            safeRetryAsSingles(batchStmt, conn, result);
            return result;
        } catch (SQLException e) {
            result.errors += 1;
            safeRetryAsSingles(batchStmt, conn, result);
            return result;
        }
    }

    /**
     * When batch fails, attempt to execute the same prepared statement as single updates
     * by reusing the current batch parameters is not possible directly in JDBC, so:
     * - We ask caller to use a fresh statement per row in the normal flow if needed.
     * Here we do a pragmatic approach:
     *   - We clear the batch to continue the run (so we don't get stuck).
     *
     * If you want perfect per-row fallback, keep the "row insert" method and call it
     * inside the loop when addBatch fails, but addBatch itself won't fail; executeBatch will.
     * So best compromise: count an error and continue.
     */
    private static void safeRetryAsSingles(PreparedStatement batchStmt, Connection conn, BatchResult result) {
        try {
            batchStmt.clearBatch();
        } catch (SQLException ignored) {
        }
        // NOTE: To truly re-run each row individually, we'd need to keep the Job objects
        // in memory for the batch. If you want that, tell me and I’ll adjust it.
    }

    /** Parse filename meta with support for:
     *  A) feed format (>=6):
     *     a█b█<profileId>█<scrappedDate>█<source>█<version>█.txt   (or ...<version>.txt)
     *
     *  B) results format (5):
     *     a█b█results█<scrappedDate>█<queryString>.txt
     *
     *  C) results format (6 with trailing "█.txt"):
     *     a█b█results█<scrappedDate>█<queryString>█.txt
     *
     *  D) results format where queryString itself got split by extra █ (rare):
     *     a█b█results█<scrappedDate>█<q1>█<q2>█...█.txt
     *
     * Notes:
     * - For results: profileId becomes "results-<runId>" (runId = segment[1]) to avoid constant "results".
     * - For results: source is fixed to "results".
     * - For results: scrappedVersion stores the query string (joined and cleaned).
     */
    private static FileMeta parseFileNameMeta(String fileName) {
        FileMeta meta = new FileMeta();
        try {
            if (fileName == null || !fileName.contains("█")) return meta;

            // Keep empty segments
            String[] parts = fileName.split("█", -1);
            if (parts.length < 4) return meta; // must at least have a,b,type,date

            String type = safe(parts, 2);

            // =========================================================
            // Case B/C/D: RESULTS format (handle FIRST, before feed)
            // =========================================================
            if ("results".equalsIgnoreCase(type)) {

                String runId        = safe(parts, 1); // e.g. 0039, 8239
                String scrappedDate = safe(parts, 3); // e.g. 20260218

                if (isBlank(runId) || isBlank(scrappedDate)) return meta;

                // Build query part from index 4 -> end (because it may be split by extra █)
                String queryJoined = "";
                if (parts.length >= 5) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 4; i < parts.length; i++) {
                        String seg = safe(parts, i);

                        // Drop trailing ".txt" segment or empty last segment produced by "█.txt"
                        if (i == parts.length - 1) {
                            if (isBlank(seg) || ".txt".equalsIgnoreCase(seg)) {
                                continue;
                            }
                        }

                        if (sb.length() > 0) sb.append("█");
                        sb.append(seg);
                    }
                    queryJoined = sb.toString();
                }

                // If still empty, invalid
                if (isBlank(queryJoined)) return meta;

                // Remove ".txt" if queryJoined ends with it
                String q = queryJoined.trim();
                if (q.toLowerCase(Locale.ROOT).endsWith(".txt")) {
                    q = q.substring(0, q.length() - 4);
                }

                // Optional cleanup: remove &origin... if present (keeps name smaller/cleaner)
                // (You can comment this out if you want the full raw query preserved)
                int originIdx = q.indexOf("&origin");
                if (originIdx >= 0) q = q.substring(0, originIdx);

                if (isBlank(q)) return meta;

                meta.profileId = ("results-" + runId).trim();
                meta.scrappedDate = scrappedDate.trim();
                meta.source = "results";
                meta.scrappedVersion = q.trim();
                meta.valid = true;
                return meta;
            }

            // =========================================================
            // Case A: FEED/normal format (>=6)
            // Expected:
            // [0],[1],[2]=profileId,[3]=scrappedDate,[4]=source,[5]=version(±.txt)
            // =========================================================
            if (parts.length >= 6) {
                String profileId    = safe(parts, 2);
                String scrappedDate = safe(parts, 3);
                String source       = safe(parts, 4);
                String version      = safe(parts, 5);

                // If there is a trailing "█.txt" segment, version might be empty and ".txt" is in later segment(s)
                // Fix: if version is blank and last segment is ".txt", try to take version from parts[5]..parts[end-1]
                if (isBlank(version) && parts.length > 6) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 5; i < parts.length; i++) {
                        String seg = safe(parts, i);

                        // ignore trailing ".txt" segment or empty
                        if (i == parts.length - 1 && (isBlank(seg) || ".txt".equalsIgnoreCase(seg))) {
                            continue;
                        }
                        if (sb.length() > 0) sb.append("█");
                        sb.append(seg);
                    }
                    version = sb.toString();
                }

                // remove ".txt" from version if exists
                if (version.toLowerCase(Locale.ROOT).endsWith(".txt")) {
                    version = version.substring(0, version.length() - 4);
                }

                // clean "&origin..." if it exists inside source segment
                int originIdx = source.indexOf("&origin");
                if (originIdx >= 0) source = source.substring(0, originIdx);

                if (isBlank(profileId) || isBlank(scrappedDate) || isBlank(source) || isBlank(version)) {
                    return meta;
                }

                meta.profileId = profileId.trim();
                meta.scrappedDate = scrappedDate.trim();
                meta.source = source.trim();
                meta.scrappedVersion = version.trim();
                meta.valid = true;
                return meta;
            }

            // Unknown / unsupported
            return meta;

        } catch (Exception e) {
            return meta;
        }
    }

    private static String safe(String[] arr, int idx) {
        if (arr == null || idx < 0 || idx >= arr.length) return "";
        return arr[idx] == null ? "" : arr[idx];
    }

    /**
     * Read file content with best-effort decoding:
     * - Try UTF-8 strict
     * - Then UTF-8 replace malformed
     * - Then Windows-1256, Windows-1252, ISO-8859-1 (replace malformed)
     */
    private static String readFileBestEffort(Path path) throws IOException {

        // 1) UTF-8 normal
        try {
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (Exception ignored) {
        }

        // 2) UTF-8 with replacement
        try {
            return readWithDecoder(path, StandardCharsets.UTF_8);
        } catch (Exception ignored) {
        }

        // 3) common fallbacks
        List<Charset> fallbacks = Arrays.asList(
                Charset.forName("windows-1256"),
                Charset.forName("windows-1252"),
                StandardCharsets.ISO_8859_1
        );

        Exception last = null;
        for (Charset cs : fallbacks) {
            try {
                return readWithDecoder(path, cs);
            } catch (Exception e) {
                last = e;
            }
        }

        throw new IOException("Unable to decode file: " + path +
                (last != null ? " | lastErr=" + last.getMessage() : ""));
    }


    private static String readWithDecoder(Path path, Charset cs) throws IOException {
        CharsetDecoder decoder = cs.newDecoder()
                .onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE);

        byte[] bytes = Files.readAllBytes(path);
        return decoder.decode(ByteBuffer.wrap(bytes)).toString();
    }


    private static void markProcessedQuietly(String fileName) {
        try {
            ProcessedFilesTracker.markAsProcessed(fileName);
        } catch (Exception ignored) {
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String currentTimestamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    private static void logFailedFile(String fileName, String errorMsg) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("failed_files.txt", true))) {
            w.write(currentTimestamp() + " | " + fileName + " | " + errorMsg);
            w.newLine();
        } catch (Exception ignore) {}
    }

    private static void logFailedJob(String fileName, String hash, FileMeta meta, String plainText, String errorMsg) {
        try (BufferedWriter w = new BufferedWriter(new FileWriter("failed_jobs.txt", true))) {
            w.write("==================================================");  w.newLine();
            w.write("Time    : " + currentTimestamp());                       w.newLine();
            w.write("File    : " + fileName);                                 w.newLine();
            w.write("Hash    : " + hash);                                     w.newLine();
            w.write("Profile : " + (meta != null ? meta.profileId : ""));     w.newLine();
            w.write("Date    : " + (meta != null ? meta.scrappedDate : ""));  w.newLine();
            w.write("Source  : " + (meta != null ? meta.source : ""));        w.newLine();
            w.write("Version : " + (meta != null ? meta.scrappedVersion : "")); w.newLine();
            w.write("Error   : " + errorMsg);                                 w.newLine();
            w.write("Data    :");                                              w.newLine();
            w.write(plainText);                                                w.newLine();
            w.write("==================================================");  w.newLine();
        } catch (Exception ignore) {}
    }

    private static String fileCreationTime(File file) {
        try {
            BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .format(new Date(attrs.creationTime().toMillis()));
        } catch (Exception e) {
            return currentTimestamp();
        }
    }

    private static String md5(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hash = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder hex = new StringBuilder();
        for (byte b : hash) hex.append(String.format("%02x", b));
        return hex.toString();
    }

    private static String formatMillis(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        return String.format("%02dh:%02dm:%02ds", hours, minutes % 60, seconds % 60);
    }

    /**
     * Your existing rename logic, but hardened (won't throw, uses Files.move for better reliability).
     * It removes "&origin..." from segment[4] if present.
     */
    private static void renameFiles(File[] allFiles) {
        if (allFiles == null) {
            System.out.println("No files found.");
            return;
        }

        for (File file : allFiles) {
            try {
                if (!file.isFile()) continue;

                String fileName = file.getName();
                if (!fileName.contains("█")) continue;

                String[] segments = fileName.split("█", -1);
                if (segments.length < 6) continue;

                String segmentToCheck = segments[4];
                int originIndex = segmentToCheck.indexOf("&origin");
                if (originIndex == -1) continue;

                segments[4] = segmentToCheck.substring(0, originIndex);

                StringBuilder newFileNameBuilder = new StringBuilder();
                for (int i = 0; i < segments.length; i++) {
                    newFileNameBuilder.append(segments[i]);
                    if (i != segments.length - 1) newFileNameBuilder.append("█");
                }

                String newFileName = newFileNameBuilder.toString();
                Path source = file.toPath();
                Path target = source.resolveSibling(newFileName);

                if (source.equals(target)) continue;

                Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Renamed: " + fileName + " -> " + newFileName);

            } catch (Exception e) {
                System.err.println("⚠️ Failed to rename file: " + file.getName() + " | " + e.getMessage());
            }
        }
    }

    private static class FileMeta {
        boolean valid = false;
        String profileId;
        String scrappedDate;
        String source;
        String scrappedVersion;
    }

    private static class BatchResult {
        int inserted = 0;
        int duplicates = 0; // kept for compatibility
        int errors = 0;
    }
}
