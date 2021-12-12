//Ben Ganon 318731007

package spritesandcollidables;

import basicshapes.Ball;
import basicshapes.Point;
import game.GameLevel;
import game.Velocity;

/**
 * the SpritesAndCollidables.Collidable interface defines the methods of collidable objects on the game screen.
 *
 * @version 1.0
 * @author Ben Ganon 318731007
 * @since 2021-04-23
 */
public interface Collidable {
    /**
     * returns the shape (in this case only rectangles) of the desired collidable object.
     * @return - returns the Rectagle of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * hit calculates the Game.Velocity of an object after hitting this collidable object.
     * @param collisionPoint  - the point the hitting object meets the collidable.
     * @param currentVelocity - the velocity of the hitting object.
     * @param hitter - the hitting ball that is hitting this collidable.
     * @return - returns the new Game.Velocity of the hitting object after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * adds this obecjt to the game g.
     * this process is different for ball. block, paddle.
     * @param g - the game we want to add the object to.
     */
    void addToGame(GameLevel g);
}
