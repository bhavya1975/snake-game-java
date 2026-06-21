package game.ui;

import game.core.GameEngine;
import game.core.IRenderer;
import game.entities.Entity;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class RendererPanel extends JPanel implements IRenderer {
    private GameEngine engine;
    private int cellSize = 20;

    public RendererPanel() {
        setBackground(new Color(15, 23, 42)); // Dark theme
    }

    public void setEngine(GameEngine engine) {
        this.engine = engine;
        setPreferredSize(new Dimension(engine.getWidth() * cellSize, engine.getHeight() * cellSize));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (engine == null) return;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        drawGrid(g2d);
        if (engine.getFood() != null) drawEntity(g2d, engine.getFood());
        if (engine.getSnake() != null) drawEntity(g2d, engine.getSnake());

        // We draw score on a separate panel, but game over here
        if (engine.isGameOver()) {
            drawGameOverOverlay(g2d);
        }
    }

    private void drawGameOverOverlay(Graphics2D g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        String msg = "Game Over! Press Space to Start.";
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(msg)) / 2;
        int y = (getHeight() / 2);
        g.drawString(msg, x, y);
    }

    @Override
    public void clear(Graphics2D g) {
        repaint();
    }

    @Override
    public void drawGrid(Graphics2D g) {
        g.setColor(new Color(255, 255, 255, 10)); // faint grid
        int w = getWidth();
        int h = getHeight();
        for (int i = 0; i <= w; i += cellSize) {
            g.drawLine(i, 0, i, h);
        }
        for (int i = 0; i <= h; i += cellSize) {
            g.drawLine(0, i, w, i);
        }
    }

    @Override
    public void drawEntity(Graphics2D g, Entity entity) {
        entity.render(g, cellSize);
    }

    @Override
    public void drawScore(int score, int highScore) {
        // We'll manage this via a parent JFrame component instead.
    }

    @Override
    public void render() {
        repaint();
    }
}
