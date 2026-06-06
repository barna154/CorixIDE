import javax.swing.*;
import java.awt.*;

public class LineNumberComponent extends JComponent {

    private final JTextArea textArea;
    private final int padding = 8;

    public LineNumberComponent(JTextArea textArea) {
        this.textArea = textArea;

        setFont(new Font("Consolas", Font.PLAIN, 16));
        setForeground(new Color(120, 120, 120));
        setBackground(new Color(30, 30, 30));

        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { repaint(); revalidate(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { repaint(); revalidate(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { repaint(); revalidate(); }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        int lineCount = textArea.getLineCount();
        int width = getFontMetrics(getFont()).stringWidth(String.valueOf(lineCount)) + padding * 2;
        return new Dimension(width, textArea.getHeight());
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
            int y = startY + i * lineHeight;

            g.drawString(lineNumber, padding, y);
        }
    }

    private int getFirstVisibleLine() {
        int scroll = textArea.getVisibleRect().y;
        int lineHeight = textArea.getFontMetrics(textArea.getFont()).getHeight();
        return scroll / lineHeight;
    }
}
