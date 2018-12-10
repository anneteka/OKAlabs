import java.util.Stack;

public class Realization {

    private Stack<Board> stack;
    private boolean solvable;
    private int M;
    public Realization(Board initial) {
        solvable = false;
        stack = null;
        M = -1;
        MinPQ<SNode> nq = new MinPQ<>();
        MinPQ<SNode> tq = new MinPQ<>();

        nq.insert(new SNode(initial));
        tq.insert(new SNode(initial.twin()));

        while (!nq.isEmpty() && !tq.isEmpty()) {
            SNode n = nq.delMin();
            SNode t = tq.delMin();

            if (t.Board.manhattan() == 0) break;

            if (n.Board.manhattan() == 0) {
                stack = new Stack<>();
                stack.push(n.Board);
                solvable = true;
                M = n.moves;

                SNode prev = n.previous;
                while (prev != null) {
                    stack.push(prev.Board);
                    M += prev.moves;
                    prev = prev.previous;
                }
                break;
            }

            for (Board nbr : n.Board.neighbors())
                if (n.previous == null || !n.previous.Board.equals(nbr))
                    nq.insert(new SNode(nbr, n.moves + 1, n));

            for (Board tbr : t.Board.neighbors())
                if (t.previous == null || !t.previous.Board.equals(tbr))
                    tq.insert(new SNode(tbr, t.moves + 1, t));

        }
    }



    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        return M;
    }


    public Iterable<Board> stack() {
        return stack;
    }

    private class SNode implements Comparable<SNode> {

        public Board Board;
        public int moves;
        public SNode previous;

        public SNode(Board initial) {
            Board = initial;
            moves = 0;
            previous = null;
        }

        public SNode(Board initial, int mov, SNode prev) {
            Board = initial;
            moves = mov;
            previous = prev;
        }

        public int compareTo(SNode that) {
            int thisMove = this.Board.manhattan() + this.moves;
            int thatMove = that.Board.manhattan() + that.moves;
            if (thisMove < thatMove) return -1;
            if (thisMove > thatMove) return 1;
            return 0;
        }
    }

    public static void main(String[] args) {
        ua.princeton.lib.In in = new ua.princeton.lib.In("puzzle31.txt");
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Realization solver = new Realization(initial);


        if (!solver.isSolvable())
            System.out.println("No solution!");
        else {
            System.out.println("Minimum number of moves = " + solver.moves());
            for (Board Board : solver.stack())
                System.out.println(Board);
        }
    }
}
