import princeton.lib.*;

public class SAP {
    private Digraph graph;

    public SAP(Digraph G) {
        if (G == null)
            throw new NullPointerException();
        graph = G;
    }

    public int length(int v, int w) {
        if (v < 0 || v > graph.V() || w < 0 || w > graph.V())
            throw new IndexOutOfBoundsException();
        BreadthFirstPaths bfs1 = new BreadthFirstPaths(graph, v);
        BreadthFirstPaths bfs2 = new BreadthFirstPaths(graph, w);
        return mod(helpBF(bfs1, bfs2))[0];
    }

    public int ancestor(int v, int w) {
        if (v < 0 || v > graph.V() || w < 0 || w > graph.V())
            throw new IndexOutOfBoundsException();
        BreadthFirstPaths bfs1 = new BreadthFirstPaths(graph, v);
        BreadthFirstPaths bfs2 = new BreadthFirstPaths(graph, w);
        return mod(helpBF(bfs1, bfs2))[1];
    }

    private int[] helpBF(BreadthFirstPaths bfs1, BreadthFirstPaths bfs2) {
        int length = Integer.MAX_VALUE, ancestor = 0;
        for (int i = 0, temp = 0; i < graph.V(); i++) {
            if (bfs1.hasPathTo(i) && bfs2.hasPathTo(i)) {
                temp = bfs1.distTo[i] + bfs2.distTo[i];
                if (temp < length) {
                    length = temp;
                    ancestor = i;
                }
            }
        }
        return new int[]{length, ancestor};
    }

    private int[] mod(int[] a) {
        if (a[0] == Integer.MAX_VALUE) {
            a[0] = -1;
            a[1] = -1;
        }
        return a;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)

            throw new NullPointerException();

        BreadthFirstPaths bfs1 = new BreadthFirstPaths(graph, v);
        BreadthFirstPaths bfs2 = new BreadthFirstPaths(graph, w);
        return mod(helpBF(bfs1, bfs2))[0];
    }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new NullPointerException();
        BreadthFirstPaths bfs1 = new BreadthFirstPaths(graph, v);
        BreadthFirstPaths bfs2 = new BreadthFirstPaths(graph, w);
        return mod(helpBF(bfs1, bfs2))[1];
    }

    public static void main(String[] args) {
        In in = new In("src/digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        while (!StdIn.isEmpty()) {

            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);

        }
    }
}

