import edu.princeton.cs.algs4.Bag;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

public class Digraph {

    private final int V;           // к-сть вершин
    private int E;                 // к-сть ребер
    private Bag<Integer>[] adj;
    private int[] indegree;

    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Кількість вершин має бути додатною");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }


    public Digraph(Scanner in) {
        try {
            this.V = in.nextInt();
            if (V < 0) throw new IllegalArgumentException("Кількість вершин має бути додатною");
            indegree = new int[V];
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.nextInt();
            if (E < 0) throw new IllegalArgumentException("Кількість граней має бути додатною");
            for (int i = 0; i < E; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Неправильний ввід");
        }
    }

    public Digraph(Digraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < V; v++)
            this.indegree[v] = G.indegree(v);
        for (int v = 0; v < G.V(); v++) {
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : G.adj[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adj[v].add(w);
            }
        }
    }


    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    private void check(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(int v, int w) {
        check(v);
        check(w);
        adj[v].add(w);
        indegree[w]++;
        E++;
    }


    public Iterable<Integer> adj(int v) {
        check(v);
        return adj[v];
    }

    public int outdegree(int v) {
        check(v);
        return adj[v].size();
    }

    public int indegree(int v) {
        check(v);
        return indegree[v];
    }

    public Digraph reverse() {
        Digraph reverse = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                reverse.addEdge(w, v);
            }
        }
        return reverse;
    }

    public String toString() {
        String res = "";
        res +=V+" вершини, "+ E+" edges\n";
        for (int v = 0; v < V; v++) {
            res+="%d: "+v;
                for (int w : adj[v]) {
                res+="%d: "+w;
            }
            res+="\n";
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner("input/digraph1.txt");
        Digraph G = new Digraph(sc);
        StdOut.println(G);
    }

}
