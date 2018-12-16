import lib.StdDraw;

import java.util.Comparator;

public class Point {
    PolarOrder BY_POLAR_ORDER = new PolarOrder();
    public int x, y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public static int ccw(Point p, Point q, Point r)  {
        return (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
    }

    @Override
    public boolean equals(Object other) {
        Point that = (Point) other;
        return this.x == that.x && this.y == that.y;
    }
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
    public void draw() {
        StdDraw.point(x, y);
    }
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }
    public int compareTo(Point that) {
        if (this.y < that.y)
            return -1;
        if (this.y > that.y)
            return 1;
        if (this.x < that.x)
            return -1;
        if (this.x > that.x)
            return 1;
        return 0;
    }
    private class PolarOrder implements Comparator<Point> {

        @Override
        public int compare(Point q1, Point q2) {
            return  Point.ccw(Point.this, q1, q2);
        }
    }
}
