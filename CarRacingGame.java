import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class CarRacingGame extends JPanel implements ActionListener, KeyListener {
    private int carX = 220, carY = 500;  // Car position
    private int obstacleX, obstacleY = -100, obstacleSpeed = 5;
    private int score = 0;
    private boolean gameOver = false;
    private Timer timer;
    private Random random = new Random();

    public CarRacingGame() {
        setPreferredSize(new Dimension(500, 600));
        setBackground(Color.GRAY);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        obstacleX = random.nextInt(350);  // Random obstacle position
        timer = new Timer(30, this);  // Game loop
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw road
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(100, 0, 300, 600);

        // Draw lane dividers
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < 600; i += 50) {
            g2d.fillRect(240, i, 10, 30);
        }

        // Draw the car
        g2d.setColor(Color.RED);
        g2d.fillRect(carX, carY, 50, 80);

        // Draw obstacles
        g2d.setColor(Color.BLACK);
        g2d.fillRect(obstacleX, obstacleY, 50, 80);

        // Draw score
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + score, 10, 30);

        // Game Over message
        if (gameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.drawString("GAME OVER", 150, 300);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            // Move the obstacle down
            obstacleY += obstacleSpeed;

            // Reset obstacle if it goes off-screen
            if (obstacleY > 600) {
                obstacleY = -100;
                obstacleX = random.nextInt(350);
                score += 10; // Increase score
            }

            // Collision detection
            if (new Rectangle(carX, carY, 50, 80).intersects(new Rectangle(obstacleX, obstacleY, 50, 80))) {
                gameOver = true;
                timer.stop();
            }

            repaint(); // Refresh screen
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT && carX > 110) {
                carX -= 20; // Move left
            }
            if (e.getKeyCode() == KeyEvent.VK_RIGHT && carX < 340) {
                carX += 20; // Move right
            }
        }
    }

    
    public void keyReleased(KeyEvent e) {}

    
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Racing Game");
        CarRacingGame game = new CarRacingGame();
        frame.add(game);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
