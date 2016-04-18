import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Snake {

    public ArrayList<Point> snakeParts = new ArrayList<Point>();
    public int tailLength;
    public Point head, cherry;
    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    public int direction = DOWN;

    public void restart(Random random){
        direction = DOWN;
        tailLength = 0;
        head = new Point(0, 0);
        snakeParts.clear();
        cherry = new Point(
                random.nextInt(Game.SCREEN_WIDTH / Game.SCALE),
                random.nextInt(Game.SCREEN_HIGHT / Game.SCALE));
        for (int i = 0; i < tailLength; i++) {
            snakeParts.add(new Point(head.x, head.y));
        }
    }
    public boolean hasTailAt(int x, int y) {
        for (Point point : snakeParts) {
            if (point.equals(new Point(x, y))) {
                return true;
            }
        }
        return false;
    }
}