import javax.swing.*;
import java.awt.*;

public class TextEditor {

    public void init(JPanel editorPanel) {

        JTextPane textComponent = new JTextPane();

        textComponent.setBackground(new Color(30, 30, 30));
        textComponent.setForeground(new Color(218, 218, 218));
        textComponent.setCaretColor(Color.GRAY);
        textComponent.setFont(new Font("Consolas", Font.PLAIN, 17));
        textComponent.setSelectionColor(new Color(40, 60, 40));     
        textComponent.setSelectedTextColor(new Color(218, 218, 218));

        JScrollPane scroll = new JScrollPane(textComponent);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(30, 30, 30));
        scroll.setRowHeaderView(new LineNumberComponent(textComponent));

        scroll.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(18, 0));
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 18));
        scroll.setCorner(JScrollPane.LOWER_RIGHT_CORNER, createDarkPanel());
        scroll.setCorner(JScrollPane.LOWER_LEFT_CORNER, createDarkPanel());
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, createDarkPanel());



        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(scroll, BorderLayout.CENTER);
    }
    private JPanel createDarkPanel() {
    JPanel p = new JPanel();
    p.setBackground(new Color(30, 30, 30));
    return p;
    }
}

