import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

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

        Rectangle clip = g.getClipBounds();
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(getForeground());
        FontMetrics fm = g.getFontMetrics();

        int startOffset = textArea.viewToModel2D(new Point(0, clip.y));
        int endOffset = textArea.viewToModel2D(new Point(0, clip.y + clip.height));

        Element root = textArea.getDocument().getDefaultRootElement();
        int startLine = root.getElementIndex(startOffset);
        int endLine = root.getElementIndex(endOffset);

        for (int line = startLine; line <= endLine; line++) {
            try {
                Rectangle2D r = textArea.modelToView2D(
                        root.getElement(line).getStartOffset()
                );

                if (r != null) {
                    String lineNumber = String.valueOf(line + 1);
                    int x = padding;
                    int y = (int) (r.getY() + r.getHeight() - fm.getDescent());
                    g.drawString(lineNumber, x, y);
                }

            } catch (Exception ignored) {}
        }
    }
}
