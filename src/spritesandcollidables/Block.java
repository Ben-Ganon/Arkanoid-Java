//Ben Ganon 318731007

package spritesandcollidables;

import basicshapes.Ball;
import basicshapes.Line;
import basicshapes.Point;
import biuoop.DrawSurface;
import game.GameLevel;
import game.Velocity;
import game.listeners.HitListener;
import game.listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * SpritesAndCollidables.Block is the class that defines the behaviour of collidable rectangles in the game.
 *
 * @version 1.0
 * @author Ben Ganon 318731007
 * @since 2021-04-23
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle shape;
    private Velocity vel;

    /**
     * builds a new block from a rectangle and a velocity.
     * @param rect - the given rectangle to create a block from.
     * @param vel - the given velocity of the SpritesAndCollidables.Block.
     */
    public Block(Rectangle rect, Velocity vel) {
        this.shape = rect;
        this.vel = vel;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * @return returns the rectangle of this block.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * hit calculates what happens when an object with a certain velocity hits this block.
     * @param hitter - the ball that is hitting this block.
     * @param collisionPoint - the expected point of collision with this block.
     * @param currentVelocity - the velocity of the object colliding.
     * @return - returns the new velocity the hitting object should have.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

        this.notifyHit(hitter);

        //defining variables for line length and readability.
        Velocity returnVel = currentVelocity;
        //reversing the top and bottom lines because the screen draws the y axis in the opposite direction.
        Point upperLeft = this.shape.getLowerLeft();
        Point upperRight = this.shape.getLowerRight();
        Point lowerLeft = this.shape.getUpperLeft();
        Point lowerRight = this.shape.getUpperRight();

        Line lower = new Line(upperLeft, upperRight);
        Line upper = new Line(lowerLeft, lowerRight);
        Line right = new Line(upperRight, lowerRight);
        Line left = new Line(upperLeft, lowerLeft);
        /*
         * these if's find out which line the hit occured on.
         * then, according to the direction the hit came from(top, bottom, left, right),
         * we reverse the hitting objects velocity.
         * the Math.abs is to make sure the ball always goes in the correct direction no matter what -
         * even if the ball is moving away from the wall, the end output velocity will stay opposite the wall.
         */
        if (upper.hasPoint(collisionPoint)) {
            returnVel = new Velocity(returnVel.getDx(), Math.abs(returnVel.getDy()));
        }
        if (lower.hasPoint(collisionPoint)) {
            returnVel = new Velocity(returnVel.getDx(), -Math.abs(returnVel.getDy()));
        }
        if (right.hasPoint(collisionPoint)) {
            returnVel = new Velocity(Math.abs(returnVel.getDx()), returnVel.getDy());
        }
        if (left.hasPoint(collisionPoint)) {
            returnVel = new Velocity(-Math.abs(returnVel.getDx()), returnVel.getDy());
        }
        return returnVel;
    }

    /**
     * drawOn draws this block on to the screen as a rectangle.
     * @param surface - the desired output window.
     */
    public void drawOn(DrawSurface surface) {
        int x = (int) shape.getUpperLeft().getX();
        int y = (int) shape.getLowerLeft().getY();
        surface.setColor(this.shape.getColor());
        surface.fillRectangle(x, y, (int) shape.getWidth(), (int) shape.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle(x, y, (int) shape.getWidth(), (int) shape.getHeight());
    }
    /**
     * timePassed defines behaviour for the SpritesAndCollidables.Sprite interface - in this case we do nothing.
     */
    public void timePassed() {

    }
    /**
     * addToGame defines the behaviour for the Sprite, Collidable interfaces.
     * @param g - the game we want to add the Block to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * removes a block from all aspects of a game- the game environment and sprite collection.
     * @param gameLevel - the game to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * notifies a ball that it hit this block.
     * @param hitter - the hitting ball.
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * adds a new listener to this block.
     * @param hl - the listener we want to add.
     */
    public void addHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.add(hl);
        }
    }

    /**
     * removes a listener from this block.
     * @param hl - the listener to remove.
     */
    public void removeHitListener(HitListener hl) {
        if (hl != null) {
            this.hitListeners.remove(hl);
        }
    }
}
