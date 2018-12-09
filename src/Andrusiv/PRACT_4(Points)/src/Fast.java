import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.princeton.cs.algs4.*;

/*    Думайте про p як про основну.
    Для кожної іншої точки q визначимо нахил до p.
    Відсортуємо точки за нахилом, що вони роблять з p
    Перевіряємо чи існує 3 (або більше) сусідніх точок в впорядкованій множині, що мають однаковий нахил до p.
    Якщо так, ці точки разом з p колінеарні.
    Застосування цього методу до кожної з N точок, у свою чергу дає ефективний алгоритм. 
 * */

public class Fast {
	private static final String testFile = "rs1423.txt";

	public static void draw(Point p, Point[] points, int k, int same) {
		System.out.print(p);
		p.draw();
		p.drawTo(points[k - 1]);
		System.out.print(" - > ");
		System.out.print(points[k - 1]);
		points[k - 1].draw();
		for (int m = k - 2; m > k - same - 2; m--) {
			System.out.print(" - > ");
			System.out.print(points[m+1]);
			points[m + 1].drawTo(points[m]);
		}
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(testFile));
		System.setIn(in);
		int n = StdIn.readInt();
		Point[] points = new Point[n];
		Point[] copy = new Point[n];
		int i = 0;
		while (!StdIn.isEmpty()) {
			int x = StdIn.readInt();
			int y = StdIn.readInt();
			Point p = new Point(x, y);
			p.draw();
			points[i] = p;
			copy[i++] = p;
		}
		for (i = 0; i < n; i++) {
			ShellSort.sort(points, copy[i].SLOPE_ORDER);
			int same = 0;
			double prev = copy[i].slopeTo(points[0]);
			for (int k = 1; k < n; k++) {
				if (prev == copy[i].slopeTo(points[k]))
					same++;
				else if (same >= 2) {
					draw(copy[i], points, k, same);
					same = 0;
					prev = copy[i].slopeTo(points[k]);
				} else {
					same = 0;
					prev = copy[i].slopeTo(points[k]);
				}
			}
			if (same >= 2) {
				draw(copy[i], points, n, same);
			}
		}

	}

}
