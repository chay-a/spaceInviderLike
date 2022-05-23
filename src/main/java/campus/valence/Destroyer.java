package campus.valence;

import campus.valence.projectile.Projectile;
import campus.valence.projectile.SimpleProjectile;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Destroyer {

    private static int STEP = 5;
    private int life;
    private final JPanel panel;
    private JLayeredPane parentPanel;
    private CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList<>();

    Destroyer(JLayeredPane parentPanel, CopyOnWriteArrayList<Projectile> projectiles) {
        this.parentPanel = parentPanel;
        this.projectiles = projectiles;
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
        projectiles.add(pro);
        parentPanel.add(pro, 1, 0);
        pro.move();
    }

    public JPanel getPanel() {
        return panel;
    }
}
