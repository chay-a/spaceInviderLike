package campus.valence.projectile;

import campus.valence.movement.InMovement;

import javax.swing.*;
import java.awt.*;

public abstract class Projectile extends JPanel implements InMovement {
    private int strength;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }


    public Projectile(int xPosition, int yPosition, int strength) {
        this.strength = strength;
        this.setBounds(xPosition, yPosition, 10, 10);
        this.setBackground(Color.magenta);
    }

    @Override
    public void move() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    setBounds(getX(), getY() - 1, getWidth(), getHeight());
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
