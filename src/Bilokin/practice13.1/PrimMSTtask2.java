import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class PrimMSTtask2 {

    private edge[] toEdge;
    private double[] toDist;
    private boolean[] mark;
    private IndexMinPQ<Double> pq;

    
    public PrimMSTtask2(edgeW G) {
        toEdge = new edge[G.V()];
        toDist = new double[G.V()];
        mark = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            toDist[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.V(); v++)
            if (!mark[v]) prim(G, v);

       
    }

    
    private void prim(edgeW G, int s) {
        toDist[s] = 0.0;
        pq.insert(s, toDist[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            sc(G, v);
        }
    }

    
    private void sc(edgeW G, int v) {
        mark[v] = true;
        for (edge e : G.adj(v)) {
            int w = e.other(v);
            if (mark[w]) continue;
            if (e.weight() < toDist[w]) {
                toDist[w] = e.weight();
                toEdge[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, toDist[w]);
                else                pq.insert(w, toDist[w]);
            }
        }
    }

    
    public Iterable<edge> edges() {
        Queue<edge> mst = new Queue<edge>();
        for (int v = 0; v < toEdge.length; v++) {
            edge e = toEdge[v];
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
        In in = new In("road.txt");
        int n = in.readInt();
        Point[] p = new Point[n];
        for(int i = 0; i < n; i++){
      p[i] = new Point(in.readInt(), in.readInt());
        }
       edgeW G = new edgeW(n, p);
        PrimMSTtask2 mst = new PrimMSTtask2(G);
       StdOut.println(mst.weight());
    }


}