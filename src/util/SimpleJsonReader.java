package util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimpleJsonReader {

    public static Map<String, String> read(String filePath) throws Exception {
        Map<String, String> values = new HashMap<>();

        try (Scanner sc = new Scanner(new File(filePath))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.startsWith("\"")) {
                    String[] parts = line.split(":", 2);
                    if (parts.length == 2) {
                        String key = parts[0].replace("\"", "").trim();
                        String value = parts[1]
                                .replace(",", "")
                                .replace("\"", "")
                                .trim();
                        values.put(key, value);
                    }
                }
            }
        }

        return values;
    }
}