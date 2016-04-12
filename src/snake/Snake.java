import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener {
    public JFrame jframe;

    public RenderPanel renderPanel;
    // This will make one instance of class Snake. Main can access it directly.
    public static Snake snake;

    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    public Timer timer = new Timer(20, this);
    public Toolkit toolkit;

    // Constructor. This is the first function invoked from main when creating object.

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;

    public int ticks = 0, direction = DOWN, score, tailLength = 10;

    public Point head, cherry;

    public Random random;

    public boolean over = false;

    public Dimension dim;

    public Snake() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(800, 700);
        jframe.setLocation((dim.width / 2) - (jframe.getWidth() / 2), (dim.height / 2) - (jframe.getHeight() / 2));
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        head = new Point(0, 0);
        random = new Random();
        cherry = new Point(dim.width / SCALE, dim.height / SCALE);
        timer.start();
        for (int i = 0; i < tailLength; i++) {
            snakeParts.add(new Point(head.x,head.y));
        }
    }

    public static void main(String[] args) {
        snake = new Snake();
        //TODO renderPanel to paint with green :D  hjkjkl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        ticks++;

        if (ticks % 10 == 0 && head != null && over != true) {
          snakeParts.add(new Point(head.x,head.y));
            if (direction == UP) {
                if (head.y - 1 > 0)
                    head = new Point(head.x, head.y - 1);
                else
                    over = true;
            }
            if (direction == DOWN) {
                if (head.y + 1 < dim.height / SCALE)
                    head = new Point(head.x, head.y + 1);
                else
                    over = true;
            }
            if (direction == LEFT) {
                if (head.x - 1 > 0)
                    head = new Point(head.x - 1, head.y);
                else
                    over = true;
            }
            if (direction == RIGHT) {
                if (head.x + 1 < dim.width / SCALE)
                    head = new Point(head.x + 1, head.y);
                else
                    over = true;

                for (int i = 0; i < tailLength; i++) {
                    snakeParts.remove(i);
                }


                if (cherry != null) {
                    if (head.equals(cherry)) {
                        score += 10;
                        tailLength++;
                        cherry.setLocation(dim.width / SCALE, dim.height / SCALE);
                    }
                }
            }
        }
    }
}