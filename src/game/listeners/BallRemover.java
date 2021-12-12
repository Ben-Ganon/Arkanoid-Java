//Ben Ganon 318731007

package game.listeners;

import basicshapes.Ball;
import game.Counter;
import game.GameLevel;
import spritesandcollidables.Block;
import spritesandcollidables.Paddle;

/**
 * BallRemover is a listener that removes balls when certain conditions are met.
 */
public class BallRemover implements HitListener {
    private static final int ONE = 1;
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * standard builder for a new listener that removes balls.
     * @param gameLevel - the game the listener should refer to.
     * @param remainingBlocks - the number of remaining blocks currently in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(ONE);
    }
    @Override
    public void hitEvent(Paddle paddle, Ball hitter) {

    }
}
