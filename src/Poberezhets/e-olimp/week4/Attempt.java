import java.util.Scanner;

/**
 * (Done) Для настольної гри використовуються картки з номерами від 1 до n
 * (натуральне число n не перевищує 106). Одна картка загубилась. Знайдіть її.
 * 
 * Вхідні дані
 * 
 * Першим задано число n. Далі йдуть n - 1 номерів карток, що залишились.
 * 
 * Вихідні дані
 * 
 * Вивести номер втраченої картки.
 * 
 * @author Богдана
 *
 */
public class Attempt {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long n = in.nextLong();

		if (n < 1000000) {
			int res = 0;
			for (int j = 1; j <= n; j++) {
				res += j;
			}
			for (int i = 1; i < n; i++)
				res -= in.nextLong();

			System.out.println(res);
		}

	}

}