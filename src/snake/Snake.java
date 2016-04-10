package snake;

import javax.swing.*;
import java.awt.*;

public class Snake {
    public JFrame jframe;

    public Toolkit toolkit;

    public Snake() {
        toolkit = Toolkit.getDefaultToolkit();
        jframe = new JFrame("Snake");
        jframe.setVisible(true);
        jframe.setSize(800, 700);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

    }
}
