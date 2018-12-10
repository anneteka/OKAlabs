import java.util.Comparator;

/**
 * властивості точки та методи пов'язані з нею
 * 
 * @author Rovnina Tetiana
 *
 */
public class Point2D implements Comparable<Point2D>{

	public final Comparator<Point2D> Polar_Order = new PolarOrder();

	private final int x;
	private final int y;

	/**
	 * конструктор з параметрами
	 */
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * метод, який визначає кут повороту трьох точок
	 */
	public static int ccw(Point2D a, Point2D b, Point2D c) {
		return (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	}

	public String toString() {
		return "(" + x + ", " + y + ")";

	}

	/**
	 * малюємо точку
	 */
	public void draw() {
		StdDraw.point(x, y);
	}

	/**
	 * малюємо лінію між двома точками
	 */
	public void drawTo(Point2D that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	/*
	 * порівнює точки за їх y-координатами. Точка (x0,y0) менша за точку that
	 * (x1,y1), тоді і тільки тоді, коли y0<y1 або y0=y1 і x0<x1
	 */
	public int compareTo(Point2D that) {
		if (this.y < that.y || this.y == that.y && this.x < that.x)
			return -1;
		if (this.y == that.y && this.x == that.x)
			return 0;
		return 1;
	}

	/**
	 * рахуємо полярний кут
	 */
	public double polarAngle(Point2D p) {
		double r = Math.sqrt((p.x - this.x) * (p.x - this.x) + (p.y - this.y) * (p.y - this.y));
		double angle = Math.acos((p.x - this.x) / r);
		return angle;
	}

	/**
	 * клас компаратор, який порівнює точки за полярним кутом
	 *
	 */
	private class PolarOrder implements Comparator<Point2D> {

		@Override
		public int compare(Point2D o1, Point2D o2) {
			if (Point2D.this.polarAngle(o1) < Point2D.this.polarAngle(o2))
				return -1;
			if (Point2D.this.polarAngle(o1) > Point2D.this.polarAngle(o2))
				return 1;
			return 0;
		}

	}
}
