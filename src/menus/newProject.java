package menus;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.nio.file.Files;
import java.awt.event.*;
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

// Menüpontok
        JLabel labelFile = new JLabel(sourcecont);
        labelFile.setFont(new Font("Arial", Font.PLAIN, 15));
        labelFile.setForeground(new Color(118, 118, 118));
        leftPanel.add(labelFile);

            
        Menu.add(leftPanel, BorderLayout.CENTER);


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
                 newProject.setVisible(false);
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


        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(closeBtn);
        Menu.add(rightPanel, BorderLayout.EAST);
        newProject.add(Menu, BorderLayout.NORTH);

    }

}