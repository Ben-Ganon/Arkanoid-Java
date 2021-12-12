//Ben Ganon 318731007

package game;
import basicshapes.Point;

/**
 * the Game.Velocity class handles the movement speed and direction of the BasicShapes.Ball class.
 *
 * @version 1.0
 * @author Ben Ganon 318731007
 * @since 2021-03-30
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * standard builder for the velocity object with dx and dy values.
     * @param dx - dx value to impliment.
     * @param dy - dy value to impliment.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;

    }

    /**
     * applyToPoint changes a points coordinates by a given velocity.
     * @param p - the point we want to move dx, dy distance.
     * @return returns the point after moving it.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * @return - returns the dx value of a velocity object.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return - returns the dy value of a velocity object.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * fromAngleAndSpeed receives angle and speed values and converts them to standard dx, dy measurements.
     * the calculation is relatively simple -
     * since the angle and distance is a side of a  straight angle triangle where dx, dy are the perpendicular sides,
     * we multiply the cos/sin of the angle to receive the ratio between sides,
     * and then multiply by length of the side (speed).
     * @param angle - the angle of the velocity to exchange.
     * @param speed - the speed(length) of the velocity vector.
     * @return returns a new velocity object crafted with the calculated dx, dy values.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(angle / 57.2958);
        double dy = speed * Math.cos(angle / 57.2958);
        return new Velocity(dx, -dy);
    }

    /**
     * returns the overall speed of an object - how much will the object move in the next frame?
     * @return - returns the speed of an object.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
}
