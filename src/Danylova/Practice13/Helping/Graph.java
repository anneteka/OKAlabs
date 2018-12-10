package Helping;



import ua.princeton.lib.In;

public class Graph {
	
	private final int V;
	Bag<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int V){
		this.V=V;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v=0; v<V; v++)
			adj[v] = new Bag<Integer>();
	}
	
	 public Graph(In in) {
	        this(in.readInt());
	        int E = in.readInt();
	        for (int i = 0; i < E; i++) {
	            int v = in.readInt();
	            int w = in.readInt();
	            addEdge(v, w);
	        }
	 }
	
	public int V(){
		return V;
	}

	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
	}

	public int degree(int v){
		int degree = 0;
		for (@SuppressWarnings("unused") int w : adj(v))
			degree++;
		return degree;
	}

	public Iterable<Integer> adj(int v){
		return adj[v];
	}
}
