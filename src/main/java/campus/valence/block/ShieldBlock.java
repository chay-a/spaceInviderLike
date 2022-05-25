package campus.valence.block;

import campus.valence.context.GameContext;
import campus.valence.projectile.Projectile;

public class ShieldBlock extends Block{
    public ShieldBlock(int frameWidth) {
        super(frameWidth, 3, "bonus");
    }

    @Override
    public void blockAction(Projectile projectile) {
        GameContext game = super.getGame();
        this.setLife(getLife()-projectile.getStrength());
        if (getLife() <= 0) {
            game.getDestroyer().setLife(game.getDestroyer().getLife()+1);
            game.getLifeMenu().setText(Integer.toString(game.getDestroyer().getLife()));
            game.getBlocks().remove(this);
            this.setVisible(false);
        }
    }
}
