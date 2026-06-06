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

        setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
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

    Rectangle visible = textArea.getVisibleRect();
    g.setColor(getBackground());
    g.fillRect(0, 0, getWidth(), visible.height);

    g.setColor(getForeground());
    FontMetrics fm = g.getFontMetrics();

    Element root = textArea.getDocument().getDefaultRootElement();

    int startOffset = textArea.viewToModel2D(new Point(0, visible.y));
    int endOffset = textArea.viewToModel2D(new Point(0, visible.y + visible.height));

    int startLine = root.getElementIndex(startOffset);
    int endLine = root.getElementIndex(endOffset);

    for (int i = startLine; i <= endLine; i++) {
        Element lineElem = root.getElement(i);

        try {
            Rectangle2D r = textArea.modelToView2D(lineElem.getStartOffset());
            if (r == null) continue;

            String text = String.valueOf(i + 1);

            int x = fixedWidth - fm.stringWidth(text) - padding;
            int y = (int) (r.getY() - visible.y + fm.getAscent());

            g.drawString(text, x, y);

        } catch (Exception ignored) {}
    }
}
 
