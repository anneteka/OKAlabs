package lin;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class Fast {
	public static void main(String[] args) {
		In in = new In("point.txt");
		Point[] points = new Point[in.readInt()];

		int x, y, i = 0;
		while (!in.isEmpty()) {
			x = in.readInt();
			y = in.readInt();
			points[i++] = new Point(x, y);
		}
		findPointsOnLine(points);
	}

	private static void findPointsOnLine(Point[] points) {
		for (int i = 0; i < points.length; i++) {
			Point p = points[i];
			Point[] q = new Point[points.length - (i + 1)];
			int minus = 0;
			for (int j = i; j < points.length; j++) {
				if (i == j) {
					minus = (i + 1);
					continue;
				}
				q[j - minus] = points[j];
			}
			ShellSort.sort(q, p.SLOPE_ORDER);

			int count = 0;
			for (int k = 1; k < q.length + 1; k++) {
				if (k >= q.length) {
					if (count >= 2) {
						for (int l = 0; l <= count; l++) {
							StdOut.print(q[k - l - 1].toString() + " -> ");
						}
						StdOut.println(p.toString());
						count = 0;
					}
				} else {
					if (p.slopeTo(q[k]) == p.slopeTo(q[k - 1])) {

						count++;
					} else {
						if (count >= 2) {
							StdOut.print(p.toString());
							for (int l = 0; l <= count; l++)
								StdOut.print(" -> " + q[k - l - 1].toString());
							StdOut.println();
							count = 0;
						}
					}
				}
			}
		}
	}
}
