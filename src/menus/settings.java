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


public class settings {

        public void init(JPanel settings) throws Exception {  


                String settingstext = LanguageManager.get("Options");
   

                settings.setLayout(new BorderLayout());


                final int[] mouseOffset = new int[2];
                JPanel Menu = new JPanel(new BorderLayout());
                Menu.setBackground(new Color(43, 43, 43));

                JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
                leftPanel.setOpaque(false);



                JLabel labelFile = new JLabel(settingstext);
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
                        findReplace.setVisible(false);
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


        }
}