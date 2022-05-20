package campus.valence.block;

import campus.valence.movement.InMovement;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
                    setBounds(getX(), getY() + 5, getWidth(), getHeight());
                    try {
                        Thread.sleep(10000/60l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public boolean intersect(InMovement other) {
        JPanel otherPanel = (JPanel) other;
        return !(otherPanel.getX()+ otherPanel.getWidth() <= this.getX()) || !(otherPanel.getX() >= this.getX()+this.getWidth()) || !(otherPanel.getY()+otherPanel.getHeight() <= this.getY()) || !(otherPanel.getY() >= this.getY()+this.getHeight());
    }
}
