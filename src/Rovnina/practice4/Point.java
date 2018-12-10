import java.util.Comparator;


/**
 * властивості точки та методи пов'язані з нею
 *
 */
public class Point implements Comparable<Point> {

	public final Comparator<Point> SLOPE_ORDER = new SOrder();

	private final int x;
	private final int y;

	/**
	 * конструктор
	 */
	public Point(int x, int y) {
		/* DO NOT MODIFY */
		this.x = x;
		this.y = y;
	}

	/**
	 * метод, що малює точку
	 */
	public void draw() {
		/* DO NOT MODIFY */
		StdDraw.point(x, y);
	}

	/**
	 * метод, що малює лінію між двома точками
	 */
	public void drawTo(Point that) {
		/* DO NOT MODIFY */
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	/**
	 * повертає градієнт між цією точкою (x0,y0) і that точкою (x1,y1), що
	 * обраховується за формулою (y1-y0)/(x1-x0). Рахувати кут нахилу, що є
	 * горизонтальною лінією як позитивний 0, вертикальною лінією позитивна
	 * нескінченність, вироджений сегмент (між точкою і собою), як негативну
	 * нескінченність
	 */
	public double slopeTo(Point that) {
		if (that.y == this.y && that.x == this.x)
			return Double.NEGATIVE_INFINITY;
		if (that.y == this.y)
			return 0;
		if (that.x == this.x)
			return Double.POSITIVE_INFINITY;

		return (double) (that.y - this.y) / (that.x - this.x);
	}

	/*
	 * порівнює точки за їх y-координатами. Точка (x0,y0) менша за точку that
	 * (x1,y1), тоді і тільки тоді, коли y0<y1 або y0=y1 і x0<x1
	 */
	public int compareTo(Point that) {
		if (this.y < that.y || this.y == that.y && this.x < that.x)
			return -1;
		if (this.y == that.y && this.x == that.x)
			return 0;
		return 1;
	}

	/**
	 * має порівнювати точки за градієнтами, що вони породжують з викликаємою точкою
	 * (x0,y0). Формально точка (x1,y1) менша за точку (x2,y2), тоді і тільки тоді,
	 * коли нахил (y1-y0)/(x1-x0) менше, ніж нахил (y2-y0)/(x2-x0)
	 *
	 */
	private class SOrder implements Comparator<Point> {
		
		public int compare(Point p, Point q) {
			if (Point.this.slopeTo(p) < Point.this.slopeTo(q))
				return -1;
			if (Point.this.slopeTo(p) > Point.this.slopeTo(q))
				return 1;
			return 0;
		}

	}

	public String toString() {
		/* DO NOT MODIFY */
		return "(" + x + ", " + y + ")";
	}
	
}
