import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Maria Skyrta. Implementation of Solver API. Solves the board if it
 *         can be solved.
 *
 */
public class Solver {
	Board start;
	private Stack<Board> sol;

	Solver(Board initial) {
		start = initial;
		sol = new Stack<>();
	}

	public boolean isSolvable() {
		int moves = 0;
		int[] matrixArray = new int[start.dimension() * start.dimension()];
		for (int i = 0; i < start.dimension(); i++) {
			for (int u = 0; u < start.dimension(); u++) {
				matrixArray[i * start.dimension() + u] = start.array()[i][u];
			}

		}
		for (int i = 0; i < matrixArray.length; i++) {
			if (matrixArray[i] != 0) {
				for (int u = i + 1; u < matrixArray.length; u++) {
					if (matrixArray[i] > matrixArray[u] && matrixArray[u] != 0) {
						moves++;
					}
				}
			}
		}
		if (start.dimension() % 2 != 0) {
			if (moves % 2 == 0) {
				return true;
			}
			return false;
		} else {
			int pos = start.getTheEmptyRev(start.array());
			if ((pos % 2 == 0 && moves % 2 != 0) || (pos % 2 != 0 && moves % 2 == 0)) {
				return true;
			}
			return false;

		}

	}

	public int move() {

		return sol.size() - 1;
	}

	public Stack<Board> solution() {
		return sol;
	}

	public void solve() {
		PriorityQueue<Board> pq;
		Iterable<Board> b;
		pq = new PriorityQueue<Board>();

		pq.add(start);
		Board goal = pq.poll();
		while (!goal.isGoal()) {

			b = goal.neighbors();
			for (Board n : b) {
				pq.add(n);

			}
			goal = pq.poll();
		}
		while (goal != null) {
			sol.push(goal);
			goal = goal.previous;
		}
	}

	// testing
	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("D://path//puzzle4x4-40.txt"));
		int N = scan.nextInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = scan.nextInt();
		Board initial = new Board(blocks, null, 0);
		Solver solver = new Solver(initial);
		if (!solver.isSolvable())
			System.out.println("This board is unsolvable!");
		else {
			solver.solve();
			int mov = solver.move();
			System.out.println("Min steps = " + mov);
			while (!solver.solution().isEmpty())
				System.out.println(solver.solution().pop());
		}

	}
}