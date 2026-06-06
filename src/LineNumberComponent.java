import javax.swing.*;
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

        // viewport lekérése
        JViewport viewport = (JViewport) getParent();
        Rectangle view = viewport.getViewRect();

        // első látható sor
        int firstLine = view.y / lineHeight;

        // utolsó látható sor (NEM viewport magasság alapján!)
        int lastLine = Math.min(
                textArea.getLineCount() - 1,
                firstLine + (view.height / lineHeight) + 1
        );

        for (int lineIndex = firstLine; lineIndex <= lastLine; lineIndex++) {

            String lineNumber = String.valueOf(lineIndex + 1);

            // jobbra igazítás
            int textWidth = fm.stringWidth(lineNumber);
            int x = fixedWidth - textWidth - padding;

            // pontos Y pozíció
            int y = (lineIndex * lineHeight) - view.y + ascent;

            g.drawString(lineNumber, x, y);
        }
    }
}
