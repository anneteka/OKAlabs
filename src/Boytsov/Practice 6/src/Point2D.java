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
		double area = (b.x - a.x)*(c.y - a.y) - (b.y - a.y)*(c.x - a.x);
		if (area < 0)
			return -1;
		if (area > 0)
			return +1;
		return 0;

	}

	public boolean equals(Point2D that) {
		if (this.x == that.x && this.x == that.y)
			return true;
		return false;

	}

	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}

	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	public void drawTo(Point2D that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public int compareTo(Point2D that) {
		if (this.y < that.y)
			return -1;
		if (this.y > that.y)
			return +1;
		if (this.x > that.x)
			return -1;
		if (this.x < that.x)
			return +1;
		return 0;
	}
	
	private class PolarOrder implements Comparator<Point2D> {

		@Override
		public int compare(Point2D q1, Point2D q2) {
			if (q1.y > y && q2.y < y)
				return -1;
			if (q1.y < y && q2.y > y)
				return +1;
			return ccw(Point2D.this, q2, q1);
		}

	}
}
