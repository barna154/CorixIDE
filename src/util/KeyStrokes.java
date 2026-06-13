import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyStrokes extends KeyAdapter {

    private final Runnable onSave;

    public KeyStrokes(Runnable onSave) {
        this.onSave = onSave;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // CTRL + S
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            onSave.run();
        }
    }
}