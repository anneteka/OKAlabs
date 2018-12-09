import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Scanner;

/**
 * (done)
 * Вхідні дані
 * 
 * Перший рядок кожного запиту містить єдине число n (1 ≤ n ≤ 20000) - кількість
 * членів відповідної делегації. У другому рядку запиту міститься n цілих чисел,
 * відокремлених одиничним пропуском - зріст відповідного члена делегації у
 * сантиметрах. Дані про зріст не відсортовані, так як заносились у базу даних у
 * останній момент і тому не були опрацьовані. Третій рядок запиту містить
 * власне сам запит: 2 числа - нижню та верхню границі ростового інтервалу, який
 * цікавить фірму-виробника. Запити продовжуються до кінця файлу.
 * 
 * Вихідні дані
 * 
 * Для кожного запиту у окремому рядку виведіть відповідь на нього.
 * 
 * @author Богдана
 *
 */

public class Main_sport {

	public static void main(String[] args) throws IOException {

		BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
		
		while (reader.ready()) {
			int counter = 0;
			int n = Integer.parseInt(reader.readLine());
			int[] arr = new int[n];
			
			String numbers[] = reader.readLine().split(" ");
			
			for (int i = 0; i < n; i++)
				arr[i] =Integer.parseInt(numbers[i]);

			
			numbers = reader.readLine().split(" ");
		      int a = Integer.parseInt(numbers[0]);
		      int b = Integer.parseInt(numbers[1]);

			for (int i=0; i<n;i++)
				if (arr[i] >= a && arr[i] <= b)
					counter++;
			System.out.println(counter);
			
		}
	}

}

/**
 import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main_sport {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner c = new Scanner(System.in);


		while (c.hasNextInt()) {
			int counter = 0;
			int n = c.nextInt();
			int[] arr = new int[n + 2];

			for (int i = 0; i < n + 2; i++)
				arr[i] = c.nextInt();

			int a = arr[arr.length - 1];
			int b = arr[arr.length - 2];

			for (int i=0; i<n;i++)
				if (arr[i] <= a && arr[i] >= b)
					counter++;
			System.out.println(counter);
			
		}
	}

} */
