package Practice_4;

import java.util.Comparator;

import ua.princeton.lib.StdDraw;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        double result;

        if (this.y == that.y) {
            if (this.x == that.x)
                result = Double.NEGATIVE_INFINITY;
            else
                result = 0.0;
        } else if (this.x == that.x) {
            result = Double.POSITIVE_INFINITY;
        } else {
            result = (that.y - this.y) * 1.0 / (that.x - this.x);
        }

        return result;
    }

    public int compareTo(Point that) {
        int result;

        if (this.y == that.y) {
            if (this.x < that.x)
                result = -1;
            else if (this.x == that.x)
                result = 0;
            else
                result = 1;
        } else if (this.y < that.y) {
            result = -1;
        } else {
            result = 1;
        }

        return result;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point a, Point b) {
            int result;
            double slopeDifference = slopeTo(a) - slopeTo(b);

            if (slopeDifference < 0)
                result = -1;
            else if (slopeDifference > 0)
                result = 1;
            else
                result = 0;

            return result;
        }
    }

}