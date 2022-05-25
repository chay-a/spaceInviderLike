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
    private JLabel imageLabel;
    private GameContext game;
    private String projectile;


    public String getProjectile() {
        return projectile;
    }

    public void setProjectile(String projectile) {
        this.projectile = projectile;
    }


    public JLabel getImageLabel() {
        return imageLabel;
    }

    public void setImageLabel(JLabel imageLabel) {
        this.imageLabel = imageLabel;
    }

    Destroyer(JLayeredPane parentPanel) {
        this.projectile = "SimpleProjectile";
        this.game = GameContext.getInstance();
        this.parentPanel = parentPanel;
        this.life = 3;
        panel = new JPanel();
        panel.setBounds(150, 500, 50, 50);
        panel.setOpaque(false);

        ImageIcon image = new ImageIcon("./img/default/ship.png");
        imageLabel = new JLabel(image);
        panel.add(imageLabel);
    }

    public void moveLeft() {
        if (game.isVertical()) {
            if (panel.getX() > 10) {
                moveX(-STEP);
            }
        } else {
            if (panel.getY() > 10) {
                moveY(-STEP);
            }
        }
    }

    public void moveRight() {
        if (game.isVertical()) {
            if (panel.getX() < game.getFrame().getWidth() - panel.getWidth() - 10) {
                moveX(STEP);
            }
        } else {
            if (panel.getY() < game.getFrame().getHeight() - panel.getHeight() - 50) {
                moveY(STEP);
            }
        }
    }

    private void moveX(int step) {
        Rectangle bounds = panel.getBounds();
        panel.setBounds(bounds.x + step, bounds.y, bounds.width, bounds.height);
    }

    private void moveY(int step) {
        Rectangle bounds = panel.getBounds();
        panel.setBounds(bounds.x, bounds.y + step, bounds.width, bounds.height);
    }

    public void destroyerFire() {
        int xPosition;
        int yPosition;
        if (game.isVertical()) {
            xPosition = (panel.getX()+panel.getWidth())/2 + panel.getX()/2;
            yPosition = panel.getY()-10;
        } else {
            xPosition = panel.getX() + panel.getWidth();
            yPosition = (panel.getY()+panel.getHeight())/2 + panel.getY()/2;
        }
        Projectile pro;
        try {
            Class<?> TypePorjectile = Class.forName("campus.valence.projectile."+projectile);
            Class[] paramType = new Class[2];
            paramType[0] = int.class;
            paramType[1] = int.class;
            pro = (Projectile) TypePorjectile.getDeclaredConstructor(paramType).newInstance(xPosition, yPosition);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
