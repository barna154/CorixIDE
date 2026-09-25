```java
package menus;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.io.File;
import java.io.IOException;

import util.RoundedBorder;
import util.AppPath;

public class boardSelector {

    private ConsolePanel console;

    public boardSelector(ConsolePanel console) {
        this.console = console;
    }

    public void init(JPopupMenu boardSelector) throws Exception {

        UIManager.put("MenuItem.background", new Color(30, 30, 30));
        UIManager.put("MenuItem.selectionBackground", new Color(60, 60, 60));
        UIManager.put("MenuItem.selectionForeground", new Color(230, 230, 230));
        UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());
        UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());

        boardSelector.setOpaque(true);
        boardSelector.setBackground(new Color(30, 30, 30));
        boardSelector.setForeground(new Color(30, 30, 30));
        boardSelector.setBorder(
            new RoundedBorder(7, new Color(20, 20, 20))
        );

        boardSelector.putClientProperty(
            "JPopupMenu.consumeEventOnClose",
            Boolean.TRUE
        );

        String[] pics = {
            "PIC16F15256",
            "PIC16F15274",
            "PIC16F15275",
            "PIC16F15276"
        };

        for (String pic : pics) {

            File board = findCuriosityBoard(pic);

            if (board != null) {

                JMenuItem item = new JMenuItem(pic);

                item.setName(pic);
                item.setMargin(new Insets(6, 0, 6, 0));
                item.setBackground(new Color(30, 30, 30));
                item.setFont(new Font("Arial", Font.PLAIN, 15));
                item.setOpaque(true);

                item.addActionListener(e -> {

                    AppPath.BoardName = pic;

                    /*
                    Ha később szükséges lesz a meghajtó eltárolása:

                    AppPath.BoardDrive = board;
                    */

                    boardSelector.repaint();
                    AppPath.save();
                });

                boardSelector.add(item);
            }
        }

        boardSelector.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {

                for (Component c : boardSelector.getComponents()) {

                    if (c instanceof JMenuItem item) {

                        if (item.getName().equals(AppPath.BoardName)) {

                            item.setForeground(
                                new Color(13, 255, 122)
                            );

                        } else {

                            item.setForeground(
                                new Color(230, 230, 230)
                            );
                        }
                    }
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
    }

    private File findCuriosityBoard(String pic) {

        File[] roots = File.listRoots();

        if (roots == null) {

            console.println(
                "Nem találhatók meghajtók."
            );

            return null;
        }

        String searchText =
            "Drag-and-drop programming of " + pic;

        for (File root : roots) {

       
            File rootStatus =
                new File(root, "status.txt");

            if (rootStatus.isFile()) {

                try {

                    String content =
                        Files.readString(
                            rootStatus.toPath()
                        );

                    if (content.contains(searchText)) {

                        console.println(
                            "Curiosity board találva: " + pic
                        );

                        return root;
                    }

                } catch (IOException ex) {
           
                }
            }

         
            File[] folders =
                root.listFiles(File::isDirectory);

            if (folders == null) {
                continue;
            }

            for (File folder : folders) {

                File statusFile =
                    new File(folder, "status.txt");

                if (!statusFile.isFile()) {
                    continue;
                }

                try {

                    String content =
                        Files.readString(
                            statusFile.toPath()
                        );

                    if (content.contains(searchText)) {

                        console.println(
                            "Curiosity board találva: " + pic
                        );

                        return root;
                    }

                } catch (IOException ex) {
       
                }
            }
        }

        console.println(
            "Curiosity board nem található: " + pic
        );

        return null;
    }
}