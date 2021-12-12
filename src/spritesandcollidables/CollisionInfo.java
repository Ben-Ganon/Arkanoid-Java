//Ben Ganon 318731007

package spritesandcollidables;

import basicshapes.Point;

/**
 * an instance of SpritesAndCollidables.CollisionInfo stores information of a collision between two objects.
 *
 * @version 1.0
 * @author Ben Ganon 318731007
 * @since 2021-04-23
 */
public class CollisionInfo {
    private Point collision;
    private Collidable collObject;

    /**
     * builfer for a SpritesAndCollidables.CollisionInfo instance.
     * @param collision - the point of collision.
     * @param e - the object being collided with.
     */
    public CollisionInfo(Point collision, Collidable e) {
        this.collision = collision;
        this.collObject = e;
    }

    /**
     * @return - the collision point.
     */
    public Point collisionPoint() {
        return this.collision;
    }

    /**
     * @return - the colliding object.
     */
    public Collidable collisionObject() {
        return this.collObject;
    }
}
