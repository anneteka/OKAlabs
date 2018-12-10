package doctorWho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * визначити, чи щасливі усі гості лікаря Хто
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}
	
	/**
	 * зчитування даних, запис їх у файл, виклик інших функцій
	 * @throws IOException
	 */
	private void run() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("src\\doctorWho\\input.txt")));
		PrintWriter pw = new PrintWriter(new File("src\\doctorWho\\output.txt"));
		while(true) {
			String s1 = br.readLine();
			if(s1 == null)
				break;
			StringTokenizer token = new StringTokenizer(s1);
			ArrayList<Integer> array = new ArrayList<>();
			int k = token.countTokens();
			for (int i = 0; i < k; i++) {
				int a = Integer.parseInt(token.nextToken());
				array.add(a);
			}
			if(allHappy(array))
				pw.println("ok");
			else
				pw.println("fail");
			pw.println();
		}
		pw.close();
		br.close();
	}

	/**
	 * @param array список гостей
	 * @return чи щасливі усі гості
	 */
	private boolean allHappy(ArrayList<Integer> array) {
		boolean allHappy = true;
		while(allHappy) {
			int i = 0;
			int j = 1;
			int n = array.get(0);
			if(n >= array.size()) {
				allHappy = false;
				break;
			}
			for (int k = 0; k < n; k++) { // перший гість спілкується з кількістю гостей, щоб бути щасливим
				int a = array.get(i)-1;
				int b = array.get(j)-1;
				array.set(i, a);
				array.set(j++, b);
			}
			array = sort(array);
			int c;
			while (true) {
				c = array.size()-1;
				if(c < 0) 
					break;
				if(array.get(c) == 0)
					array.remove(array.size()-1); // забираємо гостей, які вже щасливі (в списку мають 0)
				else
					break;
			}
			if(array.isEmpty()) { // якщо список порожній - усі щасливі
				allHappy = true;
				break;
			}
			if(array.size() == 1) { // якщо список розміром 1, то є нещасливі гості
				allHappy = false;
				break;
			}
			
		}
		return allHappy;
	}

	/**
	 * @param array
	 * @return відсортований список за спаданням
	 */
	private ArrayList<Integer> sort(ArrayList<Integer> array) {
		int n = array.size();
		for (int i = 0; i < n; i++) {
			for (int j = i; j > 0; j--)
				if (array.get(j) > array.get(j-1)) {
					int temp = array.get(j);
					array.set(j, array.get(j-1));
					array.set(j-1, temp);
				} else
					break;
		}
		return array;
	}

}
