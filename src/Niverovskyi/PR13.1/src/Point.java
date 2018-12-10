
public class Point {

	private final int x;// x
	private final int y;// y

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static double lengthEdge(Point a, Point b) {
		double q = 0.0;
		q =  Math.sqrt((b.x - a.x) * (b.x - a.x) + (b.y - a.y) * (b.y - a.y));
		return q;
	}

}