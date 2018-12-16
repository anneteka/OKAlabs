import java.util.Vector;

public class Digraph {

	private final int V;
	private int E;
	private Vector<Integer>[] adj;

	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Vector<Integer>[]) new Vector[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Vector<Integer>();
	}

	public Digraph(In in) {
		super();
		V = in.readInt();
		adj = new Vector[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Vector<Integer>();

		int e = 0;
		for (; in.hasNextLine(); e++) {
			int from = in.readInt();
			int to = in.readInt();
			addEdge(from, to);
		}

		this.E = e;
	}

	public void addEdge(int v, int w) {
		adj[v].add(w);
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

	public Digraph reverse() {
		Digraph reverse = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < V; i++) {
			for (int v : adj(i)) {
				sb.append(i).append("->").append(v).append("\n");
			}
		}
		return sb.toString();
	}

	public boolean oneEdje(int v, int w) {
		for (int vector : adj(v)) {
			if (vector == w)
				return true;
		}
		return false;
	}
}