import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File; 
import java.util.Scanner;
import javax.swing.JFrame;

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
        window.setVisible(true);


        ImageIcon icon = new ImageIcon("../gui/logo.png");
        JLabel label = new JLabel(icon);
        window.add(label);
    }
}