package menus;

import javax.swing.*;
import java.awt.*;
import util.CustomScrollBarUI;

public class ConsolePanel  {

    private JTextArea terminal;

    public void init(JPanel settings) throws Exception {

        terminal = new JTextArea();
        terminal.setEditable(false);
        terminal.setBackground(new Color(23, 24, 23));
        terminal.setForeground(Color.WHITE);
        terminal.setFont(new Font("Consolas", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(terminal);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(23, 24, 23));

        scroll.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(18, 0));
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 18));

        settings.setLayout(new BorderLayout());
        settings.add(scroll, BorderLayout.CENTER);
    }
     public JTextArea getTerminal() {
        return terminal;
    }

    public void print(String text) {
        terminal.append(text);
        terminal.setCaretPosition(terminal.getDocument().getLength());
    }

    public void println(String text) {
        print(text + "\n");
    }

    public void clear() {
        terminal.setText("");
    }
}