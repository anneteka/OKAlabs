package Poberezhets.pr12;

import princetonlib.In;
/**
 * Class - tester
 * ��������� �������� ��� Outcast:
// ����������� ������ �ᒺ�� WordNet 
public Outcast(WordNet wordnet) 
// ����� ����� WordNet ��������, ��������� �������
public String outcast(String[] nouns) 
public static void main(String[] args)

 */

import princetonlib.StdOut;

public class OutCast {
	private WordNet wordnet;

	public OutCast(WordNet wordnet) {
		this.wordnet = wordnet;
	}

	public String outcast(String[] nouns) {
		String result = "";
		int maxDistance = 0;
		for (String n : nouns) {
			int td = 0;
			for (String tn : nouns) {
				td += wordnet.distance(n, tn);
			}
			if (td > maxDistance) {
				maxDistance = td;
				result = n;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		WordNet wn = new WordNet("src/wordnet/synsets.txt", "src/wordnet/hypernyms.txt");
		OutCast oc = new OutCast(wn);
		String[] nouns = new In("src/wordnet/outcast5.txt").readAllStrings();
		for (String s : nouns)
			System.out.print(s + " ");
		StdOut.println("src/wordnet/noutcast5.txt" + ": " + oc.outcast(nouns));
		String[] nouns8 = new In("src/wordnet/outcast8.txt").readAllStrings();
		for (String s : nouns8)
			System.out.print(s + " ");
		StdOut.println("noutcast8.txt" + ": " + oc.outcast(nouns8));
		String[] nouns11 = new In("src/wordnet/outcast11.txt").readAllStrings();
		for (String s : nouns11)
			System.out.print(s + " ");
		StdOut.println("\noutcast11.txt" + ": " + oc.outcast(nouns11));

	}

}
