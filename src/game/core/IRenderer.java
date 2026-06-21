package game.core;

import game.entities.Entity;
import java.awt.Graphics2D;

public interface IRenderer {
    void clear(Graphics2D g);
    void drawGrid(Graphics2D g);
    void drawEntity(Graphics2D g, Entity entity);
    void drawScore(int score, int highScore);
    void render(); // Triggers a repaint
}
