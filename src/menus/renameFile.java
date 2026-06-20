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
import java.io.FileWriter;
import util.LanguageManager;
import java.io.IOException;
import util.AppPath;



public class renameFile {

    public void init(JPanel renameFile, File targetFile, Runnable onSuccess) throws Exception {       

        String newp = LanguageManager.get("Rename File");
        String ppath = LanguageManager.get("Project Path");
        String npan = LanguageManager.get("New name");
        String fnsbtn = LanguageManager.get("Rename Button");


        renameFile.setLayout(new BorderLayout());


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
                 renameFile.setVisible(false);
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
        renameFile.add(Menu, BorderLayout.NORTH);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
        centerPanel.setBackground(new Color(50, 55, 50));


        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.setPreferredSize(new Dimension(renameFile.getWidth(), 50));
        row1.setMaximumSize(row1.getPreferredSize());
        row1.setBackground(centerPanel.getBackground());


        JLabel namePanel = new JLabel(npan);
        namePanel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        namePanel.setPreferredSize(new Dimension(75, 25));
        namePanel.setForeground(new Color(225, 225, 225));
        JTextField pathField = new JTextField(20);
        pathField.setBackground(new Color(30, 30, 30));
        pathField.setForeground(new Color(200, 200, 200));
        pathField.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 3));
        pathField.setText(targetFile.getName());
        pathField.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
        String projectPath = pathField.getText();



        row1.add(namePanel);
        row1.add(pathField);
        centerPanel.add(row1);
        renameFile.add(centerPanel);

        JPanel rightPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        rightPanel2.setOpaque(false);
        JLabel finishBtn = new JLabel(fnsbtn);
        finishBtn.setPreferredSize(new Dimension(100, 30));
        finishBtn.setFont(new Font("Segoe UI Emoji", Font.BOLD, 17));
        finishBtn.setForeground(new Color(230, 230, 230));
        finishBtn.setOpaque(true);
        finishBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        finishBtn.setHorizontalAlignment(SwingConstants.CENTER);
        finishBtn.setVerticalAlignment(SwingConstants.CENTER);
        finishBtn.setBackground(new Color(43, 43, 43));
        finishBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                renameFile.setVisible(false);
                String newName = pathField.getText().trim();
                
                    if (newName.isEmpty()) return;

                        File renamed = new File(targetFile.getParentFile(), newName);
                        boolean success = targetFile.renameTo(renamed);

                        if (success) {
                            renameFile.setVisible(false); 
                            if (onSuccess != null) onSuccess.run();
                        } else {
                                System.out.println("Error renameing File!");
                        }


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
        renameFile.setCursor(Cursor.getDefaultCursor());
        renameFile.add(rightPanel2, BorderLayout.SOUTH);



    }

}