import java.util.Comparator;


public class Point implements Comparable<Point> {
    private static Point comparingPoint = null;

    public final static Comparator<Point> SLOPE_ORDER = new SOrder();

    private final int x;
    private final int y;

    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    public static void setComparingPoint(Point p) {
        comparingPoint = p;
    }

    public static Point getComparingPoint() { return comparingPoint; }

    public static double getSlopeFromComparingPoint(Point p) {
        return comparingPoint.slopeTo(p);
    }

    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.x == that.y)
            return Double.POSITIVE_INFINITY;
        if (this == that)
            return Double.NEGATIVE_INFINITY;
        return (double)(that.y - this.y) / (double)(that.x - this.x);
    }

    public int compareTo(Point that) {
        return Integer.compare(this.y, that.y);
    }

    private static class SOrder implements Comparator<Point>{
        public int compare(Point p, Point q){
            double slope1 = comparingPoint.slopeTo(p);
            double slope2 = comparingPoint.slopeTo(q);

            return Double.compare(slope1, slope2);
        }
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}
