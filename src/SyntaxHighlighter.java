import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.*;

public class SyntaxHighlighter {

    private final JTextPane textPane;
    private final StyledDocument doc;

    private boolean updating = false;
    private Timer timer;

    private Style normalStyle;
    private Style numberStyle;
    private Style braceStyle;
    private Style keywordStyle;
    private Style paramStyle;
    private Style boolStyle;
    private Style commentStyle;

    public SyntaxHighlighter(JTextPane textPane) {
        this.textPane = textPane;
        this.doc = textPane.getStyledDocument();

        createStyles();

        // 🔥 debounce timer (nem minden keypress-re fut)
        timer = new Timer(150, e -> highlight());
        timer.setRepeats(false);

        doc.addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { schedule(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { schedule(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { schedule(); }
        });
    }

    private void schedule() {
        if (updating) return;
        timer.restart();
    }

    private void createStyles() {

        normalStyle = doc.addStyle("normal", null);
        StyleConstants.setForeground(normalStyle, new Color(218, 218, 218));

        numberStyle = doc.addStyle("number", null);
        StyleConstants.setForeground(numberStyle, new Color(75, 220, 165));

        braceStyle = doc.addStyle("brace", null);
        StyleConstants.setForeground(braceStyle, new Color(120, 120, 200));

        keywordStyle = doc.addStyle("keyword", null);
        StyleConstants.setForeground(keywordStyle, new Color(150, 220, 150));

        paramStyle = doc.addStyle("param", null);
        StyleConstants.setForeground(paramStyle, new Color(170, 60, 150));

        boolStyle = doc.addStyle("bool", null);
        StyleConstants.setForeground(boolStyle, new Color(50, 50, 200));

        commentStyle = doc.addStyle("comment", null);
        StyleConstants.setForeground(commentStyle, new Color(150, 150, 150));
    }

    private void highlight() {
        if (updating) return;

        updating = true;

        try {
            String text = doc.getText(0, doc.getLength());

            // 🔥 reset
            doc.setCharacterAttributes(0, text.length(), normalStyle, false);

            apply("\\b\\d+\\b", text, numberStyle);
            apply("\\{|\\}", text, braceStyle);
            apply("\\b(setup|loop|config)\\b", text, keywordStyle);
            apply("\\b(IN|OUT)\\b", text, paramStyle);
            apply("\\b(TRUE|FALSE)\\b", text, boolStyle);

            // comments
            String[] lines = text.split("\n");
            int pos = 0;

            for (String line : lines) {
                if (line.startsWith("//")) {
                    doc.setCharacterAttributes(pos, line.length(), commentStyle, false);
                }
                pos += line.length() + 1;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            updating = false;
        }
    }

    private void apply(String regex, String text, Style style) {
        Matcher m = Pattern.compile(regex).matcher(text);
        while (m.find()) {
            doc.setCharacterAttributes(m.start(), m.end() - m.start(), style, false);
        }
    }
}