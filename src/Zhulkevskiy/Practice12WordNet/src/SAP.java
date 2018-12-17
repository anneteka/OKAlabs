
import edu.princeton.cs.algs4.Digraph;


public class SAP {
    private Digraph digraph;


    public SAP(Digraph G) {
        if (G == null) throw new NullPointerException("Digraph is null");
        this.digraph = G;
    }


    public int length(int v, int w) {
        if (v < 0 || w < 0 || v > digraph.V() || w > digraph.V()) throw new IndexOutOfBoundsException("Wrong vertex");
        BFS vbfs = new BFS(digraph, v);
        BFS wbfs = new BFS(digraph, w);

        return common(vbfs, wbfs, true);
    }


    public int ancestor(int v, int w) {
        if (v < 0 || w < 0 || v > digraph.V() || w > digraph.V()) throw new IndexOutOfBoundsException("Wrong vertex");
        BFS vbfs = new BFS(digraph, v);
        BFS wbfs = new BFS(digraph, w);

        return common(vbfs, wbfs, false);
    }


    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BFS vbfs = new BFS(digraph, v);
        BFS wbfs = new BFS(digraph, w);

        return common(vbfs, wbfs, true);
    }


    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BFS vbfs = new BFS(digraph, v);
        BFS wbfs = new BFS(digraph, w);

        return common(vbfs, wbfs, false);
    }

    private int common(BFS vbfs, BFS wbfs, boolean len) {
        int minLength = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int i = 0; i < digraph.V(); i++) {
            if (vbfs.hasPathTo(i) && wbfs.hasPathTo(i)) {
                int distance = vbfs.getDistTo(i) + wbfs.getDistTo(i);
                if (distance < minLength) {
                    minLength = distance;
                    ancestor = i;
                }
            }
        }

        if (len) return (minLength < Integer.MAX_VALUE) ? minLength : -1;
        return ancestor;
    }



}
