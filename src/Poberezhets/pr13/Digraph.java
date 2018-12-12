package Poberezhets.pr13;

import java.util.Vector;

import princetonlib.In;
/**
 * ���������� ����
 * @author �������
 *
 */
public class Digraph {

	private final int V;
	private int E;
	private Vector<Integer>[] adj;

	/**
	 * ��������� ������� ������ ��������� V ���������� ����� ��������
	 * �������� ���� Bag
	 * 
	 * @param V - ������� ������
	 */
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adj = (Vector<Integer>[]) new Vector[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Vector<Integer>();
	}

	/**
	 * ��������� ����� �� �����
	 */

	public Digraph(In in) {
		super();
		V = in.readInt();
		adj = new Vector[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Vector<Integer>();

		int e = 0;
		for (; in.hasNextLine(); e++) {
			int from = in.readInt();
			int to = in.readInt();
			addEdge(from, to);
		}

		this.E = e;
	}

	/**
	 * ������ ��������� ����� � v � w
	 * 
	 * @param v - �������
	 * @param w - �������
	 */
	public void addEdge(int v, int w) {
		adj[v].add(w);
	}

	/**
	 * @param v - ������� �����
	 * @return - ������ ������� ����� v
	 */
	public int degree(int v) {
		int degree = 0;
		for (int w : adj(v))
			degree++;
		return degree;
	}

	/**
	 * �������� �� �������� ������� � �������� v
	 * 
	 * @param v - �������
	 * @return - �������� �� ���� ������� � v ������
	 */
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	/**
	 * @return ������� ������
	 */
	public int V() {
		return V;
	}

	/**
	 * @return ������� �����
	 */
	public int E() {
		return E;
	}

	/**
	 * �������� ������������ ����
	 *
	 * @return the reverse of the digraph
	 */
	public Digraph reverse() {
		Digraph reverse = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj(v)) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < V; i++) {
			for (int v : adj(i)) {
				sb.append(i).append("->").append(v).append("\n");
			}
		}
		return sb.toString();
	}
	public boolean oneEdje(int v, int w) {
	    for (int vector : adj(v)) {
	      if(vector == w)
	        return true;
	    }
	    return false;
	  }
}
