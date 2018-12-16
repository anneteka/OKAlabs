import java.util.ArrayDeque;

public class Graph {

	private final int V;
	private int E;
	private ArrayDeque<Integer>[] adj;

	public Graph(int V) {
		this.V = V;
		adj = (ArrayDeque<Integer>[]) new ArrayDeque[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayDeque<Integer>();
	}

	public Graph(In in) {
		// first number i file is V
		// next pair of number determine edge
		this.V = in.readInt();
		adj = (ArrayDeque<Integer>[]) new ArrayDeque[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayDeque<Integer>();
		E = 0;
		while (in.hasNextLine()) {
			E++;
			addEdge(in.readInt(), in.readInt());
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

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public int[][] makeMatrix() {
		int[][] matr = new int[V][V];
		for (int i = 0; i < matr.length; i++) {
			for (int j : adj(i))
				matr[i][j] = 1;
		}
		return matr;
	}
}