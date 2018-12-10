import java.util.Comparator;

public class Point2D {


    private final double x;
    private final double y;

    public Point2D(double x,double y){
        this.x=x;
        this.y=y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
        if(area2 < 0)
            return -1;
        else if(area2 > 0)
            return +1;
        else
            return  0;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Point2D that = (Point2D) other;
        return this.x == that.x && this.y == that.y;
    }

    public String toString(){
        return x+" "+y;
    }

    public void draw(){
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.009);
        StdDraw.point(x,y);
    }

    public void drawTo(Point2D that) {
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;

            if      (dy1 >= 0 && dy2 < 0) return -1;
            else if (dy2 >= 0 && dy1 < 0) return +1;
            else if (dy1 == 0 && dy2 == 0) {
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            else return -ccw(Point2D.this, q1, q2);

        }
    }

    public Comparator<Point2D> polarOrder() {
        return new PolarOrder();

    }

    public double getY() {
        return y;
    }
}
