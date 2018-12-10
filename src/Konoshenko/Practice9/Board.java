import java.util.Arrays;
import java.util.Stack;

public class Board {
	private short[] blocks;
	private int size;
	private int zeroR;
	private int zeroC;

	private int man = -1;
	private int ham = -1;

	private String out = "";
	public Board(int[][] blocks) {
		size = blocks.length;

		this.blocks = new short[size * size];
		for (int i = 0; i < blocks.length; i++) {
			int[] row = blocks[i];
			for (int j = 0; j < row.length; j++) {
				this.blocks[size * i + j] = (short) blocks[i][j];

				// find out 0 element position
				if (blocks[i][j] == 0) {
					zeroR = i;
					zeroC = j;
				}
			}
		}
	}

	private Board(short[] blocks, int size, int zr, int zc) {
		this.blocks = Arrays.copyOf(blocks, blocks.length);
		this.size = size;
		this.zeroR = zr;
		this.zeroC = zc;
	}

	public int dimension() {
		return size;
	}

	public int hamming() {

		if (ham != -1) {
			return ham;
		}
		ham = 0;
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] == 0) {
				continue;
			}
			if (blocks[i] != (i + 1)) {
				ham++;
			}
		}
		return ham;
	}
	public int manhattan() {

		if (man != -1) {
			return man;
		}

		man = 0;
		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i] == 0) {
				continue;
			}
			int row = Math.abs((i) % size - (blocks[i] - 1) % size);
			int col = Math.abs((i) / size - (blocks[i] - 1) / size);

			man += row + col;
		}
		return man;
	}
	public boolean isGoal() {
		return hamming() == 0;
	}

	public Board twin() {
		boolean success = false;
		int[][] twinBlocks = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				twinBlocks[i][j] = blocks[i * size + j];
			}
		}

		while (!success) {
			int row = StdRandom.uniform(size);
			int col = StdRandom.uniform(size - 1);

			if (size == 2) {
				col = 1;
			}

			if (col < 1 || twinBlocks[row][col] == 0 || twinBlocks[row][col - 1] == 0) {
				continue;
			}

			int temp = twinBlocks[row][col];
			twinBlocks[row][col] = twinBlocks[row][col - 1];
			twinBlocks[row][col - 1] = temp;

			success = true;
		}

		return new Board(twinBlocks);
	}

	public boolean equals(Object y) {

		if (y == this)
			return true;
		if (y == null)
			return false;
		if (y.getClass() != this.getClass())
			return false;

		Board that = (Board) y;

		if (that.size != this.size) {
			return false;
		}

		return Arrays.equals(blocks, that.blocks);
	}

	public Iterable<Board> neighbors() {
		Stack<Board> boards = new Stack<Board>();

		if (zeroR != 0) {
			swap(this.blocks, zeroR - 1, zeroC, zeroR, zeroC);
			boards.push(new Board(this.blocks, this.size, zeroR - 1, zeroC));
			swap(this.blocks, zeroR - 1, zeroC, zeroR, zeroC);
		}

		if (zeroR < size - 1) {
			swap(this.blocks, zeroR + 1, zeroC, zeroR, zeroC);
			boards.push(new Board(this.blocks, this.size, zeroR + 1, zeroC));
			swap(this.blocks, zeroR + 1, zeroC, zeroR, zeroC);
		}

		if (zeroC != 0) {
			swap(this.blocks, zeroR, zeroC - 1, zeroR, zeroC);
			boards.push(new Board(this.blocks, this.size, zeroR, zeroC - 1));
			swap(this.blocks, zeroR, zeroC - 1, zeroR, zeroC);
		}

		if (zeroC < size - 1) {
			swap(this.blocks, zeroR, zeroC + 1, zeroR, zeroC);
			boards.push(new Board(this.blocks, this.size, zeroR, zeroC + 1));
			swap(this.blocks, zeroR, zeroC + 1, zeroR, zeroC);
		}

		return boards;
	}

	private void swap(short[] arr, int fromRow, int fromCol, int toRow, int toCol) {
		short temp = arr[fromRow * size + fromCol];
		arr[fromRow * size + fromCol] = arr[toRow * size + toCol];
		arr[toRow * size + toCol] = temp;
	}

	public String toString() {
		if (out.length() > 0) {
			return out;
		}

		StringBuilder s = new StringBuilder();
		s.append(size + "\n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				s.append(String.format("%2d ", blocks[size * i + j]));
			}
			s.append("\n");
		}

		out = s.toString();
		return out;
	}
}