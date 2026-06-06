import javax.swing.*;
import java.awt.*;

public class LineNumberComponent extends JComponent {

    private final JTextArea textArea;
    private final int padding = 8;
    private final int fixedWidth = 50; // fix szélesség, nem fog ugrálni

    public LineNumberComponent(JTextArea textArea) {
        this.textArea = textArea;

        setFont(new Font("Consolas", Font.PLAIN, 16));
        setForeground(new Color(120, 120, 120));
        setBackground(new Color(30, 30, 30));

        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(fixedWidth, textArea.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(getForeground());
        FontMetrics fm = g.getFontMetrics();

        int lineHeight = textArea.getFontMetrics(textArea.getFont()).getHeight();
        int ascent = textArea.getFontMetrics(textArea.getFont()).getAscent();

        int startY = -textArea.getInsets().top + ascent;
        int visibleLines = getHeight() / lineHeight + 1;

        for (int i = 0; i < visibleLines; i++) {
            int lineIndex = i + getFirstVisibleLine();
            if (lineIndex >= textArea.getLineCount()) break;

            String lineNumber = String.valueOf(lineIndex + 1);

            // JOBBRA igazítás
            int textWidth = fm.stringWidth(lineNumber);
            int x = fixedWidth - textWidth - padding;

            int y = startY + i * lineHeight;
            g.drawString(lineNumber, x, y);
        }
    }

    private int getFirstVisibleLine() {
        int scroll = textArea.getVisibleRect().y;
        int lineHeight = textArea.getFontMetrics(textArea.getFont()).getHeight();
        return scroll / lineHeight;
    }
}
