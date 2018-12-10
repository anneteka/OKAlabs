import java.util.Arrays;
import java.util.Stack;

public class Board {
    private final int N;
    private final int[][] tiles;

    public Board(int[][] blocks) {
        N = blocks.length;
        tiles = copyBoard(blocks, N);
    }

    private int[][] copyBoard(int[][] y, int M) {
        int[][] result = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                result[i][j] = y[i][j];
            }
        }
        return result;
    }

    private void changeBlocks(int[][] x, int i, int j, int m, int n) {
        int swap;
        swap = x[i][j];
        x[i][j] = x[m][n];
        x[m][n] = swap;
    }



    public boolean equals(Object y) {
        Board that = (Board) y;
        return (this.N == that.N) && (Arrays.deepEquals(this.tiles, that.tiles));
    }

    private void initBoard(Stack<Board> s, int x, int y, int m, int n) {
        int[][] neighbor = copyBoard(tiles, N);
        changeBlocks(neighbor, x, y, m, n);
        s.push(new Board(neighbor));
    }

    public Iterable<Board> neighbors() {
        Stack<Board> boards = new Stack<Board>();
        boolean ifNull = false;
        for (int i = 0; i < N && !ifNull; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    if (i > 0) initBoard(boards, i - 1, j, i, j);
                    if (j > 0) initBoard(boards, i, j - 1, i, j);
                    if (i < N - 1) initBoard(boards, i + 1, j, i, j);
                    if (j < N - 1) initBoard(boards, i, j + 1, i, j);
                    ifNull = true;
                    break;
                }
            }
        }
        return boards;
    }

    public Board restore() {
        int[][] aTwin = copyBoard(tiles, N);
        if (aTwin[0][0] * aTwin[0][1] == 0) {
            changeBlocks(aTwin, 1, 0, 1, 1);
        } else {
            changeBlocks(aTwin, 0, 0, 0, 1);
        }
        return new Board(aTwin);
    }

    private int sM(int i, int j) {
        int iLoc = tiles[i][j] / N;
        int jLoc = tiles[i][j] % N;
        if (jLoc != 0) {
            jLoc -= 1;

        } else {
            iLoc -= 1;
            jLoc = N - 1;
        }
        return Math.abs(i - iLoc) + Math.abs(j - jLoc);
    }

    public int mtn() {
        int distOfman = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != j + i * N  + 1 && tiles[i][j] != 0) {
                    distOfman += sM(i, j);
                }
            }
        }
        return distOfman;
    }

    public boolean isNeeded() {
        return mtn() == 0;
    }

    public String toString() {
        String s = "";
        s = s+"Розмір: "+N + "\n";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s = s + "   "+tiles[i][j];
            }
            s = s+"\n";
        }
        return s;
    }


}