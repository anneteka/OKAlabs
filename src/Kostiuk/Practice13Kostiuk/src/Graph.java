package Kostiuk.Practice13Kostiuk.src;

import java.util.NoSuchElementException;
import java.util.Stack;


public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");
    private final int V;
    private int E;
    private Bag<Integer>[] adj;
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("ʳ������ ������ ������� ���� ���������");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }
    public int V() {
        return V;
    }
    public int E() {
        return E;
    }
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("������� " + v + " �� �� 0 � " + (V-1));
    }
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
    public String toString() {
        StringBuilder strbuilder = new StringBuilder();
        strbuilder.append(V + " �������, " + E + " ����� " + NEWLINE);
        for (int v = 0; v < V; v++) {
            strbuilder.append(v + ": ");
            for (int w : adj[v]) {
                strbuilder.append(w + " ");
            }
            strbuilder.append(NEWLINE);
        }
        return strbuilder.toString();
    }




}