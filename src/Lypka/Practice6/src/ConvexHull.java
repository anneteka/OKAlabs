import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class ConvexHull {

	public static void main(String[] args) {

		try {
			File file = new File("input\\rs1423.txt");
			Scanner in = new Scanner(file);

			int n = in.nextInt();
			Point2D[] points = new Point2D[n];

			StdDraw.setXscale(0, 32768);
			StdDraw.setYscale(0, 32768);

			for (int i = 0; i < n; i++) {
				points[i] = new Point2D(in.nextInt(), in.nextInt());
				points[i].draw();
			}

			Arrays.sort(points);
			Point2D p = points[0];
			Point2D[] sorted = new Point2D[n - 1];
			for (int i = 0; i < n - 1; i++)
				sorted[i] = points[i + 1];
			Arrays.sort(sorted, p.POLAR_ORDER);

			Stack<Point2D> stack = new Stack<>();
			stack.push(p);
			stack.push(sorted[0]);

			for (int i = 1; i < n - 1; i++) {
				if (Point2D.ccw(stack.elementAt(stack.size() - 2), stack.elementAt(stack.size() - 1), sorted[i]) > 0) {
					stack.push(sorted[i]);
				}
				else if (Point2D.ccw(stack.elementAt(stack.size() - 2), stack.elementAt(stack.size() - 1), sorted[i]) < 0) {
					stack.pop();
					i--;
				}
				else {
					stack.pop();
					stack.push(sorted[i]);					
				}
			}

			for (int i = 1; i < stack.size(); i++) {
				Thread.sleep(400);
				stack.elementAt(i - 1).drawTo(stack.elementAt(i));
			}
			Thread.sleep(400);
			stack.elementAt(stack.size() - 1).drawTo(stack.elementAt(0));

			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
