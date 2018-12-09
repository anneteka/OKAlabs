
public class Percolation {
	
	Cell[] matrix;
	int[] sz;
	private int openedCells;
	int size;
	public Percolation(int n) {
		size = n;
		matrix = new Cell[n * n + 2];
		openedCells = 0;
		sz = new int[n * n + 2];
		for(int i = 0; i <= n * n + 1; i++) {
			matrix[i] = new Cell(i);
			sz[i] = 1;
		}
	}
	
	public int getOpenedCount() {
		return openedCells;
	}
	
	public void open(int i, int j) {
		int n = size * i + j;
		if (!isOpened(i, j)) {
			matrix[n].isOpen = true;
			openedCells++;
		}
		if (i == 0)
			union(size * size, n);
		else if (i == size - 1)
			union(size * size + 1, n);

		if (i > 0 && isOpened(i - 1, j))
			union(n - size, n);
		if (i < size - 1 && isOpened(i + 1, j))
			union(n + size, n);
		if (j > 0 && isOpened(i, j - 1))
			union(n - 1, n);
		if (j < size - 1 && isOpened(i, j + 1))
			union(n + 1, n);
	}

	private int find(int i){
		while (i != matrix[i].number)
			i = matrix[i].number;
		return i;
	}
	
	public void union(int p, int q){
		int i = find(p);
		int j = find(q);
		if (i==j) return;
		if (sz[i]<sz[j]){
			matrix[i].number = j;
			sz[j] +=sz[i];
		}else{
			matrix[j].number = i;
			sz[i] += sz[j]; 
		}
	}
	
	public boolean connected(int p, int q){
		return find(p) == find(q);
	}
	
	public boolean isOpened(int i, int j) {
		return matrix[i * size + j].isOpen;
	}
	
	public boolean percolates() {
		return connected(size * size, size * size + 1);
	}
}
