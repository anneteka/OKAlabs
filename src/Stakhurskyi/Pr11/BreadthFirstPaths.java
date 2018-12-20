package Stakhurskyi.Pr11;

import java.util.Stack;



public class BreadthFirstPaths {



	private boolean[] marked;

	private int[] edgeTo;

	private int[] distTo;

	private final int s;

	

	

	public BreadthFirstPaths(Graph G, int s) {

		this.s = s;

		distTo = new int[G.V()];

        edgeTo = new int[G.V()];

        marked = new boolean[G.V()];

        bfs(G, s);

	}



	/**

     * ����� � ������

     * @param G - ����

     * @param v - bfs � ������� v

     */

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

     * �� �������� ���� � v � s, �� ������ �������������

     * @param v - ������� �� ��� ������ ����

     * @return true ���� � ����, false ���� ����

     */

	public boolean hasPathTo(int v) {

        return marked[v];

    }

	

	/**

     * ������� ���� �� s �� v; null ���� ����� ����

     */

	public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) return null;

        Stack<Integer> path = new Stack<Integer>();

        int x;

        for (x = v; distTo[x] != 0; x = edgeTo[x])

            path.push(x);

        path.push(x);

        return path;

    }

}