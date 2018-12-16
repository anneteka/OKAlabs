import java.io.IOException;

import static edu.princeton.cs.algs4.In.readStrings;

public class Outcast {

    private WordNet wn;

    public Outcast(WordNet wn) {
        this.wn = wn;
    }

    // повертає "ізгоя"
    public String outcast(String[] words) {
        String top = "";
        int maxLength = 0;
        for (int i = 0; i < words.length; i++) {
            int currentDist = 0;
            for (int j = 0; j < words.length; j++) {
                currentDist += wn.distance(words[i], words[j]);
            }
            if (maxLength < currentDist) {
                maxLength = currentDist;
                top = words[i];
            }
        }
        return top;
    }

    public static void main(String[] args) throws IOException {
        String argTest[] = {"input/synsets.txt","input/hypernyms.txt","input/outcast5.txt","input/outcast8.txt","input/outcast11.txt"};
        WordNet wordnet = new WordNet(argTest[0], argTest[1]);
        Outcast outcast = new Outcast(wordnet);
        for (int t = 2; t < argTest.length; t++) {
            String[] nouns = readStrings(argTest[t]);
            StdOut.println(argTest[t] + ": " + outcast.outcast(nouns));
        }
    }
}