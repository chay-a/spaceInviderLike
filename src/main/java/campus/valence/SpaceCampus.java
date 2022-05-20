package campus.valence;

import campus.valence.block.Block;
import campus.valence.block.SimpleBlock;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SpaceCampus {

    private JFrame frame;
    private JPanel panel;
    private Destroyer destroyer;
    private List<Block> blocks = new ArrayList<>();

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
        destroyer = new Destroyer(this.panel);
        this.panel.add(destroyer.getPanel());
        this.panel.addKeyListener(new GameKeyListener(destroyer));
    }
}
