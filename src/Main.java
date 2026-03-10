import java.awt.*;
import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws Exception {

        // JSON fájl beolvasása
        String content = new String(Files.readAllBytes(Paths.get("lang/lang.json")));
        JSONObject obj = new JSONObject(content);

        String Title = obj.getString("Title");

        // JFrame létrehozása
        JFrame window = new JFrame();
        window.setTitle(Title);
        window.setSize(400, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}