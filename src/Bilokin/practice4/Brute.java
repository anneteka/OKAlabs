import java.util.Arrays;
import java.util.Scanner;

import princeton.lib.StdDraw;

public class Brute {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Point[] points = new Point[n];
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);

		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			points[i] = new Point(x, y);
			points[i].draw();
		}

		Arrays.sort(points);

		for (int i = 0; i < n; i++) {
			Point p1 = points[i];
			for (int j = i + 1; j < n; j++) {
				Point p2 = points[j];
				for (int k = j + 1; k < n; k++) {
					Point p3 = points[k];
					for (int l = k + 1; l < n; l++) {
						Point p4 = points[l];

						double P1P2Slope = p1.slopeTo(p2);
						double P1P3Slope = p1.slopeTo(p3);
						double P1P4Slope = p1.slopeTo(p4);

						if (P1P2Slope == P1P3Slope && P1P2Slope == P1P4Slope && P1P3Slope == P1P4Slope) {
							System.out.println("Points: " + p1 + ", " + p2 + ", " + p3 + ", " + p4 + "  Are alined ");
							p1.drawTo(p4);
						}

					}
				}
			}
		}
	}
}
