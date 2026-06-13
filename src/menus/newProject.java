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
        String npan = LanguageManager.get("Project Name");
        String mcon = LanguageManager.get("Microcontroller");


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


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS)); 
        centerPanel.setBackground(new Color(80, 88, 80));


        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row1.setPreferredSize(new Dimension(newProject.getWidth(), 40));
        row1.setMaximumSize(row1.getPreferredSize());
        row1.setBackground(new Color(80, 88, 80));


        JLabel namePanel = new JLabel(npan);
        namePanel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        namePanel.setPreferredSize(new Dimension(150, 25));
        namePanel.setForeground(new Color(225, 225, 225));
        JTextField pathField = new JTextField(20);
        pathField.setBackground(new Color(200, 200, 200));
        pathField.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 3));
        pathField.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        String projectPath = pathField.getText();


        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        row2.setPreferredSize(new Dimension(newProject.getWidth(), 40));
        row2.setBackground(new Color(80, 88, 80));
        row2.setMaximumSize(row2.getPreferredSize());
        JLabel mconPanel = new JLabel(mcon);
        mconPanel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        mconPanel.setForeground(new Color(225, 225, 225));
        mconPanel.setPreferredSize(new Dimension(150, 25));

                    
            String[] microcontrollers = {"PIC16F15256", "PIC16F15274", "PIC16F15275", "PIC16F15276"};
            JComboBox<String> mconBox = new JComboBox<>(microcontrollers);
            mconBox.setPreferredSize(new Dimension(300, 30));
            mconBox.setBackground(new Color(30, 30, 30));
            mconBox.setForeground(new Color(230, 230, 230));
            mconBox.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
            mconBox.setBorder(BorderFactory.createLineBorder(new Color(20, 20, 20), 2));
            mconBox.setRenderer(new DefaultListCellRenderer() {
                        @Override
                        public Component getListCellRendererComponent(
                                JList<?> list,
                                Object value,
                                int index,
                                boolean isSelected,
                                boolean cellHasFocus) {

                            JLabel label = (JLabel) super.getListCellRendererComponent(
                                    list, value, index, isSelected, cellHasFocus);

                            label.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));

                            if (index == -1) {
                                label.setBackground(new Color(30, 30, 30));
                                label.setForeground(new Color(230, 230, 230));
                            } else if (isSelected) {
                                label.setBackground(new Color(60, 60, 60));
                                label.setForeground(new Color(230, 230, 230));
                            } else {
                                label.setBackground(new Color(30, 30, 30));
                                label.setForeground(new Color(230, 230, 230));
                            }

                            return label;
                        }
                    });
            mconBox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
                @Override
                public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                    g.setColor(new Color(30, 30, 30)); 
                    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                }
                @Override
                protected JButton createArrowButton() {
                    JButton button = new JButton("▼");
                    button.setBackground(new Color(30, 30, 30));
                    button.setForeground(new Color(230, 230, 230));
                    button.setBorder(BorderFactory.createEmptyBorder());
                    button.setFocusPainted(false);
                    button.setContentAreaFilled(true);
                    return button;
                }
            });
            String selectedMcu = (String) mconBox.getSelectedItem();

        


        row1.add(namePanel);
        row1.add(pathField);
        row2.add(mconPanel);
        row2.add(mconBox);
        centerPanel.add(row1);
        centerPanel.add(row2);
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