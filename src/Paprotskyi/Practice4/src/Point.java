import java.util.Comparator;

public class Point implements Comparable<Point> {

    private final int x;
    private final int y;

    // порівняти точки за градієнтом до цієї точки
    public final Comparator<Point> SLOPE_ORDER = new SOrder(this);

    // створює точку (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // малює точку
    public void draw() {
        StdDraw.point(x, y);
    }

    // малює відрізок між цією точкою і that точкою
    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // нахил між цією і that точкою
    public double slopeTo(Point that) {
        if ((this.y == that.y) && (this.x == that.x)) return Double.NEGATIVE_INFINITY;
        else if (this.y == that.y) return 0;
        else if (this.x == that.x) return Double.POSITIVE_INFINITY;
        return (double)(that.y-y)/(that.x - x);
    }

    // чи ця точка лексикографічно менша за that точку?
    public int compareTo(Point that) {
        if((this.y < that.y) || (this.y ==that.y) &&(this.x < that.x))
            return -1;
        else if ((this.y == that.y)&&(this.x==that.x))
            return 0;
        return 1;
    }

    private static class SOrder implements Comparator<Point>{
private  Point point;

        public SOrder(Point p){
            point = p;
        }

        @Override
        public int compare(Point p, Point q){
            if (point.slopeTo(p)<point.slopeTo(q))return -1;
            else if (point.slopeTo(p)==point.slopeTo(q))return 0;
            return 1;
        }
    }

    // string подання
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}