package practice;

import java.io.File;
import java.util.Collection;
import java.util.Vector;

import princetonlib.In;

/**
 * Напівстепень заходу (indegree) вершини в орграфі - це кількість орієнтованих
 * ребер, що направлені в дану вершину. Напівстепень виходу вершини (outdegree)
 * - це кількість орієнтованих ребер, що виходять з даної вершини. Жодна вершина
 * недосяжна з вершини з напівступенем виходу 0 - така вершина називається стік.
 * Вершина з напівступенем захода 0 не досяжна з будь-якої іншої вершини - така
 * вершина називається джерелом. Орграф, в якому дозволені петлі, і напівстепень
 * виходу кожної вершини дорівнює 1, називається відображенням - функцією з
 * множини цілих чисел від 0 до V-1 у цю ж множину. Напишіть клас Degrees.java,
 * що реалізує усе вище сказане.
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Degrees {

	private Digraph graph;
	private Digraph revGraph; // перевернутий

	Degrees(Digraph G) {
		this.graph = G;
		this.revGraph = reverceDigraph(G);
	}

	/**
	 * @param G
	 * @return обернутий граф
	 */
	private Digraph reverceDigraph(Digraph G) {
		Digraph R = new Digraph(G.V());
		for (int i = 0; i < R.V(); i++) {
			for (int v : G.adj(i))
				R.addEdge(v, i);
		}
		return R;
	}

	/**
	 * Напівстепень заходу (indegree) вершини в орграфі - це кількість орієнтованих
	 * ребер, що направлені в дану вершину.
	 * 
	 * @return Напівстепень заходу в верщину v
	 */
	int indegree(int v) {
		Collection<Integer> collection = (Collection<Integer>) revGraph.adj(v);
		return collection.size();

	}

	/**
	 * Напівстепень виходу вершини (outdegree) - це кількість орієнтованих ребер, що
	 * виходять з даної вершини.
	 * 
	 * @return Напівстепень виходу вершини v
	 */
	int outdegree(int v) {
		Collection<Integer> collection = (Collection<Integer>) graph.adj(v);
		return collection.size();

	}

	/**
	 * Вершина з напівступенем захода 0 не досяжна з будь-якої іншої вершини - така
	 * вершина називається джерелом
	 * 
	 * @return джерела
	 */
	Iterable<Integer> sources() {
		Vector<Integer> sources = new Vector<>();
		for (int i = 0; i < graph.V(); i++) {
			if (indegree(i) == 0)
				sources.add(i);
		}
		return sources;

	}

	/**
	 * Жодна вершина недосяжна з вершини з напівступенем виходу 0 - така вершина
	 * називається стік.
	 * 
	 * @return стоки
	 */
	Iterable<Integer> sinks() {
		Vector<Integer> sinks = new Vector<>();
		for (int i = 0; i < graph.V(); i++) {
			if (outdegree(i) == 0)
				sinks.add(i);
		}
		return sinks;

	}

	/**
	 * Орграф, в якому дозволені петлі, і напівстепень виходу кожної вершини
	 * дорівнює 1, називається відображенням - функцією з множини цілих чисел від 0
	 * до V-1 у цю ж множину
	 * 
	 * @return чи є G відображенням
	 */
	boolean isMap() {
		for (int i = 0; i < graph.V(); i++)
			for (int v : graph.adj(i))
				if (outdegree(v) != 1)
					return false;

		return true;
	}

	public static void main(String[] args) {
		File file = new File("source3sinks2.txt");
		Digraph g = new Digraph(new In(file));
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
			System.out.println(i + ". indegree = " + d.indegree(i) + ", outdegree = " + d.outdegree(i));
		}

		System.out.println();
		System.out.println(file.getName() + " isMap == " + d.isMap());

		file = new File("isMap.txt");
		Digraph m = new Digraph(new In(file));
		System.out.println(m.toString());

		Degrees deg = new Degrees(m);
		System.out.println(file.getName() + " isMap == " + deg.isMap());
	}
}
