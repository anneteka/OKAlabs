import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;

public class Board {
    private final int n;
    private final char[] blocks;
    private int blankpos;


    private int row(int p) {
        return (int) Math.ceil((double) p / (double) n);
    }

    private int col(int p) {
        if (p % n == 0) return n;
        return p % n;
    }

    public Board(int[][] blocks) {
        n = blocks.length;
        this.blocks = new char[n * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.blocks[k] = (char) blocks[i][j];
                if (blocks[i][j] == 0) blankpos = k;
                k++;
            }
        }
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    public int hamming() {
        int hamming = 0;
        for (int k = 0, ans = 1; k < n * n; k++, ans++) {
            if (blocks[k] == 0) continue;
            if (blocks[k] != ans) hamming++;
        }
        return hamming;
    }

    public int manhattan() {
        int manhattan = 0;
        for (int k = 0; k < n * n; k++) {
            if (blocks[k] == 0) continue;
            int rowdiff = Math.abs(row(blocks[k]) - row(k + 1));
            int coldiff = Math.abs(col(blocks[k]) - col(k + 1));
            manhattan = manhattan + rowdiff + coldiff;
        }
        return manhattan;
    }


    public boolean isGoal() {
        for (int k = 0; k < n * n - 2; k++) {
            if (blocks[k] > blocks[k + 1]) return false;
        }
        return true;
    }


    public Board twin() {
        boolean swapSuccess = false;
        char[] twin = blocks.clone();
        // choose a non-blank block
        int k = 0;
        do {
            k = StdRandom.uniform(n * n);
        } while (blocks[k] == 0);

        while (swapSuccess == false) {
            int choice = StdRandom.uniform(4);
            switch (choice) {
                case 0: //swapAbove
                    if (row(k + 1) == 1) swapSuccess = false;
                    else if (twin[k - n] == 0) swapSuccess = false;
                    else {
                        swapAbove(twin, k);
                        swapSuccess = true;
                    }
                    break;
                case 1: //swapBelow
                    if (row(k + 1) == n) swapSuccess = false;
                    else if (twin[k + n] == 0) swapSuccess = false;
                    else {
                        swapBelow(twin, k);
                        swapSuccess = true;
                    }
                    break;
                case 2: //swapLeft
                    if (col(k + 1) == 1) swapSuccess = false;
                    else if (twin[k - 1] == 0) swapSuccess = false;
                    else {
                        swapLeft(twin, k);
                        swapSuccess = true;
                    }
                    break;
                case 3: //swapRight
                    if (col(k + 1) == n) swapSuccess = false;
                    else if (twin[k + 1] == 0) swapSuccess = false;
                    else {
                        swapRight(twin, k);
                        swapSuccess = true;
                    }
                    break;
            }
        }
        Board twinBoard = new Board(toTwoDarray(twin));
        return twinBoard;
    }

    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (!Arrays.equals(this.blocks, that.blocks)) return false;
        return true;
    }


    public Iterable<Board> neighbors() {
        Stack<Board> stackNeighbors = new Stack<Board>();
        char[] neighbor;
        if (row(blankpos + 1) != 1) {
            neighbor = blocks.clone();
            swapAbove(neighbor, blankpos);
            Board neighborBoard = new Board(toTwoDarray(neighbor));
            stackNeighbors.push(neighborBoard);
        }
        if (row(blankpos + 1) != n) {
            neighbor = blocks.clone();
            swapBelow(neighbor, blankpos);
            Board neighborBoard = new Board(toTwoDarray(neighbor));
            stackNeighbors.push(neighborBoard);
        }
        if (col(blankpos + 1) != 1) {
            neighbor = blocks.clone();
            swapLeft(neighbor, blankpos);
            Board neighborBoard = new Board(toTwoDarray(neighbor));
            stackNeighbors.push(neighborBoard);
        }
        if (col(blankpos + 1) != n) {
            neighbor = blocks.clone();
            swapRight(neighbor, blankpos);
            Board neighborBoard = new Board(toTwoDarray(neighbor));
            stackNeighbors.push(neighborBoard);
        }
        return stackNeighbors;
    }


    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n + "\n");
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", (int) blocks[k]));
                k++;
            }
            s.append("\n");
        }
        return s.toString();
    }

    private void swapAbove(char[] oneDarray, int k) {
        char temp = oneDarray[k];
        oneDarray[k] = oneDarray[k - n];
        oneDarray[k - n] = temp;
    }

    private void swapBelow(char[] oneDarray, int k) {
        char temp = oneDarray[k];
        oneDarray[k] = oneDarray[k + n];
        oneDarray[k + n] = temp;
    }

    private void swapLeft(char[] oneDarray, int k) {
        char temp = oneDarray[k];
        oneDarray[k] = oneDarray[k - 1];
        oneDarray[k - 1] = temp;
    }

    private void swapRight(char[] oneDarray, int k) {
        char temp = oneDarray[k];
        oneDarray[k] = oneDarray[k + 1];
        oneDarray[k + 1] = temp;
    }

    private int[][] toTwoDarray(char[] oneDarray) {
        int k = 0;
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                blocks[i][j] = oneDarray[k];
                k++;
            }
        return blocks;
    }


    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
    }
}
 