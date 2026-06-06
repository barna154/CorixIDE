import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class LineNumberComponent extends JComponent {

    private final JTextArea textArea;
    private final int padding = 8;
    private final int fixedWidth = 50;

    public LineNumberComponent(JTextArea textArea) {
        this.textArea = textArea;

        setOpaque(true);
        setFont(textArea.getFont());
        setForeground(new Color(120, 120, 120));
        setBackground(new Color(30, 30, 30));

        textArea.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { repaint(); }
        });

        textArea.addCaretListener(e -> repaint());
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(fixedWidth, textArea.getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                            RenderingHints.VALUE_FRACTIONALMETRICS_OFF);

        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(getForeground());

        Rectangle visible = textArea.getVisibleRect();
        Element root = textArea.getDocument().getDefaultRootElement();

        int startOffset = textArea.viewToModel2D(new Point(0, visible.y));
        int startLine = root.getElementIndex(startOffset);

        int totalLines = textArea.getLineCount();
        FontMetrics fm = textArea.getFontMetrics(textArea.getFont());

        for (int line = startLine; line < totalLines; line++) {

            Element elem = root.getElement(line);

            try {
                Rectangle2D r = textArea.modelToView2D(elem.getStartOffset());
                if (r == null) continue;

                // ha a sor már nem látszik → kilépünk
                if (r.getY() > visible.y + visible.height) break;

                String lineNumber = String.valueOf(line + 1);

                int textWidth = fm.stringWidth(lineNumber);
                int x = fixedWidth - textWidth - padding;
                int y = (int) (r.getY() - visible.y + fm.getAscent());

                g2.drawString(lineNumber, x, y);

            } catch (Exception ignored) {}
        }
    }
}
