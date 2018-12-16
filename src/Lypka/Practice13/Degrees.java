public class Degrees {

	private int size;
	private Digraph diagr;
	private Digraph rvrs;

	public Degrees(Digraph G) {
		size = G.V();
		diagr = G;
		rvrs = G.reverse();
	}

	public int indegree(int v) {
		return rvrs.indegree(v);
	}

	public int outdegree(int v) {
		return diagr.outdegree(v);
	}

	public Iterable<Integer> sources() {
		Bag<Integer> sources = new Bag<Integer>();
		for (int c = 0; c < size; c++)
			if (indegree(c) == 0)
				sources.add(c);
		return sources;
	}

	public Iterable<Integer> sinks() {
		Bag<Integer> sinks = new Bag<Integer>();
		for (int c = 0; c < size; c++)
			if (outdegree(c) == 0)
				sinks.add(c);
		return sinks;
	}

}