import edu.princeton.cs.algs4.Bag;
public class Second {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Second(int V) {
        if (V < 0) throw new IllegalArgumentException("Illegal arg");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V){
            throw new IllegalArgumentException("Дана вершина " + v + " не в межах 0 та " + (V-1));
        }

    }
    public String toString() {
        String res;
        res = V + " вершин, " + E + " ребер\n";

        for (int v = 0; v < V; v++) {
            res+=v + ": ";
            for (int w : adj[v]) {
                res+=w + " ";
            }
           res+="\n";
        }
        return res;
    }

}