package convexhull;

import java.util.Comparator;

import princetonlib.StdDraw;

/**
 * створення точки
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Point2D implements Comparable<Point2D> {
	public final Comparator<Point2D> POLAR_ORDER = new PolarOrder();
	final int x;
	final int y;

	/**
	 * конструктор
	 * 
	 * @param x
	 * @param y
	 */
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param a
	 * @param b
	 * @param c
	 * @return чи за часовою стрілкою кут, чи проти
	 */
	public static int ccw(Point2D a, Point2D b, Point2D c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

	@Override
	public boolean equals(Object o) {
		Point2D that = (Point2D) o;
		if (this.x == that.x && this.y == that.y)
			return true;
		return false;
	}

	/**
	 * малює точку
	 */
	public void draw() {
		StdDraw.point(x, y);
	}

	/**
	 * @param p
	 * @return полярний кут між this та p
	 */
	public double getPolarAngle(Point2D p) {
		double r = Math.sqrt((p.x - this.x) * (p.x - this.x) + (p.y - this.y) * (p.y - this.y));
		double polarAngle = Math.acos((p.x - this.x) / r);
		return polarAngle;
	}

	/**
	 * малює лінію між this та that
	 * 
	 * @param that
	 */
	public void drawTo(Point2D that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	@Override
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

	private class PolarOrder implements Comparator<Point2D> {
		@Override
		public int compare(Point2D p, Point2D q) {
			if (Point2D.this.getPolarAngle(p) < Point2D.this.getPolarAngle(q))
				return -1;
			if (Point2D.this.getPolarAngle(p) > Point2D.this.getPolarAngle(q))
				return 1;
			return 0;
		}
	}

}
