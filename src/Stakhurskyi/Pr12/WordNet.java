package Stakhurskyi.Pr12;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import princetonlib.In;
import princetonlib.StdOut;

public class WordNet {

	private final SAP sap;
	private final Map<Integer, String> idToSynset;
	private final Map<String, Set<Integer>> nounToIds;

	/**
	 * ����������� ������ ����� ���� �����
	 * 
	 * @param synsets
	 * @param hypernyms
	 */
	public WordNet(String synsets, String hypernyms) {
		idToSynset = new HashMap<>();
		nounToIds = new HashMap<>();
		initSynsets(synsets);
		Digraph graph = new Digraph(idToSynset.size());

		In file = new In(hypernyms);
		while (file.hasNextLine()) {
			String[] srtArray = file.readLine().split(",");
			int synsetId = Integer.parseInt(srtArray[0]);
			for (int i = 1; i < srtArray.length; i++) {
				int id = Integer.parseInt(srtArray[i]);
				graph.addEdge(synsetId, id);
			}
		}

		DirectedCycle cycle = new DirectedCycle(graph);
		if (cycle.hasCycle() || !onlyOneRoot(graph)) {
			throw new IllegalArgumentException();
		}

		sap = new SAP(graph);
	}

	/**
	 * @param g
	 * @return �� �� ���� �������, �� � ������� ��� ����� ������
	 */
	private boolean onlyOneRoot(Digraph g) {
		int roots = 0;
		for (int i = 0; i < g.V(); i++) {
			if (!g.adj(i).iterator().hasNext()) {
				roots++;
				if (roots > 1) {
					return false;
				}
			}
		}

		return roots == 1;
	}

	private void initSynsets(String synset) {
		In file = new In(synset);
		while (file.hasNextLine()) {
			String[] strArray = file.readLine().split(",");
			Integer id = Integer.valueOf(strArray[0]);
			String n = strArray[1];
			idToSynset.put(id, n);

			String[] nouns = n.split(" ");
			for (String noun : nouns) {
				Set<Integer> ids = nounToIds.get(noun);
				if (null == ids) {
					ids = new HashSet<>();
				}
				ids.add(id);
				nounToIds.put(noun, ids);
			}
		}
	}

	/**
	 * @return ������� ��������, �� ����������� �� �������� (��� ��������)
	 */
	public Iterable<String> nouns() {
		return nounToIds.keySet();
	}

	/**
	 * @param word
	 * @return �� � ����� ����� WordNet ��������?
	 */
	public boolean isNoun(String word) {
		if (null == word || "".equals(word)) {
			return false;
		}
		return nounToIds.containsKey(word);
	}

	/**
	 * @param nounA
	 * @param nounB
	 * @return ������� �� nounA � nounB
	 */
	public int distance(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB)) {
			throw new IllegalArgumentException();
		}
		Set<Integer> idsOfNounA = nounToIds.get(nounA);
		Set<Integer> idsOfNounB = nounToIds.get(nounB);
		return sap.length(idsOfNounA, idsOfNounB);
	}

	/**
	 * 
	 * @param nounA
	 * @param nounB
	 * @return ������ �� � ������� ������� nounA � nounB � ���������� ����� ��
	 *         ������
	 * 
	 */
	public String sap(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB)) {
			throw new IllegalArgumentException();
		}
		Set<Integer> idsOfNounA = nounToIds.get(nounA);
		Set<Integer> idsOfNounB = nounToIds.get(nounB);
		int ancestor = sap.ancestor(idsOfNounA, idsOfNounB);
		return idToSynset.get(ancestor);
	}

	// ����������
	public static void main(String[] args) {
		WordNet wordNet = new WordNet("synsets.txt", "hypernyms.txt");
		Outcast out = new Outcast(wordNet);
		String[] outcast5 = { "horse", "zebra", "cat", "bear", "table" };
		String[] outcast8 = { "water", "soda", "bed", "orange_juice", "milk", "apple_juice", "tea", "coffee" };
		String[] outcast11 = { "apple", "pear", "peach", "banana", "lime", "lemon", "blueberry", "strawberry", "mango",
				"watermelon", "potato" };

		String[][] arrays = { outcast5, outcast8, outcast11 };

		In in = new In(new File("digraph1.txt"));
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);

		int digraph1[][] = { { 3, 1 }, { 9, 12 }, { 7, 2 }, { 1, 6 } };

		for (int i = 0; i < digraph1.length; i++) {
			int v = digraph1[i][0];
			int w = digraph1[i][1];
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.println(v + " " + w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}

		StdOut.println();

		for (String[] nouns : arrays) {
			StdOut.println(Arrays.toString(nouns));
			StdOut.println(out.outcast(nouns));
		}

	}

}