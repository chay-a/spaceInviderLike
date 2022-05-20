package campus.valence;

import campus.valence.block.Block;
import campus.valence.block.SimpleBlock;
import campus.valence.projectile.Projectile;

import javax.swing.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class SpaceCampus {

    private JFrame frame;
    private JPanel panel;
    private Destroyer destroyer;
    private CopyOnWriteArrayList<Block> blocks = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<Projectile> projectiles = new CopyOnWriteArrayList<>();

    SpaceCampus() {
        panel = new JPanel();
        panel.setFocusable(true);
        panel.setLayout(null);

        frame = new JFrame();
        frame.setTitle("SPACE CAMPUS");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);

        createDestroyer();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    createBlock();
                    for (Block block : blocks) {
                        block.move();
                        if (block.getY() > frame.getHeight()) {
                            blocks.remove(block);
                            block.setVisible(false);
                        }
                    }
                    for (Projectile projectile : projectiles) {
                        if (projectile.getY() < 10) {
                            projectiles.remove(projectile);
                            projectile.setVisible(false);
                        }
                    }
                    try {
                        Thread.sleep(200000/60l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    private void createBlock() {
        Block block = new SimpleBlock(frame.getWidth());
        blocks.add(block);
        panel.add(block);
        panel.repaint();
    }

    public void launch() {
        this.frame.setVisible(true);
    }

    private void createDestroyer() {
        destroyer = new Destroyer(this.panel, projectiles);
        this.panel.add(destroyer.getPanel());
        this.panel.addKeyListener(new GameKeyListener(destroyer));
    }
}
