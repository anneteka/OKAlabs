import edu.princeton.cs.algs4.Bag;

public class Graph {

    private final int V;
    private Bag<Integer>[] adj;


    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();
    }


    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }


    public int degree(int v) {
        int degree = 0;
        for (int w : adj(v))
            degree++;
        return degree;
    }


    public int V() {
        return V;
    }


    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < adj.length; i++) {
            for (int v : adj(i)) {
                result += i + " - " + v + "  ";
            }
            result += "\n";
        }
        return result;
    }
}
