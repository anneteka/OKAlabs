import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * клас, що створює словник, міститьь методи додавання та видалення зі словника,
 * а також метод пошуку слова в словнику. Користувач може вводити слово із
 * замикаючим джокером або без нього
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class SearchDictionary {

	/**
	 * слова зі словника
	 */
	private LinkedHashSet<String> set;
	/**
	 * слова після пошуку
	 */
	private ArrayList<String> res;
	/**
	 * кількість слів
	 */
	private int count;

	/**
	 * конструктор
	 */
	public SearchDictionary() {
		set = new LinkedHashSet<>();
		count = 0;
	}

	/**
	 * @param word слово, яке потрібно додати
	 */
	public void addWord(String word) {
		String wordCopy = word.toLowerCase();
		if (!set.contains(wordCopy)) {
			set.add(wordCopy);
			count++;
		}
	}

	/**
	 * @param word слово, яке потрібно видалити
	 * @return видалене слово, null - слова немає в словнику
	 */
	public String delWord(String word) {
		String wordCopy = word.toLowerCase();
		if (!set.contains(wordCopy))
			return null;
		set.remove(wordCopy);
		return word;
	}

	/**
	 * @param word слово, яке потрібно знайти в словнику (без джокера)
	 * @return чи існує слово в словнику
	 */
	public boolean hasWord(String word) {
		String copyWord = word.toLowerCase();
		return set.contains(copyWord);
	}

	/**
	 * @param str  слово, яке необхідно перевірити, чи має воно такий початок, як і
	 *             задане слово
	 * @param word слово з джокером
	 * @return чи є дане слово шуканим
	 */
	private boolean present(String str, String word) {
		boolean res = true;
		if (word.length() - str.length() > 1)
			return false;
		for (int i = 0; i < word.length() - 1; i++) {
			if (str.charAt(i) != word.charAt(i))
				res = false;
		}
		return res;
	}

	/**
	 * @param query слово, яке потрібно знайти (з джокером або без)
	 * @return список, що містить результати пошуку
	 */
	public Iterable<String> query(String query) {
		res = new ArrayList<>();
		String wordCopy = query.toLowerCase();
		if (wordCopy.charAt(wordCopy.length() - 1) == '*') {
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String str = iterator.next();
				if (present(str, wordCopy))
					res.add(str);
			}
		} else {
			if (set.contains(wordCopy))
				res.add(wordCopy);
		}
		return res;
	}

	/**
	 * @return кількість слів в словнику
	 */
	public int countWords() {
		return count;
	}

	@Override
	public String toString() {
		return "SearchDictionary = " + set;
	}

}
