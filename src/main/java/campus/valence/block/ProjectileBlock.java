package campus.valence.block;

import campus.valence.context.GameContext;
import campus.valence.projectile.Projectile;

public class ProjectileBlock extends Block{
    public ProjectileBlock(int frameWidth) {
        super(frameWidth, 2, "bonus1");
    }

    @Override
    public void blockAction(Projectile projectile) {
        GameContext game = super.getGame();
        this.setLife(getLife()-projectile.getStrength());
        if (getLife() <= 0) {
            int random = (int) (Math.random()*3);
            String newProjectile;
            switch (random) {
                case 1:
                    newProjectile = "NullProjectile";
                    break;
                case 2:
                    newProjectile = "StrongProjectile";
                    break;
                default:
                    newProjectile = "SimpleProjectile";
                    break;
            }
            game.getDestroyer().setProjectile(newProjectile);
            game.getBlocks().remove(this);
            this.setVisible(false);
        }
    }
}
