package week13.practice;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdOut;

public class Degrees {

    private int sz; // кількість вершин у графа
    private Digraph d; // орграф
    private Digraph rev; // перевернутий орграф
    private boolean isMap;


    public Degrees(Digraph G) {
        sz = G.V();
        d = G;
        rev = G.reverse();
    }

    //повертає кількість ребер, які входять у дану вершину
    public int indegree(int v) {
        return rev.indegree(v);
    }

    //повертає кількість ребер які виходять з вершини
    public int outdegree(int v) { return d.outdegree(v); }

    //ітератор для ребер що входять у вершину
    public Iterable<Integer> sources() {
        Bag<Integer> sources = new Bag<Integer>();
        for (int i = 0; i < sz; i++)
            if (indegree(i) == 0) sources.add(i);
        return sources;
    }

    //ітератор для ребер які виходять з вершини
    public Iterable<Integer> sinks() {
        Bag<Integer> sinks = new Bag<Integer>();
        for (int i = 0; i < sz; i++)
            if (outdegree(i) == 0) sinks.add(i);
        return sinks;
    }

    // чи є G відображенням
    private boolean isMap() {
        return isMap;
    }

    public static void main(String[] args) {
        Digraph digraph1 = new Digraph(6);
        digraph1.addEdge(0, 1);
        digraph1.addEdge(2, 1);
        digraph1.addEdge(2, 3);
        digraph1.addEdge(3, 3);
        digraph1.addEdge(4, 3);

        Degrees degrees1 = new Degrees(digraph1);
        StdOut.println("Indegree of vertex 0: " + degrees1.indegree(0) + " Expected: 0");
        StdOut.println("Indegree of vertex 1: " + degrees1.indegree(1) + " Expected: 2");
        StdOut.println("Indegree of vertex 2: " + degrees1.indegree(2) + " Expected: 0");
        StdOut.println("Indegree of vertex 3: " + degrees1.indegree(3) + " Expected: 3");
        StdOut.println("Indegree of vertex 4: " + degrees1.indegree(4) + " Expected: 0");
        StdOut.println("Indegree of vertex 5: " + degrees1.indegree(5) + " Expected: 0");

        StdOut.println();

        StdOut.println("Outdegree of vertex 0: " + degrees1.outdegree(0) + " Expected: 1");
        StdOut.println("Outdegree of vertex 1: " + degrees1.outdegree(1) + " Expected: 0");
        StdOut.println("Outdegree of vertex 2: " + degrees1.outdegree(2) + " Expected: 2");
        StdOut.println("Outdegree of vertex 3: " + degrees1.outdegree(3) + " Expected: 1");
        StdOut.println("Outdegree of vertex 4: " + degrees1.outdegree(4) + " Expected: 1");
        StdOut.println("Outdegree of vertex 5: " + degrees1.outdegree(5) + " Expected: 0");

        StdOut.println();

        StdOut.print("Sources: ");
        for(int source : degrees1.sources()) {
            StdOut.print(source + " ");
        }

        StdOut.print("\n\nSinks: ");
        for(int sink : degrees1.sinks()) {
            StdOut.print(sink + " ");
        }

        StdOut.println("\nIs map: " + degrees1.isMap());
    }

}