package PatternRecognition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import princetonlib.StdDraw;

/**
 * Клас, що досліджує 4 точки за один раз і перевіряє, чи всі вони лежать в
 * одному сегменті лінії
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Brute {

	private Point[] points;
	private int n;
	private BufferedReader reader;
	private StringTokenizer token;
	private String res = "";

	/**
	 * конструктор
	 * 
	 * @param file файл для зчитування точок
	 * @throws IOException
	 */
	public Brute(File file) throws IOException {
		reader = new BufferedReader(new FileReader(file));
		String str = reader.readLine();
		token = new StringTokenizer(str);
		n = Integer.parseInt(token.nextToken());
		points = new Point[n];
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		fillArray();
	}

	/**
	 * заповнення масиву точками
	 * 
	 * @throws IOException
	 */
	private void fillArray() throws IOException {
		for (int i = 0; i < points.length; i++) {
			if (!token.hasMoreTokens()) {
				String str = reader.readLine();
				token = new StringTokenizer(str);
			}
			points[i] = new Point(Integer.parseInt(token.nextToken()), Integer.parseInt(token.nextToken()));
			points[i].draw();
		}
	}

	/**
	 * перевірка, чи 4 точки лежать на 1 лінії
	 */
	public void findLine() {
		double slope0;
		double slope1;
		double slope2;
		for (int i = 0; i < points.length - 3; i++) {
			for (int j = i + 1; j < points.length - 2; j++) {
				slope0 = points[i].slopeTo(points[j]);
				for (int m = j + 1; m < points.length - 1; m++) {
					slope1 = points[i].slopeTo(points[m]);
					for (int k = m + 1; k < points.length; k++) {
						slope2 = points[i].slopeTo(points[k]);
						if (slope0 == slope1 && slope0 == slope2) {
							sortForPrint(points[i], points[j], points[m], points[k]);
						}
					}
				}
			}
		}
	}

	/**
	 * сортування в порядку зростання
	 * 
	 * @param point1
	 * @param point2
	 * @param point3
	 * @param point4
	 */
	private void sortForPrint(Point point1, Point point2, Point point3, Point point4) {
		Point[] fourPoints = { point1, point2, point3, point4 };
		int n = fourPoints.length;
		for (int m = 0; m < n; m++) {
			for (int l = m; l > 0; l--)
				if (fourPoints[l].compareTo(fourPoints[l - 1]) == -1) {
					Point temp = fourPoints[l];
					fourPoints[l] = fourPoints[l - 1];
					fourPoints[l - 1] = temp;
				} else
					break;
		}
		for (int l = 0; l < fourPoints.length - 1; l++) {
			res += fourPoints[l] + " -> ";
			fourPoints[0].drawTo(fourPoints[l + 1]);
		}
		res += fourPoints[fourPoints.length - 1] + "\n";
	}

	// стрінг подання
	public String toString() {
		return res;
	}
}
