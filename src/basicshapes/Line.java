//Ben Ganon 318731007

package basicshapes;

import spritesandcollidables.Rectangle;

/**
 * The BasicShapes.Line class defines the behavior and parameters of a line in space comprised of two Points.
 * this class is used in the other class - drawAnimation.
 *
 * @version 1.0
 * @author Ben Ganon 318731007
 * @since 2021-03-30
 */
public class Line {
    static final int TWO = 2;
    static final int ZERO = 0;
    //defining epsilon to use in calculations of intersecting lines.
    static final double EPSILON = Math.pow(10, -12);
    private Point start;
    private Point end;

    /**
     *Standard line builder - defining the line as the two points receive.
     * @param start - start point.
     * @param end - end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * @return (Double) length of this line.
     */
    public double length() {
        return Math.sqrt(Math.pow(this.start.getX() - this.end.getX(), TWO)
                + Math.pow(this.start.getY() - end.getY(), TWO));
    }

    /**
     * @return (BasicShapes.Point) the middle point between the x and y of the start and end point,
     * or the middle of the line.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / TWO;
        double y = (this.start.getY() + this.end.getY()) / TWO;
        return new Point(x, y);
    }

    /**
     * @return starting BasicShapes.Point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return end BasicShapes.Point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * isInRange is used in later calculations- returns true if BasicShapes.Point p2 is between the Points p1 and p3.
     * mostly useful for checking if a point is on a vertical/horizontal line.
     * @param p1 - leftmost/rightmost point.
     * @param p2 - desired point to check if in range of the other two.
     * @param p3 - leftmost/rightmost point.
     * @return true/false depending on if p2 is in range.
     */
    public boolean isInRange(Point p1, Point p2, Point p3) {
        //defining booleans to simplify the following if
        boolean xRange = p1.getX() <= p2.getX() && p2.getX() <= p3.getX();
        boolean xRangeAlt = p3.getX() <= p2.getX() && p2.getX() <= p1.getX();
        boolean yRange = p1.getY() <= p2.getY() && p2.getY() <= p3.getY();
        boolean yRangeAlt =  p3.getY() <= p2.getY() && p2.getY() <= p1.getY();
        //if p2 is both in range of the x and y values of p1 and p3, we return true.
        return ((xRange || xRangeAlt) && (yRange || yRangeAlt));
    }

    /**
     * findSlope finds the steepness of the slope of a given lines equation.
     * @param a - first point of the desired line.
     * @param b - end point of the desired line.
     * @return returns 0 if the line is vertical (actual value is infinity) if not returns the slope.
     */
    public double findSlope(Point a, Point b) {
        if (a.getX() == b.getX()) {
            return 0;
        }
        return (a.getY() - b.getY())
                / (a.getX() - b.getX());
    }

    /**
     * findB finds the b compononent int the ax + b formula of a given line using the slope from findSlope,
     * and plugging the starting line in the equation.
     * @param slope - slope of the lines formula.
     * @return - returns the b value by algebra of the equation y = slope * x + b
     */
    public double findB(double slope) {
        return this.start.getY() - slope * (this.start.getX());

    }

    /**
     * isVertical checks if a line is vertical.
     * @return returns true if the x values of the end and start points are the same.
     */
    public boolean isVertical() {
        if (this.start.getX() - this.end.getX() == ZERO) {
            return true;
        }
        return false;
    }

    /**
     * isHorizontal checks if a line is horizontal.
     * @return true if line hoirzontal, false if not.
     */
    public boolean isHorizontal() {
        if (this.start.getY() - this.end.getY() == ZERO) {
            return true;
        }
        return false;
    }

    /**
     * pointDiffEpsilon checks if a point is on a line using epsilon -
     * this method calculates the distance between the lines starting point and the desired point,
     * then the distance from desired point to the end of the line.
     * if the difference between these distances is bigger than epsilon, the point is not on the line.
     * @param line - the line we wish to check on.
     * @param point - the point we wish to check if is on line.
     * @return true if point is on line, false otherwise.
     */
    public boolean pointDiffEpsilon(Line line, Point point) {
        if (Math.abs(line.length() - (line.start.distance(point) + point.distance(line.end))) > EPSILON) {
            return true;
        }
        return false;
    }

    /**
     * isIntersecting runs intersectionWith on two lines.
     * if intersectionWith returns null (or specific cases arise) the method returns null.
     * if intersectionWith returns a valid point, the method returns true.
     * @param other - the other line we are checking intersection wit (first is this).
     * @return returns true if there exists an intersection, false if not.
     */
    public boolean isIntersecting(Line other) {

        /*
         *this special case is when both lines are horizontal or vertical and have a section overlapping between them -
         * in this case there is technically an intersection point but we do not know which one to return,
         * so we return true even though there is no point.
         */
        if ((this.isHorizontal() && other.isHorizontal() || this.isVertical() && other.isVertical())
                && (isInRange(this.start, other.end, this.end) || isInRange(this.start, other.start, this.end))) {
            return true;
        }
        //similar to the previous exception, if both line are the same, there is no single intersection.
        if (this.equals(other)) {
            return true;
        }
        if (this.intersectionWith(other) == null) {
            return false;
        }
            return true;
        }

    /**
     * intersectionWith finds an intersection point between two lines if it exists.
     * @param other - the desired line to compare to.
     * @return returns the intersection BasicShapes.Point if found, null otherwise.
     */
    public Point intersectionWith(Line other) {
        /*
         * defining both lines formulas- if one of them is vertical, the formula is incorrect(infinity slope),
         * but we consider this fact in later calculations so we dont get an error.
         */
        double m1 = findSlope(this.start, this.end);
        double b1 = this.findB(m1);
        double m2 = findSlope(other.start, other.end);
        double b2 = other.findB(m2);
        //if both lines are the same, there is no one point where they intersect.
        if (this.equals(other)) {
            return null;
        }
        if (this.isHorizontal() && other.isHorizontal()) {
            //if both lines are horizontal but only share a single point we can return that point.
            if (this.start == other.end || this.start == other.start) {
                return this.start;
            }
            if (this.end == other.start || this.end == other.end) {
                return this.end;
            }
            //returning null when both lines share an overlap.
            if (isInRange(this.start, other.end, this.end) || isInRange(this.start, other.start, this.end)) {
                return null;
            }
            return null;
        }
        if (this.isVertical() && other.isVertical()) {
            //checking if both lines are vertical and share either a single point or a section.
            if (this.start.getX() == other.start.getX() && (isInRange(other.start, this.start, other.end())
                    || isInRange(other.start, this.end, other.end))) {
                if (isInRange(this.start, other.start, this.end)) {
                    return new Point(other.start.getX(), other.start.getY());
                }
                if (isInRange(this.start, other.end, this.end)) {
                    return new Point(other.end.getX(), other.end.getY());
                }
                return null;
            }
            return null;
        }
        //if one line is vertical we wont calculate its formula since it doesnt exist.
        if (this.isVertical() && !other.isVertical()) {
            double y = m2 * (this.start.getX()) + b2;
            //if the intersection exists it will have the x value of the vertical line.
            Point inter = new Point(this.start.getX(), y);
            //checking if the found point is on both lines.
            if (pointDiffEpsilon(other, inter) ||  pointDiffEpsilon(this, inter)) {
                return null;
            }
            return inter;

        }
        //same case as before, only now the verticality is switched.
        if (!this.isVertical() && other.isVertical()) {
            double y = m1 * (other.start.getX()) + b1;
            Point inter = new Point(other.start.getX(), y);
            if (pointDiffEpsilon(other, inter) ||  pointDiffEpsilon(this, inter)) {
                return null;
            }
            return inter;
        }
        /*
         * if none of the ifs have triggered by now, the lines are not horizontal or vertical.
         * now we can calculate both formulas and check if the point is on both lines.
         */
        double x = (b2 - b1) / (m1 - m2);
        double y = m1 * x + b1;
        Point inter = new Point(x, y);
        //if the point is not on one of the lines, we return null.
        if (pointDiffEpsilon(other, inter) ||  pointDiffEpsilon(this, inter)) {
            return null;
        }
        //if the intersection is valid, we return it.
        return inter;
    }

    /**
     * equals checks if two lines share the same start and end point.
     * @param other - the other line.
     * @return returns true if both start and end points are the same.
     */
    public boolean equals(Line other) {
        if (this.start == other.start && this.end == other.end) {
            return true;
        }
        return false;
    }

    /**
     * checks what is the closest intersection point of a line and a rectangle to the start of the line.
     * if one are found, returns null.
     * @param rect - the rectangle to check against this line.
     * @return = the intersection point, null if none found.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //creating an array of intersection points.
        java.util.List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        //finding the closest point.
        Point closest = intersections.get(0);
        for (Point curr : intersections) {
            if (this.start.distance(curr) < this.start.distance(closest)) {
                closest = curr;
            }
        }
        return closest;
    }

    /**
     * checks if point p is oon the line.
     * @param p - point p.
     * @return returns true if the point, false otherwise.
     */
    public boolean hasPoint(Point p) {
        if (pointDiffEpsilon(this, p)) {
            return false;
        }
        return true;
    }
}