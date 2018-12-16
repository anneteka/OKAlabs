package Karlysheva.practice12;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class Outcast {

    private WordNet wordnet;
    private String outcast;
    private int distance;

    // constructor takes a WordNet object
    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
        this.outcast = null;
        this.distance = -1;
    }

    // given an array of WordNet nouns, return an outcast
    public String outcast(String[] nouns) {
        int curDistance = 0;
        String curOutacast = nouns[0];
        for (String thisNoun : nouns) {
            int thisNounDistance = 0;
            for (String thatNoun : nouns) {
                thisNounDistance += wordnet.distance(thisNoun, thatNoun);
            }
            if (thisNounDistance > curDistance) {
                curDistance = thisNounDistance;
                curOutacast = thisNoun;
            }
        }
        distance = curDistance;
        outcast = curOutacast;
        return outcast;
    }

    // see test client below
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