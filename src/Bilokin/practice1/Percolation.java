public class Percolation {

	private int[] states;
	private int side;
	private WeightedQuickUnionUF cellStorage;

	public Percolation(int N) {
		side = N;

		cellStorage = new WeightedQuickUnionUF(N * N + 2);
		states = new int[N * N + 2];
		for (int index = 0; index < N * N; index++) {
			states[index] = 0;
		}
		states[N * N] = 1;
		states[N * N + 1] = 1;
	}

	public void open(int i, int j) {
		checkRange(i, j);
		if (isOpen(i, j))
			return;
		int cell = getCellIndex(i, j);
		states[cell] = 1;

		if (i != 1 && isOpen(i - 1, j)) {
			union(getCellIndex(i - 1, j), cell);
		} else if (i == 1) {
			// connect to virtual top cell
			union(cell, side * side);
		}

		if (i != side && isOpen(i + 1, j)) {
			union(getCellIndex(i + 1, j), cell);
		} else if (i == side) {
			// connect to virtual bottom cell
			union(cell, side * side + 1);
		}

		if (j != 1 && isOpen(i, j - 1)) {
			union(getCellIndex(i, j - 1), cell);
		}

		if (j != side && isOpen(i, j + 1)) {
			union(getCellIndex(i, j + 1), cell);
		}
	}

	private void checkRange(int i, int j) {
		if (i <= 0 || j <= 0 || i > side || j > side)
			throw new IndexOutOfBoundsException();
	}

	private void union(int a, int b) {
		if (!cellStorage.connected(a, b)) {
			cellStorage.union(a, b);
		}
	}

	public boolean isOpen(int i, int j) {
		checkRange(i, j);
		return states[getCellIndex(i, j)] == 1;
	}

	public boolean isFull(int i, int j) {
		checkRange(i, j);
		return cellStorage.connected(side * side, getCellIndex(i, j));
	}

	public boolean percolates() {
		return cellStorage.connected(side * side, side * side + 1);
	}

	private int getCellIndex(int row, int column) {
		return (side * (row - 1)) + column - 1;
	}

}
