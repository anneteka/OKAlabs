package practice11;


import practice3.Bag;
import ua.princeton.lib.In;

public class Graph {
	public final int V;
	private Bag<Integer>[] adj;

	public Graph(int V) {
		this.V = V;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>();
	}

	public Graph(In in) {
		V = Integer.parseInt(in.readLine());
		int edges = Integer.parseInt(in.readLine());
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<>();
		for (int i = 0; i < edges; i++) {
			String[] split = in.readLine().split(" ");
			addEdge(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
		}
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	public int degree(int v) {
		int degree = 0;
		for (int w : adj(v))
			degree++;
		return degree;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
}
