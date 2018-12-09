package Practice_6;

import java.util.Comparator;

public class Point2D implements Comparable<Point2D>
{

    private final int x;                              // x coordinate
    private final int y;// y coordinate

    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
    public final static Comparator<Point2D> SORT_BY_Y = new SortByY();

    private static class SortByY implements Comparator<Point2D> {
        public int compare(Point2D p, Point2D q) {
            if (p.y < q.y) return -1;
            if (p.y > q.y) return +1;
            return 0;
        }
    }

    private class PolarOrder implements Comparator<Point2D> {

        @Override
        public int compare(Point2D q1, Point2D q2) {
            int x1 = q1.x - x;
            int x2 = q2.x - x;
            int y1 = q1.y - y;
            int y2 = q2.y - y;
            if (y1 >= 0 && y2 < 0) return -1;
            if (y1 <= 0 && y2 > 0) return 1;
            if (y1 == 0 && y2 == 0) {
                if (x1 >= 0 && x2 < 0) return -1;
                if (x1 <= 0 && x2 > 0) return 1;
                return 0;
            }
            return ccw(Point2D.this, q1,q2);
        }

    }

    // create the point (x, y)
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double ccw = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (ccw > 0) // ccw
            return 1;
        else if (ccw == 0) // collinear
            return 0;
        else
            return -1; // cw

    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean equals(Object object) { return true;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Comparator<Point2D> getPOLAR_ORDER() {
        return POLAR_ORDER;
    }

    public static Comparator<Point2D> getSortByY() {
        return SORT_BY_Y;
    }

    @Override
    public int compareTo(Point2D that) {
        if (this.y > that.y) return 1;
        if (this.y < that.y) return -1;
        if (this.x > that.x) return 1;
        if (this.x < that.x) return -1;
        return 0;
    }
}
