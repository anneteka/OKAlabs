import java.util.Arrays;

public class Board {

    private int N;
    private int[] board;

    public Board(int[][] blocks)  {
        N = blocks[0].length;
        board = new int[N * N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                board[i * N + j] = blocks[i][j];
    }

    private Board(int[] board) {
        N = (int) Math.sqrt(board.length);
        this.board = new int[board.length];
        for (int i = 0; i < board.length; i++)
            this.board[i] = board[i];
    }

    public int dimension() {
        return N;
    }

    public int hamming() {
        int count = 0;
        for (int i = 0; i < N * N; i++)
            if (board[i] != i + 1 && board[i] != 0)
                count++;
        return count;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N * N; i++)
            if (board[i] != i + 1 && board[i] != 0)
                sum += manhattan(board[i], i);
        return sum;
    }

    private int manhattan(int goal, int current) {
        int row, col;
        row = Math.abs((goal - 1) / N - current / N);
        col = Math.abs((goal - 1) % N - current % N);
        return row + col;
    }

    public boolean isGoal() {
        for (int i = 0; i < N * N - 1; i++)
            if (board[i] != i + 1)
                return false;
        return true;
    }

    public Board twin() {
        Board twin;
        if (N == 1)  return null;
        twin = new Board(board);

        if (board[0] != 0 && board[1] != 0)
            exch(twin, 0, 1);
        else
            exch(twin, N, N + 1);
        return twin;
    }

    private Board exch(Board a, int i, int j) {
        int temp = a.board[i];
        a.board[j] = a.board[i];
        a.board[i] = temp;
        return a;
    }

    public boolean equals(Object y) {
        if (y == this)  return true;
        if (y == null)  return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        return Arrays.equals(this.board, that.board);
    }

    public Iterable<Board> neighbors() {
        int index = 0;
        boolean found = false;
        Board neighbor;
        Queue<Board> q = new Queue<Board>();

        for (int i = 0; i < board.length; i++)
            if (board[i] == 0) {
                index = i;
                found = true;
                break;
            }
        if (!found)  return null;

        if (index / N != 0) {
            neighbor = new Board(board);
            exch(neighbor, index, index - N);
            q.enqueue(neighbor);
        }

        if (index / N != (N - 1)) {
            neighbor = new Board(board);
            exch(neighbor, index, index + N);
            q.enqueue(neighbor);
        }

        if ((index % N) != 0) {
            neighbor = new Board(board);
            exch(neighbor, index, index - 1);
            q.enqueue(neighbor);
        }

        if ((index % N) != N - 1) {
            neighbor = new Board(board);
            exch(neighbor, index, index + 1);
            q.enqueue(neighbor);
        }

        return q;
    }

    public String toString() {              // string representation of the board
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < board.length; i++) {
            s.append(String.format("%2d ", board[i]));
            if (i % N == 0)
                s.append("\n");
        }
        return s.toString();
    }
}