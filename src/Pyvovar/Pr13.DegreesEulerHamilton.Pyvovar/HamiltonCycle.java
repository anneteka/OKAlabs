package practice;
import java.util.Stack;
import java.util.Vector;

import princetonlib.In;

public class HamiltonCycle {

	private int[][] matrixGraph;
	private Stack<Integer> cycle = new Stack<Integer>();// hamiltonian cycle
	private boolean[] visited;
	private int numberV;

	public HamiltonCycle(Graph gr) {
		this.numberV = gr.V(); // кількість вершин
		visited = new boolean[numberV];
		this.matrixGraph = makeMatrixFromGraph(gr);
	}
	
	public int[][] makeMatrixFromGraph(Graph gr) {
		int[][] matr = new int[gr.V()][gr.V()];
		for (int i = 0; i < gr.V(); i++) {
			for (int j : gr.adj(i))
				matr[i][j] = 1;
		}
		return matr;
	}

	public Iterable<Integer> cycle() {
		return cycle;
	}

	public boolean hasHamiltonCycle(int curr) {
		cycle.add(curr);
		if (cycle.size() == numberV) {
			if (matrixGraph[cycle.get(0)][cycle.get(cycle.size() - 1)] == 1) {
				cycle.add(0);
				return true;
			}

			cycle.remove(cycle.size() - 1);
			return false;
		}
		visited[curr] = true;
		for (int nxt = 0; nxt < numberV; ++nxt)

			if (matrixGraph[curr][nxt] == 1 && !visited[nxt])
				if (hasHamiltonCycle(nxt))
					return true;

		visited[curr] = false;
		cycle.remove(cycle.size() - 1);
		return false;
	}

	public static void main(String[] args) {
		Graph g = new Graph(new In("hamiltonNo.txt"));
		HamiltonCycle hm = new HamiltonCycle(g);
		EulerianGraph eg = new EulerianGraph(g, new In("coordinates.txt"));
		if (hm.hasHamiltonCycle(0)) {
			System.out.println(hm.cycle());
			eg.showPath(hm.cycle());
		} else {
			System.out.println("No");
			eg.showGraph();
		}
	}
}
