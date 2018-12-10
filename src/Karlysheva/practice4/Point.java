package practice4;

import ua.princeton.lib.StdDraw;

import java.util.Comparator;

public class Point implements Comparable<Point> {

    private double x, y;

    // порівняти точки за градієнтом до цієї точки
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(Point o1, Point o2) {
            double compare1 = slopeTo(o1), compare2=slopeTo(o2);
            if (compare1<compare2) return -1;
            if (compare1>compare2) return 1;
            return 0;
        }
    };

    // створює точку (x, y)
    public Point(int x, int y) {
        this.x=x;
        this.y=y;
    }

    // малює точку
    public void draw() {
        StdDraw.point(x,y);
    }

    // малює відрізок між цією точкою і that точкою
    public void drawTo(Point that) {
        StdDraw.line(x,y,that.x,that.y);
    }

    // string подання
    public String toString() {
        return "("+x+", "+y+")";
    }

    // нахил між цією і that точкою =  (y1-y0)/(x1-x0).
    public double slopeTo(Point that) {
        if (this.y==that.y&&this.x==that.x) return Double.NEGATIVE_INFINITY;
        if (this.y==that.y){
            return Double.POSITIVE_INFINITY;
        }
       if (this.x==that.x) return 0;
       return (that.y-this.y)/(that.x-this.x);
    }

    @Override
    //Точка (x0,y0) менша за точку (x1,y1), тоді і тільки тоді, коли y0<y1 або y0=y1 і x0<x1.
    public int compareTo(Point that) {
        if (this.y<that.y) return -1;
        if (this.y>that.y) return 1;

        if (this.x<that.x) return -1;
        if (this.x>that.x) return 1;

        return 0;
    }
}
