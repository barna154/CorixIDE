import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.awt.Dimension;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class LineNumberComponent extends JComponent {

    private final JTextArea textArea;
    private final int padding = 8;
    private final int fixedWidth = 60;
    private final int rightGap = 12;

    public LineNumberComponent(JTextArea textArea) {
        this.textArea = textArea;

        setOpaque(true);
        setFont(textArea.getFont());
        setForeground(new Color(120, 120, 120));
        setBackground(new Color(30, 30, 30));


        DocumentListener dl = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { revalidate(); repaint(); }
            public void removeUpdate(DocumentEvent e) { revalidate(); repaint(); }
            public void changedUpdate(DocumentEvent e) { revalidate(); repaint(); }
        };

        textArea.getDocument().addDocumentListener(dl);
        textArea.addCaretListener(e -> repaint());
    }

    @Override
    public Dimension getPreferredSize() {
        int height = textArea.getPreferredSize().height;
        return new Dimension(fixedWidth, height);
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        return new Dimension(8, 60); // 60 = maximum magasság pixelben
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

        Rectangle clip = g.getClipBounds();
        Element root = textArea.getDocument().getDefaultRootElement();

        int startOffset = textArea.viewToModel2D(new Point(0, clip.y));
        int startLine = root.getElementIndex(startOffset);
        int totalLines = textArea.getLineCount();

        FontMetrics fm = textArea.getFontMetrics(textArea.getFont());

        int caretPos = textArea.getCaretPosition();
        int currentLine = root.getElementIndex(caretPos);

        for (int line = startLine; line < totalLines; line++) {
            Element elem = root.getElement(line);
            try {
                Rectangle2D r = textArea.modelToView2D(elem.getStartOffset());
                if (r == null) continue;
                if (r.getY() > clip.y + clip.height) break;

                String lineNumber = String.valueOf(line + 1);
                int textWidth = fm.stringWidth(lineNumber);
                int x = fixedWidth - textWidth - padding - rightGap;
                int y = (int) (r.getY() + fm.getAscent());

           
                if (line == currentLine) {
                    g2.setColor(new Color(60, 125, 60)); 
                } else {
                    g2.setColor(getForeground());
                }

                g2.drawString(lineNumber, x, y);

            } catch (Exception ignored) {}
        }
    }
}