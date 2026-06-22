import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CannotRedoException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;

public class TextEditor {

    private JTextPane textComponent;
    private UndoManager undoManager = new UndoManager();
    private File currentFile;

    public void init(JPanel editorPanel) {

        textComponent = new JTextPane();

        ((AbstractDocument) textComponent.getDocument())
                .setDocumentFilter(new AutoBraceFilter());

        textComponent.setBackground(new Color(30, 30, 30));
        textComponent.setForeground(new Color(218, 218, 218));
        textComponent.setCaretColor(Color.GRAY);
        textComponent.setFont(new Font("Consolas", Font.PLAIN, 17));

        textComponent.getDocument().addUndoableEditListener(undoManager);

        setupKeyBindings();

        JScrollPane scroll = new JScrollPane(textComponent);
        scroll.setBorder(null);

        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(scroll, BorderLayout.CENTER);
    }

    private void setupKeyBindings() {

        InputMap im = textComponent.getInputMap();
        ActionMap am = textComponent.getActionMap();

        // UNDO
        im.put(KeyStroke.getKeyStroke("control Z"), "undo");
        am.put("undo", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if (undoManager.canUndo()) undoManager.undo();
                } catch (CannotUndoException ex) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        // REDO
        im.put(KeyStroke.getKeyStroke("control Y"), "redo");
        am.put("redo", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if (undoManager.canRedo()) undoManager.redo();
                } catch (CannotRedoException ex) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });

        // SMART ENTER (AUTO BRACE IDEÁLIS HELYE)
        im.put(KeyStroke.getKeyStroke("ENTER"), "smart-enter");

        am.put("smart-enter", new AbstractAction() {
            @Override
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

    // FILE LOAD
    public void openFile(File file) {
        if (file == null || file.isDirectory()) return;

        try {
            undoManager.discardAllEdits(); // 🔥 FONTOS

            String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);

            textComponent.setText(content);
            textComponent.setCaretPosition(0);

            currentFile = file;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // SAVE
    public void saveFile() {
        if (currentFile == null) return;

        try {
            Files.writeString(
                    currentFile.toPath(),
                    textComponent.getText(),
                    StandardCharsets.UTF_8
            );
        } catch (IOException ex) {
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