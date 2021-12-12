//Ben Ganon 318731007

package basicshapes;

/**
 * The BasicShapes.Point class defines a point in two dimensional space with coordinates.
 * BasicShapes.Point is used in the other classes: BasicShapes.Line, BasicShapes.Ball, and animation.
 *
 * @version  1.0
 * @author Ben Ganon 318731007
 * @since 2021-03-30
 */
public class Point {
    private double x;
    private double y;

    /**
     * Standard builder for a point in space, defining x and y in a given coordinate system.
     * @param x - X value.
     * @param y - Y value.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance receives an additional point and returns the distance between them.
     * @param other - the other point.
     * @return - the distance value.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * equals checks one point against another- if both share the same x and y values returns true, false if not.
     * @param other - the other point.
     * @return returns true\false.
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * @return returns the x value of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return returns the y value of the point.
     */
    public double getY() {
        return this.y;
    }
}
