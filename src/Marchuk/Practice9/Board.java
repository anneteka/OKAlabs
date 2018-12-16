package Practice9;

import Homework3.LinkedQueue;

public class Board {

    private int N;
    private int[][] board;

    private int emptyRow;
    private int emptyCol;

    public Board(int[][] blocks) {
        N = blocks.length;
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    emptyRow = i;
                    emptyCol = j;
                }
                board[i][j] = blocks[i][j];
            }
        }
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        int block;
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                block = board[i][j];
                if (block != 0)
                    total += hammingDistance(block, i, j);
            }
        }
        return total;
    }

    public int manhattan() {
        int block;
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                block = board[i][j];
                if (block != 0)
                    total += manhattanDistance(block, i, j);
            }
        }
        return total;
    }

    public boolean isGoal() {
        return this.hamming() == 0;
    }

    public Board twin() {
        int tmp;
        Board twinBoard = new Board(this.copy());
        if (board[0][0] == 0 || board[0][1] == 0) {
            twinBoard.swap(1, 0, 1, 1);
        }   
        else {
            twinBoard.swap(0, 0, 0, 1);
        }
        return twinBoard;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (that.N != this.N) return false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (that.board[i][j] != this.board[i][j])
                    return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        LinkedQueue<Board> q = new LinkedQueue<Board>();
        Board copy;
        if (emptyRow > 0) {
            copy = new Board(this.copy());
            copy.swap(emptyRow, emptyCol, emptyRow-1, emptyCol);
            q.enqueue(copy);
        }
        if (emptyRow < N-1) {
            copy = new Board(this.copy());
            copy.swap(emptyRow, emptyCol, emptyRow+1, emptyCol);
            q.enqueue(copy);
        }
        if (emptyCol > 0) {
            copy = new Board(this.copy());
            copy.swap(emptyRow, emptyCol, emptyRow, emptyCol-1);
            q.enqueue(copy);
        }
        if (emptyCol < N-1) {
            copy = new Board(this.copy());
            copy.swap(emptyRow, emptyCol, emptyRow, emptyCol+1);
            q.enqueue(copy);
        }
        return q;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int hammingDistance(int block, int i, int j) {
        int goalRow = (block - 1) / N;
        int goalCol = (block - 1) % N;
        if (goalRow == i && goalCol == j)
            return 0;
        return 1;
    }

    private int manhattanDistance(int block, int i, int j) {
        int goalRow = (block - 1) / N;
        int goalCol = (block - 1) % N;
        return Math.abs(goalRow - i) + Math.abs(goalCol - j);
    }

    private int[][] copy() {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                copy[i][j] = board[i][j];
        return copy;
    }

    private void swap(int r1, int c1, int r2, int c2) {
        int tmp = board[r1][c1];
        board[r1][c1] = board[r2][c2];
        board[r2][c2] = tmp;
        if (board[r1][c1] == 0) {
            emptyRow = r1;
            emptyCol = c1;
        }
        if (board[r2][c2] == 0) {
            emptyRow = r2;
            emptyCol = c2;
        }

    }
}
