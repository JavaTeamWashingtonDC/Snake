package snake;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0, 0, 800, 700);
        super.paintComponent(g);
        Snake snake = Snake.snake;
        for (Point point : snake.snakeParts){
            g.setColor(Color.RED);
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, Snake.SCALE, Snake.SCALE);
        }
    }
}
