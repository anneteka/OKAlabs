package Stakhurskyi.Pr4;



import java.util.Comparator;



import princetonlib.StdDraw;


public class Point implements Comparable<Point> {



	/**

	 * �������� ����� �� ���䳺���� �� ���� �����

	 */

	public final Comparator<Point> SLOPE_ORDER = new SOrder();



	private final int x;

	private final int y;



	/**

	 * ������� �����

	 * 

	 * @param x �������

	 * @param y ��������

	 */

	public Point(int x, int y) {

		this.x = x;

		this.y = y;

	}



	/**

	 * ����� �����

	 */

	public void draw() {

		StdDraw.point(x, y);

	}



	/**

	 * ����� ������ �� ���� ������ � that ������

	 * 

	 * @param that

	 */

	public void drawTo(Point that) {

		StdDraw.line(this.x, this.y, that.x, that.y);

	}



	/**

	 * @param that

	 * @return ����� �� ���� � that ������

	 */

	public double slopeTo(Point that) {

		if (this.x == that.x) // ������������� ��� - ��������� ������������

			return Double.POSITIVE_INFINITY;

		return ((double) (that.y - this.y)) / ((double) (that.x - this.x));

	}



	// �� �� ����� ��������������� ����� �� that �����? (������� �� ������������)

	public int compareTo(Point that) {

		if (this.y < that.y)

			return -1; // ����� this ����� �� ����� that

		if (this.y > that.y)

			return 1; // ����� this ����� �� ����� that

		if (this.x < that.x)

			return -1; // ����� this ����� �� ����� that

		if (this.x > that.x)

			return 1; // ����� this ����� �� ����� that

		return 0; // �������

	}



	private class SOrder implements Comparator<Point> {

		public int compare(Point p, Point q) {

			double slope1 = Point.this.slopeTo(p);

			double slope2 = Point.this.slopeTo(q);

			if (slope1 < slope2)

				return -1; // ����� p ����� �� q

			if (slope1 > slope2)

				return 1; // ����� p ����� �� q

			return 0;

		}

	}



	// string �������

	public String toString() {

		return "(" + x + ", " + y + ")";

	}

}