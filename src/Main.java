import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File; 
import java.util.Scanner;
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Main {

    //LANGUAGE SCANNER
    public static String getValue(String key) throws Exception {
        Scanner sc = new Scanner(new File("../lang/lang.json")); 
        String value = "";
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.startsWith("\"" + key + "\"")) {
                int firstQuote = line.indexOf("\"", key.length() + 2);
                int secondQuote = line.indexOf("\"", firstQuote + 1);
                value = line.substring(firstQuote + 1, secondQuote);
                break;
            }
        }
        sc.close();
        return value;
    }

    public static void main(String[] args) throws Exception {

        // STRINGLIST
        String mtitle = getValue("Title");
        String mfile = getValue("File");

     



        JFrame window = new JFrame();
        window.setTitle(mtitle);
        window.setSize(1080, 720);
        window.setUndecorated(true); 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        window.setBackground(new Color(60, 60, 60));


        

                final int[] mouseOffset = new int[2];
                JPanel Menu = new JPanel();
                Menu.setBackground(new Color(43, 43, 43));
                Menu.setPreferredSize(new Dimension(1080, 35));
                Menu.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 7));


                Menu.add(Box.createRigidArea(new Dimension(10, 0)));
                ImageIcon icon = new ImageIcon("../gui/logo.png");
                Image img = icon.getImage(); 
                Image scaledImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImg);
                JLabel logo = new JLabel(scaledIcon);
                Menu.add(logo);


                Menu.add(Box.createRigidArea(new Dimension(15, 0)));
                JLabel label = new JLabel(mfile); 
                label.setFont(new Font("Arial", Font.PLAIN, 15));
                label.setForeground(new Color(118, 118, 118));
                Menu.add(label);
                Menu.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        mouseOffset[0] = e.getX();
                        mouseOffset[1] = e.getY();
                    }
                });
                Menu.addMouseMotionListener(new MouseMotionAdapter() {
                    public void mouseDragged(MouseEvent e) {
                        window.setLocation(e.getXOnScreen() - mouseOffset[0], e.getYOnScreen() - mouseOffset[1]);
                    }
                }); 

                window.add(Menu, BorderLayout.NORTH);
        



                JPanel line1 = new JPanel();
                line1.setBackground(new Color(255, 0, 0));
                line1.setPreferredSize(new Dimension(1080, 2));
                window.add(line1);

     
        window.setVisible(true);

    }
}