import java.util.ArrayList;

import edu.princeton.cs.algs4.Digraph;

public class Degrees {

	private int sz;
	private Digraph d;
	private Digraph rev;

	public Degrees(Digraph G) {
		sz = G.V();
		d = G;
		rev = G.reverse();
	}

	public boolean isMap() {
		for (int i = 0; i < d.V(); i++)
			if (!(d.outdegree(i) == 1))
				return false;
		return true;
	}

	int indegree(int v) {
		int count = 0;
		for (int w = 0; w < d.V(); w++) {
			for (int x : d.adj(w)) {
				if (x == v) {
					count++;
				}
			}
		}
		return count;
	}

	int outdegree(int v) {
		int count = 0;
		for (int w : d.adj(v)) {
			count++;
		}
		return count;
	}

	public Iterable<Integer> sources() {
		ArrayList<Integer> sources = new ArrayList<Integer>();
		for (int i = 0; i < sz; i++)
			if (indegree(i) == 0)
				sources.add(i);
		return sources;
	}

	public Iterable<Integer> sinks() {
		ArrayList<Integer> sinks = new ArrayList<Integer>();
		for (int i = 0; i < sz; i++)
			if (outdegree(i) == 0)
				sinks.add(i);
		return sinks;
	}
}