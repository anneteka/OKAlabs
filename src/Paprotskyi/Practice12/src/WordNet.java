import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class WordNet {
    private final SAP sap;
    private final Map<Integer, String> idToSynset;
    private final Map<String, Set<Integer>> nounToIds;

    public WordNet(String synsets, String hypernyms) {
        idToSynset = new HashMap<>();
        nounToIds = new HashMap<>();
        initSynsets(synsets);
        Digraph graph = initHypernyms(hypernyms);

        DirectedCycle cycle = new DirectedCycle(graph);
        if (cycle.hasCycle() || !rootedDAG(graph)) {
            throw new IllegalArgumentException("The input does not correspond to a rooted DAG!");
        }

        sap = new SAP(graph);
    }

    private boolean rootedDAG(Digraph g) {
        int roots = 0;
        for (int i = 0; i < g.V(); i++) {
            if (!g.adj(i).iterator().hasNext()) {
                roots++;
                if (roots > 1) {
                    return false;
                }
            }
        }

        return roots == 1;
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
                Set<Integer> ids = nounToIds.get(noun);
                if (null == ids) {
                    ids = new HashSet<>();
                }
                ids.add(id);
                nounToIds.put(noun, ids);
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

    public Iterable<String> nouns() {
        return nounToIds.keySet();
    }

    public boolean isNoun(String word) {
        if (null == word || "".equals(word)) {
            return false;
        }
        return nounToIds.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Both words must be nouns!");
        }
        Set<Integer> idsOfNounA = nounToIds.get(nounA);
        Set<Integer> idsOfNounB = nounToIds.get(nounB);
        return sap.length(idsOfNounA, idsOfNounB);
    }

    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException("Both words must be nouns!");
        }
        Set<Integer> idsOfNounA = nounToIds.get(nounA);
        Set<Integer> idsOfNounB = nounToIds.get(nounB);
        int ancestor = sap.ancestor(idsOfNounA, idsOfNounB);
        return idToSynset.get(ancestor);
    }


    public static void main(String[] args) {
        WordNet wordNet = new WordNet(args[0], args[1]);
        while (!StdIn.isEmpty()) {
            String v = StdIn.readString();
            String w = StdIn.readString();
            if (!wordNet.isNoun(v)) {
                StdOut.println(v + " not in the word net");
                continue;
            }
            if (!wordNet.isNoun(w)) {
                StdOut.println(w + " not in the word net");
                continue;
            }
            int distance = wordNet.distance(v, w);
            String ancestor = wordNet.sap(v, w);
            StdOut.printf("distance = %d, ancestor = %s\n", distance, ancestor);
        }
    }
}