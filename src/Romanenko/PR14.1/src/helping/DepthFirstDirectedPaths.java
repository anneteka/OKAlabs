package helping;

import java.util.Stack;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class DepthFirstDirectedPaths {
    private boolean[] marked;  // marked[v] = true if v is reachable from s
    private int[] edgeTo;      // edgeTo[v] = last edge on path from s to v
    private final int s;       // source vertex

    /**
     * Computes a directed path from <tt>s</tt> to every other vertex in digraph <tt>G</tt>.
     * @param G the digraph
     * @param s the source vertex
     */
    public DepthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Digraph G, int v) { 
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    /**
     * Is there a directed path from the source vertex <tt>s</tt> to vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a directed path from the source
     *   vertex <tt>s</tt> to vertex <tt>v</tt>, <tt>false</tt> otherwise
     */
    public boolean hasPathTo(int v) {
        return marked[v];
    }

    
    /**
     * Returns a directed path from the source vertex <tt>s</tt> to vertex <tt>v</tt>, or
     * <tt>null</tt> if no such path.
     * @param v the vertex
     * @return the sequence of vertices on a directed path from the source vertex
     *   <tt>s</tt> to vertex <tt>v</tt>, as an Iterable
     */
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    /**
     * Unit tests the <tt>DepthFirstDirectedPaths</tt> data type.
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        // StdOut.println(G);

        int s = Integer.parseInt(args[1]);
        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print("-" + x);
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }

}