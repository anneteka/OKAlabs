import java.util.Comparator;

public class Point2D implements Comparable<Point2D>{
	
	public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
	
	private final int x;
	private final int y;
	
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static int ccw(Point2D a, Point2D b, Point2D c) {
		return (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
	}
	
	public boolean equals(Point2D that) {
		return (this.x == that.x && this.y == that.y);
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
		if(this.y < that.y) return -1;
    	if(this.y > that.y) return 1;
    	if(this.x < that.x) return -1;
    	if(this.x > that.x) return 1;
		return 0;
	}
	
	private class PolarOrder implements Comparator<Point2D>{

		@Override
		public int compare(Point2D p1, Point2D p2) {
			if(Point2D.this.y < p1.y && Point2D.this.y > p2.y) return -1;
			if(Point2D.this.y > p1.y && Point2D.this.y < p2.y) return 1;
			if(ccw(Point2D.this, p1, p2) > ccw(Point2D.this, p2, p1)) return -1;
			if(ccw(Point2D.this, p1, p2) < ccw(Point2D.this, p2, p1)) return 1;
			return 0;
		}		
	}	
}
