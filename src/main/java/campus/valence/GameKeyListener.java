package campus.valence;

import campus.valence.context.GameContext;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

    private GameContext game;

    public GameKeyListener(GameContext game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        Destroyer destroyer = game.getDestroyer();
        if (game.isVertical()){
            if (keyEvent.getKeyCode() == 37) {
                destroyer.moveLeft();
            } else if (keyEvent.getKeyCode() == 38) {
                destroyer.destroyerFire();
            } else if (keyEvent.getKeyCode() == 39) {
                destroyer.moveRight();
            }
        } else {
            if (keyEvent.getKeyCode() == 38) {
                destroyer.moveLeft();
            } else if (keyEvent.getKeyCode() == 39) {
                destroyer.destroyerFire();
            } else if (keyEvent.getKeyCode() == 40) {
                destroyer.moveRight();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
