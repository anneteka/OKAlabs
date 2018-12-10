import java.util.Comparator;
import ua.princeton.lib.StdDraw;

public class Point2D  implements Comparable<Point2D>{

	public final Comparator<Point2D> SLOPE_ORDER = new PolarOrder();
	int x;
	int y;

	Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static int ccw(Point2D a, Point2D b, Point2D c) {
		int area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
		if (area2 < 0)
			return -1;
		else if (area2 > 0)
			return +1;
		else
			return 0;
	}

	public boolean equals(Object other) {

		return false;
	}

	public String toString() {

		return null;
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public void drawTo(Point2D that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public int compareTo(Point2D that) {
		if (this.y < that.y)
			return -1;
		if (this.y > that.y)
			return +1;
		if (this.x < that.x)
			return -1;
		if (this.x > that.x)
			return +1;
		return 0;
	}

	public Comparator<Point2D> polarOrder() {
		return new PolarOrder();
	}

	private class PolarOrder implements Comparator<Point2D> {

		public int compare(Point2D q1, Point2D q2) {
			double dx1 = q1.x - x;
			double dy1 = q1.y - y;
			double dx2 = q2.x - x;
			double dy2 = q2.y - y;

			if (dy1 >= 0 && dy2 < 0)
				return -1; // q1 above; q2 below
			else if (dy2 >= 0 && dy1 < 0)
				return +1; // q1 below; q2 above
			else if (dy1 == 0 && dy2 == 0) { // 3-collinear and horizontal
				if (dx1 >= 0 && dx2 < 0)
					return -1;
				else if (dx2 >= 0 && dx1 < 0)
					return 1;
				else
					return 0;
			} else
				return -ccw(Point2D.this, q1, q2); // both above or below

			// Note: ccw() recomputes dx1, dy1, dx2, and dy2
		}

	}

}
