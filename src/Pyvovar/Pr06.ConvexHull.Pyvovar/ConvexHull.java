package convexhull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import princetonlib.StdDraw;

/**
 * малює опуклу оболонку
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class ConvexHull {

	/**
	 * масив точок, що зчитали з файлу
	 */
	private Point2D[] arrayPoints;

	/**
	 * найменша точка
	 */
	private Point2D p;

	public static void main(String[] args) throws IOException {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		ConvexHull convexHull = new ConvexHull();
		convexHull.run(new File("rs1423.txt"));
	}

	/**
	 * виклик функцій зчитування з файлу, пошук найменшої точки, сортування за
	 * полярним кутом, знаходження опуклої оболонки
	 * 
	 * @param file файл, з якого зчитуємо точки
	 * @throws IOException
	 */
	private void run(File file) throws IOException {
		readPoints(file);
		MergeSort.sort(arrayPoints); // сортуємо точки за збільшенням
		p = arrayPoints[0]; // перша точка у відсортованому масиві найменша
		MergeSort.sort(arrayPoints, p.POLAR_ORDER);
		findConvexHull();
	}

	/**
	 * знаходення точок, які утворюють опуклу оболонку. малювання опуклої оболонки
	 */
	private void findConvexHull() {
		ArrayList<Point2D> pointsToDraw = new ArrayList<>();
		for (int i = 0; i < arrayPoints.length; i++) { // перезаписуємо в ArrayList
			pointsToDraw.add(arrayPoints[i]);
		}

		int count = 0;
		Point2D first; // три точки для знаходження ccw
		Point2D second;
		Point2D third;

		while ((count + 2) != pointsToDraw.size()) {
			first = pointsToDraw.get(count);
			second = pointsToDraw.get(count + 1);
//			if (count + 2 == pointsToDraw.size()) //вкінці порівнюємо з першою точкою
//				third = pointsToDraw.get(0);
//			else
				third = pointsToDraw.get(count + 2);
			if (Point2D.ccw(first, second, third) > 0) //якщо більше 0, то додаємо в масив
				count++;
			else {
				pointsToDraw.remove(count + 1);
				if (count != 0)
					count--;
			}
		}

		for (int i = 0; i < pointsToDraw.size() - 1; i++) {
			pointsToDraw.get(i).drawTo(pointsToDraw.get(i + 1)); // малювання точок
		}
		pointsToDraw.get(pointsToDraw.size() - 1).drawTo(pointsToDraw.get(0));
	}

	/**
	 * зчитування точок з файлу в масив
	 * 
	 * @param file
	 * @throws IOException
	 */
	private void readPoints(File file) throws IOException {
		Scanner sc = new Scanner(file);
		int n = sc.nextInt();
		arrayPoints = new Point2D[n];

		for (int i = 0; i < n; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			arrayPoints[i] = new Point2D(x, y);
			arrayPoints[i].draw();
		}
	}

}
