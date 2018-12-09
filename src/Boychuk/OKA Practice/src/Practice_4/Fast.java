package Practice_4;

import java.util.Arrays;

import java.util.List;
import java.util.Scanner;

import ua.princeton.lib.In;
import ua.princeton.lib.StdDraw;
import ua.princeton.lib.StdOut;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;

public class Fast {
	private static void printLine(Point[] points, int start, int end) {
		Arrays.sort(points, start, end);

		for (int i = start; i < end; i++) {
			StdOut.print(points[i] + " -> ");
		}
		StdOut.println(points[0]);

		points[start].drawTo(points[0]);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("D:\\lecture11\\workspasse\\OKA Practice\\src\\grid6x6.txt"));
		int n = sc.nextInt();
		Point[] points = new Point[n];
		Point[] pointsBySlope = new Point[n];
		double slopePQ, slopePR, slopePS;
		StdDraw.setPenColor(Color.BLUE);
		StdDraw.setXscale(0, 32768);

		StdDraw.setYscale(0, 32768);

		for (int i = 0; i < n; i++) {
			points[i] = new Point(sc.nextInt(), sc.nextInt());
			pointsBySlope[i] = points[i];
			points[i].draw();
		}

		Arrays.sort(points);
		List<Double> ignoreSlopes = new ArrayList<Double>();
		StdDraw.setPenColor(Color.GREEN);
		for (int i = 0; i < n; i++) {
			Point currentPoint = points[i];
			

			Arrays.sort(pointsBySlope, 0, n, currentPoint.SLOPE_ORDER);

			ignoreSlopes.clear();

			int position = 1;
			for (int j = 1; j < n; j++) {
				Point point = pointsBySlope[j];
				double currentSlope = currentPoint.slopeTo(point);
				double previousSlope = currentPoint.slopeTo(pointsBySlope[j - 1]);

				if (previousSlope != currentSlope) {
					if (j - position > 2)
						printLine(pointsBySlope, position, j);

					position = j;
				}

				boolean alreadyIgnoredSlope = ignoreSlopes.contains(currentPoint.slopeTo(point));

				if (currentPoint.compareTo(point) < 0) {
					if (!alreadyIgnoredSlope)
						ignoreSlopes.add(currentPoint.slopeTo(point));

					position = j + 1;
				}

				if (alreadyIgnoredSlope) {
					position = j + 1;
				}
			}

			if (n - position > 2) {
				printLine(pointsBySlope, position, n);
			}
		}
	}
}