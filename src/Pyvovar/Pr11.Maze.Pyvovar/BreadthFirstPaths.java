import java.util.Stack;

public class BreadthFirstPaths {

	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;
	private final int s;
	
	/**
	 * @param G граф
	 * @param s координата, з якої починаємо
	 */
	public BreadthFirstPaths(Graph G, int s) {
		this.s = s;
		distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        bfs(G, s);
	}

	/**
     * пошук в ширину
     * @param G - граф
     * @param v - bfs з вершини v
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
     * Чи присутній шлях з v в s, що задана конструктором
     * @param v - вершина до чкої шукаємо шлях
     * @return true якщо є шлях, false якщо немає
     */
	public boolean hasPathTo(int v) {
        return marked[v];
    }
	
	/**
     * повертає шлях між s та v; null якщо шляху немає
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
