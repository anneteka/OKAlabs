import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * программа, що для кожної точки визначає нахил до певної точки і сортує їх
 * 
 * @author Rovnina Tetiana
 *
 */
public class Fast {
	private Point[] array;

	/**
	 * конструктор, який на вхід отримує файл з координатами точок
	 */
	public Fast(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		int n = sc.nextInt(); // кількість точок
		array = new Point[n];

		Point p = null;
		for (int i = 0; i < n; i++) { // заповнюємо масив точок
			p = new Point(sc.nextInt(), sc.nextInt());
			array[i] = p;
			p.draw();
		}
	}

	/**
	 * метод, який малює лінії між точками
	 */
	public void fastDraw() {

		Point[] pointArr;
		Point[] line = new Point[4]; // масив точок на одній лінії

		for (int i = 0; i < array.length; i++) {

			pointArr = new Point[array.length - i]; // масив, який будемо сортувати для кожної точки
			System.arraycopy(array, i, pointArr, 0, array.length - i);
			ShellSort.sort(pointArr, array[i].SLOPE_ORDER);

			for (int j = 1; j < pointArr.length - 2; j++) {
				line[0] = pointArr[0];
				line[1] = pointArr[j];
				line[2] = pointArr[j + 1];
				line[3] = pointArr[j + 2];

				if (line[0].slopeTo(line[1]) == line[0].slopeTo(line[2])
						&& line[0].slopeTo(line[1]) == line[0].slopeTo(line[3])) {
					ShellSort.sort(line);// сортуємо масив, щоб намалювати одну лінію між 4 точками
					line[0].drawTo(line[3]);

					showPoints(line);
				}
			}
		}

	}

	/**
	 * метод, що виводить з'єднані точки у консоль
	 */
	private void showPoints(Point[] line) {
		for (int n = 0; n < line.length; n++) {
			if (n == line.length - 1)
				System.out.print(line[n] + "\n");
			else
				System.out.print(line[n] + " -> ");
		}
	}

}
