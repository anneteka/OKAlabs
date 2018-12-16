import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;

public class Degrees {

    private int size;
    private Digraph digraph;
    private Digraph reversedGraph;

    public Degrees(Digraph G) {
        size = G.V();
        digraph = G;
        reversedGraph = G.reverse();
    }

    public int indegree(int v) {
        return reversedGraph.indegree(v);
    }

    public int outdegree(int v) {
        return digraph.outdegree(v);
    }

    public Iterable<Integer> sources() {
        Bag<Integer> sources = new Bag<Integer>();
        for (int c = 0; c < size; c++)
            if (indegree(c) == 0) sources.add(c);
        return sources;
    }

    public Iterable<Integer> sinks() {
        Bag<Integer> sinks = new Bag<Integer>();
        for (int c = 0; c < size; c++)
            if (outdegree(c) == 0) sinks.add(c);
        return sinks;
    }

    boolean isMap() {
        for (int i = 0; i < digraph.V(); i++){
            for (int v : digraph.adj(i)){
                if (outdegree(v) != 1){
                    return false;
                }
            }
        }


        return true;
    }

}