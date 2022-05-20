package campus.valence;

import campus.valence.block.Block;
import campus.valence.block.SimpleBlock;

import javax.swing.*;

public class SpaceCampus {

    private JFrame frame;
    private JPanel panel;
    private Destroyer destroyer;

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
                    Block block = createBlock();
                    block.move();
                    try {
                        Thread.sleep(200000/60l);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    private Block createBlock() {
        Block block = new SimpleBlock(frame.getWidth());
        panel.add(block);
        panel.repaint();
        return block;
    }

    public void launch() {
        this.frame.setVisible(true);
    }

    private void createDestroyer() {
        destroyer = new Destroyer();
        this.panel.add(destroyer.getPanel());
        this.panel.addKeyListener(new GameKeyListener(destroyer));
    }
}
