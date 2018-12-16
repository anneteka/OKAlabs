package euler_hamilton_cycles;

import graphs.Graph;
import prinston.In;
import prinston.StdOut;

/**
 * Написати клас, що приймає в якості параметра конструктора неорієнтований граф
 * та має два методи, що повертають Ейлеровий цикл і цикл Гамільтона відповідно,
 * якщо вони присутні
 */
public class UndirectedGraph {

	/**
	 * перевіряє чи має граф g цикл ейлера і малює цей граф
	 */
	public void checkEulerCycle(Graph g) {
		EulerianGraph eg = new EulerianGraph(g, new In("files/coordinates.txt"));
		Iterable<Integer> path = new EulerianCycle(g).cycle();

		if (path != null) {
			StdOut.print("Eulerian cycle:  ");
			for (int v : path)
				StdOut.print(v + " ");

			eg.showPath(path);
		} else {
			StdOut.println("Eulerian cycle:   none");
			eg.showGraph();
		}
	}

	/**
	 * перевіряє чи має граф g цикл ейлера і малює цей граф
	 */
	public void checkHamiltonCycle(Graph g) {
		HamiltonianCycle hm = new HamiltonianCycle(g);

		Iterable<Integer> cycle = hm.cycle();
		if (cycle != null) {
			StdOut.print("Hamilton cycle:  ");
			for (int v : cycle)
				StdOut.print(v + " ");

		} else
			StdOut.println("Hamilton cycle:   none");
	}

	public static void main(String[] args) {
//		String fileName = "files/hamiltonYes.txt";
		String fileName = "files/eulerYes.txt";
//		String fileName = "files/hamiltonNo.txt";
		Graph g = new Graph(new In(fileName));

		new UndirectedGraph().checkEulerCycle(g);
		StdOut.println();
		new UndirectedGraph().checkHamiltonCycle(g);
	}

}
