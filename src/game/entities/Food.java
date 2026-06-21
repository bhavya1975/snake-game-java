package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Food extends Entity {
    public Food(Point position) {
        super(position, new Color(251, 113, 133)); // Neon pink/red
    }

    @Override
    public void render(Graphics2D g, int cellSize) {
        g.setColor(color);
        int x = position.x * cellSize;
        int y = position.y * cellSize;
        g.fillOval(x + 2, y + 2, cellSize - 4, cellSize - 4);
    }
}
