import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.*;

public class SyntaxHighlighter {

    private final JTextPane textPane;
    private final StyledDocument doc;

    private final Style normal;
    private final Style number;
    private final Style keyword;
    private final Style brace;

    private boolean updating = false;

    public SyntaxHighlighter(JTextPane pane) {
        this.textPane = pane;
        this.doc = pane.getStyledDocument();

        normal = doc.addStyle("n", null);
        StyleConstants.setForeground(normal, new Color(218, 218, 218));

        number = doc.addStyle("num", null);
        StyleConstants.setForeground(number, new Color(80, 220, 160));

        keyword = doc.addStyle("key", null);
        StyleConstants.setForeground(keyword, new Color(150, 220, 150));

        brace = doc.addStyle("br", null);
        StyleConstants.setForeground(brace, new Color(120, 120, 200));
    }

    public void highlightNow() {
        if (updating) return;

        updating = true;

        try {
            String text = doc.getText(0, doc.getLength());

            doc.setCharacterAttributes(0, text.length(), normal, false);

            apply("\\b\\d+\\b", text, number);
            apply("\\b(setup|loop|config)\\b", text, keyword);
            apply("\\{|\\}", text, brace);

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