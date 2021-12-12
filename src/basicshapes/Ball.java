//Ben Ganon 318731007
package basicshapes;

import biuoop.DrawSurface;
import game.GameLevel;
import game.GameEnvironment;
import game.Velocity;
import spritesandcollidables.Sprite;

import java.awt.Color;

/**
 * BasicShapes.
 * Ball is the class that creates and handles all 2D circles-
 * including handling of velocity and animation(moveOneStep).
 *
 * @version 2.0
 * @author Ben Ganon 318731007
 * @since 2021-03-30
 */
public class Ball implements Sprite {
    //the distance from the screen we want the balls to bounce.
    static final int SCREEN_DELTA = 0;
    //distance out out of the screen we want the balls to return from.
    static final int SCREEN_OUT_DELTA = 1;
    //a small buffer so the balls dont seem to cut the screen.
    static final int BUFFER = 2;
    //defining how smoll the exception is so we can adress it.
    static final int SMOL_BOLL = 3;
    //ten.
    static final int TEN = 10;
    //two.
    static final int TWO = 2;
    static final double CANVAS_WIDTH = 800;
    static final int ZERO = 0;
    static final double CANVAS_HEIGHT = 600;

    private int radius;
    //leftTopCanvas defines where is the upper left corner the ball should bounce off of.
    private int leftTopCanvas;
    //same as leftTop.
    private int rightBottomCanvas;
    //color of the ball
    private java.awt.Color color;
    //center of the ball
    private Point center;
    //the velocity of the ball
    private Velocity velocity;
    //the game the ball belongs to and is drawn in
    private GameEnvironment game;

    /**
     * basic constructor for a ball in space.
     * @param x - the x coordinate for the center of the ball.
     * @param y - the y coordinate for the center of the ball.
     * @param r - radius of the circle.
     * @param color - color of the circle.
     * @param game - the game the ball belongs to.
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment game) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.game = game;
    }

    /**
     * alternative builder with a point instead of x, y.
     *
     * @param p - center point of the ball.
     * @param r - radius of the circle.
     * @param color - color of the circle.
     * @param game - the game the ball belongs to.
     */
    public Ball(Point p, int r, java.awt.Color color, GameEnvironment game) {
        this.center = p;
        this.radius = r;
        this.color = color;
        this.game = game;
    }

    /**
     * constructor for a ball with a point instead of x and y values.
     * essentially the same, just for ease of use if one wishes to make a circle with a point.
     * @param p - center point.
     * @param r - radius.
     * @param color - color.
     */
    public Ball(Point p, int r, java.awt.Color color) {
        this.center = p;
        this.radius = r;
        this.color = color;
    }

    /**
     * @return (int) x value of the center point of the circle.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * @return (int) y value of the center point of the circle.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * @return returns the center point of the ball.
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * @return (int) right bottom canvas value- where the ball is supposed to bounce off of.
     */
    public int getRightBottomCanvas() {
        return this.rightBottomCanvas;
    }

    /**
     * @return (int) left top canvas value- where the ball is supposed to bounce off of.
     */
    public int getLeftTopCanvas() {
        return this.leftTopCanvas;
    }

    /**
     * setCanvas sets two values as a circles canvas limits.
     * this means that when we animate a ball the ball already holds the information on where is its bouncing limits.
     * @param leftTop - the desired x,y value of the left top corner of the canvas.
     * @param rightBottom -the desired x,y value of the right bottom corner of the canvas.
     */
    public void setCanvas(int leftTop, int rightBottom) {
        this.leftTopCanvas = leftTop;
        this.rightBottomCanvas = rightBottom;
    }

    /**
     * @return returns the balls radius.
     */
    public int getSize() {
        return radius;
    }

    /**
     * @return returns the balls color.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * drawOn handles drawing a circle and filling it with the balls color.
     * @param surface - the draw surface the method draws on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    /**
     * setting a balls velocity via an existing velocity object.
     * @param v the desired velocity to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * setting a balls velocity via dx and dy values.
     * @param dx - dx value to set.
     * @param dy - dy value to set.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return (int) returns velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moveOneStepCanvas moves the circle its called on in the direction and speed defined by its velocity.
     * if the method detects the ball is too close to the edge of the canvas,
     * it will reverse its direction to make it appear as if the ball is touching the wall and "bouncing" off.
     * this method assumes a square canvas, so there are only two values required:
     *                  the point the canvas starts, and the point the canvas ends.
     * for example, the int leftTopCorner represents the BasicShapes.Point (leftTopCorner, leftTopCorner).
     * @param leftTopCorner - the top left corner of the canvas -
     *                      defines x and y values where the ball should not pass on the left and on the top
     * @param rightBottomCorner - the bottom right corner of the canvas -
     *                       defines x and y values where the ball should not pass on the right and on the bottom.
     */
    public void moveOneStepCanvas(int leftTopCorner, int rightBottomCorner) {
        /*
         * radiusA defines how far we want to keep the center from the edge,
         * plus a buffer for visual clarity and the "bounce" effect.
         */
        int radiusA = this.getSize() + BUFFER;
        double x = this.center.getX();
        double y = this.center.getY();
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        //exception for small balls that move too fast to be bounced accurately and must have a larger buffer.
        if (this.radius < SMOL_BOLL) {
            radiusA += BUFFER * TEN;
        }
        //if (this.radius >= 100) {
        //    radiusA -= BUFFER * TEN ;
        //}
        /*
         * series of ifs designed to find out if the ball is near any of the four edges of the screen:
         * top, bottom, left, right.
         * we check this via the leftTopCorner and rightBottomCorner values we received,
         * as well as the x and y values of the center point.
         * then, if for example the ball is too close to the bottom edge, we reverse its dy velocity value -
         * via the Math.abs function we can make sure the dy value is positive, then we apply a negative to that
         * so that the ball is assuredly moving up, away from the edge.
         * the same process is applied to all borders.
         * all in all, the final result is so that whenever the ball is too close to the border,
         * it will reverse direction.
         */
        if (x < leftTopCorner + radiusA) {
            this.setVelocity(Math.abs(dx), dy);
        }
        if (x > rightBottomCorner - radiusA) {
            this.setVelocity(-Math.abs(dx), dy);
        }
        if (y < leftTopCorner + radiusA) {
            this.setVelocity(this.velocity.getDx(), Math.abs(this.velocity.getDy()));
        }
        if (y > rightBottomCorner - radiusA) {
            this.setVelocity(this.velocity.getDx(), -Math.abs(this.velocity.getDy()));
        }
        //finally we apply the velocity of the ball to the center point, moving it int the correct direction.
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * moveOneStepColl moves the ball one step in its velocity.
     * if the ball will encounter a collidable object in one Game,
     * Velocity cycle(with a buffer thats half the ball's radius),
     * the ball will revers its Game.Velocity according to the .hit method.
     */
    public void moveOneStepColl() {
        /*
         * the following if's are designed to find out if the ball is out of the screen borders,
         * and if so return the ball to the game screen and reverse its velocity.
         */
        //if the ball is left of the left border, teleport it back to a close position and reverse the dx.
        if (this.getX() < ZERO) {
            this.center = new Point(TWO, this.center.getY());
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (this.getX() > CANVAS_WIDTH) {
            this.center = new Point(CANVAS_WIDTH - TWO, this.center.getY());
            this.velocity = new Velocity(-this.velocity.getDx(), this.velocity.getDy());
        }
        if (this.getY() < ZERO) {
            this.center = new Point(this.center.getX(), ZERO + TWO);
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }
        if (this.getY() > CANVAS_HEIGHT) {
            this.center = new Point(this.center.getX(), ZERO + TWO);
            this.velocity = new Velocity(this.velocity.getDx(), -this.velocity.getDy());
        }
        //creating the ball trajectory over the next frame with the detectionHelper method.
        Line trajectory = new Line(this.center, detectionHelper(this.velocity));
        //check to see if the ball will encounter an object during its trajectory.
        if (this.game.getClosestCollison(trajectory) != null) {
            Point collision = this.game.getClosestCollison(trajectory).collisionPoint();
            this.setVelocity(
                    this.game.getClosestCollison(trajectory).collisionObject().hit(
                                                                            this, collision, this.velocity));
            this.center = this.velocity.applyToPoint(this.center);
            //if not, apply the velocity as usual and let the ball advance.
        } else {
            this.center = this.velocity.applyToPoint(this.center);
        }
    }

    /**
     * detectionHelper returns the point of the end of a certain balls trajectory.
     * @param vel - the velocity we want to check the ball for.
     * @return returns a point after applying the new velcoity to the center of the ball.
     */
    public Point detectionHelper(Velocity vel) {
        //the sign of the dx and dy of the current ball - its direction in general.
       int signDx = Integer.signum((int) vel.getDx());
       int signDy = Integer.signum((int) vel.getDy());
       //speedCheck is the distance we want the ball to keep from collidable objects.
       double speedCheck = this.getSize() +  this.velocity.getSpeed() / TWO;
       //we create a new velocity according to the desired distance.
       Velocity ret = new Velocity(signDx * speedCheck, signDy * speedCheck);
       /*
        * we return a new point - our new velocity applied to the balls center.
        * or - approximately where the ball will be in the next round, plus a small buffer
        */
       return ret.applyToPoint(this.center);
    }

    /**
     * timePassed defines behaviour for the SpritesAndCollidables.
     * Sprite interface - in this case we simply move the ball one step.
     */
    public void timePassed() {
        this.moveOneStepColl();
    }

    /**
     * addToGame defines the behaviour for the SpritesAndCollidables.Sprite interface.
     * @param g - the game we want to add the ball to.
     */
    public void addToGame(GameLevel g) {
        this.game = g.getEnvironment();
        g.addSprite(this);
    }

    /**
     * removes this sprite from the game.
     * @param g - the game to remove from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

}
