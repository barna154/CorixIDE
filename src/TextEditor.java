import javax.swing.*;
import java.awt.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.JTextComponent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import util.LanguageManager;

public class TextEditor {

    private JTextPane textComponent;
    private File currentFile;


    public void init(JPanel editorPanel) {

        textComponent = new JTextPane();

        ((AbstractDocument) textComponent.getDocument()).setDocumentFilter(new AutoBraceFilter());

        textComponent.setBackground(new Color(30, 30, 30));
        textComponent.setForeground(new Color(218, 218, 218));
        textComponent.setCaretColor(Color.GRAY);
        textComponent.setFont(new Font("Consolas", Font.PLAIN, 17));
        textComponent.setSelectionColor(new Color(40, 60, 40));     
        textComponent.setSelectedTextColor(new Color(218, 218, 218));

         new SyntaxHighlighter(textComponent);

        JScrollPane scroll = new JScrollPane(textComponent);
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(30, 30, 30));
        scroll.setRowHeaderView(new LineNumberComponent(textComponent));

        scroll.getVerticalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getHorizontalScrollBar().setUI(new CustomScrollBarUI());
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(18, 0));
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 18));
        scroll.setCorner(JScrollPane.LOWER_RIGHT_CORNER, createDarkPanel());
        scroll.setCorner(JScrollPane.LOWER_LEFT_CORNER, createDarkPanel());
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, createDarkPanel());



        editorPanel.setLayout(new BorderLayout());
        editorPanel.add(scroll, BorderLayout.CENTER);
    }

        public void openFile(File file) {
        if (file == null || file.isDirectory()) return;

        try {
            String content = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
            textComponent.setText(content);
            textComponent.setCaretPosition(0);
            currentFile = file;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void saveFile() {
            try {
                LanguageManager.load("../lang/lang.json");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String saveerror = LanguageManager.get("Saving falied");
            if (currentFile == null) return;

            try {
                if (!Files.exists(currentFile.toPath())) {
                    if (messageHandler != null) {
                        messageHandler.show(saveerror, saveerror);

                    }
                    return;
                }
                Files.write(
                    currentFile.toPath(),
                    textComponent.getText().getBytes(StandardCharsets.UTF_8)
                );

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    public File getCurrentFile() {
        return currentFile;
    }

    public interface MessageHandler {
             void show(String title, String message);
    }

    private MessageHandler messageHandler;

    public void setMessageHandler(MessageHandler handler) {
        this.messageHandler = handler;
    }

    private JPanel createDarkPanel() {
    JPanel p = new JPanel();
    p.setBackground(new Color(30, 30, 30));
    return p;
    }


}

