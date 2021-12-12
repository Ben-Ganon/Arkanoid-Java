//Ben Ganon 318731007

package game.listeners;

import basicshapes.Ball;
import game.Counter;
import spritesandcollidables.Block;
import spritesandcollidables.Paddle;

/**
 * ScoreTrackingListener updates the score in the main game when certain actions happen.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int BLOCK_POINTS = 5;
    private Counter currentScore;

    /**
     * standard builder for a new score tracking listener.
     * @param scoreCounter - the score counter from the game to update.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * @return - returns current score(Counter).
     */
    public Counter getCurrentScore() {
        return this.currentScore;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(BLOCK_POINTS);
    }
    @Override
    public void hitEvent(Paddle beingHit, Ball hitter) {

    }
}
