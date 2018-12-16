package T3;
import Helping.Bag;
import Helping.Digraph;

public class Degrees {
	public Digraph graph;

	public Degrees(Digraph digraph) {
		this.graph = digraph;
	}

	public int indegree(int v) {
		Digraph reverse = graph.reverse();
		Degrees deg = new Degrees(reverse);
		return deg.outdegree(v);
	}

	
	public int outdegree(int v) {
		int out = 0;
		for (int i : graph.adj(v))
			out++;
		return out;
	}

	public Iterable<Integer> sources() {
		Bag<Integer> s = new Bag<Integer>();
		for (int i = 0; i < graph.V(); i++)
			if (indegree(i) == 0)
				s.add(i);
		return s;
	}

	public Iterable<Integer> sinks() {
		Bag<Integer> s = new Bag<Integer>();
		for (int i = 0; i < graph.V(); i++)
			if (outdegree(i) == 0)
				s.add(i);
		return s;
	}

	public boolean isMap() {
		for (int i = 0; i < graph.V(); i++)
			if (outdegree(i) == 1)
				return true;
		return false;
	}
}