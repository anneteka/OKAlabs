import java.util.Scanner;

public class Homework9 {
	public static void main(String[] args) {
		// create initial board from file
//		String name = "G:/NaUKMA/OKA/lection9/puzzle3x3-unsolvable.txt";
//		String name = "G:/NaUKMA/OKA/lection9/puzzle04.txt";
		String name = "G:/NaUKMA/OKA/lection9/puzzle31.txt";
		In in = new In(name);
		while (!in.isEmpty()) {
			int N = in.readInt();
			int[][] blocks = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					blocks[i][j] = in.readInt();

			Board initial = new Board(blocks);
			Solver solver = new Solver(initial);

			if (solver.isSolvable()) {
				StdOut.println("Minimum number of moves = " + solver.moves());
				for (Board board : solver.solution())
					StdOut.println(board);
			}

			else {
				StdOut.println("Unsolvable puzzle");
			}
		}
	}
}
