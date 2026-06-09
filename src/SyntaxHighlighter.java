import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.*;

public class SyntaxHighlighter {

    private final JTextPane textPane;
    private final StyledDocument doc;
    private boolean updating = false;

    public SyntaxHighlighter(JTextPane textPane) {
        this.textPane = textPane;
        this.doc = textPane.getStyledDocument();

        textPane.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { highlightSafe(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { highlightSafe(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { highlightSafe(); }
        });

        createStyles();
    }

    private Style numberStyle;
    private Style normalStyle;

    private void createStyles() {
        normalStyle = doc.addStyle("normal", null);
        StyleConstants.setForeground(normalStyle, new Color(218, 218, 218));

        numberStyle = doc.addStyle("number", null);
        StyleConstants.setForeground(numberStyle, new Color(120, 170, 255));
    }

    private void highlightSafe() {
        if (updating) return; // 🔥 megakadályozza a végtelen ciklust

        updating = true;
        SwingUtilities.invokeLater(() -> {
            try {
                highlight();
            } finally {
                updating = false;
            }
        });
    }

    private void highlight() {
        try {
            String text = doc.getText(0, doc.getLength());

            // mindent vissza normálra
            doc.setCharacterAttributes(0, text.length(), normalStyle, false);

            // számok
            Matcher m = Pattern.compile("\\b\\d+\\b").matcher(text);
            while (m.find()) {
                doc.setCharacterAttributes(m.start(), m.end() - m.start(), numberStyle, false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
