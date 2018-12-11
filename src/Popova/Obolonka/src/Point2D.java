import lib.StdDraw;

import java.util.Comparator;

public class Point2D implements Comparable<Point2D> {

    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();

    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        int value = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (value < 0) return -1;
        else if (value > 0) return 1;
        else return 0;
    }

    public boolean equals(Point2D that) {
        return (this.y == that.y & this.x == that.x);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void draw() {
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }


    public int compareTo(Point2D that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return 1;
        return 0;
    }

    private class PolarOrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D q1, Point2D q2) {
            Point2D p = Point2D.this;
            if (q1.y > p.y & q2.y < p.y) return -1;
            else if (q1.y < p.y & q2.y > p.y) return 1;
            else return ccw(p, q1, q2);
        }
    }
}
