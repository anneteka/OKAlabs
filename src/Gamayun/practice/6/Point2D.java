package area;

import java.util.Arrays;
import java.util.Comparator;

import lib.StdDraw;
import lib.StdRandom;
;

public class Point2D implements Comparable{
	
	public double x;
	public double y;
	
	public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
	
	public Point2D(double X,double Y)
	{
		x = X;
		y = Y;
	}
	
	public static int ccw(Point2D a, Point2D b, Point2D c) {
		double area2 = (b.x-a.x)*(c.y-a.y) - (b.y-a.y)*(c.x-a.x);
		if(area2 < 0) return -1;
		else if (area2 > 0) return +1;
		else return  0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point2D other = (Point2D) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point2D(" + x + "," + y + ")\n";
	}
	
	public void draw()
	{
		StdDraw.filledCircle(x, y, 200);
		//StdDraw.point(x, y);
	}
	
	public void drawTo(Point2D that)
	{
		StdDraw.line(this.x, this.y, that.x, that.y);
	}
	
	private class PolarOrder implements Comparator<Point2D> {
        public int compare(Point2D q1, Point2D q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;

            if      (dy1 >= 0 && dy2 < 0) return -1;    // q1 above; q2 below
            else if (dy2 >= 0 && dy1 < 0) return +1;    // q1 below; q2 above
            else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if      (dx1 >= 0 && dx2 < 0) return -1;
                else if (dx2 >= 0 && dx1 < 0) return +1;
                else                          return  0;
            }
            else return -ccw(Point2D.this, q1, q2);     // both above or below

            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        }
    }

	@Override
	public int compareTo(Object arg) {
		Point2D that = (Point2D)arg;
		if(y > that.y) return 1;
		if(y < that.y) return -1;
		if(x > that.x) return -1;
		if(x < that.x) return 1;
		return 0;
	}
	
	
	public static void main(String[] args) {
        int x0 = 50;
        int y0 = 50;
        int n = 100;

        StdDraw.setCanvasSize(800, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.005);
       
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = StdRandom.uniform(100);
            int y = StdRandom.uniform(100);
            points[i] = new Point2D(x, y);
            points[i].draw();
        }

        // draw p = (x0, x1) in red
        Point2D p = new Point2D(x0, y0);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.02);
        p.draw();


        // draw line segments from p to each point, one at a time, in polar order
        StdDraw.setPenRadius();
        StdDraw.setPenColor(StdDraw.BLUE);
        
        
        Arrays.sort(points, p.POLAR_ORDER);
        for (int i = 0; i < n; i++) {
            p.drawTo(points[i]);
            StdDraw.show(100);
            //StdDraw.pause(100);
        }
	}
	
	
}
