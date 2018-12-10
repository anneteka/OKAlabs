public class Percolation {
	private Cell[] matrix;
	private int size;
	private int n;
	private int count;

	public Percolation(int n) {
		matrix = new Cell[n * n + 2];
		size = n * n;
		this.n = n;
		count = 0;
		for (int i = 0; i < size; i++) {
			matrix[i] = new Cell(false, i);
		}
		matrix[size] = new Cell(true, size);
		matrix[size + 1] = new Cell(true, size + 1);
		for (int i = 0; i < n; i++) {
			union(i, size);
			union(size - n + i, size + 1);
		}

	}

	public int getOpenedCount() {
		return count;
	}

	public void union(int p, int q) {
		int pid = matrix[p].number;
		int qid = matrix[q].number;
		if (pid == qid)
			return;
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i].number == pid)
				matrix[i].number = qid;
		}
	}

	public void open(int i) {
		if (!matrix[i].open) {
			matrix[i].open = true;
			count++;
			if (i - 1 >= 0 && i - 1 < size && (i % n != 0) && matrix[i - 1].open)
				union(i, i - 1);
			if (i + 1 >= 0 && i + 1 < size && (i % n != n - 1) && matrix[i + 1].open)
				union(i, i + 1);
			if (i - n >= 0 && i - n < size && matrix[i - n].open)
				union(i, i - n);
			if (i + n >= 0 && i + n < size && matrix[i + n].open)
				union(i, i + n);

/*			for (int j = 0; j < n; j++) {
				for (int j2 = 0; j2 < n; j2++) {
					StdOut.print("[");
					if (matrix[j * n + j2].open)
						StdOut.print("o");
					else
						StdOut.print("x");
					StdOut.print("|" + matrix[j * n + j2].number + "]");
				}
				StdOut.println("");

			}
			StdOut.println("");
*/
		}

	}

	public boolean isOpened(int i) {

		return matrix[i].open;

	}

	public boolean percolates() {
		if (matrix[size].number == matrix[size + 1].number) {
			// StdOut.println("Percolation achieved.");
			return true;
		} else
			return false;
	}
}
