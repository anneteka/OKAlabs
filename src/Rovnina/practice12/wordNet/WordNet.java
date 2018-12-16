package wordNet;

import java.util.Arrays;

import prinston.In;
import prinston.StdOut;

/**
 * формує групи синонімів, що називає синсетами (synsets) і описує семантичні
 * зв’язки між ними
 * 
 * @author Rovnina Tetiana, IPZ 5 group
 *
 */
public class WordNet {
	private Digraph graph = null;
	private ST<String, Bag<Integer>> wordsId = new ST<String, Bag<Integer>>();
	private ST<Integer, String> words = new ST<Integer, String>();
	private SAP sap;

	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null)
			throw new IllegalArgumentException("Не вірний граф");

		int size = createSynsets(new In(synsets));
		createHypernyms(new In(hypernyms), size);
		sap = new SAP(graph);
	}

	/**
	 * створюємо орієнтований граф з пов'язаними id
	 */
	private void createHypernyms(In file, int size) {
		graph = new Digraph(size);

		// завантажуємо hypernyms
		while (file.hasNextLine()) {
			String[] line = file.readLine().split(",");
			int mainId = Integer.parseInt(line[0]);

			for (int i = 1; i < line.length; i++)
				graph.addEdge(mainId, Integer.parseInt(line[i]));
		}
	}

	/**
	 * створюємо масиви слів та їх id
	 */
	private int createSynsets(In file) {
		int countId = 0;

		// завантажуємо synsets
		while (file.hasNextLine()) {
			String[] line = file.readLine().split(",");
			int id = Integer.parseInt(line[0]);
			String[] nouns = line[1].split(" ");

			for (String noun : nouns) {
				words.put(id, noun);
				Bag<Integer> list = wordsId.get(noun);
				if (list == null)
					list = new Bag<Integer>();

				list.add(id);
				wordsId.put(noun, list);
			}

			countId++;
		}
		return countId;
	}

	/**
	 * @return іменники
	 */
	public Iterable<String> nouns() {
		return wordsId.keys();
	}

	/**
	 * @return чи є слово в словнику
	 */
	public boolean isNoun(String word) {
		return wordsId.contains(word);
	}

	/**
	 * @return відстань між nounA і nounB
	 */
	public int distance(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException("Не існує такого слова");

		Bag<Integer> a = wordsId.get(nounA);
		Bag<Integer> b = wordsId.get(nounB);

		return sap.length(a, b);
	}

	/**
	 * @return синсет що є спільним предком nounA і nounB
	 */
	public String sap(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException();

		Bag<Integer> a = wordsId.get(nounA);
		Bag<Integer> b = wordsId.get(nounB);

		if (sap.ancestor(a, b) == -1)
			return "no ancestors";
		else
			return words.get(sap.ancestor(a, b));
	}

	// тестування
	public static void main(String[] args) {
		WordNet wn = new WordNet("files/synsets.txt", "files/hypernyms.txt");
		Outcast out = new Outcast(wn);
		String[][] arrays = { { "horse", "zebra", "cat", "bear", "table" },
				{ "water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea", "coffee" },
				{ "apple", "pear", "peach", "banana", "lime", "lemon", "blueberry", "strawberry", "mango", "watermelon",
						"potato" } };

		for (String[] nouns : arrays) {
			StdOut.println(Arrays.toString(nouns));
			StdOut.println(out.outcast(nouns));
		}

	}

}
