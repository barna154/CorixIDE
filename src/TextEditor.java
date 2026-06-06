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
        textArea.setFont(new Font("Consolas", Font.PLAIN, 17));

        textArea.addCaretListener(e -> repaint());

        textArea.addPropertyChangeListener("font", e -> repaint());

        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
        });
        

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBorder(new EmptyBorder(1, 1, 1, 1));
        scroll.setBorder(null);
        scroll.getViewport().setBorder(null);
        scroll.getViewport().setBackground(new Color(30,30,30));
        scroll.setRowHeaderView(new LineNumberComponent(textArea));

        editorPanel.add(scroll, BorderLayout.CENTER);
    }
}