package campus.valence;

import campus.valence.context.GameContext;
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
    private GameContext game;

    Destroyer(JLayeredPane parentPanel) {
        this.game = GameContext.getInstance();
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
        if (panel.getX() < game.getFrame().getWidth()- panel.getWidth() - 10) {
            moveX(STEP);
        }
    }

    private void moveX(int step) {
        Rectangle bounds = panel.getBounds();
        panel.setBounds(bounds.x + step, bounds.y, bounds.width, bounds.height);
    }

    public void destroyerFire() {
        Projectile pro = new SimpleProjectile((panel.getX()+panel.getWidth())/2 + panel.getX()/2, panel.getY()-10);
        game.getProjectiles().add(pro);
        parentPanel.add(pro, 1, 0);
        pro.move();
    }

    public JPanel getPanel() {
        return panel;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
