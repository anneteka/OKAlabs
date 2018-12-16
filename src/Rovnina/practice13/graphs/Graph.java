package graphs;

import java.util.ArrayDeque;
import prinston.In;

public class Graph {

	private final int V;
	private int E;
	private ArrayDeque<Integer>[] adj;

	/**
	 * Створюємо порожній граф розмірності V ініціалізуємо масив порожніми списками
	 * типу ArrayDeque
	 * 
	 * @param V - кількість вершин
	 */
	public Graph(int V) {
		this.V = V;
		adj = (ArrayDeque<Integer>[]) new ArrayDeque[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayDeque<Integer>();
	}

	public Graph(In in) {
		// first number i file is V
		// next pair of number determine edge
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
	
	public boolean sameEdje(int v, int w) {
		for (int vector : adj(v)) {
			if (vector == w)
				return true;
		}
		return false;
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

	public int[][] asMatrix() {
		int[][] matr = new int[V][V];
		for (int i = 0; i < matr.length; i++) {
			for (int j : adj(i))
				matr[i][j] = 1;
		}
		return matr;
	}
}