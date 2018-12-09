import edu.princeton.cs.algs4.In;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("digraph1.txt"));
        int vertexes = sc.nextInt();
        int edges = sc.nextInt();
        Digraph graph = new Digraph(vertexes);
        for (int i = 0; i < edges; i++) {
            graph.addEdge(sc.nextInt(), sc.nextInt());
        }

        SAP sap = new SAP(graph);
        int v = 7;
        int w = 0;
        System.out.println(sap.length(v, w)+ "  " + sap.ancestor(v, w));
        v = 9;
        w = 12;
        System.out.println(sap.length(v, w)+ "  " + sap.ancestor(v, w));
        v = 7;
        w = 2;
        System.out.println(sap.length(v, w)+ "  " + sap.ancestor(v, w));
        v = 1;
        w = 6;
        System.out.println(sap.length(v, w)+ "  " + sap.ancestor(v, w));
    }

}
