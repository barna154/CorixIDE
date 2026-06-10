package menus;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File; 
import java.util.Scanner;
import util.LanguageManager;



public class newProject {

    public void init(JPanel newProject) throws Exception {
        LanguageManager.load("../lang/lang.json");
        
        String sourcecont = LanguageManager.get("Explorer");
        String mtitle = LanguageManager.get("Title");
        String mfile = LanguageManager.get("File");
        String medit = LanguageManager.get("Edit");
        String moptions = LanguageManager.get("Options");
        String terminalname = LanguageManager.get("Terminal");
        String mboards = LanguageManager.get("Boards");

        newProject.setLayout(new BorderLayout());


        final int[] mouseOffset = new int[2];
        JPanel Menu = new JPanel(new BorderLayout());
        Menu.setBackground(new Color(43, 43, 43));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 7));
        leftPanel.setOpaque(false);

// Logo
        ImageIcon icon = new ImageIcon("../gui/logo.png");
        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        leftPanel.add(new JLabel(new ImageIcon(scaledImg)));

// Menüpontok
        JLabel labelFile = new JLabel(mfile);
        labelFile.setFont(new Font("Arial", Font.PLAIN, 15));
        labelFile.setForeground(new Color(118, 118, 118));
        labelFile.addMouseListener(new MouseAdapter() {
         @Override
            public void mouseClicked(MouseEvent e) { 
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                labelFile.setForeground(new Color(60, 60, 60));
                labelFile.setBackground(new Color(60, 60, 60));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                labelFile.setForeground(new Color(118, 118, 118));
                labelFile.setBackground(new Color(43, 43, 43));
            }
        });
        leftPanel.add(labelFile);

     
        JLabel labelEdit = new JLabel(medit);
        labelEdit.setFont(new Font("Arial", Font.PLAIN, 15));
        labelEdit.setForeground(new Color(118, 118, 118));
        labelEdit.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent e) { 
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                labelEdit.setForeground(new Color(60, 60, 60));
                labelEdit.setBackground(new Color(60, 60, 60));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                labelEdit.setForeground(new Color(118, 118, 118));
                labelEdit.setBackground(new Color(43, 43, 43));
            }
        });
        leftPanel.add(labelEdit);




        JLabel labelOptions = new JLabel(moptions);
        labelOptions.setFont(new Font("Arial", Font.PLAIN, 15));
        labelOptions.setForeground(new Color(118, 118, 118));
        labelOptions.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) { 
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    labelOptions.setForeground(new Color(60, 60, 60));
                    labelOptions.setBackground(new Color(60, 60, 60));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    labelOptions.setForeground(new Color(118, 118, 118));
                    labelOptions.setBackground(new Color(43, 43, 43));
                }
            });
        leftPanel.add(labelOptions);


        JLabel labelBoards = new JLabel(mboards);
        labelBoards.setFont(new Font("Arial", Font.PLAIN, 15));
        labelBoards.setForeground(new Color(118, 118, 118));
        labelBoards.addMouseListener(new MouseAdapter() {
            @Override
                public void mouseClicked(MouseEvent e) { 
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    labelBoards.setForeground(new Color(60, 60, 60));
                    labelBoards.setBackground(new Color(60, 60, 60));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    labelBoards.setForeground(new Color(118, 118, 118));
                    labelBoards.setBackground(new Color(43, 43, 43));
                }
            });
        leftPanel.add(labelBoards);

        JLabel complinelb = new JLabel("☑️");
        complinelb.setPreferredSize(new Dimension(26, 23));
        complinelb.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        complinelb.setHorizontalAlignment(SwingConstants.CENTER);
        complinelb.setVerticalAlignment(SwingConstants.CENTER);
        complinelb.setForeground(new Color(118, 118, 118));
        complinelb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        complinelb.setOpaque(true);
        complinelb.setBackground(new Color(43, 43, 43));

        complinelb.addMouseListener(new MouseAdapter() {
           @Override
                public void mouseClicked(MouseEvent e) { 
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    complinelb.setForeground(new Color(0, 28, 150));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    complinelb.setForeground(new Color(118, 118, 118));
                }
            });

        leftPanel.add(complinelb);

        JLabel uploadb = new JLabel("⬆️");
        uploadb.setPreferredSize(new Dimension(26, 23));
        uploadb.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        uploadb.setHorizontalAlignment(SwingConstants.CENTER);
        uploadb.setVerticalAlignment(SwingConstants.CENTER);
        uploadb.setForeground(new Color(118, 118, 118));
        uploadb.setCursor(new Cursor(Cursor.HAND_CURSOR));
        uploadb.setOpaque(true);
        uploadb.setBackground(new Color(43, 43, 43));

        uploadb.addMouseListener(new MouseAdapter() {
           @Override
                public void mouseClicked(MouseEvent e) { 
                }
                @Override
                public void mouseEntered(MouseEvent e) {
                    uploadb.setForeground(new Color(0, 168, 0));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    uploadb.setForeground(new Color(118, 118, 118));
                }
            });

        leftPanel.add(uploadb);
            
        Menu.add(leftPanel, BorderLayout.WEST);


        JLabel closeBtn = new JLabel("✕");
        closeBtn.setPreferredSize(new Dimension(45, 43));
        closeBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        closeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        closeBtn.setVerticalAlignment(SwingConstants.CENTER);
        closeBtn.setForeground(new Color(118, 118, 118));
        closeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        closeBtn.setOpaque(true);
        closeBtn.setBackground(new Color(43, 43, 43));

        closeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                closeBtn.setForeground(Color.WHITE);
                closeBtn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
                closeBtn.setBackground(Color.RED);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                closeBtn.setForeground(new Color(118, 118, 118));
                closeBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
                closeBtn.setBackground(new Color(43, 43, 43));
            }
        });



        JLabel maximize = new JLabel("🗗");
        maximize.setPreferredSize(new Dimension(43, 43));
        maximize.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        maximize.setFont(new Font("Noto Sans Symbols 2", Font.PLAIN, 22));
        maximize.setHorizontalAlignment(SwingConstants.CENTER);
        maximize.setVerticalAlignment(SwingConstants.CENTER);
        maximize.setForeground(new Color(118, 118, 118));
        maximize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        maximize.setOpaque(true);
        maximize.setBackground(new Color(43, 43, 43));
        maximize.addMouseListener(new MouseAdapter() {
          
            @Override
            public void mouseClicked(MouseEvent e) { 
                Rectangle current = window.getBounds();
                if (current.width == maxBounds.width && current.height == maxBounds.height) {
                        window.setBounds(normalBounds[0]);
                    } else {
                        normalBounds[0] = current;
                        window.setBounds(maxBounds);
                    }
                 }
            @Override
            public void mouseEntered(MouseEvent e) {
                maximize.setForeground(Color.WHITE);
                maximize.setFont(new Font("Noto Sans Symbols 2", Font.BOLD, 22));
                maximize.setBackground(new Color(60, 60, 60));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                maximize.setForeground(new Color(118, 118, 118));
                maximize.setFont(new Font("Noto Sans Symbols 2", Font.PLAIN, 22));
                maximize.setBackground(new Color(43, 43, 43));
            }
        });


        JLabel minimize = new JLabel("_");
        minimize.setPreferredSize(new Dimension(43, 43));
        minimize.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        minimize.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
        minimize.setHorizontalAlignment(SwingConstants.CENTER);
        minimize.setVerticalAlignment(SwingConstants.CENTER);
        minimize.setForeground(new Color(118, 118, 118));
        minimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
        minimize.setOpaque(true);
        minimize.setBackground(new Color(43, 43, 43));
        minimize.addMouseListener(new MouseAdapter() {
          
            @Override
            public void mouseClicked(MouseEvent e) { 
                window.setState(JFrame.ICONIFIED);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                minimize.setForeground(Color.WHITE);
                minimize.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
                minimize.setBackground(new Color(60, 60, 60));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                minimize.setForeground(new Color(118, 118, 118));
                minimize.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 22));
                minimize.setBackground(new Color(43, 43, 43));
            }
        });

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(minimize);
        rightPanel.add(maximize);
        rightPanel.add(closeBtn);
        Menu.add(rightPanel, BorderLayout.EAST);

        Menu.add(rightPanel, BorderLayout.EAST);
        newProject.add(Menu, BorderLayout.NORTH);

    }

}