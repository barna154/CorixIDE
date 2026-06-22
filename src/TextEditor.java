import javax.swing.*;
import javax.swing.text.*;
import javax.swing.undo.*;
import java.awt.*;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class TextEditor {

    private JTextPane textComponent;
    private UndoManager undoManager = new UndoManager();
    private File currentFile;

    private SyntaxHighlighter highlighter;

    public void init(JPanel editorPanel) {

        textComponent = new JTextPane();

        ((AbstractDocument) textComponent.getDocument())
                .setDocumentFilter(new AutoBraceFilter());

        textComponent.setBackground(new Color(30, 30, 30));
        textComponent.setForeground(new Color(218, 218, 218));
        textComponent.setCaretColor(Color.GRAY);
        textComponent.setFont(new Font("Consolas", Font.PLAIN, 17));

        textComponent.getDocument().addUndoableEditListener(undoManager);

        highlighter = new SyntaxHighlighter(textComponent);

        textComponent.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> highlighter.highlightNow());
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> highlighter.highlightNow());
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });

        setupKeyBindings();

        JScrollPane scroll = new JScrollPane(textComponent);
        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(scroll, BorderLayout.CENTER);
    }

    private void setupKeyBindings() {

        InputMap im = textComponent.getInputMap();
        ActionMap am = textComponent.getActionMap();

        im.put(KeyStroke.getKeyStroke("control Z"), "undo");
        am.put("undo", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if (undoManager.canUndo()) undoManager.undo();
                } catch (CannotUndoException ex) {}
            }
        });

        im.put(KeyStroke.getKeyStroke("control Y"), "redo");
        am.put("redo", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if (undoManager.canRedo()) undoManager.redo();
                } catch (CannotRedoException ex) {}
            }
        });

        im.put(KeyStroke.getKeyStroke("ENTER"), "enter");

        am.put("enter", new AbstractAction() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    int pos = textComponent.getCaretPosition();
                    Document doc = textComponent.getDocument();
                    String text = doc.getText(0, doc.getLength());

                    if (pos > 0 && text.charAt(pos - 1) == '{') {
                        doc.insertString(pos, "\n    \n}", null);
                        textComponent.setCaretPosition(pos + 5);
                    } else {
                        doc.insertString(pos, "\n", null);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void openFile(File file) {

        if (file == null || file.isDirectory()) return;

        try {
            undoManager.discardAllEdits();

            String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);

            textComponent.setText(content);
            currentFile = file;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void saveFile() {

        if (currentFile == null) return;

        try {
            Files.writeString(currentFile.toPath(),
                    textComponent.getText(),
                    StandardCharsets.UTF_8);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public JTextPane getTextComponent() {
        return textComponent;
    }

    public UndoManager getUndoManager() {
        return undoManager;
    }
}