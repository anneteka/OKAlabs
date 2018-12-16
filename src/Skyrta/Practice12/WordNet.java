import java.util.HashMap;
import java.util.Map;

public class WordNet {

	private Map<Integer, String> synsetIds;
	private Map<String, Bag<Integer>> words;
	private SAP sap;

	public WordNet(String synsets, String hypernyms) {
		synsetIds = new HashMap<Integer, String>();
		words = new HashMap<String, Bag<Integer>>();
		createMaps(synsets);
		createSAP(hypernyms);
	}

	// множина іменників, що повертається як ітератор (без дублікатів)
	public Iterable<String> nouns() {
		return words.keySet();
	}

	// чи є слово серед WordNet іменників?
	public boolean isNoun(String word) {
		return words.containsKey(word);
	}

	// відстань між nounA і nounB
	public int distance(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new java.lang.IllegalArgumentException("No such nouns in WordNet!");
		return sap.length(words.get(nounA), words.get(nounB));
	}

	// синсет що є спільним предком nounA і nounB
	// в найкоршому шляху до предка
	public String sap(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new java.lang.IllegalArgumentException("No such nouns in WordNet!");
		int ancestorId = sap.ancestor(words.get(nounA), words.get(nounB));
		String valueFields[] = synsetIds.get(ancestorId).split(",");
		return valueFields[0];
	}

	private void createMaps(String synsets) {
		In in = new In(synsets);
		while (in.hasNextLine()) {
			String s = in.readLine();
			String[] fields = s.split(",");
			for (int i = 0; i < fields.length; i++) {
				fields[i] = fields[i].trim();
			}

			int id = Integer.parseInt(fields[0]);
			String synsetDefinition = fields[1] + "," + fields[2];
			synsetIds.put(id, synsetDefinition);

			String synonyms[] = fields[1].split(" ");
			for (int i = 0; i < synonyms.length; i++) {
				synonyms[i] = synonyms[i].trim();
				Bag<Integer> bag = words.get(synonyms[i]);
				if (bag == null) {
					Bag<Integer> newBag = new Bag<Integer>();
					newBag.add(id);
					words.put(synonyms[i], newBag);
				} else {
					bag.add(id);
				}
			}
		}
	}

	private void createSAP(String hypernyms) {
		In in = new In(hypernyms);
		Digraph diG = new Digraph(synsetIds.size());
		while (in.hasNextLine()) {
			String curString = in.readLine();
			String[] fields = curString.split(",");
			for (int i = 0; i < fields.length; i++) {
				fields[i] = fields[i].trim();
			}
			for (int i = 1; i < fields.length; i++) {
				diG.addEdge(Integer.parseInt(fields[0]), Integer.parseInt(fields[i]));
			}
		}

		if (!isRootedDAG(diG)) {
			throw new java.lang.IllegalArgumentException("Not rooted DAG!");
		}

		sap = new SAP(diG);
	}

	private boolean isRootedDAG(Digraph diG) {
		DirectedCycle diCycle = new DirectedCycle(diG);
		if (diCycle.hasCycle()) {
			return false;
		}
		int roots = 0;
		for (int vertex = 0; vertex < diG.V(); vertex++) {
			if (!diG.adj(vertex).iterator().hasNext())
				roots++;
		}
		if (roots != 1)
			return false;
		return true;
	}

	// тестування
	public static void main(String[] args) {

	}
}
