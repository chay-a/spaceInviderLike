package campus.valence;

import javax.swing.*;
import java.awt.*;

public class Destroyer {

    private static int STEP = 5;
    private int life;
    private final JPanel panel;

    Destroyer() {
        this.life = 3;
        panel = new JPanel();
        panel.setBounds(150, 500, 100, 30);
        panel.setBackground(Color.PINK);
    }

    public void moveLeft() {
        if (panel.getX() > 10) {
            moveX(-STEP);
        }
    }

    public void moveRight() {
        if (panel.getX() < 290) {
            moveX(STEP);
        }
    }

    private void moveX(int step) {
        Rectangle bounds = panel.getBounds();
        panel.setBounds(bounds.x + step, bounds.y, bounds.width, bounds.height);
    }

    public void destroyerFire() {
    }

    public JPanel getPanel() {
        return panel;
    }
}
