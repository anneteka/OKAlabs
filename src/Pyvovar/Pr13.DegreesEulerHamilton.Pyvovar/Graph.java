package practice;

import java.util.ArrayDeque;

import princetonlib.In;

/**
 * Граф неорієнтований
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Graph {

	private final int V; // кількість вершин
	private int E; // кількість ребер
	private ArrayDeque<Integer>[] adj; // ребра

	/**
	 * Створюємо порожній граф розмірності V
	 * 
	 * @param V - кількість вершин
	 */
	public Graph(int V) {
		this.V = V;
		adj = (ArrayDeque<Integer>[]) new ArrayDeque[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayDeque<Integer>();
	}

	/**
	 * створюємо граф із файлу
	 * 
	 * @param in
	 */
	public Graph(In in) {
		this.V = in.readInt();
		adj = (ArrayDeque<Integer>[]) new ArrayDeque[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayDeque<Integer>();
		E = 0;
		while (in.hasNextLine()) {
			E++;
			addEdge(in.readInt(), in.readInt());
		}
	}

	/**
	 * додаємо ребро між двома вершинами
	 * 
	 * @param v - вершина
	 * @param w - вершина
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w);
		adj[w].add(v);
	}

	/**
	 * @param v - вершина графу
	 * @return - ступінь вершини графу v
	 */
	public int degree(int v) {
		int degree = 0;
		for (int w : adj(v))
			degree++;
		return degree;
	}

	/**
	 * ітератор по вершинам суміжним з вершиною v
	 * 
	 * @param v - вершина
	 * @return - ітератор по мішку суміжних з v вершин
	 */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/**
	 * @return кількість вершин
	 */
	public int V() {
		return V;
	}

	/**
	 * @return кількість ребер
	 */
	public int E() {
		return E;
	}
}