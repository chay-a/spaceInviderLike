package campus.valence.context;


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
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    private GameContext()
    {
        this.score =0;
    }

    public static GameContext getInstance()
    {
        if (single_instance == null)
            single_instance = new GameContext();

        return single_instance;
    }
}
