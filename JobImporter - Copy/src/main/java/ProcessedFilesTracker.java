import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ProcessedFilesTracker {
    private static final String TRACK_FILE = "processed_files.txt";
    private static final Set<String> processed = new HashSet<>();

    static {
        try {
            File f = new File(TRACK_FILE);
            if (f.exists()) {
                try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        processed.add(line.trim());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("⚠️ Could not load processed files.");
        }
    }

    public static boolean isProcessed(String fileName) {
        return processed.contains(fileName);
    }

    public static void markAsProcessed(String fileName) {
        if (processed.add(fileName)) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRACK_FILE, true))) {
                writer.write(fileName);
                writer.newLine();
            } catch (IOException e) {
                System.err.println("⚠️ Failed to write to processed log.");
            }
        }
    }
}
