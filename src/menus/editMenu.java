package menus;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.nio.file.Files;
import java.awt.event.*;
import java.nio.file.Paths;
import java.io.File; 
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import util.LanguageManager;
import util.RoundedBorder;



public class editMenu {

            public void init(JPopupMenu editMenu) throws Exception {


                String undo = LanguageManager.get("Undo");
                String redo = LanguageManager.get("Redo");
                String cut = LanguageManager.get("Cut");
                String copy = LanguageManager.get("Copy");
                String paste = LanguageManager.get("Paste");
                String find = LanguageManager.get("Find");
                String replace = LanguageManager.get("Replace");


                UIManager.put("MenuItem.background", new Color(30, 30, 30));
                UIManager.put("MenuItem.selectionBackground", new Color(60, 60, 60));
                UIManager.put("MenuItem.selectionForeground", new Color(230, 230, 230));
                UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());
                UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());
                
                editMenu.setOpaque(true);
                editMenu.setBackground(new Color(30, 30, 30));
                editMenu.setForeground(new Color(30, 30, 30));
                editMenu.setBorder(new RoundedBorder(7, new Color(20, 20, 20)));
                editMenu.putClientProperty("JPopupMenu.consumeEventOnClose", Boolean.TRUE);


                JMenuItem item1 = new JMenuItem(undo + "                           Ctrl+Z");
                    item1.setMargin(new Insets(0, 0, 0, 0));
                    item1.setBackground(new Color(30, 30, 30));
                    item1.setFont(new Font("Arial", Font.PLAIN, 15));
                    item1.setForeground(new Color(230, 230, 230));
                    item1.setOpaque(true);
                JMenuItem item2 = new JMenuItem(redo + "                            Ctrl+Y");
                    item2.setMargin(new Insets(0, 0, 0, 0));
                    item2.setBackground(new Color(30, 30, 30));
                    item2.setFont(new Font("Arial", Font.PLAIN, 15));
                    item2.setForeground(new Color(230, 230, 230));
                    item2.setOpaque(true);
                JMenuItem item3 = new JMenuItem(cut + "                                   Ctrl+X");
                    item3.setMargin(new Insets(0, 0, 0, 0));
                    item3.setBackground(new Color(30, 30, 30));
                    item3.setFont(new Font("Arial", Font.PLAIN, 15));
                    item3.setForeground(new Color(230, 230, 230));
                    item3.setOpaque(true);
                JMenuItem item4 = new JMenuItem(copy + "                                  Ctrl+C");
                    item4.setMargin(new Insets(0, 0, 0, 0));
                    item4.setBackground(new Color(30, 30, 30));
                    item4.setFont(new Font("Arial", Font.PLAIN, 15));
                    item4.setForeground(new Color(230, 230, 230));
                    item4.setOpaque(true);
                JMenuItem item5 = new JMenuItem(paste + "                              Ctrl+V");
                    item5.setMargin(new Insets(0, 0, 0, 0));
                    item5.setBackground(new Color(30, 30, 30));
                    item5.setFont(new Font("Arial", Font.PLAIN, 15));
                    item5.setForeground(new Color(230, 230, 230));
                    item5.setOpaque(true);
                JMenuItem item6 = new JMenuItem(find + "                                  Ctrl+F");
                    item6.setMargin(new Insets(0, 0, 0, 0));
                    item6.setBackground(new Color(30, 30, 30));
                    item6.setFont(new Font("Arial", Font.PLAIN, 15));
                    item6.setForeground(new Color(230, 230, 230));
                    item6.setOpaque(true);
                JMenuItem item7 = new JMenuItem(replace + "                                      Ctrl+H");
                    item7.setMargin(new Insets(0, 0, 0, 0));
                    item7.setBackground(new Color(30, 30, 30));
                    item7.setFont(new Font("Arial", Font.PLAIN, 15));
                    item7.setForeground(new Color(230, 230, 230));
                    item7.setOpaque(true);

                JSeparator sep = new JSeparator();
                    sep.setOpaque(true);
                    sep.setForeground(new Color(80, 80, 80));
                    sep.setBackground(new Color(30, 30, 30));
                
                JSeparator sep2 = new JSeparator();
                    sep2.setOpaque(true);
                    sep2.setForeground(new Color(80, 80, 80));
                    sep2.setBackground(new Color(30, 30, 30));

                editMenu.add(item1);
                editMenu.add(item2);
                editMenu.add(sep);
                editMenu.add(item3);
                editMenu.add(item4);
                editMenu.add(item5);
                editMenu.add(sep2);
                editMenu.add(item6);
                editMenu.add(item7);

            }

}