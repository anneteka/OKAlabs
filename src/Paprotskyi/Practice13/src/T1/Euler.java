package Task1;

import java.util.Iterator;

import Helping.ArrayStack;
import Helping.Graph;
import lib.In;

public class Euler {
	private static ArrayStack<Integer> cycle = new ArrayStack<Integer>();
	private boolean isEulet = true;
	private static final String testFile = "Test01.txt";

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
		ArrayStack<Integer> stack = new ArrayStack<Integer>();
		stack.push(s);
		while (!stack.isEmpty()) {
			int v = stack.pop();
			cycle.push(v);
			int w = v;
			while (adj[w].hasNext()) {
				stack.push(w);
				w = adj[w].next();
			}
			if (w != v)
				isEulet = false;
		}
		for (int v = 0; v < G.V(); v++)
			if (adj[v].hasNext())
				isEulet = false;
	}

	public Iterable<Integer> cycle() {
		if (!isEulet)
			return null;
		return cycle;
	}

	public boolean isEuler() {
		return isEulet;
	}

	public static void main(String[] args) {
		In in = new In(testFile);
		Graph G = new Graph(in);
		Euler ec = new Euler(G);
		System.out.println("Euler cycle in graph: " + ec.isEulet);
		if (ec.isEulet) {
			while (!cycle.isEmpty()) {
				System.out.print(cycle.pop() + " -> ");

			}

		}
	}
}