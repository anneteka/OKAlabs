package wordNet;

import prinston.In;
import prinston.Queue;
import prinston.StdIn;
import prinston.StdOut;

/**
 * найкоротший шлях до предка
 * 
 * @author Rovnina Tetiana
 *
 */
public class SAP {
	private Digraph graph;

	/**
	 * конструктор, що приймає орграф d
	 */
	public SAP(Digraph d) {
		if (d == null)
			throw new NullPointerException("Digraph is null");

		this.graph = d;
	}

	/**
	 * @return довжина найкоротшого шляху до спільного батька v та w або -1 якщо
	 *         такого шляху немає
	 */
	public int length(int v, int w) {
		Bag<Integer> vList = new Bag<Integer>();
		vList.add(v);
		Bag<Integer> wList = new Bag<Integer>();
		wList.add(w);
		return result(vList, wList).length;
	}

	/**
	 * @return спільний батько v та w, з найкоротшого шляху або -1 якщо такого шляху
	 *         немає
	 */
	public int ancestor(int v, int w) {
		Bag<Integer> vList = new Bag<Integer>();
		vList.add(v);
		Bag<Integer> wList = new Bag<Integer>();
		wList.add(w);
		return result(vList, wList).ancestor;
	}

	/**
	 * @return довжина найкоротшого шляху між будь-якою вершиною з v та з w або -1
	 *         якщо такого шляху немає
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		return result(v, w).length;
	}

	/**
	 * @return спільний батько з найкоротшого шляху або -1 якщо такого шляху немає
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		return result(v, w).ancestor;
	}

	/**
	 * пошук спільного предка/довжини
	 */
	private Node result(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new NullPointerException();

		// пошук спільного id (слова в одній групі)
		for (int i : v)
			for (int j : w)
				if (i == j)// знайшли предка
					return new Node(0, i);

		int[] position = new int[graph.V()];// положення вершини зліва або справа від батька
		int[] distance = new int[graph.V()];

		Queue<Integer> queue = new Queue<Integer>();
		for (int i : v) {
			queue.enqueue(i);
			position[i] = -1;
		}

		for (int i : w) {
			queue.enqueue(i);
			position[i] = 1;
		}

		//перебираємо елементи з черги 
		while (!queue.isEmpty()) {
			int current = queue.dequeue();
			//розглядаємо всіх його предків
			for (int next : graph.adj(current)) {
				if (position[next] == 0) {//елемент, якого ще немає в черзі
					queue.enqueue(next);
					position[next] = position[current];//має положення залежно від свого нащадка
					distance[next] = distance[current] + 1;//збільшуємо дистанцію
				} else if (position[next] != position[current])//знайшли предка
					return new Node(distance[next] + distance[current] + 1, next);
			}
		}
		
		//не знайшли предка
		return new Node(-1, -1);
	}
	
	
	private class Node {
		private int length = -1;
		private int ancestor = -1;

		public Node(int length, int ancestor) {
			this.length = length;
			this.ancestor = ancestor;
		}
	}
	

	//тестування
	public static void main(String[] args) {
		In in = new In("files/digraph1.txt");
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

}
