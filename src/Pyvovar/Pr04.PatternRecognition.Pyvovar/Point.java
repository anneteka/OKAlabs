package PatternRecognition;

import java.util.Comparator;

import princetonlib.StdDraw;

/**
 * Клас, в якому створюється тип даних Point, що представляє точку на площині
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Point implements Comparable<Point> {

	/**
	 * порівняти точки за градієнтом до цієї точки
	 */
	public final Comparator<Point> SLOPE_ORDER = new SOrder();

	private final int x;
	private final int y;

	/**
	 * створює точку
	 * 
	 * @param x абсциса
	 * @param y ордината
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * малює точку
	 */
	public void draw() {
		StdDraw.point(x, y);
	}

	/**
	 * малює відрізок між цією точкою і that точкою
	 * 
	 * @param that
	 */
	public void drawTo(Point that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	/**
	 * @param that
	 * @return нахил між цією і that точкою
	 */
	public double slopeTo(Point that) {
		if (this.x == that.x) // горизонтальна лінія - позитивна нескінченність
			return Double.POSITIVE_INFINITY;
		return ((double) (that.y - this.y)) / ((double) (that.x - this.x));
	}

	// чи ця точка лексикографічно менша за that точку? (порівнює за координатами)
	public int compareTo(Point that) {
		if (this.y < that.y)
			return -1; // точка this менше за точку that
		if (this.y > that.y)
			return 1; // точка this більша за точку that
		if (this.x < that.x)
			return -1; // точка this менше за точку that
		if (this.x > that.x)
			return 1; // точка this більша за точку that
		return 0; // однакові
	}

	private class SOrder implements Comparator<Point> {
		public int compare(Point p, Point q) {
			double slope1 = Point.this.slopeTo(p);
			double slope2 = Point.this.slopeTo(q);
			if (slope1 < slope2)
				return -1; // точка p менша за q
			if (slope1 > slope2)
				return 1; // точка p більша за q
			return 0;
		}
	}

	// string подання
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}