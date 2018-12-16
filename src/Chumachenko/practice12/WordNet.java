package project;

import java.util.ArrayList;
import java.util.TreeMap;
import ua.princeton.lib.*;

public class WordNet {

	private Digraph net;

	private TreeMap<String, ArrayList<Integer>> map;

	private ArrayList<String> list;

	private int V;

	private SAP sap;

	/** конструктор приймає назви двох файлів */
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null)

			throw new NullPointerException();

		V = 0;
		list = new ArrayList<>();
		map = new TreeMap<>();
		In in = new In(synsets);
		String[] fields, keySet;
		String key;
		int val;
		ArrayList<Integer> temp;
		for (String line = in.readLine(); line != null; line = in.readLine(), V++) {
			fields = line.split(",");
			keySet = fields[1].split(" ");
			val = Integer.parseInt(fields[0]);
			for (int j = 0; j < keySet.length; j++) {
				key = keySet[j];
				// map.put(key, val);
				if (map.containsKey(key)) {
					temp = map.get(key);
					if (temp.add(val))
						map.put(key, temp);
				} else {
					temp = new ArrayList<>();
					if (temp.add(val))
						map.put(key, temp);
				}
			}
			list.add(fields[1]);
		}

		initH(hypernyms);

	
		DirectedCycle cycle = new DirectedCycle(net);

		if (cycle.hasCycle())

			throw new IllegalArgumentException("have cycle");


		for (int i = 0, count = 0; i < V; i++) {

			if (net.degree(i) == 0) {

				if (count == 0)
					count++;

				else
					throw new IllegalArgumentException("too many root");

			}

		}

		// init sap

		sap = new SAP(net);
	}

	private void initH(String hypernyms) {
		net = new Digraph(V);

		In in = new In(hypernyms);

		String[] fields;

		for (String line = in.readLine(); line != null; line = in.readLine()) {

			fields = line.split(",");

			for (int i = 1, k = Integer.parseInt(fields[0]); i < fields.length; i++)

				net.addEdge(k, Integer.parseInt(fields[i]));
		}

	}


	/** множина іменників, що повертається як ітератор (без дублікатів) */
	public Iterable<String> nouns() {
		return map.keySet();
	}

	/** чи є слово серед WordNet іменників? */
	public boolean isNoun(String word) {
		if (word == null)

			throw new NullPointerException();

		return map.containsKey(word);
	}

	/** відстань між nounA і nounB */
	public int distance(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))

			throw new IllegalArgumentException();

		return help(nounA, nounB)[0];
	}

	/**
	 * синсет що є спільним предком nounA і nounB в найкоршому шляху до предка
	 */
	public String sap(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))

			throw new IllegalArgumentException();

		return list.get(help(nounA, nounB)[1]);
	}

	private int[] help(String nounA, String nounB) {
		int length = Integer.MAX_VALUE, temp = 0, ancestor = 0;
		for (int val1 : map.get(nounA)) {
			for (int val2 : map.get(nounB)) {
				temp = sap.length(val1, val2);
				if (temp < length) {
					length = temp;
					ancestor = sap.ancestor(val1, val2);
				}
			}
		}

		return new int[] { length, ancestor };
	}

	/** тестування */
	public static void main(String[] args) {
	}
}