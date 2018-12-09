import java.util.Comparator;


public class Point implements Comparable<Point> {


    private final double x;
    private final double y;


    // порівняти точки за градієнтом до цієї точки
    public final Comparator<Point> SLOPE_ORDER = new SOrder(this);

    // створює точку (x, y)
    public Point(double x,double y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;

    }

    // малює точку
    public void draw() {
        /* DO NOT MODIFY */
        //StdDraw.setPenRadius(0.03);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(x,y);
    }

    // малює точку
    public void drawN() {
        /* DO NOT MODIFY */
        //StdDraw.setPenRadius(0.03);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(x,y);
    }

    // малює відрізок між цією точкою і that точкою
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        //StdDraw.setPenRadius(0.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // нахил між цією і that точкою
    public double slopeTo(Point that) {
        if((this.y==that.y)&&(this.x==that.x))
            return Double.NEGATIVE_INFINITY;
        else if(this.y==that.y)
            return 0;
        else if(this.x==that.x)
            return Double.POSITIVE_INFINITY;

        return (that.y-y)/(that.x-x);
    }

    // чи ця точка лексикографічно менша за that точку?
    public int compareTo(Point that) {
        if(y<that.y || y==that.y & x<that.x)
            return -1;
        else if((this.x==that.x)&&(this.y==that.y))
            return 0;

        return 1;
    }

    private static class SOrder implements Comparator<Point>{
        private Point point;

        public SOrder(Point p){
            point = p;
        }

        public int compare(Point p, Point q){
            if(point.slopeTo(q)<point.slopeTo(p))
                return -1;
            else if(point.slopeTo(q)<point.slopeTo(p))
                return 0;

            return 1;
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
}