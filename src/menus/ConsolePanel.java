package menus;

import javax.swing.*;
import java.awt.*;
import util.CustomScrollBarUI;

public class ConsolePanel  {

    public void init(JPanel settings) throws Exception {

        JTextArea terminal = new JTextArea();
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
}