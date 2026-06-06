import javax.swing.*;
import java.awt.*;

public class LineNumberComponent extends JComponent {

    private final JTextArea textArea;
    private final int padding = 5;

    public LineNumberComponent(JTextArea textArea) {
        this.textArea = textArea;
        setFont(new Font("Consolas", Font.PLAIN, 16));
        setForeground(new Color(120, 120, 120));
        setBackground(new Color(30, 30, 30));
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
        int start = textArea.viewToModel(new Point(0, clip.y));
        int end = textArea.viewToModel(new Point(0, clip.y + clip.height));

        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(getForeground());
        FontMetrics fm = g.getFontMetrics();

        int startLine = textArea.getDocument().getDefaultRootElement().getElementIndex(start);
        int endLine = textArea.getDocument().getDefaultRootElement().getElementIndex(end);

        for (int line = startLine; line <= endLine; line++) {
            try {
                Rectangle r = textArea.modelToView(
                        textArea.getDocument().getDefaultRootElement().getElement(line).getStartOffset()
                );
                if (r != null) {
                    String lineNumber = String.valueOf(line + 1);
                    int x = padding;
                    int y = r.y + r.height - fm.getDescent();
                    g.drawString(lineNumber, x, y);
                }
            } catch (Exception ignored) {}
        }
    }
}