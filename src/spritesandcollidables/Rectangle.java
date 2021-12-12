//Ben Ganon 318731007

package spritesandcollidables;

import basicshapes.Line;
import basicshapes.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the SpritesAndCollidables.Rectangle class defines the behaviour of the rectangle shape in the game.
 *
 * @version 1.0
 * @author Ben Ganon 318731007
 * @since 2021-04-23
 */
public class Rectangle {
    private Point upperLeft;
    private Point upperRight;
    private Point lowerLeft;
    private Point lowerRight;
    private double width;
    private double height;
    private Color color;

    /**
     * builds a rectangle from upperLeft to upperLeft + width, upperLeft - height.
     * @param upperLeft - the left top point of the rectangle.
     * @param width - width of the rectangle.
     * @param height - height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.lowerRight = new Point(upperLeft.getX() + width, upperLeft.getY() - height);
        this.lowerLeft = new Point(upperLeft.getX(), upperLeft.getY() - height);
    }

    /**
     * sets the color of the rectangle to color1.
     * @param color1 - the desired color of the rectangle.
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * @return - returns the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return - returns the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return - returns the color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return - returns the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * @return - returns the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * @return - returns the lower left point of the rectangle.
     */
    public Point getLowerLeft() {
        return this.lowerLeft;
    }

    /**
     * @return - returns the lower right point of the rectangle.
     */
    public Point getLowerRight() {
        return this.lowerRight;
    }

    /**
     * sets the upper left point of the rectangle to uLeft.
     * @param uLeft - the point to move the point to.
     */
    public void setUpperLeft(Point uLeft) {
        this.upperLeft = uLeft;
    }
    /**
     * sets the upper right point of the rectangle to uLeft.
     * @param uRight - the point to move the point to.
     */
    public void setUpperRight(Point uRight) {
        this.upperRight = uRight;
    }
    /**
     * sets the lower left point of the rectangle to uLeft.
     * @param lLeft - the point to move the point to.
     */
    public void setLowerLeft(Point lLeft) {
        this.lowerLeft = lLeft;
    }
    /**
     * sets the lower right point of the rectangle to uLeft.
     * @param lRight - the point to move the point to.
     */
    public void setLowerRight(Point lRight) {
        this.lowerRight = lRight;
    }

    /**
     * returns a list of the intersection points of a line with this rectangle.
     * @param line - the line to check against.
     * @return - returns a list of all points of intersections.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        Line upper = new Line(upperLeft, upperRight);
        Line lower = new Line(lowerLeft, lowerRight);
        Line right = new Line(upperRight, lowerRight);
        Line left = new Line(upperLeft, lowerLeft);
        //checking if the line intersecting each side of the square.
        if (line.isIntersecting(upper)) {
            intersections.add(line.intersectionWith(upper));
        }
        if (line.isIntersecting(lower)) {
            intersections.add(line.intersectionWith(lower));
        }
        if (line.isIntersecting(right)) {
            intersections.add(line.intersectionWith(right));
        }
        if (line.isIntersecting(left)) {
            intersections.add(line.intersectionWith(left));
        }
        return intersections;
    }
}
