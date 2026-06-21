package game.core;

import game.entities.Food;
import game.entities.Snake;
import java.awt.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import javax.swing.Timer;

public class GameEngine implements IGameEngine {
    private IRenderer renderer;
    private int width, height;
    private Snake snake;
    private Food food;
    private int score = 0;
    private int highScore = 0;
    private Direction currentDir = Direction.RIGHT;
    private Direction nextDir = Direction.RIGHT;
    private Set<Integer> vis = new HashSet<>();
    private Random random = new Random();
    private Timer timer;
    private boolean isGameOver = true;

    public GameEngine(IRenderer renderer, int width, int height) {
        this.renderer = renderer;
        this.width = width;
        this.height = height;
        
        // Loop at 120ms
        timer = new Timer(120, e -> update());
        reset();
    }

    private int f(int x, int y) {
        return y * width + x;
    }

    @Override
    public void start() {
        if (isGameOver) reset();
        isGameOver = false;
        timer.start();
    }

    @Override
    public void pause() {
        timer.stop();
    }

    @Override
    public void reset() {
        score = 0;
        currentDir = Direction.RIGHT;
        nextDir = Direction.RIGHT;
        vis.clear();
        isGameOver = true;

        Point start = new Point(0, 0);
        snake = new Snake(start);
        vis.add(f(0, 0));

        generateFood();
        renderer.render(); // initial draw
    }

    @Override
    public void update() {
        currentDir = nextDir;
        Point head = snake.getBody().peekFirst();
        int newX = head.x;
        int newY = head.y;

        if (currentDir == Direction.UP) newY--;
        if (currentDir == Direction.DOWN) newY++;
        if (currentDir == Direction.LEFT) newX--;
        if (currentDir == Direction.RIGHT) newX++;

        // Boundary collision
        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            gameOver();
            return;
        }

        boolean ateFood = false;
        if (food != null && newX == food.getPosition().x && newY == food.getPosition().y) {
            score++;
            if (score > highScore) highScore = score;
            ateFood = true;
            generateFood();
        }

        if (!ateFood) {
            Point tail = snake.getBody().pollLast();
            if (tail != null) vis.remove(f(tail.x, tail.y));
        }

        int cur = f(newX, newY);
        // Self collision
        if (vis.contains(cur)) {
            gameOver();
            return;
        }

        Point newHead = new Point(newX, newY);
        snake.getBody().addFirst(newHead);
        vis.add(cur);

        // Update graphics
        renderer.clear(null); // Signal to repaint
    }

    private void generateFood() {
        if (vis.size() >= width * height) return; // Win
        int rx, ry;
        do {
            rx = random.nextInt(width);
            ry = random.nextInt(height);
        } while (vis.contains(f(rx, ry)));
        food = new Food(new Point(rx, ry));
    }

    private void gameOver() {
        isGameOver = true;
        pause();
        renderer.render();
    }

    @Override
    public void setDirection(Direction dir) {
        if (currentDir == Direction.UP && dir == Direction.DOWN) return;
        if (currentDir == Direction.DOWN && dir == Direction.UP) return;
        if (currentDir == Direction.LEFT && dir == Direction.RIGHT) return;
        if (currentDir == Direction.RIGHT && dir == Direction.LEFT) return;
        nextDir = dir;
    }

    // Getters for renderer
    public Snake getSnake() { return snake; }
    public Food getFood() { return food; }
    public int getScore() { return score; }
    public int getHighScore() { return highScore; }
    public boolean isGameOver() { return isGameOver; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}
