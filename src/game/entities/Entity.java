package game.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public abstract class Entity {
    protected Point position;
    protected Color color;

    public Entity(Point position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public abstract void render(Graphics2D g, int cellSize);
}
