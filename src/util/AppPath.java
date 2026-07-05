package util;

import java.io.File;
import java.nio.file.Files;
import util.LanguageManager;

public class AppPath {
    
    private static final String configPath = "../cache/settings.json";

    public static String basePath;
    public static File MainProject;

    static {
        try {
            LanguageManager.load(configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String projectsFolder = LanguageManager.get("projectsFolder")
                .replace("%USERPATH%", System.getProperty("user.home"))
                .replace("/", File.separator);

        String selectedProject = LanguageManager.get("selectedProject");

        basePath = projectsFolder;
        MainProject = new File(basePath + File.separator + selectedProject);
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
        sb.append("    \"selectedProject\": \"").append(MainProject.getName()).append("\"\n");
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