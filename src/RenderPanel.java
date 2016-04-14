import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.pink);
        g.fillRect(0, 0, 700, 600);
        Snake snake = Snake.snake;
        g.setColor(Color.red);
        for (Point point : snake.snakeParts){
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        }
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,
                Snake.SCALE,Snake.SCALE);
        g.setColor(Color.BLUE);
        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE,
                Snake.SCALE,Snake.SCALE);
        String string = "Score: " + snake.score + ", Length: " + snake.tailLength + ", time: " + snake.time / 2 / 20;
        g.setColor(Color.white);
        g.drawString(string,(int)(getWidth() / 2 * string.length()* 2.5f) ,10);
    }
}
