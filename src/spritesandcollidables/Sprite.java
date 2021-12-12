//Ben Ganon 318731007

package spritesandcollidables;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * the SpritesAndCollidables.Sprite interface defines the behaviour of draw-able objects of the game.
 */
public interface Sprite {
    /**
     * drawOn handles drawing of the object(either rectangle or ball) to the screen.
     * @param d - the surface t draw to.
     */
    void drawOn(DrawSurface d);

    /**
     * timePassed tells an object to run one frame according to its defined behaviour.
     */
    void timePassed();

    /**
     * adds a sprite to the sprite array of a given game.
     * @param g - the game to add the sprite to.
     */
    void addToGame(GameLevel g);
}
