import java.awt.Color;
import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point2D implements Comparable<Point2D> {

	final int x;
	final int y;
	 public final Comparator<Point2D> POLAR_ORDER=new PolarOrder();
	    public Point2D(int x, int y){
	        this.x=x;
	        this.y=y;
	        draw();
	    }


	    public static int ccw(Point2D a, Point2D b, Point2D c){
	        return (b.x-a.x)*(c.y-a.y)-(b.y-a.y)*(c.x-a.x);
	    }


	public void draw() {
		   StdDraw.setPenRadius(.02);
		StdDraw.point(x, y);
	}

	public void drawTo(Point2D that) {
		   StdDraw.setPenRadius(.008);
		   StdDraw.setPenColor(Color.cyan);
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	  @Override
	    public int compareTo(Point2D p) {
	        if (p.y==this.y)
	            return this.x-p.x;
	        return this.y-p.y;
	    }

	public String toString() {
		return "(" + this.x + "," + this.y + ") \n";
	}

	private class PolarOrder implements Comparator<Point2D>{

        @Override
        public int compare(Point2D q1, Point2D q2)
        {
            double dy1 = q1.y - y;
            double dy2 = q2.y - y;

            if (dy1 >= 0 && dy2 < 0)
                return -1; // q1 above; q2 below
            else if (dy2 >= 0 && dy1 < 0)
                return +1; // q1 below; q2 above
            else
                return -ccw(Point2D.this, q1, q2); // both above or below
        }
    }
}