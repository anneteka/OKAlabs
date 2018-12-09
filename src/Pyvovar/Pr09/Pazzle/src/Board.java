import java.util.LinkedList;

/**
 * Дошка для гри в п'ятнашки
 * 
 * @author Пивовар Олена, 4 група
 *
 */
public class Board {

	private int[][] blocks;
	private static final int SPACE = 0; // одне вільне місце

	// конструюємо дошку у вигляді двовимірного масиву N на N ((blocks[i][j] =блок в
	// ряду i, колонці j))
	public Board(int[][] blocks) {
		this.blocks = copy(blocks);
	}

	private int[][] copy(int[][] blocks) {
		int[][] copy = new int[blocks.length][blocks.length];
		for (int i = 0; i < blocks.length; i++)
			for (int j = 0; j < blocks.length; j++)
				copy[i][j] = blocks[i][j];

		return copy;
	}

	// розмірність дошки N
	public int dimension() {
		return blocks.length;
	}

	// кількість блоків не на своєму місці
	public int hamming() {
		int count = 0;
		for (int i = 0; i < blocks.length; i++)
			for (int j = 0; j < blocks.length; j++)
				if (blockIsNotInPlace(i, j))
					count++;

		return count;
	}

	private boolean blockIsNotInPlace(int row, int col) {
		int block = blocks[row][col];

		return !isSpace(block) && block != goalPosition(row, col);
	}

	private boolean isSpace(int block) {
		return block == SPACE;
	}

	private int goalPosition(int row, int col) {
		return row * dimension() + col + 1;
	}

	// сума Манхатенських відстаней між блоками і цільовим станом
	public int manhattan() {
		int sum = 0;
		for (int i = 0; i < blocks.length; i++)
			for (int j = 0; j < blocks.length; j++)
				sum += distance(i, j);

		return sum;
	}

	private int distance(int row, int col) {
		int block = blocks[row][col];
		if (isSpace(block))
			return 0;
		return Math.abs(row - row(block)) + Math.abs(col - col(block));
	}

	private int col(int block) {
		return (block - 1) % dimension();
	}

	private int row(int block) {
		return (block - 1) / dimension();
	}

	// чи є ця дошка цільовим станом
	public boolean isGoal() {
		for (int i = 0; i < blocks.length; i++)
			for (int j = 0; j < blocks.length; j++)
				if (blockIsNotInPlace(i, j))
					return false;

		return true;
	}

	// чи ця дошка рівна y?
	public boolean equals(Object y) {
		if (this == y)
			return true;
		for (int i = 0; i < blocks.length; i++)
			for (int j = 0; j < blocks.length; j++)
				if (((Board) y).blocks[i][j] != blocks[i][j])
					return false;

		return true;
	}

	// всі сусдні дошки
	public Iterable<Board> neighbors() {
		LinkedList<Board> neighbors = new LinkedList<Board>();

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
				if (isSpace(blocks[row][col])) {
					int[] location = new int[2];
					location[0] = row;
					location[1] = col;

					return location;
				}
		return null;
	}

	private int[][] swap(int row1, int col1, int row2, int col2) {
		int[][] copy = copy(blocks);
		int tmp = copy[row1][col1];
		copy[row1][col1] = copy[row2][col2];
		copy[row2][col2] = tmp;

		return copy;
	}

	public Board twin() {
		for (int i = 0; i < blocks.length; i++)
			for (int j = 0; j < blocks.length - 1; j++)
				if (!isSpace(blocks[i][j]) && !isSpace(blocks[i][j + 1]))
					return new Board(swap(i, j, i, j + 1));
		return null;
	}

	// строкове подання
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(dimension() + "\n");
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks.length; j++)
				str.append(blocks[i][j] + " ");
			str.append("\n");
		}

		return str.toString();
	}

}
