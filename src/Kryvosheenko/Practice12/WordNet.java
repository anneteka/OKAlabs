import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.ST;

public class WordNet {
	private SAP sap;
	private Digraph digraph;
	private ST<Integer, String> IDSynsets;
	private ST<String, ArrayList<Integer>> WordIndexSynsetsTable;

	// конструктор приймає назви двох файлів
	public WordNet(String synsets, String hypernyms) {
		this.IDSynsets = new ST<>();
		this.WordIndexSynsetsTable = new ST<>();
		BufferedReader hyperIN = null;
		BufferedReader synIN = null;
		try {
			hyperIN = new BufferedReader(new FileReader(hypernyms));
			synIN = new BufferedReader(new FileReader(synsets));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String s = "";
		try {
			while ((s = synIN.readLine()) != null) {
				String[] stringSynArr = s.split(",");
				for (String noun : stringSynArr[1].split(" ")) {
					ArrayList<Integer> id = new ArrayList<Integer>();
					if (WordIndexSynsetsTable.contains(noun)) {
						id = WordIndexSynsetsTable.get(noun);
					}
					id.add(Integer.parseInt(stringSynArr[0]));
					WordIndexSynsetsTable.put(noun, id);
				}
				IDSynsets.put(Integer.parseInt(stringSynArr[0]), stringSynArr[1]);
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		digraph = new Digraph(IDSynsets.size());
		s = "";
		try {
			while ((s = hyperIN.readLine()) != null) {
				String[] splitHypernyms = s.split(",");
				int source = Integer.parseInt(splitHypernyms[0]);
				for (int i = 1; i < splitHypernyms.length; i++) {
					digraph.addEdge(source, Integer.parseInt(splitHypernyms[i]));
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		sap = new SAP(digraph);
	}

	// множина іменників, що повертається як ітератор (без дублікатів)
	public Iterable<String> nouns() {
		return WordIndexSynsetsTable;
	}

	// чи є слово серед WordNet іменників?
	public boolean isNoun(String word) {
		return WordIndexSynsetsTable.contains(word);
	}

	// відстань між nounA і nounB
	public int distance(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new java.lang.IllegalArgumentException("There is no such noun in WordNet");
		ArrayList<Integer> idA = WordIndexSynsetsTable.get(nounA);
		ArrayList<Integer> idB = WordIndexSynsetsTable.get(nounB);
		return sap.length(idA, idB);
	}

	// синсет що є спільним предком nounA і nounB
	// в найкоршому шляху до предка
	public String sap(String nounA, String nounB) {
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new java.lang.IllegalArgumentException("There is no such noun in WordNet");
		ArrayList<Integer> idA = WordIndexSynsetsTable.get(nounA);
		ArrayList<Integer> idB = WordIndexSynsetsTable.get(nounB);
		if (sap.ancestor(idA, idB) == -1) {
			return "None";
		} else {
			return IDSynsets.get(sap.ancestor(idA, idB));
		}
	}

	// тестування
	public static void main(String[] args) {
		WordNet wNet = new WordNet("synsets.txt", "hypernyms.txt");
		System.out.println("Does WordNet include word neighborhood? " + wNet.isNoun("neighborhood"));
		System.out.println("Distance between neighborhood and antbird: " + wNet.distance("antbird", "neighborhood"));
		System.out.println("Ancestor of neighborhood and antbird: " + wNet.sap("antbird", "neighborhood"));
	}
}
