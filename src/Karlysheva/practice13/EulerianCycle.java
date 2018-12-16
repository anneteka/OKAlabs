package Karlysheva.practice13;

import ua.princeton.lib.*;

public class EulerianCycle {
    private Stack<Integer> cycle = new Stack<Integer>();  // Eulerian cycle; null if no such cycle

    // an undirected edge, with a field to indicate whether the edge has already been used
    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }

        // returns the other vertex of the edge
        public int other(int vertex) {
            if      (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    public EulerianCycle(Graph G) {

        // must have at least one edge
        if (G.E() == 0) return;

        // necessary condition: all vertices have even degree
        // (this test is needed or it might find an Eulerian path instead of cycle)
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) % 2 != 0)
                return;

        // create local view of adjacency lists, to iterate one vertex at a time
        // the helper Edge data type is used to avoid exploring both copies of an edge v-w
        Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = new Queue<Edge>();

        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (int w : G.adj(v)) {
                // careful with self loops
                if (v == w) {
                    if (selfLoops % 2 == 0) {
                        Edge e = new Edge(v, w);
                        adj[v].enqueue(e);
                        adj[w].enqueue(e);
                    }
                    selfLoops++;
                }
                else if (v < w) {
                    Edge e = new Edge(v, w);
                    adj[v].enqueue(e);
                    adj[w].enqueue(e);
                }
            }
        }

        // initialize stack with any non-isolated vertex
        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        // greedily search through edges in iterative DFS style
        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].dequeue();
                if (edge.isUsed) continue;
                edge.isUsed = true;
                stack.push(v);
                v = edge.other(v);
            }
            // push vertex with no more leaving edges to cycle
            cycle.push(v);
        }

        // check if all edges are used
        if (cycle.size() != G.E() + 1)
            cycle = null;

        assert certifySolution(G);
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    // returns any non-isolated vertex; -1 if no such vertex
    private static int nonIsolatedVertex(Graph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > 0)
                return v;
        return -1;
    }


    private static boolean satisfiesNecessaryAndSufficientConditions(Graph G) {

        // Condition 0: at least 1 edge
        if (G.E() == 0) return false;

        // Condition 1: degree(v) is even for every vertex
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) % 2 != 0)
                return false;

        // Condition 2: graph is connected, ignoring isolated vertices
        int s = nonIsolatedVertex(G);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > 0 && !bfs.hasPathTo(v))
                return false;

        return true;
    }

    // check that solution is correct
    private boolean certifySolution(Graph G) {

        // internal consistency check
        if (hasEulerianCycle() == (cycle() == null)) return false;

        // hashEulerianCycle() returns correct value
        if (hasEulerianCycle() != satisfiesNecessaryAndSufficientConditions(G)) return false;

        // nothing else to check if no Eulerian cycle
        if (cycle == null) return true;

        // check that cycle() uses correct number of edges
        if (cycle.size() != G.E() + 1) return false;

        // check that cycle() is a cycle of G
        // TODO

        // check that first and last vertices in cycle() are the same
        int first = -1, last = -1;
        for (int v : cycle()) {
            if (first == -1) first = v;
            last = v;
        }
        if (first != last) return false;

        return true;
    }

}

