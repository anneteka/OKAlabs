import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UnificationDaySolver solver = new UnificationDaySolver(scanner);
        System.out.println(solver.sum);
    }
}
class UnificationDaySolver {
    double sum = 0;
    public UnificationDaySolver(Scanner scanner) {
        Town[] towns = new Town[scanner.nextInt()];
        for(int i=0;i<towns.length;i++) {
            towns[i] = new Town(scanner.nextInt(), scanner.nextInt());
        }
        EdgeWeightedGraph graph = new EdgeWeightedGraph(towns.length);
        for(int i=0;i<towns.length;i++) {
            for(int j=0;j<i;j++) {
                graph.addEdge(new Edge(towns[i].id, towns[j].id, towns[i].distanceTo(towns[j])));
            }
        }
        LazyPrimMST solver = new LazyPrimMST(graph);
        for(Edge edge: solver.mst()) {
            this.sum += Math.sqrt((double)edge.weight);
        }
    }
}
class Town {
    static int lastID = 0;
    int id;
    int x;
    int y;
    public Town(int x, int y) {
        this.x = x;
        this.y = y;
        this.id = lastID++;
    }
    public int distanceTo(Town that) {
        int x = this.x - that.x;
        int y = this.y - that.y;
        return (x*x + y*y);
    }
}


class Edge implements   Comparable{

    private final int v, w;
    final int weight;

    public Edge(int v, int w, int weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either(){
        return v;
    }

    public int other(int vertex){
        if (vertex == v) return w;
        else return v;
    }

    public int compareTo(Edge that)	{
        if (this.weight < that.weight) return -1;
        else if (this.weight > that.weight) return +1;
        else return 0;
    }

    @Override
    public int compareTo(Object o) {
        Edge that = (Edge) o;
        if(this.weight > that.weight) return 1;
        if(this.weight < that.weight) return -1;
        return 0;
    }
}
class EdgeWeightedGraph {
    private final int V;
    private final LinkedList<Edge>[] adj;
    public EdgeWeightedGraph(int V)	{
        this.V = V;
        adj = (LinkedList<Edge>[]) new LinkedList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new LinkedList<Edge>();
    }
    public void addEdge(Edge e){
        int v = e.either(), w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    public int V() {
        return V;
    }
}


class LazyPrimMST {

    private boolean[] marked; // MST vertices
    private Queue<Edge> mst; // MST edges
    private PriorityQueue<Edge> pq; // PQ of edges

    public LazyPrimMST(EdgeWeightedGraph G)	{
        pq = new PriorityQueue<>();
        mst = new LinkedList<Edge>();
        marked = new boolean[G.V()];
        visit(G, 0);
        while (!pq.isEmpty() && mst.size() < G.V() - 1)	{
            Edge e = pq.poll();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.offer(e);
            if (!marked[v])
                visit(G, v);
            if (!marked[w])
                visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v)	{
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)])
                pq.offer(e);
    }

    public Iterable<Edge> mst()	{
        return mst;
    }
}
