import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    private boolean isSolvable = true;
    private Node goalBoard;

    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private Node prev;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
        }

        @Override
        public int compareTo(Node that) {
            int thisManh = this.board.manhattan() + this.moves;
            int thatManh = that.board.manhattan() + that.moves;
            return thisManh - thatManh;
        }
    }

    /**
     * знайти рішення для дошки initial
     *
     * @param initial
     */
    public Solver(Board initial) {
        MinPQ<Node> initPQ = new MinPQ<>();
        MinPQ<Node> twinPQ = new MinPQ<>();
        initPQ.insert(new Node(initial, 0, null));
        twinPQ.insert(new Node(initial.twin(), 0, null));

        Node initNode;
        Node twinNode;

        while (true) {
            initNode = initPQ.delMin();
            twinNode = twinPQ.delMin();

            if (initNode.board.isGoal()) {
                goalBoard = initNode;
                isSolvable = true;
                break;
            }
            if (twinNode.board.isGoal()) {
                goalBoard = twinNode;
                isSolvable = false;
                break;
            }

            for (Board b : initNode.board.neighbours()) {
                if (initNode.prev == null || !b.equals(initNode.prev.board)) {
                    initPQ.insert(new Node(b, initNode.moves + 1, initNode));
                }
            }

            for (Board b : twinNode.board.neighbours()) {
                if (twinNode.prev == null || !b.equals(twinNode.prev.board)) {
                    twinPQ.insert(new Node(b, twinNode.moves + 1, twinNode));
                }
            }
        }

    }

    /**
     * чи має початкова дошка розв’язок
     *
     * @return
     */
    public boolean isSolvable() {
        return isSolvable;
    }

    /**
     * мінімальна кількість кроків для вирішення дошки, -1 якщо немає рішення
     *
     * @return
     */
    public int moves() {
        if (this.isSolvable) {
            return goalBoard.moves;
        }
        return -1;
    }

    /**
     * послідовність дошок в найкоротшому рішенні; null якщо немає рішення
     *
     * @return
     */
    public Iterable<Board> solution() {
        if (this.isSolvable) {
            Stack<Board> q = new Stack<>();
            Node tempNode = goalBoard;
            while (tempNode != null) {
                q.push(tempNode.board);
                tempNode = tempNode.prev;
            }
            return q;
        }
        return null;
    }


    public static void main(String[] args) {
        // створюємо початкову дошку з файлу
        //In in = new In("puzzle31.txt");
        In in = new In("puzzle04.txt");
        //In in = new In("unsolvable.txt");
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
