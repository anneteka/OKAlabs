import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.*;

public class Brute {
	private static final String testFile = "rs1423.txt";

	public static void main(String[] args) throws FileNotFoundException {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile));
		System.setIn(in);
		int n = StdIn.readInt();
		Point[] points = new Point[n];
		int i = 0;
		while (!StdIn.isEmpty()) {

			int x = StdIn.readInt();
			int y = StdIn.readInt();
			points[i++] = new Point(x, y);

		}

		for (i = 0; i < n - 3; i++)
			for (int j = i + 1; j < n - 2; j++)
				for (int k = j + 1; k < n - 1; k++)
					for (int l = k + 1; l < n; l++) {
						if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])
								&&points[j].slopeTo(points[k])==points[k].slopeTo(points[l])
								) {
							System.out.println(
									points[i] + " - > " + points[j] + " - > " + points[k] + " - > " + points[l]);
							
							points[i].drawTo(points[j]);
							points[j].drawTo(points[k]);
							points[k].drawTo(points[l]);
						
					}

	}
	}
}
