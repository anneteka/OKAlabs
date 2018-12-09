import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;

public class WordNet {
    private Digraph digraph;
    private ST<String, Bag<Integer>> synsetsNoun;
    private ST<Integer, String> synsetsID;
    private SAP sap;

    public WordNet(String synsets, String hypernyms) {
        //java.lang.IllegalArgumentException якщо невірний граф на вхід
        In synsetsIN = new In(synsets);
        In hypernymsIn = new In(hypernyms);

        this.synsetsID = new ST<>();
        this.synsetsNoun = new ST<>();

        while(synsetsIN.hasNextLine()){
            String[] splitSynsets = synsetsIN.readLine().split(",");
            for(String oneWord: splitSynsets[1].split(" ")){
                Bag<Integer> bagID = new Bag<>();

                if(synsetsNoun.contains(oneWord)) bagID = synsetsNoun.get(oneWord);
                bagID.add(Integer.parseInt(splitSynsets[0]));
                synsetsNoun.put(oneWord, bagID);
            }
            synsetsID.put(Integer.parseInt(splitSynsets[0]), splitSynsets[1]);
        }

        digraph = new Digraph(synsetsID.size());
        while(hypernymsIn.hasNextLine()){
            String[] splitHypernyms = hypernymsIn.readLine().split(",");
            int fromWhat = Integer.parseInt(splitHypernyms[0]);
            for (int i = 1; i < splitHypernyms.length; ++i)
            {
                digraph.addEdge(fromWhat, Integer.parseInt(splitHypernyms[i]));
            }
        }

        sap = new SAP(digraph);

    }

  
    public Iterable<String> nouns() {
        return synsetsNoun;
    }


    public boolean isNoun(String word) {

        return synsetsNoun.contains(word);
    }

  
    public int distance(String nounA, String nounB) {
        //java.lang.IllegalArgumentException якщо хоча один із наун не містить в ворднет
        if(!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException();
        Bag<Integer> idA = synsetsNoun.get(nounA);
        Bag<Integer> idB = synsetsNoun.get(nounB);

        return sap.length(idA, idB);
    }

  
    public String sap(String nounA, String nounB) {
        //java.lang.IllegalArgumentException якщо хоча один із наун не містить в ворднет
        if(!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException();
        Bag<Integer> idA = synsetsNoun.get(nounA);
        Bag<Integer> idB = synsetsNoun.get(nounB);
        if (sap.ancestor(idA,idB) == -1){
            return "not found";
        }else{
            return synsetsID.get(sap.ancestor(idA,idB));
        }
    }

    public static void main(String[] args) {
        WordNet wn =  new WordNet("synsets.txt", "hypernyms.txt");
        System.out.println(wn.distance("a","b"));
        System.out.println(wn.sap("a", "b"));
    }

}
