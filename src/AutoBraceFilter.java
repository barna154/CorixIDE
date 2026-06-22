import javax.swing.text.*;

public class AutoBraceFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        // ENTER kezelés
        if ("\n".equals(text)) {
            handleEnter(fb, offset, length, attrs);
            return;
        }

        // normál csere
        super.replace(fb, offset, length, text, attrs);
    }

    private void handleEnter(FilterBypass fb, int offset, int length, AttributeSet attrs)
            throws BadLocationException {

        Document doc = fb.getDocument();
        String content = doc.getText(0, doc.getLength());

        // Ha { után vagyunk
        if (offset > 0 && content.charAt(offset - 1) == '{') {

            // EGYETLEN replace → UndoManager boldog
            fb.replace(offset, length, "\n    \n}", attrs);

        } else {

            // sima enter → EGYETLEN replace
            fb.replace(offset, length, "\n", attrs);
        }
    }
}
