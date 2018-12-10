import java.util.Random;

import ua.princeton.lib.In;
import ua.princeton.lib.StdDraw;

public class ConvexHull {
	static String name = "G:/NaUKMA/OKA/lection5/pr4_5_data(1)/grid6x6.txt";//input6 //

	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);

		In in = new In(name);
		int N = in.readInt();
		Point2D[] points = new Point2D[N];
		int i = 0;
		while (!in.isEmpty()) {
			int x = in.readInt();
			int y = in.readInt();

			Point2D p = new Point2D(x, y);
			p.draw();
			points[i++] = p;
		}

		GrahamScan graham = new GrahamScan(points);
		int count = 0;
		Point2D prev = null; 
		Point2D first = null;
		for (Point2D p : graham.hull()) {
			 if(count > 0) {
				 prev.drawTo(p);
			 }
			 if(count == 0) first = p;
			 prev = p;
			 count++;
		}
		prev.drawTo(first);
		// Random rand = new Random();
		// int n = 5; //число вершин
		// Point2D[] points = new Point2D[n];
		// System.out.println("points: ");
		// for (int i = 0; i < n; i++) {
		// //координаты вершин
		// int x = rand.nextInt(20);
		// int y = rand.nextInt(20);
		// System.out.println("(" + x + ";" + y + ")");
		// points[i] = new Point2D(x, y);
		// }
		// GrahamScan graham = new GrahamScan(points);
		// System.out.println("------");
		// if (n==graham.ammountOfPoints()){
		// System.out.println("the hull exists");
		// }
		// else {
		// System.out.println("there is no hull with ALL points");
		// System.out.println("Possible hull:");
		// }
		// for (Point2D p : graham.hull()) {
		// System.out.println("(" + p.x + ";" + p.y + ")");
		// }

	}

}
