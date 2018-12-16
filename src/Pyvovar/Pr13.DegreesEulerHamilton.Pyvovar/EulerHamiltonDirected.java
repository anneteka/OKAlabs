package practice;

import java.util.Stack;

import princetonlib.In;

/**
 * Написати клас, що приймає в якості параметра конструктора орієнтований граф
 * та має два методи, що повертають Ейлеровий цикл і цикл Гамільтона відповідно,
 * якщо вони присутні. Порада: доведіть, що орграф G містить ейлеровий цикл тоді
 * і тільки тоді коли G є зв'язним і напівстепень заходу кожної вершини дорівнює
 * її напівстепеню виходу. Для пошуку шляху Гамільтона - виконайте топологічне
 * сортування і перевірте, чи існує ребро між кожною послідовною парою вершин в
 * топологічному порядку.
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class EulerHamiltonDirected {

	/**
	 * перевіряє, чи має граф g цикл Ейлера, і виводить в консоль цей цикл
	 */
	public void checkEulerCycle(String fileName) {
		Digraph g = new Digraph(new In(fileName));
		KosarajuSharirSCC kosaraju = new KosarajuSharirSCC(g);
		
		for (int v = 0; v < g.V(); v++)
			for (int w = 0; w < g.V(); w++)
				if (!kosaraju.stronglyConnected(v, w)) {
					System.out.println("has Euler cycle == false");
					return;
				}
		
		Degrees degres = new Degrees(g);
		for (int v = 0; v < g.V(); v++) {
			if (degres.indegree(v) != degres.outdegree(v)) {
				System.out.println("has Euler cycle == false");
				return;
			}
		}
		
		System.out.println("has Euler cycle == true");
		Stack<Integer> cycle = (Stack<Integer>) new EulerianCycle(g).cycle();
		for (int i = cycle.size()-1; i > 0; i--) {
			System.out.print(cycle.get(i) + " -> ");
		}
		System.out.println(cycle.get(0));
	}

	/**
	 * перевіряє, чи має граф g цикл Гамільтона, і виводить в консоль цей цикл
	 */
	public void checkHamiltonCycle(String nameFile) {
		String fileName = nameFile;

		Stack<Integer> cycleH = new Stack<Integer>();

		Digraph g = new Digraph(new In(fileName));
		DepthFirstOrder dfp = new DepthFirstOrder(g);

		Stack<Integer> q = (Stack<Integer>) dfp.reversePost();

		for (int i = 0; i < q.size() - 1; i++) {
			if (!g.oneEdje(q.get(i + 1), q.get(i))) {
				System.out.println("has Hamilton cycle == false");
				return;
			}
			cycleH.add(q.get(i));
		}
		if (!g.oneEdje(q.get(0), q.get(q.size() - 1))) {
			System.out.println("has Hamilton cycle == false");
			return;
		}
		System.out.println("has Hamilton cycle == true");
		
		System.out.print("Hamilton cycle: ");
		for (int i = q.size()-1; i >= 0; i--) {
			System.out.print(q.get(i) + " -> ");
		}
		System.out.println(q.get(q.size()-1));
	}

	public static void main(String[] args) {
		EulerHamiltonDirected dg = new EulerHamiltonDirected();
//		dg.checkHamiltonCycle("directedHamYes.txt");
//		dg.checkHamiltonCycle("directedEulerYes.txt");
		dg.checkHamiltonCycle("hamtrue1.txt");
//		dg.checkHamiltonCycle("hamfalse1.txt");
//		dg.checkHamiltonCycle("hamfalse2.txt");
//		dg.checkHamiltonCycle("hamfalse3.txt");
//		dg.checkHamiltonCycle("hamtrue2.txt");

		System.out.println();
		dg.checkEulerCycle("directedEulerYes.txt");
	}
}
