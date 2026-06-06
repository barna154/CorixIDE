import javax.swing.*;
import java.awt.*;

public class TextEditor {

    public void init(JPanel editorPanel) {

        JTextArea textArea = new JTextArea();

        textArea.setBackground(new Color(30, 30, 30));
        textArea.setForeground(new Color(218, 218, 218));
        textArea.setCaretColor(Color.GRAY);
        textArea.setFont(new Font("Consolas", Font.PLAIN, 17));

        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(30, 30, 30));
        scroll.setRowHeaderView(new LineNumberComponent(textArea));

        scroll.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(20, 0));
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 4));

        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(scroll, BorderLayout.CENTER);
    }
}