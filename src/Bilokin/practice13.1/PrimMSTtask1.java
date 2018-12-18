import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class PrimMSTtask1 {

    private edge[] edgeTo;
    private double[] distTo;      
    private boolean[] marked;    
    private IndexMinPQ<Double> pq;

    
    public PrimMSTtask1(edgeW G) {
        edgeTo = new edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) prim(G, v);
       
    }

    
    private void prim(edgeW G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    
    private void scan(edgeW G, int v) {
        marked[v] = true;
        for (edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;         // v-w is obsolete edge
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    
    public Iterable<edge> edges() {
        Queue<edge> mst = new Queue<edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            edge e = edgeTo[v];
            if (e != null) {
                mst.quene(e);
            }
        }
        return mst;
    }

    
    public double weight() {
        double weight = 0.0;
        for (edge e : edges())
            weight += e.weight();
        return weight;
    }


   
    
    public static void main(String[] args) {
        In in = new In("edge.txt");
        edgeW G = new edgeW(in);
        PrimMSTtask1 mst = new PrimMSTtask1(G);
       StdOut.println(mst.weight());
    }


}