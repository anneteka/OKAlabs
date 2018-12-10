import java.util.Arrays;
import java.util.Stack;

public class GrahamScan {
	private Stack<Point2D> hull = new Stack<Point2D>();

	public GrahamScan(Point2D[] pts) {
		int n = pts.length;
		Point2D[] points = new Point2D[n];
		for (int i = 0; i < n; i++) {
			points[i] = pts[i];
		}
		Arrays.sort(points);
		Arrays.sort(points, 1, n, points[0].SLOPE_ORDER);

		hull.push(points[0]);//p[0] - start point

		// find index of first point that not equals to start point
		int ind1;
		for (ind1 = 1; ind1 < n; ind1++)
			if (!points[0].equals(points[ind1]))
				break;
		if (ind1 == n)
			return;

		// find index of first point that not collinear with start point and points[ind1]		
		int ind2;
		for (ind2 = ind1 + 1; ind2 < n; ind2++)
			if (Point2D.ccw(points[0], points[ind1], points[ind2]) != 0)
				break;
		hull.push(points[ind2 - 1]);

		for (int i = ind2; i < n; i++) {
			Point2D top = hull.pop();
			while (Point2D.ccw(hull.peek(), top, points[i]) <= 0) {
				top = hull.pop();
			}
			hull.push(top);
			hull.push(points[i]);
		}

//		assert isConvex();
	}

	public Iterable<Point2D> hull() {
		Stack<Point2D> s = new Stack<Point2D>();
		for (Point2D p : hull)
			s.push(p);
		return s;
	}

	public int ammountOfPoints() {
		int n = 0;
		for (Point2D p : hull)
			++n;
		return n;
	}

//	private boolean isConvex() {
//		int n = hull.size();
//		if (n <= 2)
//			return true;
//
//		Point2D[] points = new Point2D[n];
//		int k = 0;
//		for (Point2D p : hull()) {
//			points[k++] = p;
//		}
//
//		for (int i = 0; i < n; i++) {
//			if (Point2D.ccw(points[i], points[(i + 1) % n], points[(i + 2) % n]) <= 0) {
//				return false;
//			}
//		}
//		return true;
//	}
}