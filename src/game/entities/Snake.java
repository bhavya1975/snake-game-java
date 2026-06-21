package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayDeque;
import java.util.Deque;

public class Snake extends Entity {
    private Deque<Point> body;

    public Snake(Point startPosition) {
        super(startPosition, new Color(74, 222, 128)); // Neon green
        body = new ArrayDeque<>();
        body.addFirst(startPosition);
    }

    public Deque<Point> getBody() {
        return body;
    }

    @Override
    public void render(Graphics2D g, int cellSize) {
        g.setColor(color);
        boolean isHead = true;
        for (Point p : body) {
            int x = p.x * cellSize;
            int y = p.y * cellSize;
            if (isHead) {
                g.setColor(new Color(34, 197, 94)); // Darker green for head
                isHead = false;
            } else {
                g.setColor(color);
            }
            g.fillRoundRect(x + 1, y + 1, cellSize - 2, cellSize - 2, 8, 8);
        }
    }
}
