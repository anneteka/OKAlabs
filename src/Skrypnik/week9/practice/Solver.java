package week9.practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solver {
    private int allMoves;
    private Node last;
    private boolean isSolvable;

    private void enqueue(Node node, MinPQ<Node> pq) {
        for (Board nextBoard : node.board.neighbors()) {
            if ((node.previous == null) || (!nextBoard.equals(node.previous.board))) {
                pq.insert(new Node(nextBoard, node.moves + 1, node));
            }
        }
    }

    public Solver(Board initial) {
        MinPQ<Node> nodeMinPQ = new MinPQ<Node>();
        MinPQ<Node> sameNodeMinPQ = new MinPQ<Node>();
        Node start = new Node(initial, 0, null);
        Node startSame = new Node(initial.createSameBoard(), 0, null);
        nodeMinPQ.insert(start);
        sameNodeMinPQ.insert(startSame);
        while (true) {
            Node node = nodeMinPQ.delMin();
            Node nodeTwin = sameNodeMinPQ.delMin();
            if (node.board.isGoal()) {
                last = node;
                isSolvable = true;
                allMoves = node.moves;
                break;
            }
            if (nodeTwin.board.isGoal()) {
                isSolvable = false;
                allMoves = -1;
                break;
            }
            enqueue(node, nodeMinPQ);
            enqueue(nodeTwin, sameNodeMinPQ);
        }
    }

    public boolean isSolvable() {
        return isSolvable;
    }

    public int moves() {
        return allMoves;
    }

    public Iterable<Board> solution() {
        Stack<Board> solution = new Stack<Board>();
        if (isSolvable) {
            Node s = last;
            while (s != null) {
                solution.push(s.board);
                s = s.previous;
            }
        } else {
            solution = null;
        }
        return solution;
    }

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/week9//practice/test/puzzle04.txt"));
            int N = Integer.parseInt(bufferedReader.readLine());
            int[][] blocks = new int[N][N];
            StringTokenizer tokenizer;
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(bufferedReader.readLine());
                for (int j = 0; j < N; j++)
                    blocks[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
            Board initial = new Board(blocks);
            Solver solver = new Solver(initial);
            if (!solver.isSolvable())
                System.out.println("Дошка не має розв'язку");
            else {
                System.out.println("Мінімальна кількість кроків = " + solver.moves());
                for (Board board : solver.solution())
                    System.out.println(board);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Node implements Comparable<Node> {
        private Board board;
        private int moves;
        private Node previous;
        private int priority;


        public Node(Board b, int m, Node p) {
            board = b;
            moves = m;
            previous = p;
            priority = b.manhattan() + moves;
        }

        public int compareTo(Node other) {
            if (this.priority > other.priority) return 1;
            if (this.priority < other.priority) return -1;
            return 0;
        }
    }
}