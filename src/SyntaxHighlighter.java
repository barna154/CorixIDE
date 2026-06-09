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
    private Style tesztStyle;

    private void createStyles() {
        normalStyle = doc.addStyle("normal", null);
        StyleConstants.setForeground(normalStyle, new Color(218, 218, 218));

        tesztStyle = doc.addStyle("teszt", null);
        StyleConstants.setForeground(tesztStyle, new Color(255, 120, 120));

        numberStyle = doc.addStyle("number", null);
        StyleConstants.setForeground(numberStyle, new Color(75, 220, 165));
    }

    private void highlightSafe() {
        if (updating) return; 

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

    
            doc.setCharacterAttributes(0, text.length(), normalStyle, false);


            Matcher m = Pattern.compile("\\b\\d+\\b").matcher(text);
            while (m.find()) {
                doc.setCharacterAttributes(m.start(), m.end() - m.start(), numberStyle, false);
            }


            Matcher teszt = Pattern.compile("\\bteszt\\b").matcher(text);
            while (teszt.find()) {
                doc.setCharacterAttributes(teszt.start(), teszt.end() - teszt.start(), tesztStyle, false);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
