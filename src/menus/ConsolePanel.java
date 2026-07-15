package menus;

import javax.swing.*;
import java.awt.*;
import util.CustomScrollBarUI;
import util.LanguageManager;


public class ConsolePanel  {

    private JTextArea terminal;

     String terminalname = LanguageManager.get("Terminal");

    public void init(JPanel settings) throws Exception {

        settings.setLayout(new BorderLayout());
        settings.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 5));

        JLabel terminalp = new JLabel(terminalname) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                FontMetrics fm = g.getFontMetrics();
                int textWidth = fm.stringWidth(getText());
                int underlineY = fm.getAscent() + 3; 
                int startX = getInsets().left;

                g.setColor(new Color(0, 148, 0)); 
                g.drawLine(startX, underlineY, startX + textWidth, underlineY);
            }
        };
        terminalp.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        terminalp.setFont(new Font("Arial", Font.PLAIN, 18));
        terminalp.setForeground(new Color(118, 118, 118));
        settings.add(terminalp, BorderLayout.NORTH);


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