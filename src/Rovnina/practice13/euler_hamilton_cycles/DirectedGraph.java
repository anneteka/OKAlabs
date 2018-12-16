package euler_hamilton_cycles;

import graphs.Digraph;
import graphs.Graph;
import prinston.In;
import prinston.StdOut;

/**
 * Написати клас, що приймає в якості параметра конструктора орієнтований граф
 * та має два методи, що повертають Ейлеровий цикл і цикл Гамільтона відповідно,
 * якщо вони присутні
 * 
 * * @author Rovnina Tetiana, IPZ 5 group
 */
public class DirectedGraph {

	/**
	 * перевіряє чи має граф g цикл ейлера і малює цей граф
	 */
	public void checkEulerCycle(Digraph g) {
		Iterable<Integer> path = new EulerianCycle(g).cycle();

		if (path != null) {
			StdOut.print("Eulerian cycle:  ");
			for (int v : path)
				StdOut.print(v + " ");
		} else
			StdOut.println("Eulerian cycle:   none");
	}

	/**
	 * перевіряє чи має граф g цикл ейлера і малює цей граф
	 */
	public void checkHamiltonCycle(Digraph g) {
		HamiltonianCycle hm = new HamiltonianCycle(g);

		Iterable<Integer> cycle = hm.cycle();
		if (cycle != null) {
			StdOut.print("Hamilton cycle:  ");
			for (int v : cycle)
				StdOut.print(v + " ");
		} else {
			StdOut.println("Hamilton cycle:   none");
		}
	}

	public static void main(String[] args) {
//		String fileName = "files/source3sinks2.txt";
//		String fileName = "files/hamiltonYes.txt";
//		String fileName = "files/directedEulerYes.txt";
//		String fileName = "files/hamiltonNo.txt";
		String fileName = "files/directedHamiltonYes.txt";
		Digraph g = new Digraph(new In(fileName));

		new DirectedGraph().checkEulerCycle(g);
		StdOut.println();
		new DirectedGraph().checkHamiltonCycle(g);
	}

}
