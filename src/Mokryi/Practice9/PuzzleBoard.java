public class PuzzleBoard {

    private final int[][] tiles;
    private final int N;

    public PuzzleBoard(int[][] blocks) {
        int nrows = blocks.length;
        int ncols = blocks[0].length;
        if (nrows != ncols)
            throw new IllegalArgumentException("passing non-square matrix");

        N = nrows;
        tiles = new int[N][N];
        for (int i = 0; i < blocks.length; i++)
            for (int j = 0; j < blocks[i].length; j++)
                tiles[i][j] = blocks[i][j];
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) continue;
                if (!inPlace(i, j, tiles[i][j])) {
                    ++count;
                }
            }
        return count;
    }

    private boolean inPlace(int i, int j, int val) {
        if (val == i * N + j + 1) {
            return true;
        }
        return false;
    }

    public int manhattan() {
        int count = 0;
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) continue;
                int remainder = tiles[i][j] % N;
                int posi, posj;
                if (remainder != 0) {
                    posi = tiles[i][j] / N;
                    posj = remainder - 1;
                } else {
                    posi = tiles[i][j] / N - 1;
                    posj = N - 1;
                }
                int diff = Math.abs(posi - i) + Math.abs(posj - j);
                count += diff;
            }
        return count;
    }

    public boolean isGoal() {
        return hamming() == 0;
    }

    public PuzzleBoard twin() {
        int[][] tw = new int[N][N];
        int zeroi = 0;
        int zeroj = 0;
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[i].length; j++) {
                tw[i][j] = tiles[i][j];
                if (0 == tiles[i][j]) {
                    zeroi = i;
                    zeroj = j;
                }
            }

        int ri = StdRandom.uniform(N);
        int rj = StdRandom.uniform(N);
        while (rj == N - 1 || ri == zeroi && rj == zeroj || ri == zeroi && rj + 1 == zeroj) {
            ri = StdRandom.uniform(N);
            rj = StdRandom.uniform(N);
        }
        int t = tw[ri][rj];
        tw[ri][rj] = tw[ri][rj + 1];
        tw[ri][rj + 1] = t;

        PuzzleBoard twined = new PuzzleBoard(tw);
        return twined;
    }

    public boolean equals(Object y) // чи ця дошка рівна y?
    {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        PuzzleBoard that = (PuzzleBoard) y;
        if (N != that.N) return false;
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[i].length; j++)
                if (tiles[i][j] != that.tiles[i][j])
                    return false;
        return true;
    }

    public Iterable<PuzzleBoard> neighbors() {
        int zeroi = 0;
        int zeroj = 0;
        int[][] dup = new int[N][N];
        for (int i = 0; i < tiles.length; i++)
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) {
                    zeroi = i;
                    zeroj = j;
                }
                dup[i][j] = tiles[i][j];
            }

        int stepi;
        int stepj;
        Queue<PuzzleBoard> q = new Queue<>();

        // up
        stepi = zeroi - 1;
        stepj = zeroj;
        if (valid(stepi, stepj)) {
            exch(dup, zeroi, zeroj, stepi, stepj);
            PuzzleBoard b = new PuzzleBoard(dup);
            q.enqueue(b);
            // recover
            exch(dup, zeroi, zeroj, stepi, stepj);
        }

        // down
        stepi = zeroi + 1;
        stepj = zeroj;
        if (valid(stepi, stepj)) {
            exch(dup, zeroi, zeroj, stepi, stepj);
            PuzzleBoard b = new PuzzleBoard(dup);
            q.enqueue(b);
            // recover
            exch(dup, zeroi, zeroj, stepi, stepj);
        }

        // left
        stepi = zeroi;
        stepj = zeroj - 1;
        if (valid(stepi, stepj)) {
            exch(dup, zeroi, zeroj, stepi, stepj);
            PuzzleBoard b = new PuzzleBoard(dup);
            q.enqueue(b);
            // recover
            exch(dup, zeroi, zeroj, stepi, stepj);
        }

        // right
        stepi = zeroi;
        stepj = zeroj + 1;
        if (valid(stepi, stepj)) {
            exch(dup, zeroi, zeroj, stepi, stepj);
            PuzzleBoard b = new PuzzleBoard(dup);
            q.enqueue(b);
            // recover
            exch(dup, zeroi, zeroj, stepi, stepj);
        }

        return q;
    }

    private boolean valid(int i, int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }

    private void exch(int[][] a, int i, int j, int m, int n) {
        int tmp = a[i][j];
        a[i][j] = a[m][n];
        a[m][n] = tmp;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
}