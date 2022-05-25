package campus.valence;

import campus.valence.block.*;
import campus.valence.context.GameContext;

import javax.swing.*;
import java.awt.*;


public class SpaceCampus {

    private JLayeredPane panel;
    private GameContext game;
    public static Destroyer destroyer;

    SpaceCampus(int cheat) {
        game = GameContext.getInstance(cheat);
        panel = new JLayeredPane() {
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                //draw background image
                Graphics2D g2d = (Graphics2D) g.create();
                ImageIcon imageIcon = new ImageIcon("./img/default/background.jpeg");
                g2d.drawImage(imageIcon.getImage(), 0, 0, this);
                g2d.dispose();
            }
        };
        panel.setFocusable(true);
        panel.setLayout(null);

        JFrame frame = new JFrame();
        frame.setTitle("SPACE CAMPUS");
        frame.setSize(400, 600);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        game.setFrame(frame);
        createDestroyer();

        JPanel info = new JPanel();
        info.setBounds(0, 0, 50, 100);
        info.setOpaque(true);
        info.setBackground(new Color(255, 255, 255, 150));
        JLabel life = new JLabel();
        ImageIcon lifeIcon = new ImageIcon("./img/default/heart.png");
        life.setIcon(lifeIcon);
        life.setText(Integer.toString(destroyer.getLife()));
        info.add(life);
        game.setLifeMenu(life);
        JLabel score = new JLabel();
        ImageIcon scoreIcon = new ImageIcon("./img/default/death.png");
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
        int maxPixel;
        if (game.isVertical()) {
            maxPixel = game.getFrame().getWidth();
        } else {
            maxPixel= game.getFrame().getHeight();
        }
        int random = (int) (Math.random()*5);
        Block block;
        switch (random) {
            case 1:
                block = new MassBlock(maxPixel);
                break;
            case 2:
                block = new BigBlock(maxPixel);
                break;
            case 3:
                block = new ShieldBlock(maxPixel);
                break;
            case 4:
                block = new ProjectileBlock(maxPixel);
                break;
            default:
                block = new SimpleBlock(maxPixel);
        }

        game.getBlocks().add(block);
        panel.add(block, 2, 0);
        panel.repaint();
    }

    public void launch() {
        game.getFrame().setVisible(true);
    }

    private void createDestroyer() {
        destroyer = new Destroyer(this.panel);
        this.game.setDestroyer(destroyer);
        this.panel.add(destroyer.getPanel());
        this.panel.addKeyListener(new GameKeyListener(game));
    }
}
