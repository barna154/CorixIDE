import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import org.json.JSONArray;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws Exception {

        // JSON beolvasása
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