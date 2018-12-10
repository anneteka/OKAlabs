
import java.util.Comparator;

import ua.princeton.lib.StdDraw;

public class Point2D implements Comparable<Point2D> {

	// ������� ����� �� �������
	public final Comparator<Point2D> SLOPE_ORDER = new SOrder();
	public final Comparator<Point2D> POLAR_ORDER = new POLAR_ORDER();

	private final int x;// x ����������
	private final int y;// y ����������

	// ��������� ����� (x, y)
	public Point2D(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	// ������� �����
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(getX(), getY());
	}

	// ������� ������
	public void drawTo(Point2D that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.getX(), this.getY(), that.getX(), that.getY());
	}

	// нахил між цією і that точкою
	public double slopeTo(Point2D that) {
		/* YOUR CODE HERE */
		if (compareTo(that) == 0)
			return Double.NEGATIVE_INFINITY;
		if (this.x == that.x)
			return Double.POSITIVE_INFINITY;
		if (this.y == that.y)
			return 0.0;
		return (double) (that.y - this.y) / (that.x - this.x);
	}

	// чи ця точка лексикографічно менша за that
	// точку?
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

	// порівняти точки за градієнтом до цієї
	// точки
	private class SOrder implements Comparator<Point2D> {
		public int compare(Point2D p, Point2D q) {
			double pRes = p.slopeTo(Point2D.this);
			double qRes = q.slopeTo(Point2D.this);

			if (pRes == qRes)
				return 0;

			if (pRes < qRes)
				return -1;
			if (pRes > qRes)
				return 1;

			return 0;
		}
	}

	private class POLAR_ORDER implements Comparator<Point2D> {
		public int compare(Point2D q1, Point2D q2) {

			if (q1.getY() < getY() && q2.getY() > getY())
				return -1;
			if (q1.getY() > getY() && q2.getY() < getY())
				return 1;
			return (-ccw(q1, q2));

		}
	}

	// is a->b->c a counter-clockwise turn?
	// +1 if counter-clockwise, -1 if clockwise, 0 if collinear
	public int ccw(Point2D b, Point2D c) {
		// return a.x*b.y - a.y*b.x + a.y*c.x - a.x*c.y + b.x*c.y - b.y*c.x;
		double area2 = (b.x - getX()) * (c.y - getY()) - (c.x - getX()) * (b.y - getY());
		if (area2 < 0)
			return -1;
		else if (area2 > 0)
			return 1;
		else
			return 0;
	}

	// ����� � ������ String
	public String toString() {
		/* DO NOT MODIFY */
		return "(" + getX() + ", " + getY() + ")";
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}
}
