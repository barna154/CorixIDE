package util;

import java.io.File;
import java.nio.file.Files;

public class AppPath {
    public static String basePath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "CRXProjects";
    public static File MainProject = new File(basePath + File.separator + "Test");
}