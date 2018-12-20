package Stakhurskyi.Pr6;

import java.util.Comparator;

public class Point2D implements Comparable<Point2D> {

    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();

    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c){
        int value = (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
        if(value > 0) return -1;	//counterclockwise
        if(value < 0) return 1;	//clockwise
        return 0; //collinear
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    @Override
    public int compareTo(Point2D that) {
        if(y < that.y || (y == that.y && x < that.x)) return -1;
        if(y == that.y && x == that.x) return 0;
        return 1;
    }

    private class PolarOrder implements Comparator<Point2D>{

        @Override
        public int compare(Point2D q1, Point2D q2) {
            if(compareTo(q1) < 0 && compareTo(q2) > 0) return -1;
            if(compareTo(q2) < 0 && compareTo(q1) > 0) return 1;
            return ccw(Point2D.this, q1, q2);
        }

    }

    public boolean equals(Object object) {
        if(object instanceof Point2D && ((Point2D)object).x == this.x && ((Point2D)object).y == this.y) {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
