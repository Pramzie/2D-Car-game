import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class CarRacingGame extends JPanel implements ActionListener, KeyListener {

    // Constants
    private static final int PANEL_WIDTH = 500;
    private static final int PANEL_HEIGHT = 600;
    private static final int ROAD_X = 100;
    private static final int ROAD_WIDTH = 300;
    private static final int CAR_WIDTH = 50;
    private static final int CAR_HEIGHT = 80;
    private static final int OBSTACLE_WIDTH = 50;
    private static final int OBSTACLE_HEIGHT = 80;
    private static final int OBSTACLE_SPEED = 5;
    private static final int MOVE_STEP = 20;

    private Rectangle car;
    private Rectangle obstacle;

    private int score = 0;
    private boolean gameOver = false;
    private Timer timer;
    private Random random = new Random();

    public CarRacingGame() {
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(Color.GRAY);
        setFocusable(true);
        addKeyListener(this);

        car = new Rectangle(ROAD_X + ROAD_WIDTH / 2 - CAR_WIDTH / 2, PANEL_HEIGHT - CAR_HEIGHT - 20, CAR_WIDTH, CAR_HEIGHT);
        resetObstacle();

        timer = new Timer(30, this);
        timer.start();
    }

    private void resetObstacle() {
        int x = ROAD_X + random.nextInt(ROAD_WIDTH - OBSTACLE_WIDTH);
        obstacle = new Rectangle(x, -OBSTACLE_HEIGHT, OBSTACLE_WIDTH, OBSTACLE_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Draw road
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(ROAD_X, 0, ROAD_WIDTH, PANEL_HEIGHT);

        // Lane dividers
        g2d.setColor(Color.WHITE);
        for (int i = 0; i < PANEL_HEIGHT; i += 50) {
            g2d.fillRect(ROAD_X + ROAD_WIDTH / 2 - 5, i, 10, 30);
        }

        // Draw car
        g2d.setColor(Color.RED);
        g2d.fillRect(car.x, car.y, car.width, car.height);

        // Draw obstacle
        g2d.setColor(Color.BLACK);
        g2d.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);

        // Draw score
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        g2d.drawString("Score: " + score, 10, 30);

        if (gameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 40));
            g2d.drawString("GAME OVER", 130, 300);
            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            g2d.drawString("Press ENTER to Restart", 150, 350);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            obstacle.y += OBSTACLE_SPEED;

            if (obstacle.y > PANEL_HEIGHT) {
                resetObstacle();
                score += 10;
            }

            if (car.intersects(obstacle)) {
                gameOver = true;
                timer.stop();
            }

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (car.x > ROAD_X + 10) {
                        car.x -= MOVE_STEP;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (car.x < ROAD_X + ROAD_WIDTH - CAR_WIDTH - 10) {
                        car.x += MOVE_STEP;
                    }
                    break;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            // Restart the game
            score = 0;
            car.x = ROAD_X + ROAD_WIDTH / 2 - CAR_WIDTH / 2;
            resetObstacle();
            gameOver = false;
            timer.start();
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Car Racing Game");
            CarRacingGame game = new CarRacingGame();
            frame.add(game);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
