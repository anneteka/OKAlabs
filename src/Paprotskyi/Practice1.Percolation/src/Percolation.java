
public class Percolation {

    public Cell[][] mCells;
    private int N;//rozmirnist
    public QuickFindUF qf;
    private Cell top;
    private Cell bottom;

    public Percolation(int N) {
        this.N = N;
        // створюємо матрицю N-на-N, з усіма заблокованими об’єктами
        mCells = new Cell[N][N];
        for (int o =0;o<N; o++){
            for (int m=0;m<N; m++){
                mCells[o][m] = new Cell();
            }
        }


        //algorytm
        qf = new QuickFindUF(N * N + 2);

        //n^2 v algorytmi
        top = new Cell();
        top.cellIsOpened = true;

        //n^2 + 1 v algorytmi
        bottom = new Cell();
        bottom.cellIsOpened = true;

        //union .. with ...
        for (int p = 0; p < N; p++) {
            // TOP with first row
            qf.union(N * N, p);
            // BOTTOM with last row
            qf.union(N * N + 1, N * N - N + p);
        }


    }

    public double getOpenedCount() {
        //рахуємо і повертаємо кількість відкритих комірок
        double openedCellsCount;
        openedCellsCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (mCells[i][j].cellIsOpened)
                    openedCellsCount++;
            }
        }
        return openedCellsCount;
    }

    public void open(int i, int j) {
        // відкрити об’єкт (row i, column j) якщо він ще не відкритий
        if (!mCells[i][j].cellIsOpened) {
            mCells[i][j].cellIsOpened = true;

            // не перший і не останній ряд
            if (i > 0 && i < N-1) {
                //зверху
                if (isOpened(i - 1, j))
                    qf.union(i * N + j, (i - 1) * N + j);
                //знизу
                if (isOpened(i + 1, j))
                    qf.union(i * N + j, (i + 1) * N + j);
            }else if (i == 0){
                //знизу
                if (isOpened(i + 1, j))
                    qf.union(i * N + j, (i + 1) * N + j);
            }else if (i == N-1){
                //зверху
                if (isOpened(i - 1, j))
                    qf.union(i * N + j, (i - 1) * N + j);

            }

            // не перша і не остання колонка
            if (j > 0 && j < N-1) {
                //зліва
                if (isOpened(i, j - 1))
                    qf.union(i * N + j, i * N + (j - 1));
                //справа
                if (isOpened(i, j + 1))
                    qf.union(i * N + j, i * N + (j + 1));
            }
            else if(j == 0){
                //справа
                if (isOpened(i, j + 1))
                    qf.union(i * N + j, i * N + (j + 1));
            }else if (j==N-1){
                //зліва
                if (isOpened(i, j - 1))
                    qf.union(i * N + j, i * N + (j - 1));
            }

        }

    }

    public boolean isOpened(int i, int j) {
        if (mCells[i][j].cellIsOpened)
            return true;
        return false;
    }

    public boolean percolates() {
        // чи протікає система
        if (qf.connected(N * N, N * N + 1))
            return true;
    return false;
    }
}

