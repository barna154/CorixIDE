package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LanguageManager {
    private static final Map<String, String> values = new HashMap<>();
    public static void load(String filePath) throws Exception {
        values.clear();
        Scanner sc = new Scanner(new File(filePath));
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.startsWith("\"")) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String key = parts[0]
                            .replace("\"", "")
                            .trim();
                    String value = parts[1]
                            .replace(",", "")
                            .replace("\"", "")
                            .trim();
                    values.put(key, value);
                }
            }
        }

        sc.close();
    }

    public static String get(String key) {
        return values.getOrDefault(key, key);
    }
}