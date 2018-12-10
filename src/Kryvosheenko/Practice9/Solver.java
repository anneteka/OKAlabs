import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import edu.princeton.cs.algs4.MinPQ;
import ua.princeton.lib.StdOut;

public class Solver {
	private Step lastStep;

	public Solver(Board initial) {
		// знайти рішення для дошки initial
		MinPQ<Step> steps = new MinPQ<Step>();
		steps.insert(new Step(initial));

		MinPQ<Step> twinMoves = new MinPQ<Step>();
		twinMoves.insert(new Step(initial.boardCopy()));

		while (true) {
			lastStep = expand(steps);
			if (lastStep != null || expand(twinMoves) != null)
				return;
		}
	}

	private Step expand(MinPQ<Step> steps) {
		if (steps.isEmpty())
			return null;
		Step bStep = steps.delMin();
		if (bStep.board.isGoal())
			return bStep;
		for (Board neighbor : bStep.board.neighbors()) {
			if (bStep.previous == null || !neighbor.equals(bStep.previous.board)) {
				steps.insert(new Step(neighbor, bStep));
			}
		}
		return null;
	}

	public boolean isSolvable() {
		return (lastStep != null);// чи має початкова дошка розв’язок
	}

	public int steps() {
		if (isSolvable())
			return lastStep.stepsNum;
		else
			return -1;
		// мінімальна кількість кроків для вирішення дошки, -1 якщо немає рішення
	}

	public Iterable<Board> solution() {
		// послідовність дошок в найкоротшому рішенні; null якщо немає рішення
		if (!isSolvable())
			return null;
		ArrayList<Board> steps = new ArrayList<Board>();
		while (lastStep != null) {
			steps.add(lastStep.board);
			lastStep = lastStep.previous;
		}
		return steps;
	}

	private class Step implements Comparable<Step> {
		private Step previous;
		private Board board;
		private int stepsNum = 0;

		public Step(Board board) {
			this.board = board;
		}

		public Step(Board board, Step previous) {
			this.board = board;
			this.previous = previous;
			this.stepsNum = previous.stepsNum + 1;
		}

		public int compareTo(Step step) {
			return (this.board.manhattan() - step.board.manhattan()) + (this.stepsNum - step.stepsNum);
		}
	}

	public static void main(String[] args) {
		// вирішити
		Scanner in = null;
		try {
			in = new Scanner(new File("puzzle04.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int N = in.nextInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.nextInt();
		Board initial = new Board(blocks);

		// розв’язати
		Solver solver = new Solver(initial);

		// надрукувати рішення
		if (!solver.isSolvable())
			System.out.println("The game can`t be solved.");
		else {
			StdOut.println("Min number of steps: " + solver.steps());
			for (Board board : solver.solution())
				System.out.println(board);
		}

	}
}