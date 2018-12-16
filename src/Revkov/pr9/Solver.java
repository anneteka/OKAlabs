package A;

import java.util.Stack;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

public class Solver {

	private final Stack<Board> boards;
	private int moves;
	private boolean isSolvable;

	private class SearchNode implements Comparable<SearchNode> {
		private Board board;
		private int moves;
		private SearchNode previous;
		private int priority = 0;

		SearchNode(Board board, int moves, SearchNode previous) {
			this.board = board;
			this.moves = moves;
			this.previous = previous;
		}

		private int priority() {
			if (priority == 0) {
				priority = moves + board.manhattan();
			}
			return priority;
		}

		@Override
		public int compareTo(SearchNode that) {
			if (this.priority() < that.priority()) {
				return -1;
			}
			if (this.priority() > that.priority()) {
				return +1;
			}
			return 0;
		}
	}

	
	public Solver(Board initial) {
		boards = new Stack<Board>();
		if (initial.isGoal()) {
			isSolvable = true;
			this.boards.push(initial);
			return;
		}
		if (initial.twin().isGoal()) {
			isSolvable = false;
			return;
		}

		MinPQ<SearchNode> minPQ = new MinPQ<SearchNode>();
		MinPQ<SearchNode> minPQTwin = new MinPQ<SearchNode>();
		moves = 0;
		Board board = initial;
		Board boardTwin = initial.twin();
		SearchNode node = new SearchNode(board, 0, null);
		SearchNode nodeTwin = new SearchNode(boardTwin, 0, null);
		minPQ.insert(node);
		minPQTwin.insert(nodeTwin);
		while (moves < 100) {
			node = minPQ.delMin();
			nodeTwin = minPQTwin.delMin();
			board = node.board;
			boardTwin = nodeTwin.board;
			if (boardTwin.isGoal()) {
				isSolvable = false;
				return;
			}
			if (board.isGoal()) {
				isSolvable = true;
				this.boards.push(board);
				while (node.previous != null) {
					node = node.previous;
					this.boards.push(node.board);
				}
				return;
			}
			node.moves++;
			nodeTwin.moves++;
			Iterable<Board> neighbors = board.neighbors();
			for (Board neighbor : neighbors) {
				if (node.previous != null
						&& neighbor.equals(node.previous.board)) {
					continue;
				}
				SearchNode newNode = new SearchNode(neighbor, node.moves, node);
				minPQ.insert(newNode);
			}
			Iterable<Board> neighborsTwin = boardTwin.neighbors();
			for (Board neighbor : neighborsTwin) {
				if (nodeTwin.previous != null
						&& neighbor.equals(nodeTwin.previous.board)) {
					continue;
				}
				SearchNode newNode = new SearchNode(neighbor, nodeTwin.moves,
						nodeTwin);
				minPQTwin.insert(newNode);
			}
		}
	}

	
	public boolean isSolvable() {
		return isSolvable;
	}

	
	public int moves() {
		if (isSolvable) {
			return boards.size() - 1;
		} else {
			return -1;
		}
	}

	
	public Iterable<Board> solution() {
		if (isSolvable) {
			return boards;
		} else {
			return null;
		}
	}

	
	public static void main(String[] args) {
		In in = new In("puzzle04.txt");
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				blocks[i][j] = in.readInt();
		Board initial = new Board(blocks);

		Solver solver = new Solver(initial);

		if (!solver.isSolvable())
			StdOut.println("No solution possible");
		else {
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
				StdOut.println(board);
		}
	}

}
