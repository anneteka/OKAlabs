public class WordNet {

    Digraph relations;
    TrieST<Bag<Integer>> nouns;
    String[] synsets;
    Sap Sap;

    public WordNet(String synset, String hypernyms) {

        In syn = new In(synset);
        nouns = new TrieST<Bag<Integer>>();
        String[] synsets = syn.readAllLines();
        int i, j, n;
        n = synsets.length;
        for (i = 0; i < n; i++) {
            String temp = synsets[i];
            String[] syns = temp.split("\\s*,\\s*");

            int ID = Integer.parseInt(syns[0]);
            String noun[] = syns[1].split("\\s+");
            Bag<Integer> tB;
            for (j = 0; j < noun.length; j++) {
                if (nouns.get(noun[j]) == null) {
                    tB = new Bag<Integer>();
                } else {
                    tB = nouns.get(noun[j]);
                }
                tB.add(ID);
                nouns.put(noun[j], tB);
            }
        }

        In hyp = new In(hypernyms);
        String[] hyper = hyp.readAllLines();
        int nHyp = hyper.length;
        relations = new Digraph(nHyp);
        for (i = 0; i < nHyp; i++) {
            String t = hyper[i];
            String[] hyps = t.split(",");
            int u = Integer.parseInt(hyps[0]);
            for (j = 1; j < hyps.length; j++) {
                int v = Integer.parseInt(hyps[j]);
                relations.addEdge(u, v);
            }
        }
        Sap = new Sap(relations);
    }

    public Iterable<String> nouns() {
        return nouns.keys();
    }

    public boolean isNoun(String word) {
        return (nouns.get(word) != null);
    }

    public int distance(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            return -1;
        else {
            Bag<Integer> A = nouns.get(nounA);
            Bag<Integer> B = nouns.get(nounB);
            int ans = Sap.length(A, B);
            return ans;
        }
    }

    public String sap(String nounA, String nounB) {
        if (!isNoun(nounA) || !isNoun(nounB))
            return null;

        else {
            Bag<Integer> A = nouns.get(nounA);
            Bag<Integer> B = nouns.get(nounB);
            int ans = Sap.ancestor(A, B);
            if (ans != -1) {
                String[] arr= synsets[ans].split(",");
                return arr[1];
            } else
                return null;
        }

    }
}