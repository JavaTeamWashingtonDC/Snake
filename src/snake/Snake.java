package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake implements ActionListener {
    public JFrame jframe;

    public RenderPanel renderPanel;

    public static Snake snake;

    public Toolkit toolkit;

    public Snake() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(800, 700);
        jframe.setLocation((dim.width / 2) - (jframe.getWidth() / 2), (dim.height / 2) - (jframe.getHeight() / 2));
        jframe.add(renderPanel = new RenderPanel());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        snake = new Snake();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
