package practice;

import princetonlib.In;
import princetonlib.StdOut;

/**
 * Написати клас, що приймає в якості параметра конструктора неорієнтований граф
 * та має два методи, що повертають Ейлеровий цикл і цикл Гамільтона відповідно,
 * якщо вони присутні.
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class EulerHamiltonUndirected {

	private Graph g;

	EulerHamiltonUndirected(Graph g) {
		this.g = g;
	}

	/**
	 * @return чи є в неорієнтованому графі цикл Ейлера
	 */
	public boolean hasEulerCycle() {
		EulerianGraph eg = new EulerianGraph(g, new In("coordinates.txt"));
		Iterable<Integer> path = new EulerianCycle(g).cycle();
		
		if(path == null) {
			StdOut.println("has Eulerian cycle == false");
			eg.showGraph();
			return false;
		}

		StdOut.println("has Eulerian cycle == true");
		StdOut.print("Eulerian cycle:  ");
		for (int v : path)
			StdOut.print(v + " ");

		eg.showPath(path);
		return true;
	}

	/**
	 * @return чи є в неорієнтованому графі Гамільтонів цикл
	 */
	public boolean hasHamiltonCycle() {
		EulerianGraph eg = new EulerianGraph(g, new In("coordinates.txt"));
		HamiltonCycle hc = new HamiltonCycle(g);
		
		boolean hasHamiltonCycle = hc.hasHamiltonCycle(0);
		
		if(!hasHamiltonCycle) {
			StdOut.println("has Hamilton cycle == false");
			eg.showGraph();
			return hasHamiltonCycle;
		}

		StdOut.println("has Hamilton cycle == true");
		StdOut.print("Hamilton cycle:  ");
		for (int v : hc.cycle())
			StdOut.print(v + " ");

		eg.showGraph();
		eg.showPath(hc.cycle());
		return hasHamiltonCycle;
	}

	public static void main(String[] args) {
//		String fileName = "hamiltonYes.txt";
		String fileName = "eulerYes.txt";
//		String fileName = "hamiltonNo.txt";
		Graph g = new Graph(new In(fileName));

		new EulerHamiltonUndirected(g).hasEulerCycle();
		StdOut.println();
//		new EulerHamiltonUndirected(g).hasHamiltonCycle();
//		StdOut.println();
		// new UndirectedGraph().checkHamiltonCycle(g);
	}

}
