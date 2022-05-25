package campus.valence.block;

import campus.valence.context.GameContext;
import campus.valence.projectile.Projectile;

public abstract class EnemyBlock extends Block{
    public EnemyBlock(int frameWidth, int life, String imageType) {
        super(frameWidth, life, imageType);
    }

    @Override
    public void blockAction(Projectile projectile) {
        GameContext game = super.getGame();
        this.setLife(getLife()-projectile.getStrength());
        if (getLife() <= 0) {
            game.setScore(game.getScore()+1);
            game.getScoreMenu().setText(Integer.toString(game.getScore()));
            game.getBlocks().remove(this);
            this.setVisible(false);
        }
    }
}
