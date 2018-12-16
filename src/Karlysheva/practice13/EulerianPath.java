package Karlysheva.practice13;

import ua.princeton.lib.BreadthFirstPaths;
import ua.princeton.lib.Graph;
import ua.princeton.lib.Queue;
import ua.princeton.lib.Stack;

public class EulerianPath {
    private Stack<Integer> path = null;   // Eulerian path; null if no suh path

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

    public EulerianPath(Graph G) {

        // find vertex from which to start potential Eulerian path:
        // a vertex v with odd degree(v) if it exits;
        // otherwise a vertex with degree(v) > 0
        int oddDegreeVertices = 0;
        int s = nonIsolatedVertex(G);
        for (int v = 0; v < G.V(); v++) {
            if (G.degree(v) % 2 != 0) {
                oddDegreeVertices++;
                s = v;
            }
        }

        // graph can't have an Eulerian path
        // (this condition is needed for correctness)
        if (oddDegreeVertices > 2) return;

        // special case for graph with zero edges (has a degenerate Eulerian path)
        if (s == -1) s = 0;

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
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        // greedily search through edges in iterative DFS style
        path = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].dequeue();
                if (edge.isUsed) continue;
                edge.isUsed = true;
                stack.push(v);
                v = edge.other(v);
            }
            // push vertex with no more leaving edges to path
            path.push(v);
        }

        // check if all edges are used
        if (path.size() != G.E() + 1)
            path = null;

        assert certifySolution(G);
    }


    public Iterable<Integer> path() {
        return path;
    }

    public boolean hasEulerianPath() {
        return path != null;
    }


    // returns any non-isolated vertex; -1 if no such vertex
    private static int nonIsolatedVertex(Graph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > 0)
                return v;
        return -1;
    }


    // Determines whether a graph has an Eulerian path using necessary
    // and sufficient conditions (without computing the path itself):
    //    - degree(v) is even for every vertex, except for possibly two
    //    - the graph is connected (ignoring isolated vertices)
    // This method is solely for unit testing.
    private static boolean satisfiesNecessaryAndSufficientConditions(Graph G) {
        if (G.E() == 0) return true;

        // Condition 1: degree(v) is even except for possibly two
        int oddDegreeVertices = 0;
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) % 2 != 0)
                oddDegreeVertices++;
        if (oddDegreeVertices > 2) return false;

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
        if (hasEulerianPath() == (path() == null)) return false;

        // hashEulerianPath() returns correct value
        if (hasEulerianPath() != satisfiesNecessaryAndSufficientConditions(G)) return false;

        // nothing else to check if no Eulerian path
        if (path == null) return true;

        // check that path() uses correct number of edges
        if (path.size() != G.E() + 1) return false;

        // check that path() is a path in G
        // TODO

        return true;
    }

}

