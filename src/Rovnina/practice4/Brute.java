import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * программа, що досліджує 4 точки за один раз і перевіряє, чи всі вони лежать в
 * одному сегменті лінії, виводить кожну таку лінію і малюйте використовуючи
 * StdDraw.
 * 
 * @author Rovnina Tetiana
 *
 */
public class Brute {
	private Point[] array;

	/**
	 * конструктор, який на вхід отримує файл з координатами точок
	 */
	public Brute(File file) throws FileNotFoundException {
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
	 * метод, що шукає точки на одній лінії і з'єднує їх
	 */
	public void bruteForse() {
		Point[] line = new Point[4]; // масив точок на одній лінії

		for (int i = 0; i < array.length - 3; i++) {
			for (int j = i + 1; j < array.length - 2; j++) {
				for (int k = j + 1; k < array.length - 1; k++) {
					for (int l = k + 1; l < array.length; l++) {
						line[0] = array[i];
						line[1] = array[j];
						line[2] = array[k];
						line[3] = array[l];
						if (line[0].slopeTo(line[1]) == line[0].slopeTo(line[2])
								&& line[0].slopeTo(line[1]) == line[0].slopeTo(line[3])) {

							ShellSort.sort(line);// сортуємо масив, щоб намалювати одну лінію між 4 точками
							line[0].drawTo(line[3]);
							
							showPoints(line);
						}
					}
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
