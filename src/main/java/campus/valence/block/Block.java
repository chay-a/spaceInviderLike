package campus.valence.block;

import campus.valence.context.GameContext;
import campus.valence.movement.InMovement;
import campus.valence.projectile.Projectile;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static campus.valence.SpaceCampus.destroyer;

public abstract class Block extends JPanel implements InMovement {
    private int life;
    private boolean isAlreadyDidDamage;
    private GameContext game;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Block(int frameWidth, int life) {
        this.game = GameContext.getInstance();
        this.life = life;
        this.isAlreadyDidDamage = false;
        Random r = new Random();
        int low = 10;
        int high = frameWidth-40;
        int result = r.nextInt(high-low) + low;
        this.setBounds(result, 0, 30, 30);
        this.setBackground(Color.BLUE);
    }

    @Override
    public void outOfWindow() {
        if (this.getY() > game.getFrame().getHeight()) {
            this.setVisible(false);
            game.getBlocks().remove(this);
            if (!isAlreadyDidDamage) {
                isAlreadyDidDamage = true;
                destroyer.setLife(destroyer.getLife() - 1);
                game.getLifeMenu().setText(Integer.toString(destroyer.getLife()));
                if (destroyer.getLife() <= 0) {
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public void move(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    setBounds(getX(), getY() + 1, getWidth(), getHeight());
                    checkIntersect();
                    outOfWindow();
                    if (!isVisible()) {
                        return;
                    }
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
        for (Projectile projectile: game.getProjectiles()) {
            if (this.getBounds().intersects(projectile.getBounds())) {
                this.setLife(getLife()-projectile.getStrength());
                game.getProjectiles().remove(projectile);
                projectile.setVisible(false);
                if (getLife() <= 0) {
                    game.setScore(game.getScore()+1);
                    game.getScoreMenu().setText(Integer.toString(game.getScore()));
                    game.getBlocks().remove(this);
                    this.setVisible(false);
                }
            }
        }
    }
}
