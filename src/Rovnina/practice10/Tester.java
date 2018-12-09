import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * тестувальник класу SearchDictionary
 * 
 * @author Rovnina Tetiana
 *
 */
public class Tester {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		SearchDictionary dictionary = new SearchDictionary();
		new Tester().addWords(dictionary);

		while (true) {
			System.out.print("Запит: ");
			Iterable<String> words = dictionary.query(sc.next());
			for (String w : words) {// виводимо слова на екран
				System.out.println(w);
			}

		}
	}

	/**
	 * метод, який додає слова з файлу до словника
	 * 
	 * @param sd словник
	 * @throws IOException
	 */
	private void addWords(SearchDictionary sd) throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("src/input.txt"));
		String str = "";

		while ((str = bf.readLine()) != null) {
			String[] arr = str.split(" +");// ділимо стрічку на слова
			for (String s : arr)// додаємо слова в словник
				sd.addWord(s);
		}

	}

}
