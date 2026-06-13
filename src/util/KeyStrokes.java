package util;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyStrokes extends KeyAdapter {

    private final Runnable onSave;
    private final Runnable onUndo;
    private final Runnable onRedo;

    public KeyStrokes(Runnable onSave, Runnable onUndo, onRedo) {
        this.onSave = onSave;
        this.onUndo = onUndo;
        this.onRedo = onRedo;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
            onSave.run();
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
            onUndo.run();
        }

        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
            onRedo.run();
        }
    }
}