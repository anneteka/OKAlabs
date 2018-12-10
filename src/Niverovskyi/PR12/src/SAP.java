import java.util.Arrays;

import ua.princeton.lib.In;
import ua.princeton.lib.StdIn;
import ua.princeton.lib.StdOut;

public class SAP {
	private Digraph storage;

	public SAP(Digraph G) {
		storage = new Digraph(G);
	}

	public int length(int v, int w) {
		checkInts(v, w);

		return min(v, w);
	}

	public int ancestor(int v, int w) {
		checkInts(v, w);

		return argmin(v, w);
	}

	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		checkInts(v);
		checkInts(w);

		return min(v, w);
	}

	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		checkInts(v);
		checkInts(w);

		return argmin(v, w);
	}

	public static void main(String[] args) {
		In in = new In("digraph2.txt");
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

	private void checkInts(Integer... toBeChecked) {
		checkInts(Arrays.asList(toBeChecked));
	}

	private void checkInts(Iterable<Integer> toBeChecked) {
		for (int checkMe : toBeChecked) {
			if (checkMe < 0 || checkMe >= storage.V()) {
				throw new IndexOutOfBoundsException(
						String.format(
								"Integer %d does not fall in the range of the digraph (0 to %d)",
								checkMe, storage.V()));
			}
		}
	}

	private int min(final int v, final int w) {
		return min(Arrays.asList(v), Arrays.asList(w));
	}

	private int min(Iterable<Integer> v, Iterable<Integer> w) { // (BreadthFirstDirectedPaths
																// bfsV,
																// BreadthFirstDirectedPaths
																// bfsW) {
		int retVal = -1;

		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(storage,
				v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(storage,
				w);

		for (int i = 0; i < storage.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
				int currDist = bfsV.distTo(i) + bfsW.distTo(i);
				if (retVal < 0 || currDist < retVal) {
					retVal = currDist;
				}
			}
		}
		return retVal;
	}

	private int argmin(int v, int w) {
		return argmin(Arrays.asList(v), Arrays.asList(w));
	}

	private int argmin(Iterable<Integer> v, Iterable<Integer> w) {
		int retVal = -1;
		int minDist = -1;

		BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(storage,
				v);
		BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(storage,
				w);

		for (int i = 0; i < storage.V(); i++) {
			if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
				int currDist = bfsV.distTo(i) + bfsW.distTo(i);
				if (minDist < 0 || currDist < minDist) {
					minDist = currDist;
					retVal = i;
				}
			}
		}
		return retVal;
	}
}