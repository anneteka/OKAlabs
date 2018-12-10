import java.util.Stack;

public class Board {
	int tiles[][];
	int n;

	public Board(int[][] blocks) {
		n = blocks.length;
		tiles = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				tiles[i][j] = blocks[i][j];
			}
		}
	} // construct a board from an n-by-n array of blocks
		// (where blocks[i][j] = block in row i, column j)

	public int dimension() {
		return n;
	} // board dimension n

	public int hamming() {
		int num = 0;
		int cur = 1;
		for (cur = 1; cur < n * n; cur++) {
			if (cur != tiles[(cur - 1) / n][(cur - 1) % n])
				num++;
		}
		return num;
	} // number of blocks out of place

	public int manhattan() {
		int num = 0;
		int cur = 1;
		for (cur = 1; cur < n * n + 1; cur++) {
			int iPos = (cur - 1) / n;
			int jPos = (cur - 1) % n;
			int block = tiles[iPos][jPos];
			if (block == 0)
				continue;
			num += Math.abs((iPos) - (block - 1) / n) + Math.abs((jPos) - (block - 1) % n);
		}
		return num;
	} // sum of Manhattan distances between blocks and goal

	public boolean isGoal() {
		return hamming() == 0;
	} // is this board the goal board?

	public Board twin() {
		Board twin = new Board(this.tiles);
		if (twin.tiles[0][0] == 0) {
			twin.tilesSwap(0, 1, 1, 1);
			// int temp = twin.tiles[0][1];
			// twin.tiles[0][1] = twin.tiles[1][1];
			// twin.tiles[1][1] = temp;
		} else if (twin.tiles[0][1] != 0) {
			twin.tilesSwap(0, 0, 0, 1);
			// int temp = twin.tiles[0][0];
			// twin.tiles[0][0] = twin.tiles[0][1];
			// twin.tiles[0][1] = temp;
		} else {
			twin.tilesSwap(0, 0, 1, 0);
			// int temp = twin.tiles[0][0];
			// twin.tiles[0][0] = twin.tiles[1][0];
			// twin.tiles[1][0] = temp;
		}
		return twin;
	} // a board that is obtained by exchanging any pair of blocks

	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null)
			return false;
		if (y.getClass() != this.getClass())
			return false;

		Board that = (Board) y;
		return this.n == that.n && tilesEquals(this.tiles, that.tiles);
	} // does this board equal y?

	private boolean tilesSwap(int i, int j, int it, int jt) {
		if (it < 0 || it >= n || jt < 0 || jt >= n) {
			return false;
		}
		int temp = tiles[i][j];
		tiles[i][j] = tiles[it][jt];
		tiles[it][jt] = temp;
		return true;
	}

	private boolean tilesEquals(int[][] tiles1, int[][] tiles2) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tiles1[i][j] != tiles2[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public Iterable<Board> neighbors() {
		int i0 = 0, j0 = 0;
		boolean found = false;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (tiles[i][j] == 0) {
					i0 = i;
					j0 = j;
					found = true;
					break;
				}
			}
			if (found) {
				break;
			}
		}

		Stack<Board> boards = new Stack<Board>();
		Board board = new Board(tiles);
		boolean isNeighbor = board.tilesSwap(i0, j0, i0 - 1, j0);
		if (isNeighbor) {
			boards.push(board);
		}
		board = new Board(tiles);
		isNeighbor = board.tilesSwap(i0, j0, i0, j0 - 1);
		if (isNeighbor) {
			boards.push(board);
		}
		board = new Board(tiles);
		isNeighbor = board.tilesSwap(i0, j0, i0 + 1, j0);
		if (isNeighbor) {
			boards.push(board);
		}
		board = new Board(tiles);
		isNeighbor = board.tilesSwap(i0, j0, i0, j0 + 1);
		if (isNeighbor) {
			boards.push(board);
		}

		return boards;
	} // all neighboring boards

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(n + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				s.append(String.format("%2d ", tiles[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}

	public static void main(String[] args) {
		int[][] bl = { { 1, 0, 3 }, { 4, 5, 6 }, { 7, 8, 2 } };
		Board a = new Board(bl);
		Board b = new Board(bl);
		System.out.println(a.hamming());
		System.out.println(a.manhattan());
		System.out.println(a.isGoal());
		System.out.println(a.twin());
		System.out.println(b.equals(a));
		Board c = b.twin();
		System.out.println(b.equals(c));
	}// unit tests (not graded)
}
