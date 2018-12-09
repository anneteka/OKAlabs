package ConvexHull;
import java.util.Comparator;

import lib.StdDraw;
/**
 * DONE
 * @author Богдана
 *
 */

public class Point2D implements Comparable<Point2D> {
	public final Comparator<Point2D> SLOPE_ORDER = new PolarOrder();
	final int x;
	final int y;
	double polarAngle;

	public Point2D(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}
	//(𝑏_𝑥−𝑎_𝑥 )(𝑐_𝑦−𝑎_𝑦 )−(𝑏_𝑦−𝑎_𝑦)(𝑐_𝑥−𝑎_𝑥)

	public static int ccw(Point2D a, Point2D b, Point2D c) {
		return (b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x);
	}


    public boolean equals(Object o) {
        Point2D that = (Point2D) o;
        if(this.x == that.x && this.y == that.y) return true;
        return false;
    }
	
    public void draw() {
		StdDraw.point(x, y);
	}
    //p-найнижча точка по у
    //повертаэ кут з точкою
    public  double setAngle(Point2D p) {
    	double ro=Math.sqrt((p.x-this.x)*(p.x-this.x)+ (p.y-this.y)*(p.y-this.y));
    	double fi=Math.acos((p.x-this.x)/ro);
    	return fi;
    }

	public void drawTo(Point2D that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	// compareTo() має порівнювати точки за їх y-координатами.
		// Точка (x0,y0) менша за точку (x1,y1), тоді і тільки тоді, коли y0<y1 або
		// y0=y1 і x0<x1.
		public int compareTo(Point2D that) {
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
		public String toString() {
			return "(" + x + ", " + y + ")";
		}

		private  class PolarOrder implements Comparator<Point2D> {
		    @Override
		    public int compare(Point2D p, Point2D q) {
		      if(Point2D.this.setAngle(p) < Point2D.this.setAngle(q))
		        return -1;
		      if(Point2D.this.setAngle(p) > Point2D.this.setAngle(q))
			        return 1;
		      return 0;
		    }
		  }

}
