package util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LanguageManager {
    private static final Map<String, String> values = new HashMap<>();

    public static void load(String resourcePath) throws Exception {
        values.clear();

        InputStream input = LanguageManager.class.getResourceAsStream(resourcePath);

        if (input == null) {
            throw new FileNotFoundException("Resource not found: " + resourcePath);
        }

        Scanner sc = new Scanner(input, StandardCharsets.UTF_8);

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