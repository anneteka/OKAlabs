import java.util.Comparator;

public class Point2D implements Comparable<Point2D>{

    int x;
    int y;

    Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        if ((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) > 0) return 1;
        if ((b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x) < 0) return -1;
        return 0;
    }

    public boolean equals (Point2D that){
        return (this.x== that.x && this.y==that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public int compareTo(Point2D that) {
        if (this.y-that.y<0) return -1;
        if (this.y-that.y>0) return 1;
        return 0;
    }

    private class PolarOrder implements Comparator<Point2D> {
        @Override
        public int compare(Point2D o1, Point2D o2) {
            if (Point2D.this.compareTo(o1) < 0 && Point2D.this.compareTo(o2) > 0)
                return -1;
            if (Point2D.this.compareTo(o1) > 0 && Point2D.this.compareTo(o2) < 0)
                return 1;
            return ccw(Point2D.this, o1, o2);
        }
    }

    public Comparator<Point2D> POLAR_ORDER = new PolarOrder();

}
