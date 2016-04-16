import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {
    Snake cSnake;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Snake snake = Snake.snake;
        // draw field
        g.setColor(Color.pink);
        g.fillRect(0, 0, snake.SCREEN_WIDTH, snake.SCREEN_HIGHT - snake.SCALE);
        // draw snake body
        g.setColor(Color.red);
        for (Point point : snake.snakeParts){
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        }
        // draw head
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,
                Snake.SCALE,Snake.SCALE);
        // draw food
        g.setColor(Color.BLUE);
        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE,
                Snake.SCALE,Snake.SCALE);
        String text = "Score: " + snake.score + ", Length: " + snake.tailLength + ", time: " + snake.time / 3 / 300;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 12));
        g.drawString(text, 10 ,snake.SCREEN_HIGHT - 40);
    }
}
