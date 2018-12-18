import java.util.Stack;

import ua.princeton.lib.In;
import ua.princeton.lib.StdRandom;

public class edgeW {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private bag<edge>[] adj;

	public edgeW(int V) {
		if (V < 0)
			throw new IllegalArgumentException(
					"Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (bag<edge>[]) new bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new bag<edge>();
		}
	}

	public edgeW(int V, int E) {
		this(V);
		if (E < 0)
			throw new IllegalArgumentException(
					"Number of edges must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			double weight = Math.round(100 * StdRandom.uniform()) / 100.0;
			edge e = new edge(v, w, weight);
			addEdge(e);
		}
	}

	public edgeW(In in) {
		this(in.readInt());
		int E = in.readInt();
		if (E < 0)
			throw new IllegalArgumentException(
					"Number of edges must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = in.readInt() - 1;
			int w = in.readInt() - 1;
			double weight = in.readDouble();
			edge e = new edge(v, w, weight);
			addEdge(e);
		}
	}

	public edgeW(int Ed, Point[] m) {
		this(Ed);
		for (int i = 0; i < m.length; i++) {
			for (int j = i + 1; j < m.length; j++) {
				edge e = new edge(i, j, Point.lengthEdge(m[i], m[j]));
				addEdge(e);
			}
		}
	}

	public edgeW(edgeW G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<edge> reverse = new Stack<edge>();
			for (edge e : G.adj[v]) {
				reverse.push(e);
			}
			for (edge e : reverse) {
				adj[v].add(e);
			}
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	private void validateVer(int v) {
		if (v < 0 || v >= V)
			throw new IndexOutOfBoundsException("vertex " + v
					+ " is not between 0 and " + (V - 1));
	}

	public void addEdge(edge e) {
		int v = e.either();
		int w = e.other(v);
		validateVer(v);
		validateVer(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	public Iterable<edge> adj(int v) {
		validateVer(v);
		return adj[v];
	}

	public int degree(int v) {
		validateVer(v);
		return adj[v].size();
	}

	public Iterable<edge> edges() {
		bag<edge> list = new bag<edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}

				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0)
						list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (edge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
}