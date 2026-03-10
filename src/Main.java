import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        // JSON fájl beolvasása sztringként
        String content = new String(Files.readAllBytes(Paths.get("lang/lang.json")));

        // Egyszerű keresés a "Title" kulcshoz
        String key = "\"Title\":";
        int start = content.indexOf(key);
        int end = content.indexOf("\"", start + key.length() + 1);
        String title = content.substring(start + key.length() + 2, end); // 2 = idézőjel és szóköz

        // JFrame létrehozása
        JFrame window = new JFrame();
        window.setTitle(title);
        window.setSize(400, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}