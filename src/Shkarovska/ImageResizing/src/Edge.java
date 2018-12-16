class Edge {
	
	private final int v, w;
	private final double weight;
	public Edge(int v, int w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	public int from(){ 
		return v; 
	}
	
	public int to()	{ 
		return w; 
		}
	
	public double weight()	{ 
		return weight; 
		}

}
class EDigraph {

	private final int V;
	private final Bag<Edge>[] adj;
	
	public EDigraph(int V){
		this.V = V;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Edge>();
	}
	
	public void addEdge(Edge e)	{
		int v = e.from();
		adj[v].add(e);
	}
	
	public Iterable<Edge> adj(int v){ 
		return adj[v]; 
	}
	public int V() {
		return V;
	}

}