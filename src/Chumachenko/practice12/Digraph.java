package project;

import java.util.NoSuchElementException;

import ua.princeton.lib.In;

public class Digraph {

	private final int V;
	private Bag<Integer>[] adj;
	
	public int V() {
		return V;
	}

	/**
	 * Створюємо порожній орграф розмірності V 
	 * ініціалізуємо масив порожніми списками типу Bag
	 * @param V - кількість вершин
	 */
	public Digraph(int V){
		this.V=V;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v=0; v<V; v++)
			adj[v] = new Bag<Integer>();
	}
	
	 public Digraph(In in) {
	        try {
	            this.V = in.readInt();
	            if (V < 0) throw new IllegalArgumentException("number of vertices in a Digraph must be nonnegative");
	           
	            adj = (Bag<Integer>[]) new Bag[V];
	            for (int v = 0; v < V; v++) {
	                adj[v] = new Bag<Integer>();
	            }
	            int E = in.readInt();
	            if (E < 0) throw new IllegalArgumentException("number of edges in a Digraph must be nonnegative");
	            for (int i = 0; i < E; i++) {
	                int v = in.readInt();
	                int w = in.readInt();
	                addEdge(v, w); 
	            }
	        }
	        catch (NoSuchElementException e) {
	            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
	        }
	    }


	
	/**
	 * додаємо орієнтоване ребро з v в w
	 * @param v - вершина
	 * @param w - вершина
	 */
	public void addEdge(int v, int w){
		adj[v].add(w);
	}
	
	/**
	 * @param v - вершина графу
	 * @return - ступінь вершини графу v
	 */
	public int degree(int v){
		int degree = 0;
		for (int w : adj(v))
			degree++;
		return degree;
	}
	
	/**
	 * ітератор по вершинам суміжним з вершиною v
	 * @param v - вершина
	 * @return - ітератор по мішку суміжних з v вершин
	 */
	public Iterable<Integer> adj(int v){
		return adj[v];
	}

	public Digraph reverse() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
