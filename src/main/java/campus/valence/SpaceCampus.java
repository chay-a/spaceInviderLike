package campus.valence;

import campus.valence.block.Block;
import campus.valence.block.SimpleBlock;
import campus.valence.context.GameContext;

import javax.swing.*;


public class SpaceCampus {

    private JLayeredPane panel;
    private GameContext game;
    public static Destroyer destroyer;

    SpaceCampus() {
        game = GameContext.getInstance();
        panel = new JLayeredPane();
        panel.setFocusable(true);
        panel.setLayout(null);

        JFrame frame = new JFrame();
        frame.setTitle("SPACE CAMPUS");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        game.setFrame(frame);
        createDestroyer();

        JPanel info = new JPanel();
        info.setBounds(0, 0, 50, 100);
        info.setOpaque(false);
        JLabel life = new JLabel();
        ImageIcon lifeIcon = new ImageIcon("heart.png");
        life.setIcon(lifeIcon);
        life.setText(Integer.toString(destroyer.getLife()));
        info.add(life);
        game.setLifeMenu(life);
        JLabel score = new JLabel();
        ImageIcon scoreIcon = new ImageIcon("death.png");
        score.setIcon(scoreIcon);
        score.setText(Integer.toString(game.getScore()));
        info.add(score);
        game.setScoreMenu(score);
        panel.add(info, 5, 0);



        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    createBlock();
                    for (Block block : game.getBlocks()) {
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
        Block block = new SimpleBlock(game.getFrame().getWidth());
        game.getBlocks().add(block);
        panel.add(block, 2, 0);
        panel.repaint();
    }

    public void launch() {
        game.getFrame().setVisible(true);
    }

    private void createDestroyer() {
        destroyer = new Destroyer(this.panel);
        this.panel.add(destroyer.getPanel());
        this.panel.addKeyListener(new GameKeyListener(destroyer));
    }
}
