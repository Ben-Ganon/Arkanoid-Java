//Ben Ganon 318731007

package spritesandcollidables;

import basicshapes.Ball;
import basicshapes.Line;
import basicshapes.Point;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.Velocity;
import game.listeners.HitListener;
import game.listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the Paddle class defines the behaviour of the special Block Paddle which can move on the screen.
 *
 * @version 1.0
 * @author Ben Ganon 318731007
 * @since 2021-04-23
 */
public class Paddle implements Collidable, Sprite, HitNotifier {
    static final int PADDLE_PARTS = 5;
    static final int EDGE_BUFFER = 8;
    static final int TWO = 2;
    static final int THREE = 3;
    static final int FOUR = 4;
    static final int PADDLE_ANGLE = 30;
    static final int CIRCLE = 360;
    static final int EDGE_WIDTH = 20;
    static final int PADDLE_SPEED = 12;
    static final double CANVAS_WIDTH = 800;

    private List<HitListener> hitListeners;
    private KeyboardSensor keyboard;
    private Rectangle shape;

    /**
     * builder for a new paddle.
     * @param rect - the paddle shape(rectangle).
     * @param sensor - the sensor responsible for the movement of the paddle.
     */
    public Paddle(Rectangle rect, KeyboardSensor sensor) {
        this.shape = rect;
        this.keyboard = sensor;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * moves the paddle left by a fixed amount per click.
     * if the paddle encounters the left edge it will stop.
     */
    public void moveLeft() {
        Rectangle rec = this.shape;
        //checking if the paddle is right of left edge.
        if (rec.getUpperLeft().getX() > EDGE_WIDTH + EDGE_BUFFER) {
            rec.setUpperLeft(new Point(rec.getUpperLeft().getX() - PADDLE_SPEED, rec.getUpperLeft().getY()));
            rec.setUpperRight(new Point(rec.getUpperRight().getX() - PADDLE_SPEED,
                    rec.getUpperRight().getY()));
           rec.setLowerLeft(new Point(rec.getLowerLeft().getX() - PADDLE_SPEED, rec.getLowerLeft().getY()));
            rec.setLowerRight(new Point(rec.getLowerRight().getX() - PADDLE_SPEED, rec.getLowerRight().getY()));
        }
    }

    /**
     * moves the paddle right by a fixed amount per click.
     * if the paddle encounters the right border it will stop.
     */
    public void moveRight() {
        Rectangle rec = this.shape;
        //checking if the paddle is past the left edge minus a buffer for visual clarity.
        if (rec.getUpperRight().getX() < CANVAS_WIDTH - EDGE_WIDTH - EDGE_BUFFER) {
            //moving every point int the paddle rectangle.
            rec.setUpperLeft(new Point(rec.getUpperLeft().getX() + PADDLE_SPEED, rec.getUpperLeft().getY()));
            rec.setUpperRight(new Point(rec.getUpperRight().getX() + PADDLE_SPEED, rec.getUpperRight().getY()));
            rec.setLowerLeft(new Point(rec.getLowerLeft().getX() + PADDLE_SPEED, rec.getLowerLeft().getY()));
            rec.setLowerRight(new Point(rec.getLowerRight().getX() + PADDLE_SPEED, rec.getLowerRight().getY()));
        }
    }

    /**
     * @return - returns the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return shape;
    }

    /**
     * the hit method calculates a balls encounter with the paddle and calculates the ball velocity after the hit.
     * @param collisionPoint  - the point the hitting object meets the collidable.
     * @param currentVelocity - the velocity of the hitting object.
     * @param hitter - the ball that is hitting this padle.
     * @return - returns the new velocity after the ball has hit the paddle.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);

        //creating a temp velocity to manipulate.
        Velocity returnVel = currentVelocity;
        //defining variables for line length and readability.
        Point upperLeft = this.shape.getLowerLeft();
        Point upperRight = this.shape.getLowerRight();
        Point lowerLeft = this.shape.getUpperLeft();
        Point lowerRight = this.shape.getUpperRight();

        Line upper = new Line(upperLeft, upperRight);
        Line lower = new Line(lowerLeft, lowerRight);
        Line right = new Line(upperRight, lowerRight);
        Line left = new Line(upperLeft, lowerLeft);

        Point paddle1Right = new Point(upper.length() / PADDLE_PARTS + upperLeft.getX(), upperLeft.getY());
        Point paddle2Right = new Point(TWO * (upper.length() / PADDLE_PARTS) + upperLeft.getX(), upperLeft.getY());
        Point paddle3Right = new Point(THREE * (upper.length() / PADDLE_PARTS) + upperLeft.getX(), upperLeft.getY());
        Point paddle4Right = new Point(FOUR * (upper.length() / PADDLE_PARTS) + upperLeft.getX(), upperLeft.getY());

        Line paddle1 = new Line(upperLeft, paddle1Right);
        Line paddle2 = new Line(paddle1Right, paddle2Right);
        Line paddle3 = new Line(paddle2Right, paddle3Right);
        Line paddle4 = new Line(paddle3Right, paddle4Right);
        Line paddle5 = new Line(paddle4Right, upperRight);


        double ballSpeed = currentVelocity.getSpeed();
        //checking where the ball hit, and returning a new velocity
        if (paddle1.hasPoint(collisionPoint)) {
            //fixed angle for hits on the edges.
            returnVel = Velocity.fromAngleAndSpeed(CIRCLE - TWO * PADDLE_ANGLE, ballSpeed);
        }
        if (paddle2.hasPoint(collisionPoint)) {
            returnVel = Velocity.fromAngleAndSpeed(CIRCLE - PADDLE_ANGLE, ballSpeed);
        }
        if (paddle3.hasPoint(collisionPoint)) {
            //if the ball hits the middle of the paddle the return velocity will be as if the ball hit a regular block.
            returnVel = new Velocity(returnVel.getDx(), -Math.abs(returnVel.getDy()));
        }
        if (paddle4.hasPoint(collisionPoint)) {
            returnVel = Velocity.fromAngleAndSpeed(PADDLE_ANGLE, ballSpeed);
        }
        if (paddle5.hasPoint(collisionPoint)) {
            returnVel = Velocity.fromAngleAndSpeed(TWO * PADDLE_ANGLE, ballSpeed);
        }
        //if the ball hits the lower or side part of the paddle the return velocity is as if the ball hit a block.
        if (lower.hasPoint(collisionPoint)) {
            returnVel = new Velocity(returnVel.getDx(), Math.abs(returnVel.getDy()));
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
     * adds the paddle to a certain game.
     * @param g - the game we want to add the object to.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * draws on the paddle to the screen.
     * @param d - the drawsurface we want to draw to.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.shape.getColor());
        d.fillRectangle((int) shape.getUpperLeft().getX(), (int) shape.getLowerRight().getY(),
                                                (int) shape.getWidth(), (int) shape.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) shape.getUpperLeft().getX(), (int) shape.getLowerRight().getY(),
                                                (int) shape.getWidth(), (int) shape.getHeight());
    }

    /**
     * notifying the paddle time has passed, checking if the keyboard is pressed and moving the ball accordingly.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * notifies this ball that it hit this paddle.
     * @param hitter - the hitting ball
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
        this.hitListeners.add(hl);
    }

    /**
     * removes a listener from this block.
     * @param hl - the listener to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
