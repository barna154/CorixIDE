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

        Rectangle visible = textArea.getVisibleRect();

        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(getForeground());

        Element root = textArea.getDocument().getDefaultRootElement();

        int startOffset = textArea.viewToModel2D(new Point(0, visible.y));
        int endOffset = textArea.viewToModel2D(new Point(0, visible.y + visible.height));

        int startLine = root.getElementIndex(startOffset);
        int endLine = root.getElementIndex(endOffset);

        for (int i = startLine; i <= endLine; i++) {

            Element line = root.getElement(i);

            try {
                Rectangle2D r = textArea.modelToView2D(line.getStartOffset());
                if (r == null) continue;

                FontMetrics fm = textArea.getFontMetrics(textArea.getFont());

                int x = fixedWidth - fm.stringWidth(String.valueOf(i + 1)) - padding;
                int y = (int) (r.getY() - visible.y + fm.getAscent());

                g2.drawString(String.valueOf(i + 1), x, y);

            } catch (Exception ignored) {}
        }
    }
}
