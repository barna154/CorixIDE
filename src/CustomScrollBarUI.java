import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import javax.swing.text.JTextComponent;

public class CustomScrollBarUI extends BasicScrollBarUI {

    private final Color thumbColor = new Color(80, 80, 80);
    private final Color trackColor = new Color(30, 30, 30);
    private final int thumbArc = 6;

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(trackColor);
        g2.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(thumbColor);

        g2.fillRoundRect(
            thumbBounds.x + 2,
            thumbBounds.y + 2,
            thumbBounds.width - 4,
            thumbBounds.height - 4,
            thumbArc, thumbArc
        );

        g2.dispose();
    }

    @Override
    protected Dimension getMaximumThumbSize() {
        return new Dimension(8, 60); // 60 = maximum magasság pixelben
    }
    
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return createInvisibleButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return createInvisibleButton();
    }

    private JButton createInvisibleButton() {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(0, 0));
        btn.setMinimumSize(new Dimension(0, 0));
        btn.setMaximumSize(new Dimension(0, 0));
        return btn;
    }
}