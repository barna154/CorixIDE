package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import util.SimpleJsonReader;

public class AppPath {
    
    private static final String configPath = "../cache/settings.json";

    public static String basePath;
    public static File MainProject;
    public static String BoardName;


    static {
        Map<String, String> values;
        try {
            values = SimpleJsonReader.read(configPath);
        } catch (Exception e) {
            values = new java.util.HashMap<>();
        }

        String projectsFolder = values.getOrDefault(
                "projectsFolder",
                "%USERPATH%" + File.separator + "Documents" + File.separator + "CRXProjects"
        ).replace("%USERPATH%", System.getProperty("user.home"))
         .replace("/", File.separator);

        String selectedProject = values.getOrDefault("selectedProject", "Test");
        String selectedBoard = values.getOrDefault("selectedBoard", "PIC16F15276");

        basePath = projectsFolder;
        MainProject = new File(basePath + File.separator + selectedProject);
        BoardName = selectedBoard;
    }

    public static void save() {
        String home = System.getProperty("user.home");
        String storedFolder = basePath.startsWith(home)
                ? basePath.replaceFirst(java.util.regex.Pattern.quote(home), "%USERPATH%")
                : basePath;

        storedFolder = storedFolder.replace("\\", "/");

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("    \"projectsFolder\": \"").append(storedFolder).append("\",\n");
        sb.append("    \"selectedProject\": \"").append(MainProject.getName()).append("\",\n");
        sb.append("    \"selectedBoard\": \"").append(BoardName).append("\"\n");
        sb.append("}\n");

        try {
            File configFile = new File(configPath);
            File parent = configFile.getParentFile();
            if (parent != null) parent.mkdirs();
            Files.write(configFile.toPath(), sb.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}