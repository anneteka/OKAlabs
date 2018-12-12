package Poberezhets.pr12;

/**
 * �� ���� ���������� �������� ��� WordNet � ��������� API:
// ����������� ������ ����� ���� �����
public WordNet(String synsets, String hypernyms) 
// ������� ��������, �� ����������� �� �������� (��� ��������)
public Iterable<String> nouns() 
// �� � ����� ����� WordNet ��������? 
public boolean isNoun(String word) 
// ������� �� nounA � nounB
public int distance(String nounA, String nounB) 
// ������ �� � ������� ������� nounA � nounB 
// � ���������� ����� �� ������
public String sap(String nounA, String nounB) 
// ����������

 */
import princetonlib.In;

public class WordNet {
	private Digraph digraph;
	private ST<String, Bag<Integer>> synsetsNoun;
	private ST<Integer, String> synsetsID;
	private SAP sap;
	//constructor
	public WordNet(String synsets, String hypernyms) {
		// java.lang.IllegalArgumentException ���� ������� ���� �� ����
		In synsetsIN = new In(synsets);
		In hypernymsIn = new In(hypernyms);

		this.synsetsID = new ST<>();
		this.synsetsNoun = new ST<>();

		while (synsetsIN.hasNextLine()) {
			String[] splitSynsets = synsetsIN.readLine().split(",");
			for (String oneWord : splitSynsets[1].split(" ")) {
				Bag<Integer> bagID = new Bag<>();

				if (synsetsNoun.contains(oneWord))
					bagID = synsetsNoun.get(oneWord);
				bagID.add(Integer.parseInt(splitSynsets[0]));
				synsetsNoun.put(oneWord, bagID);
			}
			synsetsID.put(Integer.parseInt(splitSynsets[0]), splitSynsets[1]);
		}

		digraph = new Digraph(synsetsID.size());
		while (hypernymsIn.hasNextLine()) {
			String[] splitHypernyms = hypernymsIn.readLine().split(",");
			int fromWhat = Integer.parseInt(splitHypernyms[0]);
			for (int i = 1; i < splitHypernyms.length; ++i) {
				digraph.addEdge(fromWhat, Integer.parseInt(splitHypernyms[i]));
			}
		}

		sap = new SAP(digraph);

	}

	public Iterable<String> nouns() {
		return synsetsNoun;
	}
//check if the word is noun
	public boolean isNoun(String word) {

		return synsetsNoun.contains(word);
	}
//return distance between A and B
	public int distance(String nounA, String nounB) {

		if (!isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException();
		Bag<Integer> idA = synsetsNoun.get(nounA);
		Bag<Integer> idB = synsetsNoun.get(nounB);

		return sap.length(idA, idB);
	}

	public String sap(String nounA, String nounB) {

		if (!isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException();
		Bag<Integer> idA = synsetsNoun.get(nounA);
		Bag<Integer> idB = synsetsNoun.get(nounB);
		if (sap.ancestor(idA, idB) == -1) {
			return "not found";
		} else {
			return synsetsID.get(sap.ancestor(idA, idB));
		}
	}

}
