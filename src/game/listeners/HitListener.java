//Ben Ganon 318731007

package game.listeners;
import basicshapes.Ball;
import spritesandcollidables.Block;
import spritesandcollidables.Paddle;

/**
 * interface defining methods for different listeners.
 */
public interface HitListener {
    /**
     * hitEvent defines what the listener should do when a block is being hit -
     * print a message, update the score, remove a block, etc.
     * @param beingHit - the collidable being hit - Block.
     * @param hitter - the ball that is hitting the object.
     */
    void hitEvent(Block beingHit, Ball hitter);

    /**
     * hitEvent relating to the event of a paddle being hit instead of a regular block.
     * @param beingHit - the collidable being hit - Block.
     * @param hitter - the ball that is hitting the object.
     */
    void hitEvent(Paddle beingHit, Ball hitter);
}
