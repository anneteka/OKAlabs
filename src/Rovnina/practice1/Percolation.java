/**
 * клас, що створює матрицю і досліджує її на протікання
 * 
 * @author Rovnina Tetiana
 *
 */
public class Percolation {
	private int[][] matrix;
	private int n;
	private QuickFindUF uf;
	private int opened = 0;

	/**
	 * створюємо матрицю N*N, з усіма заблокованими об’єктами
	 */
	public Percolation(int n) {
		this.n = n;
		matrix = new int[n][n];
		// for (int i = 0; i < n; i++) {
		// for (int j = 0; j < n; j++) {
		// matrix[i][j] = 0;
		// }
		// }
		// createImagineMatrix();
		uf = new QuickFindUF(n * n + 2);
	}

	/**
	 * створюємо уявну матрицю для алгоритма Union-Find
	 */
	private void createImagineMatrix() {
		uf = new QuickFindUF(n * n + 2);
		for (int k = 0; k < n; k++) {
			uf.union(getIndex(0, k), 0);// з'єднуємо верхній рядок з уявною коміркою top
			uf.union(getIndex(n - 1, k), n * n + 1);// нижній з уявною коміркою bottom
		}

	}

	/**
	 * метод, що повертає індекс комірки (i,j)
	 */
	private int getIndex(int i, int j) {
		return i * n + j + 1;
	}

	/**
	 * @return кількість відкритих комірок
	 */
	public int getOpenedCount() {
		return opened;
	}

	/**
	 * відкрити об’єкт (row i, column j), якщо він ще не відкритий
	 * 
	 */
	public void open(int i, int j) {
		if (isOpened(i, j))
			return;

		matrix[i][j] = 1;
		opened++;
		conectCells(i, j);
	}

	/**
	 * з'єднуємо відкриту комірку(i,j) з сусідніми
	 */
	private void conectCells(int i, int j) {
		int x = getIndex(i, j); // індекс клітинки, яку відкрили

		// з'єднуємо верхній рядок або нижній з уявною коміркою top або bottom
		if (i == 0)
			uf.union(x, 0);
		else if (i == (n - 1))
			uf.union(x, n * n + 1);

		if (i > 0 && matrix[i - 1][j] == 1)
			uf.union(x, getIndex(i - 1, j));
		if (i < (n - 1) && matrix[i + 1][j] == 1)
			uf.union(x, getIndex(i + 1, j));
		if (j > 0 && matrix[i][j - 1] == 1)
			uf.union(x, getIndex(i, j - 1));
		if (j < (n - 1) && matrix[i][j + 1] == 1)
			uf.union(x, getIndex(i, j + 1));
	}

	/**
	 * @return чи відкитий об’єкт (row i, column j)?
	 */
	public boolean isOpened(int i, int j) {
		return matrix[i][j] == 1;
	}

	/**
	 * @return чи протікає матриця?
	 */
	public boolean percolates() {
		return uf.connected(0, n * n + 1);
	}

}
