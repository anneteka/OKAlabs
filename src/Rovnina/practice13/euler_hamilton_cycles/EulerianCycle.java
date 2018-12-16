package euler_hamilton_cycles;

import java.util.Iterator;
import java.util.Stack;
import graphs.Digraph;
import graphs.Graph;
import graphs.Kosaraju;
import graphs.Queue;

public class EulerianCycle {
	private Stack<Integer> cycle = null;

	private static class Edge {
		private final int v1;
		private final int v2;
		private boolean isUsed;

		public Edge(int v1, int v2) {
			this.v1 = v1;
			this.v2 = v2;
			isUsed = false;
		}

		// інша вершина
		public int other(int vertex) {
			if (vertex == v1)
				return v2;
			else if (vertex == v2)
				return v1;
			else
				throw new IllegalArgumentException("Ребро не маэ такої вершини");
		}
	}

	/**
	 * шукає цикл ейлера для неорієнтованих графів
	 */
	public EulerianCycle(Graph G) {
		if (G.E() == 0)// має бути хоч одне ребро
			return;

		// кількість ребер кожної вершини має бути парна
		for (int v = 0; v < G.V(); v++)
			if (G.degree(v) % 2 != 0)
				return;

		// створити список суміжних вершин кожної вершини
		Queue<Edge>[] adj = (Queue<Edge>[]) new Queue[G.V()];
		for (int v = 0; v < G.V(); v++)
			adj[v] = new Queue<Edge>();

		for (int v = 0; v < G.V(); v++) {
			int loops = 0;// петлі
			for (int w : G.adj(v)) {
				if (v < w || (v == w && (loops % 2 == 0))) {
					Edge e = new Edge(v, w);
					adj[v].enqueue(e);
					adj[w].enqueue(e);
				} else if (v == w)
					loops++;
			}
		}

		// початок циклу
		int s = notAloneV(G);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(s);

		// шукаємо цикл через усі ребра методом DFS
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
			// додаємо вершину в цикл
			cycle.push(v);
		}

		// чи всі ребра задіяні
		if (cycle.size() != G.E() + 1)
			cycle = null;
	}

	// returns будь-яку не одиноку вершину
	private static int notAloneV(Graph G) {
		for (int v = 0; v < G.V(); v++)
			if (G.degree(v) > 0)
				return v;
		return -1;
	}

	/**
	 * @return цикл ейлера
	 */
	public Iterable<Integer> cycle() {
		if (cycle == null)
			return null;
		
		// перевертаємо цикл
		Stack<Integer> cycleRev = new Stack<Integer>();
		while (!cycle.isEmpty()) {
			cycleRev.add(cycle.pop());
		}
		return cycleRev;
	}

	////////////////////////////////////////////////////////////////////////////

	/**
	 * шукає цикл ейлера для орієнтованих графів
	 */
	public EulerianCycle(Digraph G) {
		// необхідне хоча б одне ребро
		if (G.E() == 0)
			return;

		// граф має бути зв'язним
		Kosaraju ksscc = new Kosaraju(G);
		for (int v = 0; v < G.V(); v++)
			for (int w = 0; w < G.V(); w++)
				if (!ksscc.stronglyConnected(v, w))
					return;

		// indegree(v) = outdegree(v) для кожної вершини v
		Degrees degrees = new Degrees(G);
		for (int v = 0; v < G.V(); v++)
			if (degrees.indegree(v) != degrees.outdegree(v))
				return;

		// список суміжних вершин з кожною вершиною
		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
		for (int v = 0; v < G.V(); v++)
			adj[v] = G.adj(v).iterator();

		// вершина циклу
		int s = notAloneV(G, degrees);
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(s);

		// шукаємо цикл через усі ребра методом DFS
		cycle = new Stack<Integer>();
		while (!stack.isEmpty()) {
			int v = stack.pop();
			while (adj[v].hasNext()) {
				stack.push(v);
				v = adj[v].next();
			}
			cycle.push(v);
		}

		// всі ребра мають бути використані
		if (cycle.size() != G.E() + 1)
			cycle = null;

	}

	// returns будь-яку не одиноку вершину з якої є вихід
	private static int notAloneV(Digraph G, Degrees degrees) {
		for (int v = 0; v < G.V(); v++)
			if (degrees.outdegree(v) > 0)
				return v;
		return -1;
	}

}