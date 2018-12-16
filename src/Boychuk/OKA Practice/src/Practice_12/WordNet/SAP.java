package WordNet;

import graph.BFDPaths;
import graph.Digraph;
import lib.In;
import lib.StdOut;

public class SAP {
    private Digraph graph;
    // do unit testing of this class
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        int length   = sap.length(7093, 18765);
        int ancestor = sap.ancestor(7093, 18765);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);

    }
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new NullPointerException();
        graph = new Digraph(G);
    }


    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        if (v < 0 || v > graph.V() || w < 0 || w > graph.V())
            throw new IndexOutOfBoundsException();
        BFDPaths bfs1 = new BFDPaths(graph, v);
        BFDPaths bfs2 = new BFDPaths(graph, w);
        return modify(helper(bfs1, bfs2))[1];
    } 
    
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v < 0 || v > graph.V() || w < 0 || w > graph.V())
            throw new IndexOutOfBoundsException();
        BFDPaths bfs1 = new BFDPaths(graph, v);
        BFDPaths bfs2 = new BFDPaths(graph, w);
        return modify(helper(bfs1, bfs2))[0];
    }
    private int[] helper(BFDPaths bfs1, BFDPaths bfs2) {
//        BFDPaths bfs1 = new BFDPaths(graph, v);
//        BFDPaths bfs2 = new BFDPaths(graph, w);
        int length = Integer.MAX_VALUE, ancestor = 0;
        for (int i = 0, temp = 0; i < graph.V(); i++) {
            if (bfs1.hasPathTo(i) && bfs2.hasPathTo(i)) {
                temp = bfs1.distTo(i) + bfs2.distTo(i);
                if (temp < length) {
                    length = temp;
                    ancestor = i;
                }   
            }
        }
        return new int[] {length, ancestor};
    }

    private int[] modify(int[] a) {
        if (a[0] == Integer.MAX_VALUE) {
            a[0] = -1;
            a[1] = -1;
        }
        return a;
    }
        
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new NullPointerException();
        BFDPaths bfs1 = new BFDPaths(graph, v);
        BFDPaths bfs2 = new BFDPaths(graph, w);
        return modify(helper(bfs1, bfs2))[0];
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new NullPointerException();
        BFDPaths bfs1 = new BFDPaths(graph, v);
        BFDPaths bfs2 = new BFDPaths(graph, w);
        return modify(helper(bfs1, bfs2))[1];
    }
        
    

}
