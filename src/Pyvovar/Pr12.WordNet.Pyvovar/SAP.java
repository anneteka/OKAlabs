import java.io.File;

import princetonlib.In;
import princetonlib.StdIn;
import princetonlib.StdOut;

/**
 * Клас, який шукає найкоротший шлях до предка та предка
 * 
 * @author Пивовар Олена, 4 група
 *
 */
public class SAP {

	private Digraph graph;

	/**
	 * конструктор приймає орграф (не обов’язково DAG)
	 * 
	 * @param G
	 */
	public SAP(Digraph G) {
		if (G == null)
			throw new NullPointerException();
		graph = new Digraph(G);
	}

	private int[] modify(int[] a) {
		if (a[0] == Integer.MAX_VALUE) {
			a[0] = -1;
			a[1] = -1;
		}
		return a;
	}

	private int[] helper(BreadthFirstDirectedPaths bfs1, BreadthFirstDirectedPaths bfs2) {
		int length = Integer.MAX_VALUE, ancestor = 0;
		for (int i = 0, temp = 0; i < graph.V(); i++) {
			if (bfs1.hasPathTo(i) && bfs2.hasPathTo(i)) {
				temp = bfs1.distTo(i) + bfs2.distTo(i);
				if (temp < length) {
					length = temp;
					ancestor = i;
				}
			}
		}
		return new int[] { length, ancestor };
	}

	/**
	 * 
	 * @param v
	 * @param w
	 * @return довжина найкоротшого шляху до спільного батька v та w, -1 якщо такого
	 *         шляху немає
	 */
	public int length(int v, int w) {
		if (v < 0 || v > graph.V() || w < 0 || w > graph.V())
			throw new IndexOutOfBoundsException();
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
		return modify(helper(bfs1, bfs2))[0];
	}

	/**
	 * @param v
	 * @param w
	 * @return спільний батько v та w, з найкоротшого шляху -1 якщо такого шляху
	 *         немає
	 * 
	 * 
	 */
	public int ancestor(int v, int w) {
		if (v < 0 || v > graph.V() || w < 0 || w > graph.V())
			throw new IndexOutOfBoundsException();
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
		return modify(helper(bfs1, bfs2))[1];
	}

	/**
	 * @param v
	 * @param w
	 * @return довжина найкоротшого шляху між будь-якою вершиною з v та з w; -1 якщо
	 *         такого шляху немає
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new NullPointerException();
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
		return modify(helper(bfs1, bfs2))[0];
	}

	/**
	 * @param v
	 * @param w
	 * @return спільний батько з найкоротшого шляху …; -1 якщо такого шляху немає
	 * 
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new NullPointerException();
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
		return modify(helper(bfs1, bfs2))[1];
	}

	public static void main(String[] args) {
		In in = new In(new File("digraph1.txt"));
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

}
