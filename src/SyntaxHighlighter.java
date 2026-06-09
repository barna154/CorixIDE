import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.*;

public class SyntaxHighlighter {

    private final JTextPane textPane;
    private final StyledDocument doc;

    public SyntaxHighlighter(JTextPane textPane) {
        this.textPane = textPane;
        this.doc = textPane.getStyledDocument();

        textPane.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { highlight(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { highlight(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { highlight(); }
        });

        createStyles();
    }

    private Style numberStyle;
    private Style normalStyle;

    private void createStyles() {
        normalStyle = doc.addStyle("normal", null);
        StyleConstants.setForeground(normalStyle, new Color(218, 218, 218));

        numberStyle = doc.addStyle("number", null);
        StyleConstants.setForeground(numberStyle, new Color(120, 170, 255)); // világoskék számok
    }

    private void highlight() {
        SwingUtilities.invokeLater(() -> {
            try {
                String text = doc.getText(0, doc.getLength());

                doc.setCharacterAttributes(0, text.length(), normalStyle, true);

                Matcher m = Pattern.compile("\\b\\d+\\b").matcher(text);
                while (m.find()) {
                    doc.setCharacterAttributes(m.start(), m.end() - m.start(), numberStyle, true);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }
}