import java.util.ArrayList;

public class Board {
	private static final int EMPTY = 0;

	private int[][] blocks;

	// конструюЇмо дошку у вигл€д≥ двовим≥рного масиву N на N
	public Board(int[][] blocks) {
		this.blocks = copy(blocks);
	}

	// (blocks[i][j] =блок в р€ду i, колонц≥ j)
	public int dimension() {
		return blocks.length;
	}

	// к≥льк≥сть блок≥в не на своЇму м≥сц≥
	public int hamming() {
		int count = 0;
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				if (blockIsNotInPlace(row, col))
					count++;

		return count;
	}

	private boolean blockIsNotInPlace(int row, int col) {
		int block = blocks[row][col];
		return !isEmpty(block) && block != countGoal(row, col);
	}

	private boolean isEmpty(int block) {
		return block == EMPTY;
	}

	private int countGoal(int row, int col) {
		return row * dimension() + col + 1;
	}

	private int[][] copy(int[][] blocks) {
		int[][] copy = new int[blocks.length][blocks.length];
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				copy[row][col] = blocks[row][col];

		return copy;
	}

	public int manhattan() {
		int sum = 0;
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				sum += countDistances(row, col);

		return sum;
	}

	private int countDistances(int row, int col) {
		int block = blocks[row][col];
		return (isEmpty(block)) ? 0 : Math.abs(row - row(block)) + Math.abs(col - column(block));
	}

	private int row(int block) {
		return (block - 1) / dimension();
	}

	private int column(int block) {
		return (block - 1) % dimension();
	}

	// чи Ї ц€ дошка ц≥льовим станом
	public boolean isGoal() {
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				if (blockPlacedCorrectly(row, col))
					return false;

		return true;
	}

	private boolean blockPlacedCorrectly(int row, int col) {
		int block = blocks[row][col];
		return !isEmpty(block) && block != countGoal(row, col);
	}

	public Board boardCopy() {
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length - 1; col++)
				if (!isEmpty(blocks[row][col]) && !isEmpty(blocks[row][col + 1]))
					return new Board(swap(row, col, row, col + 1));
		return null;
	}

	private int[][] swap(int row1, int col1, int row2, int col2) {
		int[][] copy = copy(blocks);
		int tmp = copy[row1][col1];
		copy[row1][col1] = copy[row2][col2];
		copy[row2][col2] = tmp;

		return copy;
	}

	// чи ц€ дошка р≥вна y?
	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null || !(y instanceof Board) || ((Board) y).blocks.length != blocks.length)
			return false;
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				if (((Board) y).blocks[row][col] != blocks[row][col])
					return false;

		return true;
	}

	// вс≥ сусiдн≥ дошки
	public Iterable<Board> neighbors() {
		ArrayList<Board> neighbors = new ArrayList<Board>();
		int[] location = spaceLocation();
		int spaceRow = location[0];
		int spaceCol = location[1];

		if (spaceRow > 0)
			neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow - 1, spaceCol)));
		if (spaceRow < dimension() - 1)
			neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow + 1, spaceCol)));
		if (spaceCol > 0)
			neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol - 1)));
		if (spaceCol < dimension() - 1)
			neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol + 1)));

		return neighbors;
	}

	private int[] spaceLocation() {
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				if (isEmpty(blocks[row][col])) {
					int[] location = new int[2];
					location[0] = row;
					location[1] = col;
					return location;
				}
		return null;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++)
				str.append(blocks[row][col]).append(' ');
			str.append("\n");
		}
		return str.toString();
	}
}