import java.util.Comparator;

public class Point2D implements Comparable<Point2D> {

	private int x, y;
	private static Point2D comparablePoint;

	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static int ccw(Point2D a, Point2D b, Point2D c) {
		return (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());
	}

	public static void setComparableOrder(Point2D point) {
		comparablePoint = point;
	}

	public boolean equals(Point2D other) {
		if (this.x == other.getX() && this.y == other.getY())
			return true;
		return false;
	}

	public void drawTo(Point2D that) {
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public void draw() {
		StdDraw.point(x, y);
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public String toString() {
		return "(" + x + ";" + y + ")";
	}

	public int compareTo(Point2D that) {
		return Integer.compare(this.y, that.getY());
	}

	public final static Comparator<Point2D> POLAR_ORDER = new PolarOrder();

	private static class PolarOrder implements Comparator<Point2D> {
		@Override
		public int compare(Point2D arg0, Point2D arg1) {
			double x0 = ((arg0.x-comparablePoint.x)/
					Math.sqrt(Math.pow(arg0.x-comparablePoint.x, 2)+Math.pow(arg0.y-comparablePoint.y, 2)));
			double x1 = ((arg1.x-comparablePoint.x)/
					Math.sqrt(Math.pow(arg1.x-comparablePoint.x, 2)+Math.pow(arg1.y-comparablePoint.y, 2)));
			return Double.compare(x1, x0);
		}

	}
}
