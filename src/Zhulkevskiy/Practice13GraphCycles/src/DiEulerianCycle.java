import edu.princeton.cs.algs4.Digraph;

import java.util.Iterator;
import java.util.Stack;

public class DiEulerianCycle {
    private Stack<Integer> cycle = new Stack<Integer>();

    public DiEulerianCycle(Digraph G) {

        // must have at least one edge
        if (G.E() == 0) return;

        // necessary condition: indegree(v) = outdegree(v) for each vertex v
        // (without this check, DFS might return a path instead of a cycle)
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) != G.indegree(v))
                return;

        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        // initialize stack with any non-isolated vertex
        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next();
            }
            // add vertex with no more leaving edges to cycle
            cycle.push(v);
        }

        // check if all edges have been used
        // (in case there are two or more vertex-disjoint Eulerian cycles)
        if (cycle.size() != G.E() + 1)
            cycle = null;
    }

    private static int nonIsolatedVertex(Digraph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.outdegree(v) > 0)
                return v;
        return -1;
    }

    public boolean hasEulerianCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }


}
