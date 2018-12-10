package practice6;

import ua.princeton.lib.In;
import ua.princeton.lib.StdDraw;

import java.util.Comparator;

public class Point2D implements Comparable<Point2D>{

    private double x, y;

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static int ccw(Point2D a, Point2D b, Point2D c) {
        //(𝑏_𝑥−𝑎_𝑥 )(𝑐_𝑦−𝑎_𝑦 )−(𝑏_𝑦−𝑎_𝑦)(𝑐_𝑥−𝑎_𝑥);
        double s = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        return (int) Math.signum(s);
    }

    public boolean equals(Object other) {
        Point2D that = (Point2D) other;
        return (this.x == that.x && this.y == that.y);
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public void draw() {
        StdDraw.point(this.x, this.y);
    }

    public void drawTo(Point2D that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public int compareTo(Point2D that) {
        if (this.y < that.y) return -1;
        if (this.y > that.y) return 1;
        if (this.x < that.x) return -1;
        if (this.x > that.x) return 1;
        return 0;
    }


    public Comparator<Point2D> PolarOrder() {
        return new Comparator<Point2D>() {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                Point2D _this = Point2D.this;
                if (o1.equals(_this)) return -1;
                if (o2.equals(_this)) return 1;
                if (o1.y < _this.y && o2.y > _this.y) return 1;
                if (o1.y > _this.y && o2.y < _this.y) return -1;

                int ccw = Point2D.ccw(_this, o1, o2);
                if (ccw<0) return 1;
                if (ccw>0) return -1;

                return 0;
            }
        };
    }

    public double distance(Point2D that){
        return(that.x-this.x)*(that.x-this.x)+(that.y-this.y)*(that.y-this.y);
    }
}
