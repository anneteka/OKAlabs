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

    public int dimension() {
        return N;
    }

    public int hamming() {
        int differentBlocks = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != j + i * N  + 1) {
                    differentBlocks++;
                }
            }
        }
        return --differentBlocks;
    }

    //paths for each tile to be moved
    private int singleMan(int i, int j) {
        int goalI = tiles[i][j] / N;
        int goalJ = tiles[i][j] % N;
        if (goalJ == 0) {
            goalI -= 1;
            goalJ = N - 1;
        } else {
            goalJ -= 1;
        }
        return Math.abs(i - goalI) + Math.abs(j - goalJ);
    }

    public int manhattan() {
        int manDist = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] != j + i * N  + 1 && tiles[i][j] != 0) {
                    manDist += singleMan(i, j);
                }
            }
        }
        return manDist;
    }

    public boolean isGoal() {
        return manhattan() == 0;
    }

    private void swapBlocks(int[][] x, int i, int j, int m, int n) {
        int swap;
        swap = x[i][j];
        x[i][j] = x[m][n];
        x[m][n] = swap;
    }

    public Board createSameBoard() {
        int[][] aTwin = copyBoard(tiles, N);
        if (aTwin[0][0] * aTwin[0][1] == 0) {
            swapBlocks(aTwin, 1, 0, 1, 1);
        } else {
            swapBlocks(aTwin, 0, 0, 0, 1);
        }
        return new Board(aTwin);
    }

    public boolean equals(Object y) {
        Board that = (Board) y;
        return (this.N == that.N) && (Arrays.deepEquals(this.tiles, that.tiles));
    }

    private void pushBoard(Stack<Board> s, int x, int y, int m, int n) {
        int[][] neighbor = copyBoard(tiles, N);
        swapBlocks(neighbor, x, y, m, n);
        s.push(new Board(neighbor));
    }

    public Iterable<Board> neighbors() {
        Stack<Board> boards = new Stack<Board>();
        boolean nullBoard = false;
        for (int i = 0; i < N && !nullBoard; i++) {
            for (int j = 0; j < N; j++) {
                if (tiles[i][j] == 0) {
                    if (i > 0) pushBoard(boards, i - 1, j, i, j);
                    if (j > 0) pushBoard(boards, i, j - 1, i, j);
                    if (i < N - 1) pushBoard(boards, i + 1, j, i, j);
                    if (j < N - 1) pushBoard(boards, i, j + 1, i, j);
                    nullBoard = true;
                    break;
                }
            }
        }
        return boards;
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
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