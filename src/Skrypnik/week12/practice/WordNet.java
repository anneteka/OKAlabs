package week12.practice;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;


public class WordNet {

    private final HashMap<Integer, String> idToSynset;
    private final HashMap<String, Bag<Integer>> nounToIds;
    private final Digraph G;

    //Приймає назви двох файлів
    public WordNet(String synsets, String hypernyms) {
        In in = new In(synsets);
        String delimiter = ",";

        idToSynset = new HashMap<Integer, String>();
        nounToIds = new HashMap<String, Bag<Integer>>();

        while (in.hasNextLine()) {
            String synsetLine = in.readLine();
            String[] fields = synsetLine.split(delimiter);
            int id = Integer.parseInt(fields[0]);
            String synset = fields[1];
            idToSynset.put(id, synset);
            String[] nouns = synset.split(" ");

            for (String noun : nouns) {
                if (nounToIds.containsKey(noun)) {
                    Bag<Integer> b = nounToIds.get(noun);
                    b.add(id);
                    nounToIds.put(noun, b);
                } else {
                    Bag<Integer> b = new Bag<Integer>();
                    b.add(id);
                    nounToIds.put(noun, b);
                }
            }
        }

        Digraph digraph = new Digraph(idToSynset.size());
        G = digraph;

        in = new In(hypernyms);
        while (in.hasNextLine()) {
            String line = in.readLine();
            String[] fields = line.split(delimiter);
            int id = Integer.parseInt(fields[0]);
            Bag<Integer> bag = new Bag<Integer>();

            for (int i = 1; i < fields.length; i++) {
                int f = Integer.parseInt(fields[i]);
                bag.add(f);
                digraph.addEdge(id, f);
            }

        }

        DirectedCycle cycle = new DirectedCycle(this.G);
        if (cycle.hasCycle()) {
            throw new IllegalArgumentException("Not a valid DAG");
        }

        int rooted = 0;
        for (int i = 0; i < G.V(); i++) {
            if (!this.G.adj(i).iterator().hasNext())
                rooted++;
        }

        if (rooted != 1) {
            throw new IllegalArgumentException("Not a rooted DAG");
        }
    }

    //Множина іменників, що повертається як ітератор (без дублікатів)
    public Iterable<String> nouns() {
        return nounToIds.keySet();
    }

    //Чи є слово серед WordNet іменників?
    public boolean isNoun(String word) {
        return nounToIds.containsKey(word);
    }

    //Відстань між nounA і nounB
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }
        SAP sap = new SAP(G);
        Bag<Integer> bag = nounToIds.get(nounA);
        Bag<Integer> bag1 = nounToIds.get(nounB);
        return sap.length(bag, bag1);
    }

    //Синсет що є спільним предком nounA і nounB
    //в найкоротшому шляху до предка
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new java.lang.IllegalArgumentException();
        }
        Bag<Integer> bag = nounToIds.get(nounA);
        Bag<Integer> bag1 = nounToIds.get(nounB);
        SAP sap = new SAP(G);

        return idToSynset.get(sap.ancestor(bag, bag1));
    }

    //Тестування
    public static void main(String[] args) {
        WordNet w = new WordNet("src/week12/practice/synsets.txt", "src/week12/practice/hypernyms.txt");
        System.out.println(w.sap("Romulus", "Henry_Valentine_Miller"));
        System.out.println(w.distance("Hussein", "Henry_Valentine_Miller"));
    }
}