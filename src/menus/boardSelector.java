package menus;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.nio.file.Files;
import java.awt.event.*;
import java.nio.file.Paths;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import java.io.File; 
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import util.LanguageManager;
import util.RoundedBorder;
import util.selectedBoard;



public class boardSelector {

            public void init(JPopupMenu boardSelector) throws Exception {


                UIManager.put("MenuItem.background", new Color(30, 30, 30));
                UIManager.put("MenuItem.selectionBackground", new Color(60, 60, 60));
                UIManager.put("MenuItem.selectionForeground", new Color(230, 230, 230));
                UIManager.put("PopupMenu.border", BorderFactory.createEmptyBorder());
                UIManager.put("MenuItem.border", BorderFactory.createEmptyBorder());
                
                boardSelector.setOpaque(true);
                boardSelector.setBackground(new Color(30, 30, 30));
                boardSelector.setForeground(new Color(30, 30, 30));
                boardSelector.setBorder(new RoundedBorder(7, new Color(20, 20, 20)));
                boardSelector.putClientProperty("JPopupMenu.consumeEventOnClose", Boolean.TRUE);


                JMenuItem item1 = new JMenuItem("PIC16F15256");
                    item1.setName("PIC16F15256");
                    item1.setMargin(new Insets(6, 0, 6, 0));
                    item1.setBackground(new Color(30, 30, 30));
                    item1.setFont(new Font("Arial", Font.PLAIN, 15));
                    item1.setOpaque(true);
                    item1.addActionListener(e -> {
                        selectedBoard.BoardName = "PIC16F15256";
                        boardSelector.setVisible(false);
                        boardSelector.repaint();
                        System.out.println("Selected: " + selectedBoard.BoardName);
                    });
                JMenuItem item2 = new JMenuItem("PIC16F15274");
                    item2.setName("PIC16F15274");
                    item2.setMargin(new Insets(6, 0, 6, 0));
                    item2.setBackground(new Color(30, 30, 30));
                    item2.setFont(new Font("Arial", Font.PLAIN, 15));
                    item2.setOpaque(true);
                    item2.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                selectedBoard.BoardName = "PIC16F15274";
                                boardSelector.setVisible(false); 
                                boardSelector.repaint();
                        }
                    });
                JMenuItem item3 = new JMenuItem("PIC16F15275");
                    item3.setName("PIC16F15275");
                    item3.setMargin(new Insets(6, 0, 6, 0));
                    item3.setBackground(new Color(30, 30, 30));
                    item3.setFont(new Font("Arial", Font.PLAIN, 15));
                    item3.setOpaque(true);
                    item3.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                selectedBoard.BoardName = "PIC16F15275";
                                boardSelector.setVisible(false); 
                                boardSelector.repaint();
                        }
                    });
                JMenuItem item4 = new JMenuItem("PIC16F15276");
                    item4.setName("PIC16F15276");
                    item4.setMargin(new Insets(6, 0, 6, 0));
                    item4.setBackground(new Color(30, 30, 30));
                    item4.setFont(new Font("Arial", Font.PLAIN, 15));
                    item4.setOpaque(true);
                    item4.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                selectedBoard.BoardName = "PIC16F15276";
                                boardSelector.setVisible(false); 
                                boardSelector.repaint();
                        }
                    });
                JMenuItem item5 = new JMenuItem("Atmega328P");
                    item5.setName("Atmega328P");
                    if (item5.getName().equals(selectedBoard.BoardName)) {
                        item5.setForeground(new Color(13, 255, 122));
                    } else {
                        item5.setForeground(new Color(230, 230, 230));
                    }
                    item5.setMargin(new Insets(6, 0, 6, 0));
                    item5.setBackground(new Color(30, 30, 30));
                    item5.setFont(new Font("Arial", Font.PLAIN, 15));
                    item5.setOpaque(true);
                    item5.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                selectedBoard.BoardName = "Atmega328P";
                                boardSelector.setVisible(false); 
                                boardSelector.repaint();
                                System.out.println(selectedBoard.BoardName);
                        }
                    });

                boardSelector.add(item1);
                boardSelector.add(item2);
                boardSelector.add(item3);
                boardSelector.add(item4);
                boardSelector.add(item5);

                
                boardSelector.addPopupMenuListener(new PopupMenuListener() {
                    @Override
                    public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                        for (Component c : boardSelector.getComponents()) {
                            if (c instanceof JMenuItem item) {
                                if (item.getName().equals(selectedBoard.BoardName)) {
                                    item.setForeground(new Color(13, 255, 122));
                                } else {
                                    item.setForeground(new Color(230, 230, 230));
                                }
                            }
                        }
                    }

                    @Override public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {}
                    @Override public void popupMenuCanceled(PopupMenuEvent e) {}
                });



            }

