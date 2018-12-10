import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class Outcast {
	private WordNet theNet;

	public Outcast(WordNet wordnet) {
		theNet = wordnet;
	}

	public String outcast(String[] nouns) {
		int loserSoFar = -1;
		int maxDist = -1;

		for (int i = 0; i < nouns.length; i++) {
			int currDist = 0;
			for (int j = 0; j < nouns.length; j++) {
				if (j != i) {
					currDist += theNet.distance(nouns[i], nouns[j]);
				}
			}
			if (currDist > maxDist) {
				maxDist = currDist;
				loserSoFar = i;
			}
		}

		return nouns[loserSoFar];
	}

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