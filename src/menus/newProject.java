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
        

        String newp = LanguageManager.get("New Project");
        String ppath = LanguageManager.get("Project Path");


        newProject.setLayout(new BorderLayout());


        final int[] mouseOffset = new int[2];
        JPanel Menu = new JPanel(new BorderLayout());
        Menu.setBackground(new Color(43, 43, 43));

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        leftPanel.setOpaque(false);


        JLabel labelFile = new JLabel(newp);
        labelFile.setFont(new Font("Arial", Font.BOLD, 18));
        labelFile.setForeground(new Color(250, 250, 250));
        leftPanel.add(labelFile);
        Menu.add(leftPanel, BorderLayout.CENTER);




        JLabel closeBtn = new JLabel("✕");
        closeBtn.setPreferredSize(new Dimension(45, 43));
        closeBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        closeBtn.setHorizontalAlignment(SwingConstants.CENTER);
        closeBtn.setVerticalAlignment(SwingConstants.CENTER);
        closeBtn.setForeground(new Color(118, 118, 118));
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


        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(closeBtn);
        Menu.add(rightPanel, BorderLayout.EAST);
        newProject.add(Menu, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JLabel pathLabel = new JLabel(ppath);

        JTextField pathField = new JTextField(40); 
        String projectPath = pathField.getText();
        centerPanel.add(pathLabel);
        centerPanel.add(pathField);
        newProject.add(centerPanel);

        JPanel rightPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 100, 20));
        rightPanel2.setOpaque(false);
        JLabel finishBtn = new JLabel("Finish");
        finishBtn.setPreferredSize(new Dimension(90, 30));
        finishBtn.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        finishBtn.setForeground(new Color(193, 199, 193));
        finishBtn.setOpaque(true);
        finishBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        finishBtn.setHorizontalAlignment(SwingConstants.CENTER);
        finishBtn.setVerticalAlignment(SwingConstants.CENTER);
        finishBtn.setBackground(new Color(43, 43, 43));
        finishBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 newProject.setVisible(false);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                finishBtn.setBackground(new Color(4, 71, 6));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                finishBtn.setBackground(new Color(43, 43, 43));
            }
        });
        rightPanel2.add(finishBtn);
        newProject.add(rightPanel2, BorderLayout.SOUTH);



    }

}