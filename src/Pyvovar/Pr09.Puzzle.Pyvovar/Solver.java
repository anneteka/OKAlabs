import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

import princetonlib.StdOut;

public class Solver {

	private class Move implements Comparable<Move> {
		private Move previous;
		private Board board;
		private int numberMove = 0;

		public Move(Board board) {
			this.board = board;
		}

		public Move(Board board, Move previous) {
			this.board = board;
			this.previous = previous;
			this.numberMove = previous.numberMove + 1;
		}

		public int compareTo(Move move) {
			return (this.board.manhattan() - move.board.manhattan()) + (this.numberMove - move.numberMove);
		}
	}

	private Move lastMove;

	// знайти розв'язок для дошки initial
	public Solver(Board initial) {
		MinPQ<Move> moves = new MinPQ<Move>();
		moves.insert(new Move(initial));

		MinPQ<Move> twinMoves = new MinPQ<Move>();
		twinMoves.insert(new Move(initial.twin()));

		while (true) {
			lastMove = expand(moves);
			if (lastMove != null || expand(twinMoves) != null)
				return;
		}
	}

	private Move expand(MinPQ<Move> moves) {
		if (moves.isEmpty())
			return null;
		Move bestMove = moves.delMin();
		if (bestMove.board.isGoal())
			return bestMove;
		for (Board neighbor : bestMove.board.neighbors()) {
			if (bestMove.previous == null || !neighbor.equals(bestMove.previous.board)) {
				moves.insert(new Move(neighbor, bestMove));
			}
		}
		return null;
	}

	// чи має початкова дошка розв’язок
	public boolean isSolvable() {
		return (lastMove != null);
	}

	// мінімальна кількість кроків для вирішення дошки, -1 якщо немає рішення
	public int moves() {
		return isSolvable() ? lastMove.numberMove : -1;
	}

	// послідовність дошок в найкоротшому рішенні; null якщо немає рішення
	public Iterable<Board> solution() {
		if (!isSolvable())
			return null;

		Stack<Board> moves = new Stack<Board>();
		while (lastMove != null) {
			moves.push(lastMove.board);
			lastMove = lastMove.previous;
		}

		return moves;
	}

	// вирішити
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File("puzzle04.txt")));
		// створюємо початкову дошку з файлу
		// In in = new In(args[0]);
		String s = br.readLine();
		int N = Integer.parseInt(s);
		int[][] blocks = new int[N][N];
		StringTokenizer token;

		for (int i = 0; i < N; i++) {
			s = br.readLine();
			token = new StringTokenizer(s);
			for (int j = 0; j < N; j++) {
				blocks[i][j] = Integer.parseInt(token.nextToken());
			}
		}
		Board initial = new Board(blocks);

		// розв’язати
		Solver solver = new Solver(initial);

		// надрукувати рішення
		if (!solver.isSolvable())
			StdOut.println("Дошка не має розв’язку");
		else {
			StdOut.println("Мінімальна кількість кроків = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
		br.close();

	}

}
