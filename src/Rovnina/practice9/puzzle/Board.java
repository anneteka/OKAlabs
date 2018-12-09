package puzzle;

import java.util.LinkedList;

/**
 * Дошка
 * 
 * @author Rovnina Tetiana
 *
 */
public class Board {
	private static final int EMPTY = 0; // empty cell

	private int[][] blocks;

	/**
	 * конструюємо дошку у вигляді двовимірного масиву N на N (blocks[i][col] =блок
	 * в ряду i, колонці col)
	 * 
	 */
	public Board(int[][] blocks) {
		this.blocks = copy(blocks);
	}

	/**
	 * @return cтворюємо і повертаємо копію масиву пазлів
	 */
	private int[][] copy(int[][] blocks) {
		int[][] copy = new int[blocks.length][blocks.length];
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				copy[row][col] = blocks[row][col];
		return copy;
	}

	/**
	 * @return розмірність дошки N
	 */
	public int dimension() {
		return blocks.length;
	}

	/**
	 * @return кількість блоків не на своєму місці
	 */
	public int hamming() {
		int sum = 0;
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				if (blockInPlace(row, col))
					sum++;

		return sum;
	}

	/**
	 * @return сума Манхатенських відстаней між блоками і цільовим станом
	 */
	public int manhattan() {
		int sum = 0;
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				sum += distance(row, col);

		return sum;
	}

	/**
	 * @return выдстань від клітинки, в якій має знаходитись число до клітинки, в
	 *         якій воно знаходиться
	 */
	private int distance(int row, int col) {
		int block = blocks[row][col];
		if (isEmpty(block))
			return 0;

		return Math.abs(row - row(block)) + Math.abs(col - col(block));
	}

	/**
	 * @return колонка в якій має бути число block
	 */
	private int col(int block) {
		return (block - 1) % dimension();
	}

	/**
	 * @return рядок в якому має бути число block
	 */
	private int row(int block) {
		return (block - 1) / dimension();
	}

	/**
	 * @return чи є ця дошка цільовим станом
	 */
	public boolean isGoal() {
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				if (blockInPlace(row, col))
					return false;

		return true;
	}

	/**
	 * @return чи знаходиться блок на своєму місці
	 */
	private boolean blockInPlace(int row, int col) {
		int block = blocks[row][col];
		return !isEmpty(block) && block != getNumber(row, col);
	}

	/**
	 * @return чи це пуста клітинка
	 */
	private boolean isEmpty(int block) {
		return block == EMPTY;
	}

	/**
	 * @return число яке має бути в рядку row та колонці col
	 */
	private int getNumber(int row, int col) {
		return row * dimension() + col + 1;
	}

	/*
	 * чи ця дошка рівна y?
	 */
	public boolean equals(Object y) {
		if (y == this)
			return true;

		if (y == null || !(y instanceof Board) || ((Board) y).blocks.length != blocks.length)
			return false;

		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++) {
				if (((Board) y).blocks[row][col] != blocks[row][col])
					return false;
			}
		}

		return true;
	}

	/**
	 * @return список результатва можливих ходів, як об'єктів Board у вигляді
	 *         об'єкту типу Iterable
	 */
	public Iterable<Board> neighbors() {
		LinkedList<Board> neighbors = new LinkedList<Board>();
		// визначаємо координати пустої клітинки
		int[] location = emptyLocation();
		int spaceRow = location[0];
		int spaceCol = location[1];

		if (spaceRow > 0)// Якшо над пустою є паззл
			neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow - 1, spaceCol)));
		if (spaceRow < dimension() - 1)// якщо під пустою є пазл
			neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow + 1, spaceCol)));
		if (spaceCol > 0)// якщо ліворуч є паззл
			neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol - 1)));
		if (spaceCol < dimension() - 1) // якщо праворуч є паззл
			neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol + 1)));

		return neighbors;
	}

	/**
	 * @return координати пустої клітинки
	 */
	private int[] emptyLocation() {
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length; col++)
				if (isEmpty(blocks[row][col])) {
					int[] location = new int[2];
					location[0] = row;
					location[1] = col;

					return location;
				}
		throw new RuntimeException();
	}

	/**
	 * Створюємо новий Board, де у якомусь рядочку сусідні непусті клітинки
	 * обмінялися пазлами. Для контролю
	 */
	public Board twin() {
		for (int row = 0; row < blocks.length; row++)
			for (int col = 0; col < blocks.length - 1; col++)
				if (!isEmpty(blocks[row][col]) && !isEmpty(blocks[row][col + 1]))
					return new Board(swap(row, col, row, col + 1));
		throw new RuntimeException();
	}

	/**
	 * @return масив, де два пазли обмінялися місцями
	 */
	private int[][] swap(int row1, int col1, int row2, int col2) {
		// беремо копию існуючого
		int[][] copy = copy(blocks);
		// міняємо
		int tmp = copy[row1][col1];
		copy[row1][col1] = copy[row2][col2];
		copy[row2][col2] = tmp;

		return copy;
	}

	public String toString() {
		String string = "";
		for (int row = 0; row < blocks.length; row++) {
			for (int col = 0; col < blocks.length; col++)
				string += " " + blocks[row][col];
			string += "\n";
		}

		return string;
	}
}
