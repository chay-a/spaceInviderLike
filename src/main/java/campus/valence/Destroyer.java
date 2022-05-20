package campus.valence;

import campus.valence.projectile.Projectile;
import campus.valence.projectile.SimpleProjectile;

import javax.swing.*;
import java.awt.*;

public class Destroyer {

    private static int STEP = 5;
    private int life;
    private final JPanel panel;
    private JPanel parentPanel;

    Destroyer(JPanel parentPanel) {
        this.parentPanel = parentPanel;
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
        Projectile pro = new SimpleProjectile((panel.getX()+panel.getWidth())/2 + panel.getX()/2, panel.getY()-10);
        parentPanel.add(pro);
        pro.move();
    }

    public JPanel getPanel() {
        return panel;
    }
}
