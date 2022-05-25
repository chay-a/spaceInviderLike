package campus.valence.projectile;

import campus.valence.context.GameContext;
import campus.valence.movement.InMovement;

import javax.swing.*;

public abstract class Projectile extends JPanel implements InMovement {
    private int strength;
    private GameContext game;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }


    public Projectile() {
    }

    public Projectile(int xPosition, int yPosition, int strength, String imagePro) {
        this.game = GameContext.getInstance();
        this.strength = strength;
        this.setOpaque(false);
        ImageIcon image;
        if (game.isVertical()){
            image = new ImageIcon("./img/default/"+imagePro+".png");
        } else {
            image = new ImageIcon("./img/default/"+imagePro+"-horizontal.png");
        }
        this.setBounds(xPosition, yPosition, 20, 20);
        JLabel label = new JLabel(image);
        this.add(label);
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
