import java.util.Stack;

public class Realization {

    private Stack<PuzzleBoard> solutions;
    private boolean solvable;
    private int M;    // minimal moves
    public Realization(PuzzleBoard initial) {
        solvable = false;
        solutions = null;
        M = -1;
        MinPQ<SNode> nq = new MinPQ<>();
        MinPQ<SNode> tq = new MinPQ<>();

        nq.insert(new SNode(initial));
        tq.insert(new SNode(initial.twin()));

        while (!nq.isEmpty() && !tq.isEmpty()) {
            SNode n = nq.delMin();
            SNode t = tq.delMin();

            if (t.PuzzleBoard.manhattan() == 0) break;

            if (n.PuzzleBoard.manhattan() == 0) {
                solutions = new Stack<>();
                solutions.push(n.PuzzleBoard);
                solvable = true;
                M = n.moves;

                SNode prev = n.previous;
                while (prev != null) {
                    solutions.push(prev.PuzzleBoard);
                    M += prev.moves;
                    prev = prev.previous;
                }
                break;
            }

            // putting neighbors into queues
            for (PuzzleBoard nbr : n.PuzzleBoard.neighbors())
                if (n.previous == null || !n.previous.PuzzleBoard.equals(nbr))
                    nq.insert(new SNode(nbr, n.moves + 1, n));

            for (PuzzleBoard tbr : t.PuzzleBoard.neighbors())
                if (t.previous == null || !t.previous.PuzzleBoard.equals(tbr))
                    tq.insert(new SNode(tbr, t.moves + 1, t));

        }
    }



    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return M;
    }


    public Iterable<PuzzleBoard> solution() {
        return solutions;
    }

    private class SNode implements Comparable<SNode> {

        public PuzzleBoard PuzzleBoard;
        public int moves;
        public SNode previous;

        public SNode(PuzzleBoard initial) {
            PuzzleBoard = initial;
            moves = 0;
            previous = null;
        }

        public SNode(PuzzleBoard initial, int mov, SNode prev) {
            PuzzleBoard = initial;
            moves = mov;
            previous = prev;
        }

        public int compareTo(SNode that) {
            int thisMove = this.PuzzleBoard.manhattan() + this.moves;
            int thatMove = that.PuzzleBoard.manhattan() + that.moves;
            if (thisMove < thatMove) return -1;
            if (thisMove > thatMove) return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        In in = new In();
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        PuzzleBoard initial = new PuzzleBoard(blocks);

        Realization solver = new Realization(initial);


        if (!solver.isSolvable())
            StdOut.println("No solution!");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (PuzzleBoard PuzzleBoard : solver.solution())
                StdOut.println(PuzzleBoard);
        }
    }
}
