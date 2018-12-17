import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private WordNet wordnet;

    public Outcast(WordNet wordnet) {
        this.wordnet = wordnet;
    }


    public String outcast(String[] nouns) {
        String result = "";
        int maxDistance = 0;
        for(String n: nouns){
            int td = 0;
            for(String tn: nouns){
                td += wordnet.distance(n, tn);
            }
            if (td > maxDistance){
                maxDistance = td;
                result = n;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        WordNet wn =  new WordNet("synsets.txt", "hypernyms.txt");
        Outcast oc = new Outcast(wn);
        String[] nouns =  new In("outcast5.txt").readAllStrings();
        for(String s: nouns) System.out.print(s + " ");
        StdOut.println("\noutcast5.txt" + ": " + oc.outcast(nouns));
        String[] nouns8 =  new In("outcast8.txt").readAllStrings();
        for(String s: nouns8) System.out.print(s + " ");
        StdOut.println("\noutcast8.txt" + ": " + oc.outcast(nouns8));
        String[] nouns11 =  new In("outcast11.txt").readAllStrings();
        for(String s: nouns11) System.out.print(s + " ");
        StdOut.println("\noutcast11.txt" + ": " + oc.outcast(nouns11));

    }

}
