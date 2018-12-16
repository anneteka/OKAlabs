import java.util.Stack;

public class EulerCycle {
	private Stack<Integer> cycle = new Stack<Integer>();

	private static class Edge {
		private final int vrtx;
		private final int vrtxw;
		private boolean isUsed;

		public Edge(int v, int w) {
			this.vrtx = v;
			this.vrtxw = w;
			isUsed = false;
		}

		public int other(int vertex) {
			if (vertex == vrtx)
				return vrtxw;
			else if (vertex == vrtxw)
				return vrtx;
			else
				throw new IllegalArgumentException("Argument is illegal");
		}
	}

	public EulerCycle(Graph G) {
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