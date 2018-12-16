//Найкоротший шлях до предка
public class SAP {
	private Digraph dig;
	private int prevV, prevW, prevLen, prevAncestor;
	private Iterable<Integer> prevVI;
	private Iterable<Integer> prevWI;

	// конструктор приймає орграф (не обов’язково DAG)
	public SAP(Digraph G) {
		this.dig = G;
		this.prevV = -1;
		this.prevW = -1;
		this.prevVI = null;
		this.prevWI = null;
		this.prevAncestor = -1;
		this.prevLen = -1;
	}

	// довжина найкоротшого шляху до спільного батька v та w
	// -1 якщо такого шляху немає
	public int length(int v, int w) {
		checkInput(v);
		checkInput(w);
		if ((prevV == v && prevW == w) || (prevV == w && prevW == v)) {
			return prevLen;
		}
		bfs(v, w);
		return prevLen;
	}

	// спільний батько v та w, з найкоротшого шляху
	// -1 якщо такого шляху немає
	public int ancestor(int v, int w) {
		checkInput(v);
		checkInput(w);
		if ((prevV == v && prevW == w) || (prevV == w && prevW == v)) {
			return prevAncestor;
		}
		bfs(v, w);
		return prevAncestor;
	}

	// довжина найкоротшого шляху між будь-якою вершиною з v та з w;
	// -1 якщо такого шляху немає
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		checkInput(v);
		checkInput(w);
		if (prevVI != null && prevWI != null) {
			if ((prevVI.equals(v) && prevWI.equals(w)) || (prevVI.equals(w) && prevWI.equals(v))) {
				return prevLen;
			}
		}
		bfs(v, w);
		return prevLen;
	}

	// спільний батько з найкоротшого шляху …;
	// -1 якщо такого шляху немає
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		checkInput(v);
		checkInput(w);
		if (prevVI != null && prevWI != null) {
			if ((prevVI.equals(v) && prevWI.equals(w)) || (prevVI.equals(w) && prevWI.equals(v))) {
				return prevAncestor;
			}
		}
		bfs(v, w);
		return prevAncestor;
	}

	private void checkInput(int vertex) {
		if (vertex < 0 || vertex > dig.V() - 1)
			throw new java.lang.IndexOutOfBoundsException();
	}

	private void checkInput(Iterable<Integer> vertex) {
		for (Integer v : vertex) {
			if (v < 0 || v > dig.V() - 1)
				throw new java.lang.IndexOutOfBoundsException();
		}
	}

	private void cache(int v, int w, int length, int ancestor) {
		prevV = v;
		prevW = w;
		prevVI = null;
		prevWI = null;
		prevLen = length;
		prevAncestor = ancestor;
	}

	private void cache(Iterable<Integer> v, Iterable<Integer> w, int length, int ancestor) {
		prevV = -1;
		prevW = -1;
		prevVI = v;
		prevWI = w;
		prevLen = length;
		prevAncestor = ancestor;
	}

	private void bfs(int v, int w) {
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dig, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dig, w);
		int curLength = Integer.MAX_VALUE;
		int curAncestor = -1;
		for (int vertex = 0; vertex < dig.V(); vertex++) {
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
		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(dig, v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(dig, w);
		int curLength = Integer.MAX_VALUE;
		int curAncestor = -1;
		for (int vertex = 0; vertex < dig.V(); vertex++) {
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
}
