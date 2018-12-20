package Stakhurskyi.Pr10;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.LinkedHashSet;





public class SearchDictionary {



	/**

	 * ����� � ��������

	 */

	private LinkedHashSet<String> set;

	/**

	 * ����� ���� ������

	 */

	private ArrayList<String> res;

	/**

	 * ������� ���

	 */

	private int count;



	/**

	 * �����������

	 */

	public SearchDictionary() {

		set = new LinkedHashSet<>();

		count = 0;

	}



	/**

	 * @param word �����, ��� ������� ������

	 */

	public void addWord(String word) {

		String wordCopy = word.toLowerCase();

		if (!set.contains(wordCopy)) {

			set.add(wordCopy);

			count++;

		}

	}



	/**

	 * @param word �����, ��� ������� ��������

	 * @return �������� �����, null - ����� ���� � ��������

	 */

	public String delWord(String word) {

		String wordCopy = word.toLowerCase();

		if (!set.contains(wordCopy))

			return null;

		set.remove(wordCopy);

		return word;

	}



	/**

	 * @param word �����, ��� ������� ������ � �������� (��� �������)

	 * @return �� ���� ����� � ��������

	 */

	public boolean hasWord(String word) {

		String copyWord = word.toLowerCase();

		return set.contains(copyWord);

	}



	/**

	 * @param str  �����, ��� ��������� ���������, �� �� ���� ����� �������, �� �

	 *             ������ �����

	 * @param word ����� � ��������

	 * @return �� � ���� ����� �������

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

	 * @param query �����, ��� ������� ������ (� �������� ��� ���)

	 * @return ������, �� ������ ���������� ������

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

	 * @return ������� ��� � ��������

	 */

	public int countWords() {

		return count;

	}



	@Override

	public String toString() {

		return "SearchDictionary = " + set;

	}



}