package Karlysheva.practice12;

import ua.princeton.lib.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordNet {

    private Map<Integer, String> idToSynset;

    private Map<String, Set<Integer>> synsetToId;

    private SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        idToSynset = new HashMap<Integer, String>();
        synsetToId = new HashMap<String, Set<Integer>>();
        initSynsets(synsets);
        Digraph graph = initHypernyms(hypernyms);

        DirectedCycle cycle = new DirectedCycle(graph);
        if (cycle.hasCycle() || !isDAG(graph)) {
            throw new IllegalArgumentException("The input does not correspond to a rooted DAG!");
        }

        sap = new SAP(graph);
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return synsetToId.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        return synsetToId.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException("No such nouns in WordNet!");
        return sap.length(synsetToId.get(nounA), synsetToId.get(nounB));
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException("No such nouns in WordNet!");
        int ancestorId = sap.ancestor(synsetToId.get(nounA), synsetToId.get(nounB));
        return idToSynset.get(ancestorId).split(",")[0];
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet w = new WordNet("synsets.txt", "hypernyms.txt");
        System.out.println(w.sap("Rameses_the_Great", "Henry_Valentine_Miller"));
        System.out.println(w.distance("Rameses_the_Great", "Henry_Valentine_Miller"));
    }

    private boolean isDAG(Digraph digraph) {
        DirectedCycle temp = new DirectedCycle(digraph);
        if (temp.hasCycle()) {
            return false;
        }
        int roots = 0;
        for (int i = 0; i < digraph.V(); i++) {
            if (!digraph.adj(i).iterator().hasNext()) roots++;
        }
        return roots==1;
    }

    private void initSynsets(String synset) {
        In file = new In(synset);
        while (file.hasNextLine()) {
            String[] line = file.readLine().split(",");
            Integer id = Integer.valueOf(line[0]);
            String n = line[1];
            idToSynset.put(id, n);

            String[] nouns = n.split(" ");
            // String definition = line[2];
            for (String noun : nouns) {
                Set<Integer> ids = synsetToId.get(noun);
                if (null == ids) {
                    ids = new HashSet<>();
                }
                ids.add(id);
                synsetToId.put(noun, ids);
            }
        }
    }

    private Digraph initHypernyms(String hypernyms) {
        Digraph graph = new Digraph(idToSynset.size());

        In file = new In(hypernyms);
        while (file.hasNextLine()) {
            String[] line = file.readLine().split(",");
            Integer synsetId = Integer.valueOf(line[0]);
            for (int i = 1; i < line.length; i++) {
                Integer id = Integer.valueOf(line[i]);
                graph.addEdge(synsetId, id);
            }
        }

        return graph;
    }

}
