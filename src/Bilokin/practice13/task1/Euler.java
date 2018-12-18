package Task1;

import java.util.Iterator;

import Helping.ArrayStack;
import Helping.Graph;
import ua.princeton.lib.In;

public class Euler {
	private static ArrayStack<Integer> cycle = new ArrayStack<Integer>();
	private boolean isEuler = true;
	private static final String testFile = "test.txt";

	public Euler(Graph G){
		@SuppressWarnings("unchecked")
		Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
		for (int v = 0; v < G.V(); v++)
			adj[v] = G.adj(v).iterator();
		int s = 0;
		for (int v = 0; v < G.V(); v++) {
			if (adj[v].hasNext()) {
				s = v;
				break;
			}
		}
		ArrayStack<Integer> st = new ArrayStack<Integer>();
		st.push(s);
		while (!st.isEmpty()) {
			int v = st.pop();
			cycle.push(v);
			int w = v;
			while (adj[w].hasNext()) {
				st.push(w);
				w = adj[w].next();
			}
			if (w != v)
				isEuler = false;
		}
		for (int v = 0; v < G.V(); v++)
			if (adj[v].hasNext())
				isEuler = false;
	}

	public Iterable<Integer> cycle() {
		if (!isEuler)
			return null;
		return cycle;
	}

	public boolean isEuler() {
		return isEuler;
	}

	public static void main(String[] args) {
		In in = new In(testFile);
		Graph G = new Graph(in);
		Euler ec = new Euler(G);
		System.out.println("Euler cycle : " + ec.isEuler);
		if (ec.isEuler) {
			while (!cycle.isEmpty()) {
				System.out.print(cycle.pop() + " => ");

			}

		}
	}
}