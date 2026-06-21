package game.core;

public interface IGameEngine {
    void start();
    void pause();
    void reset();
    void update();
    void setDirection(Direction dir);
}
