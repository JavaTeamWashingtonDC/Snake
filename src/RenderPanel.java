import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RenderPanel extends JComponent {

    private Snake snake;

    RenderPanel(Snake snake) {
        loadPictures();
        this.snake = snake;
    }

    public BufferedImage headImg;
    public BufferedImage backgroundImg;
    public BufferedImage snakeBodyImg;

    public void loadPictures() {
        try {
            headImg = ImageIO.read(this.getClass().getResource("pics/headcircle1.png"));
            backgroundImg = ImageIO.read(this.getClass().getResource("pics/background.jpeg"));
            snakeBodyImg = ImageIO.read(this.getClass().getResource("pics/unnamed.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw field
        g.drawImage(backgroundImg, 0, 0, null);
        // draw food
        g.setColor(Color.BLUE);
        g.fillRect(snake.cherry.x * Game.SCALE, snake.cherry.y * Game.SCALE,
                Game.SCALE,Game.SCALE);
        // draw snake body
        g.setColor(Color.red);
        for (Point point : snake.snakeParts){
            g.drawImage( snakeBodyImg, point.x * Game.SCALE, point.y * Game.SCALE, null);
        }
        // draw head
        g.drawImage( headImg, snake.head.x * Game.SCALE, snake.head.y * Game.SCALE, null);
        // draw score
        String text = "Score: " + Game.getInstance().score + ", Length: " + snake.tailLength + ", Moves: " + Game.getInstance().time;
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 12));
        g.drawString(text, 10 , Game.SCREEN_HIGHT - 40);
        // draw pause
        if(Game.getInstance().paused){
            String pauseText = "Paused.";
            g.setColor(Color.DARK_GRAY);
            g.setFont(new Font("Verdana", Font.BOLD, 20));
            g.drawString(pauseText, 150 , Game.SCREEN_HIGHT / 2);
        }
    }
}
