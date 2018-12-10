import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import princetonlib.In;
import princetonlib.StdOut;

/**
 * @author Пивовар Олена, 4 група
 *
 */
public class WordNet {

	private final SAP sap;
	private final Map<Integer, String> idToSynset;
	private final Map<String, Set<Integer>> nounToIds;

	/**
	 * конструктор приймає назви двох файлів
	 * 
	 * @param synsets
	 * @param hypernyms
	 */
	public WordNet(String synsets, String hypernyms) {
		idToSynset = new HashMap<>();
		nounToIds = new HashMap<>();
		initSynsets(synsets);
		Digraph graph = initHypernyms(hypernyms);

		DirectedCycle cycle = new DirectedCycle(graph);
		if (cycle.hasCycle() || !rootedDAG(graph)) {
			throw new IllegalArgumentException();
		}

		sap = new SAP(graph);
	}

	/**
	 * @param g
	 * @return чи має одну вершину, що є предком всіх інших вершин
	 */
	private boolean rootedDAG(Digraph g) {
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
			String[] line = file.readLine().split(",");
			Integer id = Integer.valueOf(line[0]);
			String n = line[1];
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

	private Digraph initHypernyms(String hypernyms) {
		Digraph graph = new Digraph(idToSynset.size());

		In file = new In(hypernyms);
		while (file.hasNextLine()) {
			String[] line = file.readLine().split(",");
			Integer synsetId = Integer.valueOf(line[0]);
			for (int i = 1; i < line.length; i++) {
				Integer id = Integer.valueOf(line[i]);
				graph.addEdge(synsetId, id);
			}
		}

		return graph;
	}

	/**
	 * @return множина іменників, що повертається як ітератор (без дублікатів)
	 */
	public Iterable<String> nouns() {
		return nounToIds.keySet();
	}

	/**
	 * @param word
	 * @return чи є слово серед WordNet іменників?
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
	 * @return відстань між nounA і nounB
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
	 * @return синсет що є спільним предком nounA і nounB в найкоршому шляху до
	 *         предка
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

	// тестування
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
