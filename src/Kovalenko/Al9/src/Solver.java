import java.util.Comparator;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class Solver {
    private class Node implements Comparable<Node> {
        private Board board;

        private int moves;

        private Node prev;

        public Node(Board b, int m, Node p) {
            board = b;
            moves = m;
            prev = p;
        }

        public int compareTo(Node o) {
            int c = (board.manhattan() + moves)
                    - (o.board.manhattan() + o.moves);
            if (c == 0) {
                return board.manhattan() - o.board.manhattan();
            }
            return c;
        }

        @Override
        public boolean equals(Object y) {
            if (!(y instanceof Node)) {
                return false;
            }

            Node that = (Node) y;
            return board.equals(that.board);
        }
    }

    private class BoardComparator implements Comparator<Node> {
        public int compare(Node o1, Node o2) {
            if (o1.equals(o2)) {
                return 0;
            }
            return 1;
        }
    }

    private Stack<Board> solve;

    private boolean solvable;

    public Solver(Board initial) {
        solve = new Stack<Board>();
        solvable = true;

        Board contrast = initial.twin();
        Set<Node> set = new TreeSet<Node>(new BoardComparator());
        MinPQ<Node> pq1 = new MinPQ<Node>();
        MinPQ<Node> pq2 = new MinPQ<Node>();

        Node iniNode = new Node(initial, 0, null);
        Node conNode = new Node(contrast, 0, null);
        set.add(iniNode);
        set.add(conNode);
        pq1.insert(iniNode);
        pq2.insert(conNode);

        Node b1 = null, b2 = null;
        while (true) {
            b1 = pq1.delMin();
            b2 = pq2.delMin();

            if (b1.board.isGoal() || b2.board.isGoal()) {
                break;
            }

            for (Board b : b1.board.neighbors()) {
                Node no = new Node(b, b1.moves + 1, b1);
                if (b1.prev == null
                        || (!b1.prev.equals(no) && !set.contains(no))) {
                    pq1.insert(no);
                    set.add(no);
                }
            }
            for (Board b : b2.board.neighbors()) {
                Node no = new Node(b, b2.moves + 1, b2);
                if (b2.prev == null
                        || (!b2.prev.equals(no) && !set.contains(no))) {
                    pq2.insert(no);
                    set.add(no);
                }
            }
        }

        if (b1.board.isGoal()) {
            Node no = b1;
            while (no != null) {
                solve.push(no.board);
                no = no.prev;
            }
            solvable = true;
        } else {
            solvable = false;
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solvable;
    }

    public int moves() {
        if (solvable) {
        	
        	
            return solve.size() - 1;
        }

        return -1;
    }

    public Iterable<Board> solution() {
        if (solvable) {
            return solve;
        }

        return null;
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