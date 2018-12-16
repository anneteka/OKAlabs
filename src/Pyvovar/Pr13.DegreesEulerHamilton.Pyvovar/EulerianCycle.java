package practice;

import java.util.Iterator;
import java.util.Stack;

public class EulerianCycle {
	private Stack<Integer> cycle = null;

	private static class Edge {
		private final int v;
		private final int w;
		private boolean visited;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
			visited = false;
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

	/**
	 * Рахує цикл Ейлера, якщо той є
	 * 
	 * @param G
	 */
	public EulerianCycle(Graph G) {
		if (G.E() == 0)
			return;

		for (int v = 0; v < G.V(); v++)
			if (G.degree(v) % 2 != 0)
				return;

		Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[G.V()];
		for (int v = 0; v < G.V(); v++)
			adj[v] = new Queue<Edge>();

		for (int v = 0; v < G.V(); v++) {
			int loops = 0;
			for (int w : G.adj(v)) {
				if (v == w) {
					if (loops % 2 == 0) {
						Edge e = new Edge(v, w);
						adj[v].enqueue(e);
						adj[w].enqueue(e);
					}
					loops++;
				} else if (v < w) {
					Edge e = new Edge(v, w);
					adj[v].enqueue(e);
					adj[w].enqueue(e);
				}
			}
		}

		makeCycle(G, adj);
	}

	/**
	 * заповнення стеку циклом
	 * 
	 * @param G
	 * @param adj
	 */
	private void makeCycle(Graph G, Queue<Edge>[] adj) {
		int s = vertexWithOutdegrees(G);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(s);

		cycle = new Stack<Integer>();
		while (!stack.isEmpty()) {
			int v = stack.pop();
			while (!adj[v].isEmpty()) {
				Edge edge = adj[v].dequeue();
				if (edge.visited)
					continue;
				edge.visited = true;
				stack.push(v);
				v = edge.other(v);
			}

			cycle.push(v);
		}

		if (cycle.size() - 1 != G.E())
			cycle = null;
	}

	/**
	 * 
	 * @return цикл Ейлера
	 */
	public Iterable<Integer> cycle() {
		return cycle;
	}

	/**
	 * @return чи є цикл Ейлера в графі
	 */
	public boolean hasEulerianCycle() {
		return cycle != null;
	}

	/**
	 * рахує цикл Ейлера в орієнтованому графі
	 * 
	 * @param G
	 */
	public EulerianCycle(Digraph G) {
		if (G.E() == 0)
			return;

		KosarajuSharirSCC ksscc = new KosarajuSharirSCC(G);
		for (int v = 0; v < G.V(); v++)
			for (int w = 0; w < G.V(); w++)
				if (!ksscc.stronglyConnected(v, w))
					return;

		Degrees degrees = new Degrees(G);
		for (int v = 0; v < G.V(); v++)
			if (degrees.indegree(v) != degrees.outdegree(v))
				return;

		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
		for (int v = 0; v < G.V(); v++)
			adj[v] = G.adj(v).iterator();
		makeCycle(G, adj, degrees);
	}

	/**
	 * заповнення стеку циклом
	 * 
	 * @param G
	 * @param adj
	 * @param degrees
	 */
	private void makeCycle(Digraph G, Iterator<Integer>[] adj, Degrees degrees) {
		int s = vertexWithOutdegrees(G, degrees);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(s);

		cycle = new Stack<Integer>();
		while (!stack.isEmpty()) {
			int v = stack.pop();
			while (adj[v].hasNext()) {
				stack.push(v);
				v = adj[v].next();
			}
			cycle.push(v);
		}

		if (cycle.size() - 1 != G.E())
			cycle = null;
	}

	/**
	 * @param G
	 * @return вершину в неорієнтованому графі, степінь якої більший за 0
	 */
	private static int vertexWithOutdegrees(Graph G) {
		for (int v = 0; v < G.V(); v++)
			if (G.degree(v) > 0)
				return v;
		return -1;
	}

	/**
	 * @param graph
	 * @param degrees
	 * @return вершину з орієнтованого графа, у якої напівстепінь виходу більше 0
	 */
	private static int vertexWithOutdegrees(Digraph graph, Degrees degrees) {
		for (int v = 0; v < graph.V(); v++)
			if (degrees.outdegree(v) > 0)
				return v;
		return -1;
	}
}
