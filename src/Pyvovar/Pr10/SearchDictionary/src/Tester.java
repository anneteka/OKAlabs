import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * клас, що зчитує слова з файлу і додає їх до SearchDictionary, отримує запит
 * користувача (з джокером та без) і відображає результат пошуку в консоль
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Tester {

	private File file = new File("Dictionary01.txt");
	private Scanner sc;
	private SearchDictionary search;
	private ArrayList<String> res;

	public static void main(String[] args) throws IOException {
		Tester tester = new Tester();
		tester.readDictionary();
		tester.seachWord();
	}

	/**
	 * зчитування з фпйлу слів та запис в словник
	 * 
	 * @throws IOException
	 */
	private void readDictionary() throws IOException {
		sc = new Scanner(file);
		String str;
		search = new SearchDictionary();
		while (sc.hasNext()) {
			str = sc.next();
			search.addWord(str);
		}
	}

	/**
	 * отримування запиту від користувача та виведення на екран результат пошуку
	 */
	private void seachWord() {
		System.out.println("Слова з файлу: " + file.getName());
		sc = new Scanner(System.in);
		String wordToSeach;
		while (true) {
			System.out.print("Введіть слово: ");
			wordToSeach = sc.next();
			res = (ArrayList<String>) search.query(wordToSeach);
			if(res.isEmpty())
				System.out.println("даного слова в словнику немає");
			else
				System.out.println(res.toString());
		}
	}

}
