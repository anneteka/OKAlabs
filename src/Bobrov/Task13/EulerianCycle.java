package Task13;

import java.util.Iterator;
import java.util.Stack;



public class EulerianCycle {
    private Stack<Integer> cycle = null;


    private static class Edge {
        private final int v;
        private final int w;
        private boolean isUsed;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
            isUsed = false;
        }


        public int other(int vertex) {
            if (vertex == v)
                return w;
            else if (vertex == w)
                return v;
            else
                throw new IllegalArgumentException("Illegal endpoint");
        }
    }


    public EulerianCycle(Graph G) {
        if (G.E() == 0)
            return;

        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) % 2 != 0)
                return;
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
                } else if (v < w) {
                    Edge e = new Edge(v, w);
                    adj[v].enqueue(e);
                    adj[w].enqueue(e);
                }
            }
        }
        int s = nonIsolatedVertex(G);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);
        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].dequeue();
                if (edge.isUsed)
                    continue;
                edge.isUsed = true;
                stack.push(v);
                v = edge.other(v);
            }
            cycle.push(v);
        }
        if (cycle.size() != G.E() + 1)
            cycle = null;
    }

    public EulerianCycle(Digraph G) {
        if (G.E() == 0)
            return;
        KosarajuSharirSCC ksscc = new KosarajuSharirSCC(G);
        for (int v = 0; v < G.V(); v++)
            for (int w = 0; w < G.V(); w++)
                if (!ksscc.stronglyConnected(v, w))
                    return;

        Degrees degrees = new Degrees(G);
        for (int v = 0; v < G.V(); v++)
            if (degrees.indegree(v) != degrees.outdegree(v))
                return;


        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();


        int s = nonIsolatedVertex(G, degrees);
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(s);

        cycle = new Stack<Integer>();
        while (!stack.isEmpty()) {
            int v = stack.pop();
            while (adj[v].hasNext()) {
                stack.push(v);
                v = adj[v].next();
            }
            cycle.push(v);
        }

        if (cycle.size() != G.E() + 1)
            cycle = null;

    }


    public Iterable<Integer> cycle() {
        return cycle;
    }


    public boolean hasEulerianCycle() {
        return cycle != null;
    }
    private static int nonIsolatedVertex(Graph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > 0)
                return v;
        return -1;
    }

    private static int nonIsolatedVertex(Digraph G, Degrees degrees) {
        for (int v = 0; v < G.V(); v++)
            if (degrees.outdegree(v) > 0)
                return v;
        return -1;
    }

}