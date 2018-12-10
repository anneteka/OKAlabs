package minSkeleton;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Determine the weight of the minimum spindle tree for a non-oriented weighed
 * connected graph
 * 
 * @author Rovnina Tetiana
 *
 */
public class MinSkeleton {

	public static void main(String[] args) throws IOException {
		new MinSkeleton().run();
	}

	private void run() throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/minSkeleton/output.txt")));
		EdgeWeightedGraph g = new EdgeWeightedGraph("src/minSkeleton/input.txt");
		LazyPrimMST mst = new LazyPrimMST(g);
		int w = 0;
		for (Edge e : mst.mst) {
			w += e.weight;
		}
		out.printf("%d\n", w);
		out.close();
	}

	// *********************************************************//
	static class LazyPrimMST {
		private boolean[] marked; // MST vertices
		private ArrayDeque<Edge> mst; // MST edges
		private PriorityQueue<Edge> pq; // PQ of edges

		public LazyPrimMST(EdgeWeightedGraph G) {
			pq = new PriorityQueue<Edge>();
			mst = new ArrayDeque<Edge>();
			marked = new boolean[G.V()];
			visit(G, 0);
			while (!pq.isEmpty() && mst.size() < G.V() - 1) {
				Edge e = pq.remove();
				int v = e.either(), w = e.other(v);
				if (marked[v] && marked[w])
					continue;
				mst.add(e);
				if (!marked[v])
					visit(G, v);
				if (!marked[w])
					visit(G, w);
			}
		}

		private void visit(EdgeWeightedGraph G, int v) {
			marked[v] = true;
			for (Edge e : G.adj(v))
				if (!marked[e.other(v)])
					pq.offer(e);
		}
	}

	// *******************************************************//
	static class EdgeWeightedGraph {
		int V;
		ArrayDeque<Edge>[] adj;
		ArrayDeque<Edge> edges;

		// create graph from file
		EdgeWeightedGraph(String fileName) throws FileNotFoundException {
			super();
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			Scanner sc = new Scanner(br);
			V = sc.nextInt();
			adj = new ArrayDeque[V];
			for (int v = 0; v < V; v++)
				adj[v] = new ArrayDeque<Edge>();
			int m = sc.nextInt();
			edges = new ArrayDeque<Edge>();
			for (int i = 0; i < m; i++) {
				int u = sc.nextInt() - 1;
				int v = sc.nextInt() - 1;
				int w = sc.nextInt();
				Edge e = new Edge(u, v, w);
				edges.add(e);
				adj[u].add(e);
				adj[v].add(e);
			}
			sc.close();
		}

		// edge out of vertex v
		Iterable<Edge> adj(int v) {
			return adj[v];
		}

		// number of vertex
		int V() {
			return V;
		}
	}

	// *************************************************//

	static class Edge implements Comparable<Edge> {
		private int v;
		private int w;
		int weight;

		public Edge(int v, int w, int weight) {
			super();
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		// vertex
		int either() {
			return v;
		}

		// another vertex (not v)
		int other(int u) {
			if (u == v)
				return w;
			else
				return v;
		}

		// compare two edges
		public int compareTo(Edge that) {
			return Integer.compare(weight, that.weight);
		}

		int weight() {
			return weight;
		}
	}

}
