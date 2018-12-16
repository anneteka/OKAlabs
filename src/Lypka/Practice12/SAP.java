import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;

public class SAP {
	private Digraph digraph;
	private static final int Integer_MAX_VALUE = Integer.MAX_VALUE;
	
	public SAP(Digraph G) {
		if (G == null)
			throw new java.lang.NullPointerException("Error, digraph is null.");
		digraph = new Digraph(G);
	}
	
	public int length(int v, int w) {
		if (v < 0 || w < 0 || v > digraph.V() || w > digraph.V())
			return -1;
		return find_SAP2(v, w, 1);
	}
	
	public int ancestor(int v, int w) {
		if (v < 0 || w < 0 || v > digraph.V() || w > digraph.V())
			return -1;

		Integer vI = new Integer(v);
		Integer wI = new Integer(w);
		return find_SAP2(vI, wI, 0);
	}
	
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new java.lang.NullPointerException("Error, digraph is null");
		for (int vertex : v) {
			if (vertex < 0 || vertex > digraph.V())
				throw new java.lang.IndexOutOfBoundsException("Vertex does not exist");
		}
		for (int vertex : w) {
			if (vertex < 0 || vertex > digraph.V())
				throw new java.lang.IndexOutOfBoundsException("Vertex does not exist");
		}
		return find_SAP(v, w, 1);
	}
	
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new java.lang.NullPointerException("Error, digraph is null");
		for (int vertex : v) {
			if (vertex < 0 || vertex > digraph.V())
				throw new java.lang.IndexOutOfBoundsException("Vertex does not exist");
		}
		for (int vertex : w) {
			if (vertex < 0 || vertex > digraph.V())
				throw new java.lang.IndexOutOfBoundsException("Vertex does not exist");
		}
		
		return find_SAP(v, w, 0);
	}
	
	private int find_SAP(Iterable<Integer> v, Iterable<Integer> w, int flag) {
		int minLength = Integer_MAX_VALUE;
		int minAncestor = -1;
		BreadthFirstDirectedPaths dist2w = new BreadthFirstDirectedPaths(digraph, w);
		int[] dto = new int[digraph.V()];
		Boolean[] visited = new Boolean[digraph.V()];
		Queue<Integer> queue = new Queue<Integer>();
		for (int i = 0; i < digraph.V(); i++) {
			visited[i] = false;
			dto[i] = Integer_MAX_VALUE;
		}
		for (int vertex : v) {
			visited[vertex] = true;
			dto[vertex] = 0;
			queue.enqueue(vertex);
		}
		while (!queue.isEmpty()) {
			int current = queue.dequeue();
			if (minLength < dto[current])
				break;
			if (dist2w.hasPathTo(current) && (dist2w.distTo(current) + dto[current] < minLength)) {
				minLength = dist2w.distTo(current) + dto[current];
				minAncestor = current;
			}
			for (int next : digraph.adj(current)) {
				if (!visited[next]) {
					dto[next] = dto[current] + 1;
					visited[next] = true;
					queue.enqueue(next);
				}
			}
		}
		if (flag == 1) {
			if (minLength < Integer_MAX_VALUE)
				return minLength;
			else
				return -1;
		} else
			return minAncestor;
	}
	
	private int find_SAP2(int v, int w, int flag) {
		int minLen = Integer_MAX_VALUE;
		int minAncestor = -1;
		BreadthFirstDirectedPaths dist2w = new BreadthFirstDirectedPaths(digraph, w);
		Boolean[] marked = new Boolean[digraph.V()];
		int[] distTo = new int[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {
			marked[i] = false;
			distTo[i] = Integer_MAX_VALUE;
		}
		Queue<Integer> qv = new Queue<Integer>();
		marked[v] = true;
		distTo[v] = 0;
		qv.enqueue(v);
		while (!qv.isEmpty()) {
			int curr = qv.dequeue();
			if (minLen < distTo[curr])
				break;
			if (dist2w.hasPathTo(curr) && (dist2w.distTo(curr) + distTo[curr] < minLen)) {
				minLen = dist2w.distTo(curr) + distTo[curr];
				minAncestor = curr;
			}
			for (int next : digraph.adj(curr)) {
				if (!marked[next]) {
					distTo[next] = distTo[curr] + 1;
					marked[next] = true;
					qv.enqueue(next);
				}
			}
		}
		if (flag == 1) {
			if (minLen < Integer_MAX_VALUE)
				return minLen;
			else
				return -1;
		} else
			return minAncestor;
	}

	public static void main(String[] args) {

	}

}