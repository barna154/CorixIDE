import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
 

        File file = new File("../lang/lang.json"); // igazítsd az útvonalat
        Scanner sc = new Scanner(file);
        String title = "";

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.startsWith("\"Title\"")) {
                // Kivágjuk a "Title": "..." részt
                int firstQuote = line.indexOf("\"", 8); // az első idézőjel a kulcs után
                int secondQuote = line.indexOf("\"", firstQuote + 1);
                title = line.substring(firstQuote + 1, secondQuote);
                break;
            }
        }
        sc.close();

        // JFrame létrehozása
        JFrame window = new JFrame();
        window.setTitle(title);
        window.setSize(400, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}