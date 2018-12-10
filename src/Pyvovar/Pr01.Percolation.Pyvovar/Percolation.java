import java.util.Random;

/**
 * Визначення структури даних Percolation
 * 
 * @author Пивовар Олена, 4 група
 *
 */
public class Percolation {

	private int[][] matr;
	private boolean[][] matrOpen;
	private int openedCount;
	private int N;
	private int top;
	private int buttom;

	/**
	 * конструктор класу
	 * 
	 * @param N розмір матриці
	 */
	public Percolation(int N) { // створюємо матрицю
		matr = new int[N][N];
		matrOpen = new boolean[N][N];
		this.N = N;
		openedCount = 0;
		top = -1;
		buttom = N;

		int n = 0;
		for (int i = 0; i < N; i++) { // заповнюємо матрицю
			for (int j = 0; j < N; j++) {
				matr[i][j] = n;
				n++;
				matrOpen[i][j] = false;
			}
		}
	}

	/**
	 * рахує і повертає кількість відкритих комірок
	 * 
	 * @return кількість відкритих комірок
	 */
	public int getOpenedCount() {
		return openedCount;
	}

	/**
	 * відкриває об'єкт, якщо він ще не відкритий
	 * 
	 * @param i рядок об'єкта
	 * @param j стовпець об'єкта
	 */
	public void open(int i, int j) {
		if (matrOpen[i][j] == false) {
			matrOpen[i][j] = true;
			openedCount++;
			if (i == 0)
				top = matr[i][j]; // з'єднання першого рядка з верхом
			if (i == N - 1)
				buttom = matr[i][j]; // з'єднання останнього рядка з низом
			checkCloseOpen(i, j);
		}
	}

	/**
	 * перевірка, чи знаходяться поряд відкриті об'єкти
	 * 
	 * @param i рядок об'єкта
	 * @param j стовпець об'єкта
	 */
	private void checkCloseOpen(int i, int j) {
		if (j > 0) // перевірка зліва
			if (isOpened(i, j - 1))
				connected(i, j, i, j - 1);
		if (j < N - 1) // перевірка справа
			if (isOpened(i, j + 1))
				connected(i, j, i, j + 1);
		if (i > 0) // перевірка зверху
			if (isOpened(i - 1, j))
				connected(i, j, i - 1, j);
		if (i < N - 1) // перевірка знизу
			if (isOpened(i + 1, j))
				connected(i, j, i + 1, j);
	}

	/**
	 * об'єднання декількох об'єктів
	 * 
	 * @param i1 рядок об'єкта, який щойно відкрили
	 * @param j1 стовпець об'єкта, який щойно відкрили
	 * @param i2 рядок об'єкта, з яким з'єднуємо
	 * @param j2 стовпець об'єкта, з який з'єднуємо
	 */
	private void connected(int i1, int j1, int i2, int j2) {
		int n = matr[i2][j2]; // збереження старого значення
		if (top == n)
			top = matr[i1][j1];
		if (buttom == n)
			buttom = matr[i1][j1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (matr[i][j] == n) {
					matr[i][j] = matr[i1][j1];
					if (i == 0)
						top = matr[i1][j1];
					if (i == N - 1)
						buttom = matr[i1][j1];
				}
			}
		}
	}

	/**
	 * @param i рядок об'єкта
	 * @param j стовпець об'єкта
	 * @return чи відкритий об'єкт
	 */
	public boolean isOpened(int i, int j) {
		return matrOpen[i][j];
	}

	/**
	 * @return чи протікає система
	 */
	public boolean percolates() {
		return top == buttom;
	}

}
