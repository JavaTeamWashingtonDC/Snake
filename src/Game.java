import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by maggie on 18.4.2016 г..
 */
public class Game {

    private Snake snake;
    private RenderPanel renderPanel;
    // This will make one instance of class Snake. Main can access it directly.
    private static Game game;

    public boolean over = false, paused, isMovedToChangeDirection;
    public static final int SCREEN_WIDTH = 780, SCREEN_HIGHT = 540;
    private Timer timer;
    public static final int SCALE = 60, SPEED = 150;
    public int ticks = 0;
    public int score;
    public int time;
    public Random random;

    /**
     * Default constructor
     */
    private Game() {
        snake = new Snake();
        renderPanel = new RenderPanel(snake);
        JFrame jFrame = new JFrame("Snake");

        jFrame.setVisible(true);
        jFrame.setSize(SCREEN_WIDTH, SCREEN_HIGHT);
        jFrame.setResizable(false);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame.setLocation((dim.width / 2) - (jFrame.getWidth() / 2), (dim.height / 2) - (jFrame.getHeight() / 2));

        jFrame.add(renderPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Handler handler = new Handler(renderPanel, snake);
        jFrame.addKeyListener(handler);
        timer = new Timer(SPEED, handler);
        startGame();
    }

    public void startGame() {
        random = new Random();
        over = false;
        paused = false;
        time = 0;
        score = 0;
        ticks = 0;
        snake.restart(random);

        snake.cherry = new Point(random.nextInt(SCREEN_WIDTH / SCALE), random.nextInt(SCREEN_HIGHT / SCALE));
        for (int i = 0; i < snake.tailLength; i++) {
            snake.snakeParts.add(new Point(snake.head.x, snake.head.y));
        }
        timer.start();
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public static void main(String[] args) {
        Runnable event = new Runnable() {
            @Override
            public void run() {
                Game game = Game.getInstance();
            }
        };
        SwingUtilities.invokeLater(event);
    }

}
