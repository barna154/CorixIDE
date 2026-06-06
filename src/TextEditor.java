import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;


public class TextEditor {

    public void init(JPanel editorPanel) {

        JTextArea textArea = new JTextArea();

        textArea.setBackground(new Color(30,30,30));
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBorder(new EmptyBorder(1, 1, 1, 1));

        editorPanel.add(scroll, BorderLayout.CENTER);
    }
}