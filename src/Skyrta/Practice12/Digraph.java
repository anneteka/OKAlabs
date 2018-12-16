public class Digraph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;

	public Digraph(int V) {
		if (V < 0)
			throw new IllegalArgumentException();
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	/*
	 * public Digraph(Digraph G) { this(G.V()); this.E = G.E(); for (int v = 0; v <
	 * G.V(); v++) { Stack<Integer> reverse = new Stack<Integer>(); for (int w :
	 * G.adj[v]) { reverse.push(w); } for (int w : reverse) { adj[v].add(w); } } }
	 */

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public void addEdge(int v, int w) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException();
		if (w < 0 || w >= V)
			throw new IndexOutOfBoundsException();
		adj[v].add(w);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException();
		return adj[v];
	}

	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				R.addEdge(w, v);
			}
		}
		return R;
	}
}
