import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class TextEditor {

    public void init(JPanel editorPanel) {

        JTextArea textArea = new JTextArea();

        textArea.setBackground(new Color(30,30,30));
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);

        JScrollPane scroll = new JScrollPane(textArea);

        editorPanel.add(scroll, BorderLayout.CENTER);
    }
}