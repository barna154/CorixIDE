import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class LineNumberComponent extends JComponent {

    private final JTextArea textArea;
    private final int padding = 8;
    private final int fixedWidth = 50;

    public LineNumberComponent(JTextArea textArea) {
        this.textArea = textArea;

        setFont(new Font("JetBrains Mono", Font.PLAIN, 16));
        setForeground(new Color(120, 120, 120));
        setBackground(new Color(30, 30, 30));

        textArea.addCaretListener(e -> repaint());
        textArea.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                repaint();
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(fixedWidth, Integer.MAX_VALUE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        Rectangle visible = textArea.getVisibleRect();

        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), visible.height);

        g2.setColor(getForeground());
        FontMetrics fm = textArea.getFontMetrics(textArea.getFont());

        Element root = textArea.getDocument().getDefaultRootElement();

        int startLine = root.getElementIndex(textArea.viewToModel2D(new Point(0, visible.y)));
        int endLine = root.getElementIndex(textArea.viewToModel2D(
                new Point(0, visible.y + visible.height)));

        int lineHeight = fm.getHeight();

        for (int i = startLine; i <= endLine; i++) {

            Element line = root.getElement(i);

            Rectangle r;
            try {
                r = textArea.modelToView2D(line.getStartOffset()).getBounds();
            } catch (Exception ex) {
                continue;
            }

            int y = (i - startLine) * lineHeight + fm.getAscent();

            String text = String.valueOf(i + 1);

            int x = fixedWidth - fm.stringWidth(text) - padding;

            g2.drawString(text, x, y);
        }
    }
}