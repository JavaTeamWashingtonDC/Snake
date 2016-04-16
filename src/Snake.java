import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener, KeyListener {
    private RenderPanel renderPanel;
    // This will make one instance of class Snake. Main can access it directly.
    public static Snake snake;
    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    private Toolkit toolkit;

    //something to change
    private Timer timer = new Timer(300, this);
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 60;
    public static final int SCREEN_WIDTH = 780, SCREEN_HIGHT = 540;
    public int ticks = 0, direction = DOWN, score, tailLength, time;

    public Point head, cherry;
    private Random random;
    private boolean over = false, paused, isMovedToChangeDirection;
    private Dimension dim;

    // Empty Constructor. This is the first function invoked from main when creating object.
    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(SCREEN_WIDTH, SCREEN_HIGHT);
        jframe.setResizable(false);
        jframe.setLocation((dim.width / 2) - (jframe.getWidth() / 2), (dim.height / 2) - (jframe.getHeight() / 2));
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.addKeyListener(this);
        startGame();
    }

    public void startGame() {
        over = false;
        paused = false;
        time = 0;
        score = 0;
        tailLength = 1;
        ticks = 0;
        direction = DOWN;
        head = new Point(0, 0);
        random = new Random();
        snakeParts.clear();
        cherry = new Point(random.nextInt(SCREEN_WIDTH / SCALE), random.nextInt(SCREEN_HIGHT / SCALE));
        for (int i = 0; i < tailLength; i++) {
            snakeParts.add(new Point(head.x,head.y));
        }
        timer.start();
    }

    public static void main(String[] args) {
        snake = new Snake();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        ticks++;

        if (ticks % 3 == 0 && head != null && !over && !paused) {
            time++;
            System.out.printf("head: %d, %d\n", head.x, head.y);
            snakeParts.add(new Point(head.x, head.y));

            if (direction == UP)
                if (head.y - 1 >= 0 && noTailAt(head.x, head.y - 1))
                    head = new Point(head.x, head.y - 1);
                else
                    over = true;
            if (direction == DOWN)
                if (head.y + 1 < SCREEN_HIGHT / SCALE && noTailAt(head.x, head.y + 1))
                    head = new Point(head.x, head.y + 1);
                else
                    over = true;
            if (direction == LEFT)
                if (head.x - 1 >= 0 && noTailAt(head.x - 1, head.y))
                    head = new Point(head.x - 1, head.y);
                else
                    over = true;
            if (direction == RIGHT) {
                if (head.x + 1 < SCREEN_WIDTH / SCALE && noTailAt(head.x + 1, head.y)) {
                    head = new Point(head.x + 1, head.y);
                } else {
                    over = true;
                }
            }

            isMovedToChangeDirection = true;
            if (snakeParts.size() > tailLength) {
                snakeParts.remove(0);
            }
            if (cherry != null) {
                if (head.equals(cherry)) {
                    score += 10;
                    tailLength++;
                    cherry.setLocation(random.nextInt(SCREEN_WIDTH / SCALE), random.nextInt(SCREEN_HIGHT / SCALE));
                }
            }
        }
    }

    public boolean noTailAt(int x,int y) {
        for (Point point : snakeParts) {
            if (point.equals(new Point(x, y))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if(i ==  KeyEvent.VK_A && direction != RIGHT && isMovedToChangeDirection) {
            direction = LEFT;
            isMovedToChangeDirection = false;
        }
        if(i ==  KeyEvent.VK_D && direction != LEFT && isMovedToChangeDirection) {
            direction = RIGHT;
            isMovedToChangeDirection = false;
        }
        if(i ==  KeyEvent.VK_W && direction != DOWN && isMovedToChangeDirection) {
            direction = UP;
            isMovedToChangeDirection = false;
        }
        if(i ==  KeyEvent.VK_S && direction != UP && isMovedToChangeDirection) {
            direction = DOWN;
            isMovedToChangeDirection = false;
        }
        if(i ==  KeyEvent.VK_SPACE) {
            if (over) {
                startGame();
            }else {
                paused = !paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
