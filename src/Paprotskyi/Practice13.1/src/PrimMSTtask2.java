import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class PrimMSTtask2 {

    private Edge[] edgeTo;        
    private double[] distTo;      
    private boolean[] marked;    
    private IndexMinPQ<Double> pq;

    
    public PrimMSTtask2(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.V(); v++)      // run from each vertex to find
            if (!marked[v]) prim(G, v);      // minimum spanning forest

       
    }

    
    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    
    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
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

    
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }


   
    
    public static void main(String[] args) {
        In in = new In("road.txt");
        int n = in.readInt();
        Point[] p = new Point[n];
        for(int i = 0; i < n; i++){
      p[i] = new Point(in.readInt(), in.readInt());
        }
       EdgeWeightedGraph G = new EdgeWeightedGraph(n, p);
        PrimMSTtask2 mst = new PrimMSTtask2(G);
       StdOut.println(mst.weight());
    }


}