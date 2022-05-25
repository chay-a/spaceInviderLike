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

    public GameContext getGame() {
        return game;
    }



    public Block(int frameWidth, int life, String imageType) {
        this.game = GameContext.getInstance();
        this.life = life;
        this.isAlreadyDidDamage = false;
        this.setOpaque(false);
        Random r = new Random();
        int low = 10;
        int high;
        if (game.isVertical()){
            high =frameWidth-40;
        } else {
            high = frameWidth-150;
        }
        ImageIcon image;
        int result = r.nextInt(high-low) + low;
        if (game.isVertical()) {
            image = new ImageIcon("./img/default/"+imageType+".png");
            this.setBounds(result, 0, 40, 40);
        } else {
            image = new ImageIcon("./img/default/"+imageType+"-horizontal.png");
            this.setBounds(game.getFrame().getWidth(), result, 40, 40);
        }
        JLabel labelImage = new JLabel(image);
        this.add(labelImage);
    }

    @Override
    public void outOfWindow() {
        if ((game.isVertical() && this.getY() > game.getFrame().getHeight()) || (!game.isVertical() && this.getX() < 0)) {
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
                    if (game.isVertical()) {
                        setBounds(getX(), getY() + 1, getWidth(), getHeight());
                    } else {
                        setBounds(getX()-1, getY(), getWidth(), getHeight());
                    }
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
                blockAction(projectile);
                game.getProjectiles().remove(projectile);
                projectile.setVisible(false);
            }
        }
    }

    public abstract void blockAction(Projectile projectile);
}
