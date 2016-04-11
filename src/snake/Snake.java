package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Snake implements ActionListener {
    public JFrame jframe;

    public RenderPanel renderPanel;
    // This will make one instance of class Snake. Main can access it directly.
    public static Snake snake;

    public Toolkit toolkit;
    // Constructor. This is the first function invoked from main when creating object.
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
        //TODO renderPanel to paint with green :D  hjkjkl;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
