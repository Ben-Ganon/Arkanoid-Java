//Ben Ganon 318731007

package game.listeners;

import basicshapes.Ball;
import game.Counter;
import game.GameLevel;
import spritesandcollidables.Block;
import spritesandcollidables.Paddle;

/**
 * BlockRemover removes blocks from the game when certain conditions are met.
 */
public class BlockRemover implements HitListener {
    static final int ONE = 1;

    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * standard builder for a new block remover.
     * @param gameLevel - the game we want to remove block from.
     * @param remainingBlocks - the current block counter from the main game to update.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(ONE);
    }
    @Override
    public void hitEvent(Paddle beingHit, Ball hitter) {

    }
}
