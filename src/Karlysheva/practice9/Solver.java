package practice9;

import ua.princeton.lib.In;
import ua.princeton.lib.StdOut;

import java.util.ArrayList;

public class Solver {
    private class Move implements Comparable<Move> {
        private int movesCount;
        private Move prev;
        public final Board board;

        public Move(Board board) {
            this.board = board;
        }

        public Move(Board board, Move prev) {
            this.board = board;
            this.prev = prev;
            movesCount = prev.movesCount + 1;
        }

        @Override
        public int compareTo(Solver.Move o) {
            return (board.manhattan() - o.board.manhattan()) + (movesCount - o.movesCount);
        }
    }

    private Move lastMove;

    public Solver(Board initial) {
        MinPQ<Move> moves = new MinPQ<Move>();
        moves.insert(new Move(initial));

        while (lastMove != null)
            lastMove = makeMove(moves);
    }

    private Move makeMove(MinPQ<Move> moves) {
        if(moves.isEmpty()) return null;
        Move bestMove = moves.delMin();
        if (bestMove.board.isGoal()) return bestMove;
        for (Board neighbor : bestMove.board.neighbors())
            if (bestMove.prev == null || !neighbor.equals(bestMove.prev.board))
                moves.insert(new Move(neighbor, bestMove));
        return null;
    }

    // чи має початкова дошка розв’язок
    public boolean isSolvable() {
        return lastMove!= null && lastMove.movesCount % 2 == 0;
    }

    // мінімальна кількість кроків для вирішення дошки, -1 якщо немає рішення
    public int moves() {
        return isSolvable() ? lastMove.movesCount : -1;
    }

    // послідовність дошок в найкоротшому рішенні; null якщо немає рішення
    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        ArrayList<Board> moves = new ArrayList<>();
        while(lastMove != null) {
            moves.add(lastMove.board);
            lastMove = lastMove.prev;
        }

        return moves;
    }

    public static void main(String[] args) {
        // створюємо початкову дошку з файлу
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // розв’язати
        Solver solver = new Solver(initial);

        // надрукувати рішення
        if (!solver.isSolvable())
            StdOut.println("Дошка не має розв’язку");
        else {
            StdOut.println("Мінімальна кількість кроків = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
