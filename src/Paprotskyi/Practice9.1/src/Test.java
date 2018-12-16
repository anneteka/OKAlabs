import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws FileNotFoundException {
		String filename = "number.txt";
		Scanner s = new Scanner(new FileInputStream(filename));
		int test = s.nextInt();
		for (int i = 0; i < test; i++) {
			int n = s.nextInt();
			String[] numbers = new String[n];
			for (int j = 0; j < n; j++) {
				numbers[j] = s.next();
			}
			System.out.println(checkCompatibility(numbers) ? "YES" : "NO");
		}
	}

	public static boolean checkPref(String prefix, String word){
		if (prefix.length() >= word.length())
			return false;
		for (int i = 0; i < prefix.length(); i++) {
			if (prefix.charAt(i) != word.charAt(i))
				return false;
		}
		return true;
	}

	public static boolean checkCompatibility(String[] numbers) {
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length - 1; i++) {
			if (checkPref(numbers[i], numbers[i + 1]))
				return false;
		}
		return true;
	}
}