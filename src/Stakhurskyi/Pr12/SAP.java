package Stakhurskyi.Pr12;

import java.io.File;

import princetonlib.In;
import princetonlib.StdIn;
import princetonlib.StdOut;


public class SAP {

	private Digraph graph;

	/**
	 * ����������� ������ ������ (�� ���������� DAG)
	 * 
	 * @param G
	 */
	public SAP(Digraph G) {
		if (G == null)
			throw new NullPointerException();
		graph = new Digraph(G);
	}

	/**
	 * @param bfs1
	 * @param bfs2
	 * @param lengthReturn ���� true ������� ����������� ����, ������ - ������
	 * @return ������ ��� ������� ������������ �����, �������� �� ���������
	 *         lengthReturn
	 */
	private int findAncestorAndLength(BreadthFirstDirectedPaths bfs1, BreadthFirstDirectedPaths bfs2,
                                      boolean lengthReturn) {
		int ancestor = 0;
		int length = Integer.MAX_VALUE;
		int pathTogether = 0;
		for (int i = 0; i < graph.V(); i++) {
			if (bfs1.hasPathTo(i) && bfs2.hasPathTo(i)) {
				pathTogether = bfs1.distTo(i) + bfs2.distTo(i);
				if (pathTogether < length) {
					length = pathTogether;
					ancestor = i;
				}
			}
		}

		if (length == Integer.MAX_VALUE) {
			length = -1;
			ancestor = -1;
		}

		if (lengthReturn)
			return length;
		return ancestor;
	}

	/**
	 * 
	 * @param v
	 * @param w
	 * @return ������� ������������ ����� �� �������� ������ v �� w, -1 ���� ������
	 *         ����� ����
	 */
	public int length(int v, int w) {
		if (v < 0 || v > graph.V() || w < 0 || w > graph.V())
			throw new IndexOutOfBoundsException();
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
		return findAncestorAndLength(bfs1, bfs2, true);
	}

	/**
	 * @param v
	 * @param w
	 * @return ������� ������ v �� w, � ������������ ����� -1 ���� ������ �����
	 *         ����
	 * 
	 * 
	 */
	public int ancestor(int v, int w) {
		if (v < 0 || v > graph.V() || w < 0 || w > graph.V())
			throw new IndexOutOfBoundsException();
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
		return findAncestorAndLength(bfs1, bfs2, false);
	}

	/**
	 * @param v
	 * @param w
	 * @return ������� ������������ ����� �� ����-���� �������� � v �� � w; -1 ����
	 *         ������ ����� ����
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new NullPointerException();
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
		return findAncestorAndLength(bfs1, bfs2, true);
	}

	/**
	 * @param v
	 * @param w
	 * @return ������� ������ � ������������ ����� �; -1 ���� ������ ����� ����
	 * 
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new NullPointerException();
		BreadthFirstDirectedPaths bfs1 = new BreadthFirstDirectedPaths(graph, v);
		BreadthFirstDirectedPaths bfs2 = new BreadthFirstDirectedPaths(graph, w);
		return findAncestorAndLength(bfs1, bfs2, false);
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