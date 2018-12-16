package helping;

public class Topological {
    private Iterable<Integer> order;  // topological order
    private int[] rank;               // rank[v] = position of vertex v in topological order

    /**
     * Determines whether the digraph <tt>G</tt> has a topological order and, if so,
     * finds such a topological order.
     * @param G the digraph
     */
    public Topological(Digraph G) {
        DirectedCycle finder = new DirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
           // order = dfs.post();
            rank = new int[G.V()];
            int i = 0;
            for (int v : order)
                rank[v] = i++;
        }
    }

    /**
     * Determines whether the edge-weighted digraph <tt>G</tt> has a topological
     * order and, if so, finds such an order.
     * @param G the edge-weighted digraph
     */
    /*
    public Topolog(EdgeWeightedDigraph G) {
        EdgeWeightedDirectedCycle finder = new EdgeWeightedDirectedCycle(G);
        if (!finder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }
*/
    /**
     * Returns a topological order if the digraph has a topologial order,
     * and <tt>null</tt> otherwise.
     * @return a topological order of the vertices (as an interable) if the
     *    digraph has a topological order (or equivalently, if the digraph is a DAG),
     *    and <tt>null</tt> otherwise
     */
    public Iterable<Integer> order() {
        return order;
    }

    /**
     * Does the digraph have a topological order?
     * @return <tt>true</tt> if the digraph has a topological order (or equivalently,
     *    if the digraph is a DAG), and <tt>false</tt> otherwise
     */
    public boolean hasOrder() {
        return order != null;
    }

    /**
     * The the rank of vertex <tt>v</tt> in the topological order;
     * -1 if the digraph is not a DAG
     * @return the position of vertex <tt>v</tt> in a topological order
     *    of the digraph; -1 if the digraph is not a DAG
     * @throws IndexOutOfBoundsException unless <tt>v</tt> is between 0 and
     *    <em>V</em> &minus; 1
     */
    public int rank(int v) {
        validateVertex(v);
        if (hasOrder()) return rank[v];
        else            return -1;
    }

    // throw an IndexOutOfBoundsException unless 0 <= v < V
    private void validateVertex(int v) {
        int V = rank.length;
        if (v < 0 || v >= V)
            throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (V-1));
    }



}
