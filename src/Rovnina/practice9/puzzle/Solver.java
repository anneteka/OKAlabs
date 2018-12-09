package puzzle;

import java.util.Stack;

import prinston.In;
import prinston.StdOut;

/**
 * Вирішувач
 * 
 * @author Rovnina Tetiana
 *
 */
public class Solver {

	/**
	 * Об'єкт цього класу є нодою прирітетної черги і є результатом чергового ходу.
	 * Об'єкт містить дошку з елементами, що розташовані в певному порядку, пам’ятає
	 * кількість кроків, що необхідно зробити для досягнення цієї пошукової ноди і
	 * попередню пошукову ноду
	 *
	 */
	private class Move implements Comparable<Move> {
		private Move previous;
		private Board board;
		private int count = 0;

		/**
		 * щоб створити початкову пошукову ноду для priority queue
		 */
		public Move(Board board) {
			this.board = board;
		}

		public Move(Board board, Move previous) {
			this.board = board;
			this.previous = previous;
			this.count = previous.count + 1;
		}

		public int compareTo(Move move) {
			return (this.board.manhattan() - move.board.manhattan()) + (this.count - move.count);
		}
	}

	private Move lastMove;

	/**
	 * знайти рішення для дошки initial
	 */
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
		// видаляємо з черги пошукову ноду з мінімальним пріоритетом
		Move bestMove = moves.delMin();
		if (bestMove.board.isGoal())
			return bestMove;
		// вставляємо в чергу всіх наступникыв видаленої
		// (ті, що можуть бути досягнуті за один крок з цієї ноди).
		for (Board neighbor : bestMove.board.neighbors()) {
			if (bestMove.previous == null || !neighbor.equals(bestMove.previous.board)) {
				moves.insert(new Move(neighbor, bestMove));
			}
		}
		return null;
	}

	/**
	 * @return чи має початкова дошка розв’язок
	 */
	public boolean isSolvable() {
		return (lastMove != null);
	}

	/**
	 * @return мінімальна кількість кроків для вирішення дошки,-1 якщо немає рішення
	 */
	public int moves() {
		if (isSolvable())
			return lastMove.count;
		return -1;
	}

	/**
	 * @return послідовність дошок в найкоротшому рішенні; null якщо немає рішення
	 */
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

	public static void main(String[] args) {
		// створюємо початкову дошку з файлу
		In in = new In("files/puzzle31.txt");
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
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
	}

}
