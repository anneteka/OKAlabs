import java.util.Arrays;

import princetonlib.In;
import princetonlib.StdOut;

/**
 * вимірювання семантичної зв'язності двох іменників
 * 
 * @author Пивовар Олена, 4 група
 *
 */
public class Outcast {

	private final WordNet wordNet;

	/**
	 * конструктор приймає об’єкт WordNet
	 * 
	 * @param wordnet
	 */
	public Outcast(WordNet wordnet) {
		wordNet = wordnet;
	}
	
	/**
	 * @param nouns
	 * @return маючи масив WordNet іменників, повернути «ізгоя»
	 */
	public String outcast(String[] nouns) {
		String outcast = null;
		int max = 0;

		for (String nounA : nouns) {
			int dist = 0;
			for (String nounB : nouns) {
				if (!nounA.equals(nounB)) {
					dist += wordNet.distance(nounA, nounB);
				}
			}
			if (dist > max) {
				max = dist;
				outcast = nounA;
			}
		}
		return outcast;
	}

	public static void main(String[] args) {
		WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
		Outcast out = new Outcast(wordNet);
		String[] outcast5 = { "horse", "zebra", "cat", "bear", "table" };
		String[] outcast8 = { "water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea", "coffee" };
		String[] outcast11 = { "apple", "pear", "peach", "banana", "lime", "lemon", "blueberry", "strawberry", "mango",
				"watermelon", "potato" };

		String[][] arrays = { outcast5, outcast8, outcast11 };

		for (String[] nouns : arrays) {
			StdOut.println(Arrays.toString(nouns));
			StdOut.println(out.outcast(nouns));
		}
	}

}
