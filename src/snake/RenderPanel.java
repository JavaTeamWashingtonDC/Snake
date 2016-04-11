package snake;

import javax.swing.*;
import java.awt.*;

public class RenderPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(0, 0, 800, 700);
        super.paintComponent(g);
    }
}
