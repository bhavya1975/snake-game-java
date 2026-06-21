package game.core;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class InputManager extends KeyAdapter implements IInputManager {
    private IGameEngine engine;
    private JPanel component;

    public InputManager(IGameEngine engine, JPanel component) {
        this.engine = engine;
        this.component = component;
    }

    @Override
    public void startListening() {
        component.addKeyListener(this);
        component.setFocusable(true);
        component.requestFocusInWindow();
    }

    @Override
    public void stopListening() {
        component.removeKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
            engine.setDirection(Direction.UP);
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
            engine.setDirection(Direction.DOWN);
        } else if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            engine.setDirection(Direction.LEFT);
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            engine.setDirection(Direction.RIGHT);
        } else if (key == KeyEvent.VK_SPACE) {
            engine.start();
        }
    }
}
