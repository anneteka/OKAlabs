package week11.lection;

//import ua.com.oka.lib.Bag;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class Graph {
	
	public final int V;
	public final int E;
	private Bag<Integer>[] adj;

	public Graph(int V, int E){
		this.V=V;
		this.E=E;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v=0; v<V; v++)
			adj[v] = new Bag<Integer>();
	}

	public Graph(In in){
	    this.V = in.readInt();
	    this.E = in.readInt();
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v=0; v<V; v++)
            adj[v] = new Bag<Integer>();
        while(in.hasNextLine()){
            int first = in.readInt();
            int second = in.readInt();
            adj[first].add(second);
            adj[second].add(first);
        }
    }

	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
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
}
