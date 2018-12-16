package euler_hamilton_cycles;

import java.util.Stack;
import graphs.DepthFirstOrder;
import graphs.Digraph;
import graphs.Graph;
import prinston.In;

public class HamiltonianCycle {

	private Stack<Integer> cycle;

	/**
	 * @return цикл гамільтона
	 */
	public Iterable<Integer> cycle() {
		if (cycle == null)
			return null;

		// перевертаємо цикл
		Stack<Integer> cycleRev = new Stack<Integer>();
		while (!cycle.isEmpty()) {
			cycleRev.add(cycle.pop());
		}
		return cycleRev;
	}

	/**
	 * шукає цикл гамільтона для орієнтованого графа d
	 */
	public HamiltonianCycle(Digraph d) {
		Stack<Integer> path = (Stack<Integer>) new DepthFirstOrder(d).reversePost();

		for (int i = 0; i < path.size() - 1; i++) {
			if (!d.sameEdje(path.get(i + 1), path.get(i)))
				return;
		}
		if (!d.sameEdje(path.get(0), path.get(path.size() - 1)))
			return;

		cycle = path;
	}

	/**
	 * шукає цикл гамільтона для неорієнтованого графа g
	 */
	public HamiltonianCycle(Graph g) {
		Stack<Integer> path = (Stack<Integer>) new DepthFirstOrder(g).reversePost();

		for (int i = 0; i < path.size() - 1; i++) {
			if (!g.sameEdje(path.get(i + 1), path.get(i)))
				return;
		}
		if (!g.sameEdje(path.get(0), path.get(path.size() - 1)))
			return;

		cycle = path;
	}

	public static void main(String[] args) {
		Graph g = new Graph(new In("files/hamiltonYes.txt"));
		HamiltonianCycle hm = new HamiltonianCycle(g);
		Iterable<Integer> cycle = hm.cycle();
		if (cycle != null)
			System.out.println(cycle);
		else
			System.out.println("No");

	}
}
