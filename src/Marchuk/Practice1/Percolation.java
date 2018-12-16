package Practice1;

public class Percolation {
	private boolean matrix [][];
	private QuickFindUF qf;
	private int openedCounter;
	private int N;
	public Percolation(int a) {
	// створюємо матрицю N-на-N, з усіма заблокованими об’єктами 
		N = a;
		matrix = new boolean[N][N];
		qf = new QuickFindUF((N*N)+2);
		for(int i=0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				matrix[i][j] = false;
			}
		}
	}
	public int getOpenedCount() {
	//рахуємо і повертаємо кількість відкритих комірок
		return openedCounter;
	}
	public void open(int i, int j) {
	// відкрити об’єкт (row i, column j) якщо він ще не відкритий 
		int r = i - 1;
		int c = j - 1;
		
		matrix[r][c] = true;
		
		if(r == 0) {
			qf.union(0, numberOfCell(r, c));
		}
		if(r == N-1) {
			qf.union((N*N)+1, numberOfCell(r, c));
		}
		openedCounter++;
		  if((r+1) < N) {
			   if(matrix[r+1][c] == true)
			    qf.union((numberOfCell(r, c)), (numberOfCell(r+1, c)));
			  }
			  if((r-1) >= 0) {
			   if(matrix[r-1][c]==true)
			    qf.union((numberOfCell(r, c)), (numberOfCell(r-1, c)));
			  }
			  if((c+1) < N) {
			   if(matrix[r][c+1]==true)
			    qf.union((numberOfCell(r, c)), (numberOfCell(r, c+1)));
			  }
			  if((c-1) >= 0) {
			   if(matrix[r][c-1]==true)
			    qf.union((numberOfCell(r, c)), (numberOfCell(r, c-1)));
			  }
	}
	public boolean isOpened(int i, int j) {
	// чи відкитий об’єкт (row i, column j)? 
		if(matrix[i-1][j-1] == true) {
			return true;
		} else {
			return false;
		}
	}
	public boolean percolates() {
	// чи протікає система? 
		return qf.connected(0, (N*N)+1);
	}
	
	private int numberOfCell(int i, int j) {
		return((i*N)+j)+1;
	}
	
	int getN() {
		return N;
	}
	
	QuickFindUF getQuickFindUF() {
		return qf;
	}
	
}
