package campus.valence.context;


import campus.valence.Destroyer;
import campus.valence.block.Block;
import campus.valence.projectile.Projectile;

import javax.swing.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameContext {
    private static GameContext single_instance = null;
    private JFrame frame;
    private CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList<>();
    private JLabel lifeMenu;
    private JLabel scoreMenu;
    private Destroyer destroyer;
    private int score;

    public Destroyer getDestroyer() {
        return destroyer;
    }

    public void setDestroyer(Destroyer destroyer) {
        this.destroyer = destroyer;
    }

    public boolean isVertical() {
        return isVertical;
    }

    public void setVertical(boolean vertical) {
        isVertical = vertical;
    }

    private boolean isVertical;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
        if (this.score%100 ==0) {
            this.isVertical = !this.isVertical;
            this.frame.setSize(this.frame.getHeight(), this.frame.getWidth());
            JPanel destroyerPanel = this.destroyer.getPanel();
            for (Block block : blocks) {
                block.setVisible(false);
                blocks.remove(block);
            }
            if (isVertical) {
                destroyerPanel.setBounds(destroyerPanel.getY(), 500, destroyerPanel.getWidth(), destroyerPanel.getHeight());
            } else {
                destroyerPanel.setBounds(30, destroyerPanel.getX(), destroyerPanel.getWidth(), destroyerPanel.getHeight());
            }
        }
    }

    public JLabel getLifeMenu() {
        return lifeMenu;
    }

    public void setLifeMenu(JLabel lifeMenu) {
        this.lifeMenu = lifeMenu;
    }

    public JLabel getScoreMenu() {
        return scoreMenu;
    }

    public void setScoreMenu(JLabel scoreMenu) {
        this.scoreMenu = scoreMenu;
    }


    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public CopyOnWriteArrayList<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(CopyOnWriteArrayList<Block> blocks) {
        this.blocks = blocks;
    }

    public CopyOnWriteArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void setProjectiles(CopyOnWriteArrayList<Projectile> projectiles) {
        this.projectiles = projectiles;
    }

    private GameContext(int score)
    {
        this.score = score;
        this.isVertical = true;
    }

    public static GameContext getInstance()
    {
        if (single_instance == null)
            single_instance = new GameContext(0);

        return single_instance;
    }

    public static GameContext getInstance(int cheat)
    {
        if (single_instance == null)
            single_instance = new GameContext(cheat);

        return single_instance;
    }
}
