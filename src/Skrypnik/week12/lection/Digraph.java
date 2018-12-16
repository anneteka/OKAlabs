package week12.lection;

import edu.princeton.cs.algs4.Bag;

public class Digraph {

	private final int V;
	private Bag<Integer>[] adj;

	public Digraph(int V){
		this.V=V;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v=0; v<V; v++)
			adj[v] = new Bag<Integer>();
	}

	public void addEdge(int v, int w){
		adj[v].add(w);
	}

	public int degree(int v){
		int degree = 0;
		for (int w : adj(v))
			degree++;
		return degree;
	}

	public int V(){
		return V;
	}

	public Iterable<Integer> adj(int v){
		return adj[v];
	}

	public Digraph reverse() {
		Digraph reverse = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}
}
