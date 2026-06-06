import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class TextEditor {

    public void init(JPanel editorPanel) {

        JTextArea textArea = new JTextArea();

        textArea.setBackground(new Color(30,30,30));
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 18));
        

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBorder(new EmptyBorder(1, 1, 1, 1));
        scroll.setBorder(null);
        scroll.getViewport().setBorder(null);
        scroll.getViewport().setBackground(new Color(30,30,30));

        editorPanel.add(scroll, BorderLayout.CENTER);
    }
}