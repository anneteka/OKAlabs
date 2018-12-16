package wordNet;

import prinston.In;
import prinston.StdOut;

/**
 * клас, який визначає "ізгоя"
 * 
 * @author Rovnina Tetiana
 *
 */
public class Outcast {
	private WordNet wordNet;

	/**
	 * конструктор приймає об’єкт WordNet
	 */
	public Outcast(WordNet wordNet) {
		this.wordNet = wordNet;
	}

	/**
	 * @return маючи масив WordNet іменників, повернути «ізгоя»
	 */
	public String outcast(String[] nouns) {
		int maxDist = 0;
		int out = -1;
		for (int i = 0; i < nouns.length; i++) {
			int dist = 0;
			for (int j = 0; j < nouns.length; j++)
				dist += wordNet.distance(nouns[i], nouns[j]);

			if (dist > maxDist) {
				maxDist = dist;
				out = i;
			}
		}
		return nouns[out];

	}

	// тестування
	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}

}
