package campus.valence.block;

import campus.valence.movement.InMovement;
import campus.valence.projectile.Projectile;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static campus.valence.SpaceCampus.blocks;
import static campus.valence.SpaceCampus.projectiles;

public abstract class Block extends JPanel implements InMovement {
    private int life;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Block(int frameWidth, int life) {
        this.life = life;
        Random r = new Random();
        int low = 10;
        int high = frameWidth-40;
        int result = r.nextInt(high-low) + low;
        this.setBounds(result, 0, 30, 30);
        this.setBackground(Color.BLUE);
    }

    @Override
    public void move() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    setBounds(getX(), getY() + 1, getWidth(), getHeight());
                    checkIntersect();
                    try {
                        Thread.sleep(1000/60l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void checkIntersect () {
        for (Projectile projectile: projectiles) {
            if (this.getBounds().intersects(projectile.getBounds())) {
                this.setLife(getLife()-projectile.getStrength());
                projectiles.remove(projectile);
                projectile.setVisible(false);
                if (getLife() <= 0) {
                    blocks.remove(this);
                    this.setVisible(false);
                }
            }
        }
    }
}
