package pr13;

/**
 *  Напівстепень заходу (indegree) вершини в орграфі - це кількість орієнтованих ребер, що направлені в дану вершину. 
 *  Напівстепень виходу вершини (outdegree) - це кількість орієнтованих ребер, що виходять з даної вершини.
 *   Жодна вершина недосяжна з вершини з напівступенем виходу 0 - така вершина називається стік.
 *    Вершина з напівступенем захода 0 не досяжна з будь-якої іншої вершини - така вершина називається джерелом. Орграф, в якому дозволені петлі,
 *     і напівстепень виходу кожної вершини дорівнює 1, називається відображенням - функцією з множини цілих чисел від 0 до V-1 у цю ж множину. 
 *     Напишіть клас Degrees.java, що реалізує наступний API:
 */
import java.util.Collection;
import java.util.Vector;

import prinston.In;

public class Degrees {
	Digraph G, R;

	Degrees(Digraph G) {
		this.G = G;
		R = new Digraph(G.V());
		// Обертаємо граф
		for (int i = 0; i < R.V(); i++) {
			for (int v : G.adj(i))
				R.addEdge(v, i);
		}
	}

	/**
	 * @return кількість ребер направлених в вершину v
	 */
	int indegree(int v) {
		Collection<Integer> c = (Collection<Integer>) R.adj(v);
		return c.size();

	}

	/**
	 * @return  кількість ребер направлених з вершини v
	 */
	int outdegree(int v) {
		Collection<Integer> c = (Collection<Integer>) G.adj(v);
		return c.size();

	}

	/**
	 *вершина, з якоъ виходять ребра, але в яку не можна зайти
	 * вершини
	 * 
	 * @return джерела
	 */
	Iterable<Integer> sources() {
		Vector<Integer> bag = new Vector<>();
		for (int i = 0; i < G.V(); i++) {
			if (indegree(i) == 0)
				bag.add(i);
		}
		return bag;

	}

	/**
	 *вершина в яку всі ребра заходять
	 * 
	 * @return стоки
	 */
	Iterable<Integer> sinks() {
		Vector<Integer> bag = new Vector<>();
		for (int i = 0; i < G.V(); i++) {
			if (outdegree(i) == 0)
				bag.add(i);
		}
		return bag;

	}

	/**
	 *
	 * Один вихід, але декілька входів та можуть бути петлі
	 * 
	 * @return чи є G відображенням
	 */
	boolean isMap() {
		for (int i = 0; i < G.V(); i++)
			for (int v : G.adj(i))
				if (outdegree(v) != 1)
					return false;

		return true;
	}

	public static void main(String[] args) {

		Digraph g = new Digraph(new In("src/pr13/source3sinks2.txt"));
		System.out.println(g.toString());
		Degrees d = new Degrees(g);

		System.out.println("souces:");
		for (int v : d.sources())
			System.out.print(v + " ");
		System.out.println();
		System.out.println("sinks:");
		for (int v : d.sinks())
			System.out.print(v + " ");
		System.out.println();
		for (int i = 0; i < g.V(); i++) {
			System.out.println(i + ". indegree  = " + d.indegree(i) + ", outdegree = " + d.outdegree(i));
			if (d.indegree(i) > 0) {
				System.out.println("from: ");
				for (int v : d.getR().adj(i))
					System.out.print(v + " ");
				System.out.println();
			}
			if (d.outdegree(i) > 0) {
				System.out.println("to: ");
				for (int v : g.adj(i))
					System.out.print(v + " ");
				System.out.println();
			}
		}
		System.out.println("source3sinks2 isMap - " + d.isMap());

		Digraph m = new Digraph(new In("src/pr13/isMap.txt"));
		System.out.println(m.toString());
		Degrees deg = new Degrees(m);
		System.out.println("isMap isMap - " + deg.isMap());
	}

	// перевернутий граф
	public Digraph getR() {
		return R;
	}
}
