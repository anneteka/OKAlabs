package meize;
/** BFS realization
 * Ви потрапили в лабіринт. Десь в лабіринті знаходиться чарівна лампа, що дозволить Вам повернутися додому. Іншого виходу з лабіринту, крім як знайти лампу, не існує.

Ви з'євляєтеся в лабіринті завжди в одній і тій же точці, лабіринт задається з файлу, чарівна лампа з'являється випадково.

Ваша задача знайти шлях до лампи використовуючи метод DFS і BFS, як результат вивести шлях, довжину шляху, та кількість відвіданих вузлів.

Як ви зрозуміли лабіринт задається у вигляді графа. При реалізації DFS і BFS вузли додаються в чергу або стек в порядку зростання їх нумерів.

За бажанням зробити графічний інтерфейс.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;

	public BreadthFirstPaths(Graph g, int l) {
		marked = new boolean[g.getV()];
		edgeTo = new int[g.getV()];
		distTo = new int[g.getV()];
		bfs(g, l);
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		marked[s] = true;
		distTo[s] = 0;
		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					q.enqueue(w);
					marked[w] = true;
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
				}
			}
		}
	}

	/**
	 * Чи присутній шлях з v в s, що задана конструктором
	 * 
	 * @param v
	 *            - вершина до чкої шукаємо шлях
	 * @return true якщо є шлях, false якщо немає
	 */
	public boolean hasPathTo(int v) {
		return marked[v];
	}

	/**
	 * повертає шлях між s та v; null якщо шляху немає
	 */
	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x])
			path.push(x);
		path.push(x);
		return path;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("src/meize/sec.txt"));
		int lamp = sc.nextInt();
		int v = sc.nextInt();
		int edge = sc.nextInt();
		Graph gr = new Graph(v);

		for (int i = 0; i < edge; i++) {
			int e = sc.nextInt();
			int e1 = sc.nextInt();
			gr.addEdge(e, e1);
		}
		BreadthFirstPaths bfs = new BreadthFirstPaths(gr, lamp);
		if (bfs.hasPathTo(0)) {
			Stack path = (Stack) bfs.pathTo(0);
			for (int weg : bfs.pathTo(0))
				System.out.print(weg + " - ");
			System.out.println("BFS Path");
			System.out.println("Amount of veges:" + path.size());
			System.out.println("Path`s lenght:" + (path.size() - 1));
		} else {
			System.err.println("No path found!");
		}

	}

}