package util;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.*;
import javax.swing.undo.UndoManager;
import editor.TextEditor;

public class SyntaxHighlighter {

    private final JTextPane textPane;
    private final StyledDocument doc;
    private final UndoManager undoManager;
    private boolean updating = false;

    public SyntaxHighlighter(JTextPane textPane, UndoManager undoManager) {
        this.undoManager = undoManager;
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
    private Style boolStyle;
    private Style fullLineStyle;

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

        boolStyle = doc.addStyle("bool", null);
        StyleConstants.setForeground(boolStyle, new Color(0, 143, 29));

        fullLineStyle = doc.addStyle("fullLine", null);
        StyleConstants.setForeground(fullLineStyle, new Color(150, 150, 150));  

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

            doc.removeUndoableEditListener(undoManager);


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
            
            Matcher v = Pattern.compile("\\bsetup\\b").matcher(text);
            while (v.find()) {
                doc.setCharacterAttributes(v.start(), v.end() - v.start(), typeStyle, false);
            }

            Matcher l = Pattern.compile("\\bloop\\b").matcher(text);
            while (l.find()) {
                doc.setCharacterAttributes(l.start(), l.end() - l.start(), typeStyle, false);
            }

            Matcher cf = Pattern.compile("\\bconfig\\b").matcher(text);
            while (cf.find()) {
                doc.setCharacterAttributes(cf.start(), cf.end() - cf.start(), typeStyle, false);
            }

            Matcher input = Pattern.compile("\\bIN\\b").matcher(text);
            while (input.find()) {
                doc.setCharacterAttributes(input.start(), input.end() - input.start(), paramStyle, false);
            }

            Matcher output = Pattern.compile("\\bOUT\\b").matcher(text);
            while (output.find()) {
                doc.setCharacterAttributes(output.start(), output.end() - output.start(), paramStyle, false);
            }

            Matcher trueb = Pattern.compile("\\bTRUE\\b").matcher(text);
            while (trueb.find()) {
                doc.setCharacterAttributes(trueb.start(), trueb.end() - trueb.start(), boolStyle, false);
            }

            Matcher falseb = Pattern.compile("\\bFALSE\\b").matcher(text);
            while (falseb.find()) {
                doc.setCharacterAttributes(falseb.start(), falseb.end() - falseb.start(), boolStyle, false);
            }

            Matcher highb = Pattern.compile("\\bHIGH\\b").matcher(text);
            while (highb.find()) {
                doc.setCharacterAttributes(highb.start(), highb.end() - highb.start(), boolStyle, false);
            }

            Matcher lowb = Pattern.compile("\\bLOW\\b").matcher(text);
            while (lowb.find()) {
                doc.setCharacterAttributes(lowb.start(), lowb.end() - lowb.start(), boolStyle, false);
            }

            Matcher osc1b = Pattern.compile("\\bIN1MHZ\\b").matcher(text);
            while (osc1b.find()) {
                doc.setCharacterAttributes(osc1b.start(), osc1b.end() - osc1b.start(), boolStyle, false);
            }

            Matcher osc32b = Pattern.compile("\\bIN32MHZ\\b").matcher(text);
            while (osc32b.find()) {
                doc.setCharacterAttributes(osc32b.start(), osc32b.end() - osc32b.start(), boolStyle, false);
            }

            Matcher lpinb = Pattern.compile("\\bLPIN\\b").matcher(text);
            while (lpinb.find()) {
                doc.setCharacterAttributes(lpinb.start(), lpinb.end() - lpinb.start(), boolStyle, false);
            }

            Matcher extlb = Pattern.compile("\\bEXTL\\b").matcher(text);
            while (extlb.find()) {
                doc.setCharacterAttributes(extlb.start(), extlb.end() - extlb.start(), boolStyle, false);
            }

            Matcher exthb = Pattern.compile("\\bEXTH\\b").matcher(text);
            while (exthb.find()) {
                doc.setCharacterAttributes(exthb.start(), exthb.end() - exthb.start(), boolStyle, false);
            }


            String[] lines = text.split("\n");
            int pos = 0;

            for (String line : lines) {

                if (line.startsWith("//")) { 
                    doc.setCharacterAttributes(pos, line.length(), fullLineStyle, false);
                }

                pos += line.length() + 1;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            doc.addUndoableEditListener(undoManager);
        }
    }
}
