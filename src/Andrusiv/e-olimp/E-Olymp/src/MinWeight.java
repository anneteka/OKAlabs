import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class MinWeight {
    static class MinPQ<Key extends Comparable<Key>> {
        private Key[] pq;
        private int n;

        public MinPQ() {
            this(1);
        }

        public MinPQ(int capacity) {
            pq = (Key[]) new Comparable[capacity + 1];
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public void insert(Key key) {
            if (key != null) {
                if ((n + 1) == pq.length) resize(2 * pq.length);
                pq[++n] = key;
                swim(n);
            }
        }

        public Key delMin() { //look
            Key max = pq[1];
            exch(1, n--);
            sink(1);
            pq[n + 1] = null;
            if (n > 0 && n <= pq.length / 4) resize(pq.length / 2);
            return max;
        }

        private void swim(int k) {
            while (k > 1 && more(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && more(j, j + 1)) j++;
                if (!more(k, j)) break;
                exch(k, j);
                k = j;
            }
        }

        private boolean more(int i, int j) {
            if (pq[j] == null || pq[i] == null) return false;
            return pq[j].compareTo(pq[i]) < 0;
        }

        private void exch(int i, int j) {
            Key t = pq[i];
            pq[i] = pq[j];
            pq[j] = t;
        }

        private void resize(int capacity) {
            Key[] copy = (Key[]) new Comparable[capacity];
            for (int i = 0; i < Math.min(pq.length, capacity); i++)
                copy[i] = pq[i];
            pq = copy;
        }

        @Override
        public String toString() {
            return Arrays.toString(pq);
        }

    }

    static class Edge implements Comparable<Edge> {

        private final int v, w;
        private final int weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int either() {
            return v;
        }

        public int other(int vertex) {
            if (vertex == v) return w;
            else return v;
        }

        public int compareTo(Edge that) {
            if (this.weight < that.weight) return -1;
            else if (this.weight > that.weight) return 1;
            else return 0;
        }
    }

    static class EdgeWeightedGraph {

        private final int V;
        private final Stack<Edge>[] adj;

        public EdgeWeightedGraph(int V) {
            this.V = V;
            adj = (Stack<Edge>[]) new Stack[V];
            for (int v = 0; v < V; v++)
                adj[v] = new Stack<Edge>();
        }

        public void addEdge(Edge e) {
            int v = e.either(), w = e.other(v);
            adj[v].add(e);
            adj[w].add(e);
        }

        public int V() {
            return V;
        }

        public Iterable<Edge> adj(int v) {
            return adj[v];
        }

    }

    private boolean[] marked; // MST vertices
    private Stack<Edge> mst; // MST edges
    private MinPQ<Edge> pq; // PQ of edges
    private int sum = 0;

    MinWeight(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        mst = new Stack<>();
        marked = new boolean[G.V()];
        visit(G, 1);

        while (!pq.isEmpty() && (mst.size() < (G.V() - 1))) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.push(e);
            sum += e.weight;
            if (!marked[v])
                visit(G, v);
            if (!marked[w])
                visit(G, w);
        }
    }


    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)])
                pq.insert(e);
    }

    public Iterable<Edge> mst() {
        return mst;
    }


    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in);
        EdgeWeightedGraph ewg = new EdgeWeightedGraph(sc.nextInt() + 1);
        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            ewg.addEdge(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }

        MinWeight mw = new MinWeight(ewg);
        System.out.println(mw.sum);
    }
}