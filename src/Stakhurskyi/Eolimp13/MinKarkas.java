package Stakhurskyi.Eolimp13;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		new Main().run();
	}

	private void run() throws IOException {
		EdgeWeightedGraph graph = new EdgeWeightedGraph("src/MinKarkas/input.txt");
		PrintWriter pw = new PrintWriter(new FileWriter("src/MinKarkas/output.txt"));
		LazyPrimMST mst = new LazyPrimMST(graph);
		
		int weigth = 0;
		for (Edge e : mst.mst) {
			weigth += e.weight;
		}
		
		pw.println(weigth);
		pw.close();
	}
	
	class Edge implements Comparable<Edge> {
		private int v, w;
		int weight;

		public Edge(int v, int w, int weight) {
			super();
			this.v = v;
			this.w = w;
			this.weight = weight;
		}

		int getWeight() {
			return weight;
		}

		int either() {
			return v;
		}

		int other(int u) {
			if (u == v)
				return w;
			else
				return v;
		}

		public int compareTo(Edge that) {
			if (this.weight < that.weight)
				return -1;
			else if (this.weight > that.weight)
				return 1;
			else
				return 0;
		}
	}
	
	class EdgeWeightedGraph {
		int V;
		ArrayDeque<Edge>[] adj;
		ArrayDeque<Edge> edges;

		EdgeWeightedGraph(String fileName) throws FileNotFoundException {
			Scanner sc = new Scanner(new BufferedReader(new FileReader(fileName)));
			createGraph(sc);
			sc.close();
		}

		void createGraph(Scanner sc) {
			V = sc.nextInt();
			adj = new ArrayDeque[V];

			for (int i = 0; i < V; i++)
				adj[i] = new ArrayDeque<Edge>();

			int m = sc.nextInt();
			edges = new ArrayDeque<Edge>();

			for (int i = 0; i < m; i++) {
				int a = sc.nextInt() - 1;
				int b = sc.nextInt() - 1;
				int c = sc.nextInt();
				Edge edg = new Edge(a, b, c);
				edges.add(edg);
				adj[a].add(edg);
				adj[b].add(edg);
			}
		}

		int V() {
			return V;
		}

		Iterable<Edge> adj(int v) {
			return adj[v];
		}
	}

	class LazyPrimMST {
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

		Iterable<Edge> mst() {
			return mst;
		}
	}
}