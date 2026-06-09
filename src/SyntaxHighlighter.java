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
    private Style typeStyle;
    private Style paramStyle;

    private void createStyles() {
        normalStyle = doc.addStyle("normal", null);
        StyleConstants.setForeground(normalStyle, new Color(218, 218, 218));

        tesztStyle = doc.addStyle("brace", null);
        StyleConstants.setForeground(tesztStyle, new Color(120, 120, 200));

        typeStyle = doc.addStyle("types", null);
        StyleConstants.setForeground(typeStyle, new Color(150, 220, 150));

        numberStyle = doc.addStyle("number", null);
        StyleConstants.setForeground(numberStyle, new Color(75, 220, 165));

        paramStyle = doc.addStyle("param", null);
        StyleConstants.setForeground(paramStyle, new Color(170, 60, 150));


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


            Matcher zarojel = Pattern.compile("\\{").matcher(text);
            while (zarojel.find()) {
                doc.setCharacterAttributes(zarojel.start(), zarojel.end() - zarojel.start(), tesztStyle, false);
            }

            Matcher zarojel2 = Pattern.compile("\\}").matcher(text);
            while (zarojel2.find()) {
                doc.setCharacterAttributes(zarojel2.start(), zarojel2.end() - zarojel2.start(), tesztStyle, false);
            }

            Matcher vesszo = Pattern.compile("\\;").matcher(text);
            while (vesszo.find()) {
                doc.setCharacterAttributes(vesszo.start(), vesszo.end() - vesszo.start(), tesztStyle, false);
            }
            
            Matcher v = Pattern.compile("\\bvoid\\b").matcher(text);
            while (v.find()) {
                doc.setCharacterAttributes(v.start(), v.end() - v.start(), typeStyle, false);
            }

            Matcher l = Pattern.compile("\\bloop\\b").matcher(text);
            while (l.find()) {
                doc.setCharacterAttributes(l.start(), l.end() - l.start(), typeStyle, false);
            }

            Matcher input = Pattern.compile("\\bIN\\b").matcher(text);
            while (input.find()) {
                doc.setCharacterAttributes(input.start(), input.end() - input.start(), paramStyle, false);
            }

            Matcher output = Pattern.compile("\\bOUT\\b").matcher(text);
            while (output.find()) {
                doc.setCharacterAttributes(output.start(), output.end() - output.start(), paramStyle, false);
            }
    

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
