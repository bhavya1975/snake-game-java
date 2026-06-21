# 🐍 Snake Game (Java Swing)

A fully functional, 2D interactive Snake Game built natively in **Java Swing**. This project translates LeetCode logic (specifically the $O(1)$ collision detection using HashSets and Deques) into a real-time OOP architecture.

## 🚀 Features
- **SOLID Architecture**: Cleanly separated layers with `GameEngine`, `RendererPanel`, and `InputManager`.
- **$O(1)$ Collision Detection**: Leverages Java's `HashSet` and `ArrayDeque` for optimal performance during self-collision and boundary checks.
- **Modern Aesthetics**: Built with `Graphics2D` rendering for anti-aliasing, dark mode support, and smooth frame updates.
- **High Score System**: Tracks the top score per session.

## 🛠️ Technology Stack
- Java 8+ (JDK)
- Java Swing & AWT

## 🎮 How to Run Locally

1. **Clone the repository:**
   ```bash
   git clone https://github.com/bhavya1975/snake-game-java.git
   cd snake-game-java
   ```

2. **Compile the code:**
   ```bash
   javac $(find src -name "*.java")
   ```

3. **Run the game:**
   ```bash
   java -cp src game.Main
   ```

## 🕹️ Controls
- **W, A, S, D** or **Arrow Keys**: Move the snake.
- **Spacebar**: Restart the game when you lose.

Enjoy playing!
