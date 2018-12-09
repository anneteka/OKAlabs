package pr13;

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
 * Визначити вагу мінімального дерева для неорієнтовного зваженого зв'язного
 * графа.
 * 
 * Вхідні дані У першому рядку знаходиться кількість вершин n та ребер m (1 ≤ n
 * ≤ 100, 1 ≤ m ≤ 6000) у графі. У кожному з наступних m рядків записано по
 * трійці чисел a, b, c, де a та b - номери вершин, з'єднаних ребром, а c - вага
 * ребра (натуральне число, яке не перевищує 30000).
 * 
 * Вихідні дані Вивести вагу мінімального остовного дерева.
 */
public class Kruskala {

	public static void main(String[] args) throws IOException {
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/pr13/output.txt")));
		EdgeWeightedGraph g = new EdgeWeightedGraph("src/pr13/input.txt");
		LazyPrimMST mst = new LazyPrimMST(g);
		int w = 0;
		for (Edge e : mst.mst) {
			w += e.weight;
		}

		out.print(w);
		out.close();
	}

	static class LazyPrimMST {
		private boolean[] marked;
		private ArrayDeque<Edge> mst;
		private PriorityQueue<Edge> pq;

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

		Iterable<Edge> adj(int v) {
			return adj[v];
		}

		int V() {
			return V;
		}
	}

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
			return Integer.compare(weight, that.weight);
		}

		int weight() {
			return weight;
		}
	}

}
