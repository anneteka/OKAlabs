import java.util.ArrayList;

public class Outcast {
	private WordNet wordnet;
	private ArrayList<ArrayList<Integer>> distances;

	// конструктор приймає об’єкт WordNet
	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}

	// маючи масив WordNet іменників, повернути «ізгоя»
	public String outcast(String[] nouns) {
		distances = new ArrayList<ArrayList<Integer>>();
		int[] countDist = new int[nouns.length];
		for (int i = 0; i < nouns.length; i++) {
			int count = 0;
			ArrayList<Integer> intList = new ArrayList<Integer>();
			distances.add(intList);
			for (int j = i + 1; j < nouns.length; j++) {
				int distance = wordnet.distance(nouns[i], nouns[j]);
				distances.get(i).add(distance);
				count += distance;
			}
			for (int j = i - 1; j >= 0; j--) {
				count += distances.get(j).get(nouns.length - 2 - j);
			}
			countDist[i] = count;
		}
		int maxDist = 0;
		int maxDistIndex = -1;
		for (int i = 0; i < countDist.length; i++) {
			if (countDist[i] >= maxDist) {
				maxDist = countDist[i];
				maxDistIndex = i;
			}
		}
		if (maxDistIndex == -1)
			return "Same distance";
		return nouns[maxDistIndex];
	}

	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			String[] nouns = In.readStrings(args[t]);
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}

	}
}
