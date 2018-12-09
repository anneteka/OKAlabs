package pr10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Дуже часто в галузі інформаційного пошуку доводиться працювати з нечітким
 * пошуком.
 * 
 * Це пов'язано з моментами коли користувачі не знають як вірно пишуться ті або
 * інші слова, знають про різні варіанти написання слів та ін. В таких ситуаціях
 * пошукові системи надають можливість використання джокера (*).
 * 
 * Приклад: В словнику присутні терміни Kyiv та Kiev і користувач знає про це.
 * Але він хоче знайти обидва цих слова, тоді він пише запит у вигляді K*v, що
 * поверне йому обидва слова.
 * 
 * Наша задача трохи простіша. Замикаючий джокер Ми дозволимо використовувати
 * користувачу лише замикаючий джокер, запити вигляду auto*. Наша задача знайти
 * всі слова в колекції, що задовільняють запиту користувача.
 * 
 * Ви маєте реалізувати наступну задачу: читати з файлу слова і утворювати "на
 * льоту" спеціалізовану структуру, що дозволить "швидко" давати відповідь на
 * запити користувача. Та маєте написати клас тестер, що: зчитує слова з файлу і
 * додає їх до SearchDictionary отримує запит користувача (з джокером та без) і
 * відображає результат пошуку на екран. Аргументувати обрану структуру даних.
 *
 */

public class SearchDictionary implements Iterable<String> {
	String adress;
	ArrayList<String> dictionary = new ArrayList<>();

	public SearchDictionary(String adress) {
		this.adress = adress;
	}

	/**
	 * Заповнює словник словами з файлу
	 * 
	 * @throws IOException
	 */
	public void fillDictionary() throws IOException {
		try {
			Scanner in = new Scanner(new File(adress));
			while (in.hasNextLine()) {

				String str = in.nextLine();
				if (str != null && str.length() != 0) {
					if (!hasWord(str)) {
						dictionary.add(str);
					}
				}
			}

		} catch (IOException e) {
			System.out.println("No file found");
		}

	}

	/**
	 * add word to dictionary
	 * 
	 * @param word-new
	 *            word
	 * @throws FileNotFoundException
	 */
	public void addWord(String word) throws FileNotFoundException {
		if (word != null) {
			dictionary.add(word);
			//updateDictionary();
		}
	}

	/**
	 * delete word from dictionary
	 * 
	 * @param word
	 *            0 word to delete
	 * @return - deleted word
	 * @throws FileNotFoundException
	 */
	public String delWord(String word) throws FileNotFoundException {
		if (hasWord(word)) {
			dictionary.remove(word);
			System.out.println(word + " succesfully deleted");
			//updateDictionary();
		} else
			System.out.println("No word found!");
		return word;
	}

	/**
	 * ckeck if the word is present in the dictionary
	 * 
	 * @param word
	 * @return
	 */
	public boolean hasWord(String word) {
		for (String e : dictionary) {
			if (dictionary.contains(word))
				return true;
		}
		return false;
	}

//	public void updateDictionary() throws FileNotFoundException {
//		PrintWriter wr = new PrintWriter(adress);
//		for (int i = 0; i < dictionary.size(); i++)
//			wr.write(dictionary.get(i));
//
//	}

	/**
	 * return the word if present
	 * 
	 * @param word
	 */
	public void printWord(String word) {
		if (hasWord(word))
			System.out.println(word);
		else
			System.out.println("no words found");
	}

	public void printJoker(String joker) {
		for (int i = 0; i < dictionary.size(); i++) {
			String s = dictionary.get(i);
			if (joker.length() <= s.length()) {
				String sub = s.substring(0, joker.length());
				if (sub.equals(joker)) {
					System.out.println(dictionary.get(i));
				}
			}
		}

	}

	public int countWords() {
		return dictionary.size();
	}

	public String toString() {
		System.out.println("Ваш словник:");
		for (String str : dictionary)
			System.out.println(str);
		return adress;
	}

	@Override
	public Iterator<String> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}