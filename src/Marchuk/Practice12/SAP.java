package Practice12;

import ua.princeton.lib.In;
import ua.princeton.lib.StdIn;
import ua.princeton.lib.StdOut;

public class SAP {

	private Digraph diG;
	private int lastV;
	private int lastW;
	private Iterable<Integer> lastVI;
	private Iterable<Integer> lastWI;
	private int lastLength;
	private int lastAncestor;

	public SAP(Digraph G) {
		this.diG = G;
		this.lastV = -1;
		this.lastW = -1;
		this.lastVI = null;
		this.lastWI = null;
		this.lastAncestor = -1;
		this.lastLength = -1;
	}

	public int length(int v, int w) {
		checkInput(v);
		checkInput(w);
		if ((lastV == v && lastW == w) || (lastV == w && lastW == v)) {
			return lastLength;
		}

		bfs(v, w);

		return lastLength;
	}

	public int ancestor(int v, int w) {
		checkInput(v);
		checkInput(w);
		if ((lastV == v && lastW == w) || (lastV == w && lastW == v)) {
			return lastAncestor;
		}

		bfs(v, w);

		return lastAncestor;
	}

	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		checkInput(v);
		checkInput(w);
		if (lastVI != null && lastWI != null) {
			if ((lastVI.equals(v) && lastWI.equals(w)) || (lastVI.equals(w) && lastWI.equals(v))) {
				return lastLength;
			}
		}

		bfs(v, w);

		return lastLength;
	}

	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		checkInput(v);
		checkInput(w);
		if (lastVI != null && lastWI != null) {
			if ((lastVI.equals(v) && lastWI.equals(w)) || (lastVI.equals(w) && lastWI.equals(v))) {
				return lastAncestor;
			}
		}

		bfs(v, w);

		return lastAncestor;
	}

	private void checkInput(int vertex) {
		if (vertex < 0 || vertex > diG.V() - 1)
			throw new java.lang.IndexOutOfBoundsException();
	}

	private void checkInput(Iterable<Integer> vertex) {
		for (Integer v : vertex) {
			if (v < 0 || v > diG.V() - 1)
				throw new java.lang.IndexOutOfBoundsException();
		}
	}

	private void cache(int v, int w, int length, int ancestor) {
		lastV = v;
		lastW = w;
		lastVI = null;
		lastWI = null;
		lastLength = length;
		lastAncestor = ancestor;
	}

	private void cache(Iterable<Integer> v, Iterable<Integer> w, int length, int ancestor) {
		lastV = -1;
		lastW = -1;
		lastVI = v;
		lastWI = w;
		lastLength = length;
		lastAncestor = ancestor;
	}

	private void bfs(int v, int w) {
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(diG, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(diG, w);
		int curLength = Integer.MAX_VALUE;
		int curAncestor = -1;
		for (int vertex = 0; vertex < diG.V(); vertex++) {
			if (bfsV.hasPathTo(vertex) && bfsW.hasPathTo(vertex)) {
				int lengthToVertex = bfsV.distTo(vertex) + bfsW.distTo(vertex);
				if (lengthToVertex < curLength) {
					curLength = lengthToVertex;
					curAncestor = vertex;
				}
			}
		}
		if (curLength == Integer.MAX_VALUE)
			curLength = -1;

		cache(v, w, curLength, curAncestor);
	}

	private void bfs(Iterable<Integer> v, Iterable<Integer> w) {
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(diG, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(diG, w);
		int curLength = Integer.MAX_VALUE;
		int curAncestor = -1;
		for (int vertex = 0; vertex < diG.V(); vertex++) {
			if (bfsV.hasPathTo(vertex) && bfsW.hasPathTo(vertex)) {
				int lengthToVertex = bfsV.distTo(vertex) + bfsW.distTo(vertex);
				if (lengthToVertex < curLength) {
					curLength = lengthToVertex;
					curAncestor = vertex;
				}
			}
		}
		if (curLength == Integer.MAX_VALUE)
			curLength = -1;

		cache(v, w, curLength, curAncestor);
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
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
