package campus.valence.projectile;

import campus.valence.movement.InMovement;

import javax.swing.*;
import java.awt.*;

public abstract class Projectile extends JPanel implements InMovement {
    public Projectile(int xPosition, int yPosition) {
        this.setBounds(xPosition, yPosition, 10, 10);
        this.setBackground(Color.magenta);
    }

    @Override
    public void move() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    setBounds(getX(), getY() - 5, getWidth(), getHeight());
                    try {
                        Thread.sleep(10000/60l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
