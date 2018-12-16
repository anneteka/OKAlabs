import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import java.util.Stack;

public class EC {
    private Stack<Integer> cycle = new Stack<Integer>();

    public boolean containsEulerianCycle() {
        if(cycle!=null){
            return true;
        }
        return false;
    }


    private static int nonIsolatedVertex(Graph G) {
        for (int v = 0; v < G.V(); v++)
            if (G.degree(v) > 0)
                return v;
        return -1;
    }


    public EC(Graph G) {
        if (G.E() == 0){
            return;
        }

        for (int v = 0; v < G.V(); v++){
            if (G.degree(v) % 2 == 1){
                return;
            }
        }

        Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[G.V()];
        for (int v = 0; v < G.V(); v++){
            adj[v] = new Queue<Edge>();
        }

        for (int v = 0; v < G.V(); v++) {
            int sloop = 0;
            for (int w : G.adj(v)) {
                if (v == w) {

                    if (sloop % 2 == 0) {
                        Edge e = new Edge(v, w);
                        adj[v].enqueue(e);
                        adj[w].enqueue(e);
                    }
                    sloop++;

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
                if (edge.moreThanOnce) continue;
                edge.moreThanOnce = true;
                stack.push(v);
                v = edge.other(v);
            }
            cycle.push(v);
        }

        if (cycle.size() != G.E() + 1)
            cycle = null;
    }


    private static class Edge {

        private int vertex;
        private int vertexW;
        private boolean moreThanOnce;


        public Edge(int v, int w) {
            this.vertex = v;
            this.vertexW = w;
            moreThanOnce = false;
        }

        public int other(int vertex) {
            if (vertex == this.vertex){
                return vertexW;
            }
            else if (vertex == vertexW){
                return this.vertex;
            }
            else{
                throw new IllegalArgumentException();
            }
        }
    }



}