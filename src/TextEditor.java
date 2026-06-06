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

    JScrollPane scroll = new JScrollPane(textArea);
    scroll.setBorder(null);
    scroll.getViewport().setBackground(new Color(30,30,30));

    LineNumberComponent lineNumbers = new LineNumberComponent(textArea);
    scroll.setRowHeaderView(lineNumbers);

    // ha mégis kell frissítés:
    textArea.addCaretListener(e -> scroll.getRowHeader().repaint());

    textArea.addPropertyChangeListener("font",
        e -> scroll.getRowHeader().repaint()
    );

    editorPanel.add(scroll, BorderLayout.CENTER);
}
}