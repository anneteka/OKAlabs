import lib.StdDraw;

import java.awt.*;
import java.util.Comparator;

public class Point2D implements Comparable<Point2D> {
    private Point2D it;
    private int x;
    private int y;

    public Point2D(int x, int y) {
        it = this;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paintNumber(String number) {
        StdDraw.text(x, y, number);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Point2D)) return false;
        return ((Point2D) other).x == x && ((Point2D) other).y == y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(x, y, that.x, that.y);
    }

    @Override
    public int compareTo(Point2D that) {
        return Integer.compare(y, that.y);
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
    }

    public Comparator<Point2D> POLAR_ORDER = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            if (compareTo(o1) > 0 && compareTo(o2) < 0) return -1;
            if (compareTo(o1) < 0 && compareTo(o2) > 0) return 1;
//            if (compareTo(o1) == 0 && compareTo(o2) == 0) return Point2D.compareByX(o1, o2);
            return (int) -Math.signum(ccw(it, o1, o2));
        }
    };

    private static int compareByX(Point2D first, Point2D second) {
        if (first.x > second.x) return 1;
        if (first.x < second.x) return -1;
        return 0;
    }
}
