import javax.swing.text.*;

public class AutoBraceFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attrs)
            throws BadLocationException {

        if ("\n".equals(text)) {
            handleEnter(fb, offset, attrs);
        } else {
            super.insertString(fb, offset, text, attrs);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        if ("\n".equals(text)) {
            handleEnter(fb, offset, attrs);
        } else {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    private void handleEnter(FilterBypass fb, int offset, AttributeSet attrs)
            throws BadLocationException {

        Document doc = fb.getDocument();
        String content = doc.getText(0, doc.getLength());

        // ha az előző karakter '{'
        if (offset > 0 && content.charAt(offset - 1) == '{') {

            String insert =
                    "\n    \n}";  // középső sorba kerül a kurzor

            fb.insertString(offset, insert, attrs);

        } else {
            fb.insertString(offset, "\n", attrs);
        }
    }
}
