import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Знаходження мінімальної кількісті кульок, яких потрібно перефарбувати
 * 
 * @author Пивовар Олена
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().countBalls();
	}

	/**
	 * зчитування даних з файлу та запис результату у файл, підрахунок кульок
	 * 
	 * @throws IOException
	 */
	private void countBalls() throws IOException {
		Scanner sc = new Scanner(new File("src\\input.txt"));
		PrintWriter pw = new PrintWriter(new File("src\\output.txt"));
		int[] balls = new int[10];
		for (int i = 0; i < balls.length; i++) {
			balls[i] = 0;
		}
		int n = sc.nextInt();
		if (n >= 1 && n <= 100000) {
			int[] ballsInput = new int[n];
			for (int i = 0; i < ballsInput.length; i++) {
				ballsInput[i] = sc.nextInt();
			}
			int max = 0;
			for (int i : ballsInput) {
				balls[i - 1] += 1;
				if (balls[i - 1] > max)
					max = balls[i - 1];
			}
			pw.println(n - max);
			pw.close();
		}
	}
}
