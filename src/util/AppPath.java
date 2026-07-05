package util;

import java.io.File;
import java.nio.file.Files;
import util.LanguageManager;

public class AppPath {

    public static String basePath;
    public static File MainProject;

    static {
        try {
            LanguageManager.load("../cache/settings.json");
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
}