import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Handler implements ActionListener, KeyListener {

    private static Snake snake;
    private RenderPanel renderPanel;

    public Handler(RenderPanel renderPanel, Snake snake) {
        this.renderPanel = renderPanel;
        this.snake = snake;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        Game.getInstance().ticks++;

        if (Game.getInstance().ticks % 3 == 0 && snake.head != null && !Game.getInstance().over && !Game.getInstance().paused) {
            Game.getInstance().time++;
            System.out.printf("head: %d, %d\n", snake.head.x, snake.head.y);
            snake.snakeParts.add(new Point(snake.head.x, snake.head.y));

            if (snake.direction == Direction.UP) {
                if (snake.head.y - 1 >= 0 && !snake.hasTailAt(snake.head.x, snake.head.y - 1)) {
                    snake.head = new Point(snake.head.x, snake.head.y - 1);
                } else {
                    Game.getInstance().over = true;
                }
            }
            if (snake.direction == Direction.DOWN) {
                if (snake.head.y + 1 < Game.getInstance().SCREEN_HEIGHT / Game.getInstance().SCALE && !snake.hasTailAt(snake.head.x, snake.head.y + 1)) {
                    snake.head = new Point(snake.head.x, snake.head.y + 1);
                } else {
                    Game.getInstance().over = true;
                }
            }

            if (snake.direction == Direction.LEFT) {
                if (snake.head.x - 1 >= 0 && !snake.hasTailAt(snake.head.x - 1, snake.head.y)) {
                    snake.head = new Point(snake.head.x - 1, snake.head.y);
                } else {
                    Game.getInstance().over = true;
                }
            }

            if (snake.direction == Direction.RIGHT) {
                if (snake.head.x + 1 < Game.getInstance().SCREEN_WIDTH / Game.getInstance().SCALE && !snake.hasTailAt(snake.head.x + 1, snake.head.y)) {
                    {
                        snake.head = new Point(snake.head.x + 1, snake.head.y);
                    }
                } else {
                    Game.getInstance().over = true;
                }
            }

            Game.getInstance().isMovedToChangeDirection = true;
            if (snake.snakeParts.size() > snake.tailLength) {
                snake.snakeParts.remove(0);
            }
            if (snake.cherry != null) {
                if (snake.head.equals(snake.cherry)) {
                    Game.getInstance().score += 10;
                    snake.tailLength++;
                    boolean isFoodOnSnake = true;
                    while(snake.head.equals(snake.cherry)&& isFoodOnSnake)
                    snake.cherry.setLocation(
                            Game.getInstance().random.nextInt(Game.getInstance().SCREEN_WIDTH / Game.getInstance().SCALE),
                            Game.getInstance().random.nextInt(Game.getInstance().SCREEN_HEIGHT / Game.getInstance().SCALE));
                        for (Point snakePart: snake.snakeParts) {
                           if (snakePart.equals(snake.cherry)){
                               continue;
                           }
                        }
                        isFoodOnSnake = false;
                }
            }
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_A && snake.direction != Direction.LEFT && Game.getInstance().isMovedToChangeDirection) {
            snake.direction = Direction.LEFT;
            Game.getInstance().isMovedToChangeDirection = false;
        }

        if (k == KeyEvent.VK_D && snake.direction != Direction.RIGHT && Game.getInstance().isMovedToChangeDirection) {
            snake.direction = Direction.RIGHT;
            Game.getInstance().isMovedToChangeDirection = false;
        }

        if (k == KeyEvent.VK_W && snake.direction != Direction.DOWN && Game.getInstance().isMovedToChangeDirection) {
            snake.direction = Direction.UP;
            Game.getInstance().isMovedToChangeDirection = false;
        }

        if (k == KeyEvent.VK_S && snake.direction != Direction.UP && Game.getInstance().isMovedToChangeDirection) {
            snake.direction = Direction.DOWN;
            Game.getInstance().isMovedToChangeDirection = false;
        }

        if (k == KeyEvent.VK_SPACE) {
            if (Game.getInstance().over) {
                Game.getInstance().startGame();
            } else {
                Game.getInstance().paused = !Game.getInstance().paused;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
