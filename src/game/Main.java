package game;

import game.core.GameEngine;
import game.core.InputManager;
import game.ui.RendererPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Snake Game (Java Edition)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setLayout(new BorderLayout());

            RendererPanel renderer = new RendererPanel();
            // Grid 30x20
            GameEngine engine = new GameEngine(renderer, 30, 20);
            renderer.setEngine(engine);

            // Score Panel
            JPanel scorePanel = new JPanel();
            scorePanel.setBackground(new Color(30, 41, 59));
            scorePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel scoreLabel = new JLabel("Score: 0   High Score: 0");
            scoreLabel.setForeground(Color.WHITE);
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
            scorePanel.add(scoreLabel);

            frame.add(scorePanel, BorderLayout.NORTH);
            frame.add(renderer, BorderLayout.CENTER);

            // Setup input
            InputManager input = new InputManager(engine, renderer);
            input.startListening();

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            // Poll engine for score updates every 100ms
            new Timer(100, e -> {
                scoreLabel.setText("Score: " + engine.getScore() + "   High Score: " + engine.getHighScore());
            }).start();
        });
    }
}
