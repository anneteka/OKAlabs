import java.util.Scanner;

public class Garden {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int rows = sc.nextInt();
			System.out.println(((2 + (2 * rows)) / 2) * rows+1);
		}
		sc.close();
	}
}
