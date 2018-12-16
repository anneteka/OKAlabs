package SeamyCarving;

import java.util.Stack;

public class AcyclicSP {

	private double[] distTo; // distTo[v] = найкоротша відстань від s до v
	private DirectedEdge[] edgeTo; // edgeTo[v] = останній елемент найкоротшої відстані від s до v

	public AcyclicSP(EdgeWeightedDigraph G, int s) {
		edgeTo = new DirectedEdge[G.V()];
		distTo = new double[G.V()];

		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		DepthFirstOrder topological = new DepthFirstOrder(G);
		for (int v : topological.reversePost())
			for (DirectedEdge e : G.adj(v))
				relax(e);
	}

	public double[] distTo() {
		return distTo;
	}

	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}

	// чи є шлях до v
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	
	// шлях до v, якщо шляху немає повертає null
	public Iterable<DirectedEdge> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
			path.push(e);
		}
		return path;
	}

}
