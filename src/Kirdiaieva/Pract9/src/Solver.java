import java.util.Comparator;
import java.util.Stack;

public class Solver {
    private SearchNode goal;

    private class SearchNode {
        private int moves;
        private Board board;
        private SearchNode prev;

        public SearchNode(Board initial) {
            moves = 0;
            prev = null;
            board = initial;
        }
    }

    public Solver(Board initial) {
        PriorityOrder order = new PriorityOrder();
        MinPQ<SearchNode> PQ = new MinPQ<SearchNode>(order);
        MinPQ<SearchNode> twinPQ = new MinPQ<SearchNode>(order);
        SearchNode Node = new SearchNode(initial);
        SearchNode twinNode = new SearchNode(initial);
        PQ.insert(Node);
        twinPQ.insert(twinNode);

        SearchNode min = PQ.delMin();
        SearchNode twinMin = twinPQ.delMin();

        while(!min.board.isGoal() && !twinMin.board.isGoal()) {

            for (Board b : min.board.neighbors()) {
                if (min.prev == null || !b.equals(min.prev.board)) {
                    SearchNode n = new SearchNode(b);
                    n.moves = min.moves + 1;
                    n.prev = min;
                    PQ.insert(n);
                }
            }

            for (Board b : twinMin.board.neighbors()) {
                if (twinMin.prev == null ||!b.equals(twinMin.prev.board)) {
                    SearchNode n = new SearchNode(b);
                    n.moves = twinMin.moves + 1;
                    n.prev = twinMin;
                    twinPQ.insert(n);
                }
            }

            min = PQ.delMin();
            twinMin = twinPQ.delMin();
        }
        if (min.board.isGoal())  goal = min;
        else                     goal = null;
    }

    private class PriorityOrder implements Comparator<SearchNode> {
        public int compare(SearchNode a, SearchNode b) {
            int pa = a.board.manhattan() + a.moves;
            int pb = b.board.manhattan() + b.moves;
            if (pa > pb)   return 1;
            if (pa < pb)   return -1;
            else              return 0;
        }
    }

    public boolean isSolvable() {
        return goal != null;
    }

    public int moves() {
        if (!isSolvable())  return -1;
        else                   return goal.moves;
    }

    public Iterable<Board> solution() {
        if (!isSolvable())  return null;
        Stack<Board> s = new Stack<Board>();
        for (SearchNode n = goal; n != null; n = n.prev)
            s.push(n.board);
        return s;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}