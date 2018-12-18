import princeton.lib.Graph;
import java.util.ArrayList;

public class EulerianCycle {
    private ArrayList<Integer> cycle = new ArrayList<Integer>();

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
        ArrayQueue<Edge>[] adj = (ArrayQueue<Edge>[]) new ArrayQueue[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = new ArrayQueue<>();

        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (int w : G.adj(v)) {
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
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(s);

        cycle = new ArrayList<Integer>();
        while (!list.isEmpty()) {
            int v = list.remove(list.size() - 1);
            while (!adj[v].isEmpty()) {
                Edge edge = adj[v].dequeue();
                if (edge.isUsed)
                    continue;
                edge.isUsed = true;
                list.add(v);
                v = edge.other(v);
            }
            cycle.add(v);
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


}