import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class ConvexHull {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		Scanner sc = new Scanner(new File("input40.txt"));
		int count = sc.nextInt();
		Point2D points[] = new Point2D[count];
		for (int i = 0; i < count; i++) {
			points[i] = new Point2D(sc.nextInt(), sc.nextInt());
			points[i].draw();

		}

		Arrays.sort(points);
		Arrays.sort(points, points[0].POLAR_ORDER);

		 for (int i = 1; i < count; i++) { // draws the polar angles from p
		 points[0].drawTo(points[i]);
		 Thread.sleep(30);
		 }

		Stack<Point2D> hull = new Stack<Point2D>();
		hull.push(points[0]);
		hull.push(points[1]);
		for (int i = 2; i < count; i++) {
			Point2D top = hull.pop();
			while (Point2D.ccw(hull.peek(), top, points[i]) <= 0 && hull.size() >= 2)
				top = hull.pop();
			hull.push(top);
			hull.push(points[i]);
		}
		Point2D first = hull.peek();
		while (!hull.empty()) {
			if (hull.size() > 1)
				hull.pop().drawTo(hull.peek());
			else
				hull.pop().drawTo(first);
		}
		sc.close();
	}
}