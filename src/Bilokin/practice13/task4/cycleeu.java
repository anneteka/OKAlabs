package Task4;



import java.util.Iterator;

import Helping.ArrayStack;
import Helping.Digraph;
import ua.princeton.lib.In;


public class cycleeu {
	private static final String file = "test4.txt";
	private static ArrayStack<Integer> cycle = new ArrayStack<Integer>();
	private boolean isEu = true;

	public cycleeu(Digraph G) {
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
				isEu = false;
		}
		for (int v = 0; v < G.V(); v++)
			if (adj[v].hasNext())
				isEu = false;
	}

	public Iterable<Integer> cycle(){
		if (!isEu)
			return null;
		return cycle;
	}

	public boolean isEu(){
		return isEu;
	}

	public static void main(String[] args){
		In in = new In(file);
		Digraph G = new Digraph(in);
		cycleeu ec = new cycleeu(G);
		System.out.println("Euler cycle:" + ec.isEu);
		if (ec.isEu) {
			while(!cycle.isEmpty()) {
				System.out.print(cycle.pop() + " => ");
			}
		}
	}
}
