package helping;

import ua.princeton.lib.In;


public class Graph {
	
	private final int V;
	private Bag<Integer>[] adj;
	
	/**
	 * ��������� ������� ���� ��������� V 
	 * ���������� ����� �������� �������� ���� Bag
	 * @param V - ������� ������
	 */
	public Graph(int V){
		this.V=V;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v=0; v<V; v++)
			adj[v] = new Bag<Integer>();
	}
	   public Graph(In in) {
	        this(in.readInt());
	        int E = in.readInt();
	        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
	        for (int i = 0; i < E; i++) {
	            int v = in.readInt();
	            int w = in.readInt();
	            addEdge(v, w);
	        }
	    }
	/**
	 * ������ ����� �� ����� ���������
	 * @param v - �������
	 * @param w - �������
	 */
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
	}
	public int V(){
		return V;
	}
	
	/**
	 * @param v - ������� �����
	 * @return - ������ ������� ����� v
	 */
	public int degree(int v){
		int degree = 0;
		for (int w : adj(v))
			degree++;
		return degree;
	}
	
	/**
	 * �������� �� �������� ������� � �������� v
	 * @param v - �������
	 * @return - �������� �� ���� ������� � v ������
	 */
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
}
