package Stakhurskyi.Pr12;

import java.util.Arrays;



import princetonlib.StdOut;




public class Outcast {



	private final WordNet wordNet;



	/**

	 * ����������� ������ �ᒺ�� WordNet

	 * 

	 * @param wordnet

	 */

	public Outcast(WordNet wordnet) {

		wordNet = wordnet;

	}

	

	/**

	 * ����� ����� WordNet ��������, ��������� �������

	 * @param nouns

	 * @return ������

	 */

	public String outcast(String[] nouns) {

		String outcast = null;

		int max = 0;



		for (String first : nouns) {

			int distance = 0;

			for (String second : nouns) {

				if (!first.equals(second)) {

					distance += wordNet.distance(first, second);

				}

			}

			if (distance > max) {

				max = distance;

				outcast = first;

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