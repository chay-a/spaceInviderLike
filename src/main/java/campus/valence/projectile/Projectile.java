package campus.valence.projectile;

import campus.valence.context.GameContext;
import campus.valence.movement.InMovement;

import javax.swing.*;
import java.awt.*;

public abstract class Projectile extends JPanel implements InMovement {
    private int strength;
    private GameContext game;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }


    public Projectile(int xPosition, int yPosition, int strength) {
        this.game = GameContext.getInstance();
        this.strength = strength;
        this.setBounds(xPosition, yPosition, 10, 10);
        this.setBackground(Color.magenta);
    }

    @Override
    public void outOfWindow() {
        if (this.getY() < 10) {
            game.getProjectiles().remove(this);
            this.setVisible(false);
        }
    }

    @Override
    public void move() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (game.isVertical()) {
                        setBounds(getX(), getY() - 1, getWidth(), getHeight());
                    } else {
                        setBounds(getX()+1, getY(), getWidth(), getHeight());
                    }
                    outOfWindow();
                    if (!isVisible()) {
                        return;
                    }
                    try {
                        Thread.sleep(100/60l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

}
