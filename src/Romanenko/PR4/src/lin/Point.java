package lin;

import java.util.Comparator;

import ua.princeton.lib.StdDraw;

public class Point implements Comparable<Point> {

	public final Comparator<Point> SLOPE_ORDER = new SOrder();

	private final int x;// x
	private final int y;// y

	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public double slopeTo(Point that) {

		return (double) (that.y - this.y) / (that.x - this.x);
	}

	public int compareTo(Point that) {
		if (this.y < that.y || (this.y == that.y && this.x < that.x)) {
			return -1;
		} else if (this.y == that.y && this.x == that.x) {
			return 0;
		} else {
			return 1;
		}
	}

	private class SOrder implements Comparator<Point> {
		public int compare(Point p, Point q) {
			Point k = new Point(x, y);
			double z = k.slopeTo(p) - k.slopeTo(q);
			if (z > 0)
				return -1;
			if (z < 0)
				return 1;
			return 0;
		}
	}

	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}
}