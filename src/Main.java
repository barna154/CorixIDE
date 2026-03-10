import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
     static String json = Paths.get("lang/lang.json");

    public static void main(String[] args) {

        JSONObject obj = new JSONObject(json);
        String Title = obj.getString("Title");

        JFrame window = new JFrame();

        window.setTitle(Title);
        window.setSize(400,300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.setVisible(true);
    }
}