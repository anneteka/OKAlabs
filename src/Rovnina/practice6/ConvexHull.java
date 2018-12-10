import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * клас, що малює опуклу оболонку навколо точок
 * 
 * @author Rovnina Tetiana
 *
 */
public class ConvexHull {

	private Point2D[] array;

	public static void main(String[] args) throws IOException {
		StdDraw.setXscale(-1000, 32768);
		StdDraw.setYscale(-1000, 32768);

		new ConvexHull(new File("files/input50.txt")).buildHull();

	}

	/**
	 * створюємо масив точок та малюємо їх
	 */
	public ConvexHull(File file) throws IOException {
		Scanner sc = new Scanner(file);
		int n = sc.nextInt(); // кількість точок
		array = new Point2D[n];

		Point2D p = null;
		for (int i = 0; i < n; i++) { // заповнюємо масив точок
			p = new Point2D(sc.nextInt(), sc.nextInt());
			array[i] = p;

			p.draw();
		}
	}

	/**
	 * будуємо опуклу оболонку
	 */
	public void buildHull() {
		MergeSort.sort(array);// сортуємо точки в декартовій системі координат
		MergeSort.sort(array, array[0].Polar_Order);// сортуємо точки за полярною системою координат відносно найменшої

		ArrayList<Point2D> list = new ArrayList<>();// список точок по периметру
		for (Point2D p : array) {
			list.add(p);
		}

		Point2D p1 = null;
		Point2D p2 = null;
		Point2D p3 = null;

		int i = 0;
		while ((i + 2) != list.size()) {// перевіряємо точки і викидаємо ті, що не входять до оболонки
			p1 = list.get(i);
			p2 = list.get(i + 1);
			if ((i + 2) == list.size())// перевірка для останньої точки
				p3 = list.get(0);
			else
				p3 = list.get(i + 2);

			if (Point2D.ccw(p1, p2, p3) > 0)
				i++;
			else {// викидаємо зайву точку
				list.remove(i + 1);
				if (i != 0)
					i--;
			}
		}

		drawHull(list);
	}

	/**
	 * малюємо оболонку
	 */
	private void drawHull(ArrayList<Point2D> list) {
		int j = 0;
		for (; j < list.size() - 1; j++)
			list.get(j).drawTo(list.get(j + 1));

		list.get(j).drawTo(list.get(0));// з'єднуємо останню та першу точки
	}

}
