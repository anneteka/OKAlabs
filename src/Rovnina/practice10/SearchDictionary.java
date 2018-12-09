import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * клас, який створює словник. та має методи для роботи зі словником
 * 
 * @author Rovnina Tetiana
 *
 */
public class SearchDictionary {
	// для словника був використаний TreeSet оскільки він зберігає лише унікальні
	// слова та сортує їх за алфавітом
	private TreeSet<String> dictionary;
	private int count;

	/**
	 * конструктор, який створюэ новий словник
	 */
	public SearchDictionary() {
		dictionary = new TreeSet<>();
	}

	/**
	 * додати слово до словника
	 * 
	 * @param word слово, яке треба додати
	 */
	public void addWord(String word) {
		dictionary.add(word);
		count++;
	}

	/**
	 * видалити слово зі словника
	 * 
	 * @param word слово, яке треба видалити
	 * @return видалене слово або нічого, якщо слово не було знайдене
	 */
	public String delWord(String word) {
		if (dictionary.remove(word)) {
			count--;
			return word;
		}
		return null;
	}

	/**
	 * чи є слово в словнику
	 * 
	 * @param word слово
	 * @return true якщо слово є в словнику
	 */
	public boolean hasWord(String word) {
		return dictionary.contains(word);
	}

	/**
	 * повертає слова, які шукав користувач
	 * 
	 * @param query запит
	 * @return список слів
	 */
	public Iterable<String> query(String query) {
		List<String> words = new ArrayList<>();

		if (query.charAt(query.length() - 1) == '*') {// якщо запит з *
			query = query.substring(0, query.length() - 1);// прибрати *

			Set<String> tail = dictionary.tailSet(query, true);// частина словника починаючи від слова запиту і до кінця

			for (String w : tail) {// знайти лише ті слова, які нам потрібні
				if (w.indexOf(query) != -1)
					words.add(w);
				else
					break;
			}

		} else if (hasWord(query))// якщо запит без *
			words.add(query);

		return words;
	}

	/**
	 * @return кількість слів у словнику
	 */
	public int countWords() {
		return count;
	}
}