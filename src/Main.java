import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File; 
import java.util.Scanner;
import javax.swing.JFrame;
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
        String title = getValue("Title");

     



        JFrame window = new JFrame();
        window.setTitle(title);
        window.setSize(1080, 720);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel Menu = new JPanel();
        Menu.setLayout(new BoxLayout(Menu, BoxLayout.X_AXIS));
        Menu.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon("../gui/logo.png");
        Image img = icon.getImage(); 
        Image scaledImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        JLabel logo = new JLabel(scaledIcon);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        Menu.add(logo);

        JLabel label = new JLabel("Helló világ!", JLabel.CENTER); 
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setForeground(Color.BLUE);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        Menu.add(label);

        // Panel hozzáadása az ablakhoz
        window.add(Menu);
        window.setVisible(true);

    }
}