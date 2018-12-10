package phoneNumbers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * За заданим списком телефонних номерів визначіть, чи є він сумісним у тому
 * сенсі, що жоден номер не є префіксом іншого. Для кожного тесту вивести "YES",
 * якщо список телефонних номерів є сумісним, та "NO" інакше.
 * 
 * @author Пивовар Олена, 4 група
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src/phoneNumbers/input.txt")));
		PrintWriter pw = new PrintWriter(new File("src/phoneNumbers/output.txt"));
		String s = br.readLine();
		StringTokenizer tokens = new StringTokenizer(s);
		int t = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < t; i++) {
			s = br.readLine();
			tokens = new StringTokenizer(s);
			int n = Integer.parseInt(tokens.nextToken());
			String[] array = new String[n];
			String str;
			for (int j = 0; j < n; j++) {
				str = br.readLine();
				array[j] = str;
			}
			Arrays.sort(array);
			boolean correctNumber = isCorrectNumber(array);
			if (correctNumber)
				pw.println("YES");
			else
				pw.println("NO");
		}
		pw.close();
		br.close();
	}

	private boolean isCorrectNumber(String[] array) {
		int i = 0;
		int j = i + 1;
		while (j < array.length) {
			if (array[i].length() <= array[j].length()) {
				if (array[i].equals(array[j].substring(0, array[i].length())))
					return false;
			}
			i++;
			j++;
		}

		return true;
	}

}