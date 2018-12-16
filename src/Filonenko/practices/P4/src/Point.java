
import lib.StdDraw;

import java.util.Comparator;


public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new SOrder(this);

    private final int x;// x ����������
    private final int y;// y ����������

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x,y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.y == that.y && this.x == that.x)
            return Float.NEGATIVE_INFINITY;
        if (this.y == that.y)
            return +0;
        if (this.x == that.x)
            return Float.POSITIVE_INFINITY;

        return (double) (that.y - this.y) / (double) (that.x - this.x);
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

    private static class SOrder implements Comparator<Point>{
        public  SOrder(Point currentPoint) {
            super();
            this.currentPoint = currentPoint;
        }
        Point currentPoint;
        public int compare(Point p, Point q) {
            double difference = currentPoint.slopeTo(p)-currentPoint.slopeTo(q);
            if(difference>0)
                return 1;
            if(difference<0)
                return -1;

            return 0;
        }
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    @Override
    public boolean equals(Object o) {
        Point that = (Point) o;
        if(this.x == that.x && this.y == that.y) return true;
        return false;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
}