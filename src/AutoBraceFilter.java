import javax.swing.text.*;
import javax.swing.undo.CompoundEdit;

public class AutoBraceFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        if ("\n".equals(text)) {
            handleEnter(fb, offset, length, attrs);
            return;
        }

        super.replace(fb, offset, length, text, attrs);
    }

    private void handleEnter(FilterBypass fb, int offset, int length, AttributeSet attrs)
            throws BadLocationException {

        Document doc = fb.getDocument();
        String content = doc.getText(0, doc.getLength());

        CompoundEdit edit = new CompoundEdit();

        if (offset > 0 && content.charAt(offset - 1) == '{') {

            fb.replace(offset, length, "\n    \n}", attrs);

        } else {
            fb.replace(offset, length, "\n", attrs);
        }

        edit.end(); 
    }
}