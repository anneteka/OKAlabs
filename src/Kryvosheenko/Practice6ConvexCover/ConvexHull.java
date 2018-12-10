import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import ua.princeton.lib.StdDraw;

public class ConvexHull {
	private static ArrayList<Point2D> list;
	private static ArrayList<Point2D> connectedPoints;
	private static int number;

	public static void main(String args[]) {
		drawPoints();
		createCover();
		System.out.println("end");

	}

	private static void drawPoints() {
		File file = new File("rs1423.txt");
		try {
			Scanner sc = new Scanner(file);
			if (sc.hasNextInt())
				number = sc.nextInt();
			list = new ArrayList<>(number);
			while (sc.hasNext()) {
				list.add(new Point2D(sc.nextInt(), sc.nextInt()));
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		StdDraw.setPenRadius(0.005);

		for (int i = 0; i < list.size(); i++) {
			list.get(i).draw();
		}
	}

	private static void createCover() {
		// firstly find the biggest point and sort others
		Collections.sort(list);
		Point2D p = list.get(0);
		// sort
		Collections.sort(list, p.POLAR_ORDER);

		connectedPoints = new ArrayList<>(1);
		connectedPoints.add(list.get(0));
		connectedPoints.add(list.get(1));

		for (int i = 2; i < list.size(); i++) {
			while ((connectedPoints.size() > 2) && (connectedPoints.get(connectedPoints.size() - 2)
					.ccw(connectedPoints.get(connectedPoints.size() - 1), list.get(i)) != 1)) {
				connectedPoints.remove(connectedPoints.remove(connectedPoints.size() - 1));
			}

			connectedPoints.add(list.get(i));

		}
		StdDraw.setPenColor(Color.RED);
		for (int n = 0; n < connectedPoints.size() - 1; n++) {
			connectedPoints.get(n).drawTo(connectedPoints.get(n + 1));
		}

		list.get(list.size() - 1).drawTo(p);

	}

}
