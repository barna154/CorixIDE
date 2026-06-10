import javax.swing.*;
import java.awt.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File; 
import java.util.Scanner;

public class FileExplorer {

    public void init(JPanel filePanel String[] args) throws Exception {
        LanguageManager.load("../lang/lang.json");
        
        String sourcecont = LanguageManager.get("Explorer");

        filePanel.setLayout(new BorderLayout());


        JLabel sourcecon = new JLabel(sourcecont);
        sourcecon.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        sourcecon.setFont(new Font("Arial", Font.PLAIN, 18));
        sourcecon.setForeground(new Color(118, 118, 118));
        filePanel.add(sourcecon, BorderLayout.CENTER);
    }

}