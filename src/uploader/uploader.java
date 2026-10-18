
package uploader;

import menus.ConsolePanel;
import util.AppPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class uploader {

    private final ConsolePanel console;

    public uploader(ConsolePanel console) {
        this.console = console;
    }

    public void upload(File currentProject) {

        if (currentProject == null) {
            console.println("Hiba: nincs megnyitott projektfájl!");
            return;
        }

        String cpu = AppPath.BoardName;

        if (cpu == null || cpu.isEmpty()) {
            console.println("Hiba: nincs kiválasztott PIC!");
            return;
        }


        String projectName = currentProject.getName();
        int dotIndex = projectName.lastIndexOf('.');

        if (dotIndex > 0) {
            projectName = projectName.substring(0, dotIndex);
        }

        File projectDir = currentProject.getAbsoluteFile().getParentFile();
        File hexFile = new File(projectDir, projectName + ".hex");

        if (!hexFile.isFile()) {
            console.println("Hiba: a HEX fájl nem található!");
            console.println(hexFile.getAbsolutePath());
            return;
        }

        File board = findCuriosityBoard(cpu);

        if (board == null) {
            console.println("Hiba: a kiválasztott PIC nem található: " + cpu);
            return;
        }

        // A HEX fájl bemásolása a meghajtó gyökérkönyvtárába.
        File destination = new File(board, hexFile.getName());

        try {
            Files.copy(
                hexFile.toPath(),
                destination.toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );

            console.println("Sikeres feltöltés!");
            console.println("PIC: " + cpu);
            console.println("Forrás: " + hexFile.getAbsolutePath());
            console.println("Cél: " + destination.getAbsolutePath());

        } catch (IOException ex) {
            console.println("Hiba a feltöltés során: " + ex.getMessage());
        }
    }

    private File findCuriosityBoard(String pic) {

        File[] roots = File.listRoots();

        if (roots == null) {
            return null;
        }

        String searchText = "Drag-and-drop programming of " + pic;

        for (File root : roots) {

         
            if (matchesBoard(root, searchText)) {
                return root;
            }

 
            File[] folders = root.listFiles(File::isDirectory);

            if (folders == null) {
                continue;
            }

            for (File folder : folders) {
                if (matchesBoard(folder, searchText)) {
                    return root;
                }
            }
        }

        return null;
    }

    private boolean matchesBoard(File folder, String searchText) {

        File statusFile = new File(folder, "status.txt");

        if (!statusFile.isFile()) {
            return false;
        }

        try {
            String content = Files.readString(statusFile.toPath());
            return content.contains(searchText);

        } catch (IOException ex) {
            return false;
        }
    }
}
