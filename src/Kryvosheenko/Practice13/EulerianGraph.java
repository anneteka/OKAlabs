import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.StdDraw;

public class EulerianGraph {
	private Graph graph;
	private Deque<Integer> eulerianCycle;
	private Map<Edge, Integer> edgesMap;
	private Deque<Edge> cycle = new ArrayDeque<Edge>();
	private Iterator<Integer>[] adj;
	private int[] x;
	private int[] y;
		
		 class Edge {
			int vertexA;
			int vertexB;
			
			public Edge(int vertexA, int vertexB) {
				super();
				this.vertexA = Math.min(vertexA, vertexB);
				this.vertexB = Math.max(vertexA, vertexB);
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + getOuterType().hashCode();
				result = prime * result + vertexA;
				result = prime * result + vertexB;
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (obj == null) {
					return false;
				}
				if (!(obj instanceof Edge)) {
					return false;
				}
				Edge other = (Edge) obj;
				if (!getOuterType().equals(other.getOuterType())) {
					return false;
				}
				if (vertexA != other.vertexA) {
					return false;
				}
				if (vertexB != other.vertexB) {
					return false;
				}
				return true;
			}

			private EulerianGraph getOuterType() {
				return EulerianGraph.this;
			}
			
			@Override
			public String toString() {
				return " (" + vertexA + ") --- (" + vertexB + ") ";
			}
		}


	public EulerianGraph(Graph graph) {
		super();
		this.graph = graph;
	}

	private void addEdgeCount(Edge edge) {
		int countSoFar = edgesMap.getOrDefault(edge, 0);
		setEdgeCount(edge, countSoFar + 1);
	}

	private void setEdgeCount(Edge edge, int count) {
		edgesMap.put(edge, count);
	}

	private void findEulerianCycle(Graph graph) {
		eulerianCycle = new ArrayDeque<Integer>();
		Deque<Integer> stack = new ArrayDeque<Integer>();
		stack.offerFirst(0);
		while (!stack.isEmpty()) {
			int v = stack.peek();
			while (adj[v].hasNext()) {
				int w = adj[v].next();
				Edge edge = new Edge(v, w);
				if (edgeAvailable(edge)) {
					stack.offerFirst(w);
					decrementEdgeCount(edge);
					v = w;
				}
			}
			while (!stack.isEmpty()) {
				int x = stack.pop();
				eulerianCycle.push(x);
				if (stack.isEmpty()) {
					return;
				}
				int k = stack.peek();
				if (adj[k].hasNext()) {
					break;
				}
			}
		}
	}

	private boolean edgeAvailable(Edge edge) {
		int edgeCount = edgesMap.getOrDefault(edge, 0);

		return edgeCount > 0;
	}

	private void decrementEdgeCount(Edge edge) {
		int countSoFar = edgesMap.getOrDefault(edge, 0);
		setEdgeCount(edge, countSoFar - 1);
	}

	public Deque<Integer> getEulerianCycle() {
		return eulerianCycle;
	}
	
	public void show() {
		for (int i = 0; i < graph.V(); i++) {
			StdDraw.text(x[i], y[i], String.valueOf(i));
			StdDraw.circle(x[i], y[i], 3);
			for (int v : graph.adj(i)) {
				double x1 = x[i], x2 = x[v], y1 = y[i], y2 = y[v];
				double a = Math.atan2(y2 - y1, x2 - x1);
				double lenCut = 3;
				double dx = lenCut * Math.cos(a);
				double dy = lenCut * Math.sin(a);
				x1 = x1 + dx;
				y1 = y1 + dy;
				x2 = x2 - dx;
				y2 = y2 - dy;
				StdDraw.line(x1, y1, x2, y2);
			}
		}
	}
}
