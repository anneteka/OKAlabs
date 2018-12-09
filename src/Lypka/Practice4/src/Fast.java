import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Fast {

	public static void main(String[] args) {

		try {
			File file = new File("input\\input8.txt");
			Scanner in = new Scanner(file);

			int n = in.nextInt();
			Point[] points = new Point[n];

			StdDraw.setXscale(0, 32768);
			StdDraw.setYscale(0, 32768);

			for (int i = 0; i < n; i++) {
				points[i] = new Point(in.nextLong(), in.nextLong());
				points[i].draw();
			}

			for (int i = 0; i < n; i++) {
				int k = 0;
				Point[] slopes = new Point[n - 1];

				for (int j = 0; j < n; j++) {
					if (i == j)
						continue;
					slopes[k] = points[j];
					k++;
				}

				Arrays.sort(slopes, points[i].SLOPE_ORDER);
				int count = 1;
				for (int j = 1; j < n - 1; j++) {
					if (points[i].slopeTo(slopes[j]) == points[i].slopeTo(slopes[j - 1])) {
						count++;
					} else {
						if (count >= 3) {
							Point[] p1 = new Point[count + 1];
							Point[] p2 = Arrays.copyOfRange(slopes, j - count, j);
							for (int m = 0; m < p2.length; m++) {
								p1[m] = p2[m];
							}
							p1[p1.length - 1] = points[i];
							for (int q = 0; q < p1.length - 1; q++) {
								System.out.print(p1[q] + " -> ");
							}
							System.out.println(p1[p1.length - 1]);
							drawLine(p1);
						}
						count = 1;
					}
				}
			}

			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	public static void drawLine(Point[] p) {
		Point from = p[0], to = p[1];

		for (int i = 0; i < p.length; i++) {
			boolean min = true, max = true;
			for (int j = 0; j < p.length; j++) {
				if (i == j)
					continue;
				if (p[i].compareTo(p[j]) < 0)
					max = false;
				if (p[i].compareTo(p[j]) > 0)
					min = false;
			}
			if (min)
				from = p[i];
			if (max)
				to = p[i];
		}

		from.drawTo(to);
	}
}
