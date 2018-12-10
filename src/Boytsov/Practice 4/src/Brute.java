
public class Brute {

	public static void analyze(Point[] points) {
		for (int p = 0; p < points.length - 3; p++) {
			for (int q = p + 1; q < points.length - 2; q++) {
				for (int r = q + 1; r < points.length - 1; r++) {
					for (int s = r + 1; s < points.length; s++) {
						double pq = points[p].slopeTo(points[q]);
						double pr = points[p].slopeTo(points[r]);
						double ps = points[p].slopeTo(points[s]);
						if (pq == pr && pr == ps) {
							points[p].drawTo(points[q]);
							points[q].drawTo(points[r]);
							points[r].drawTo(points[s]);
							StdOut.println(points[p] + " -> " + points[q] + " -> " + points[r] + " -> " + points[s]);
						}

					}
				}
			}
		}

	}

}
