import edu.princeton.cs.algs4.MinPQ;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
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

    public Main(Board initial) {
        MinPQ<Node> mpq = new MinPQ<Node>();
        MinPQ<Node> spq = new MinPQ<Node>();
        Node start = new Node(initial, 0, null);
        Node startSame = new Node(initial.restore(), 0, null);
        mpq.insert(start);
        spq.insert(startSame);
        while (true) {
            Node node = mpq.delMin();
            Node nodeTwin = spq.delMin();
            if (node.board.isNeeded()) {
                last = node;
                isSolvable = true;
                allMoves = node.moves;
                break;
            }
            if (nodeTwin.board.isNeeded()) {
                isSolvable = false;
                allMoves = -1;
                break;
            }
            enqueue(node, mpq);
            enqueue(nodeTwin, spq);
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

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File("input/puzzle.txt");
            Scanner sc = new Scanner(f);
            int N = Integer.parseInt(sc.nextLine());
            int[][] blocks = new int[N][N];
            StringTokenizer tokenizer;
            for (int i = 0; i < N; i++) {
                tokenizer = new StringTokenizer(sc.nextLine());
                for (int j = 0; j < N; j++)
                    blocks[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
            Board initial = new Board(blocks);
            Main solver = new Main(initial);
            if (!solver.isSolvable())
                System.out.println("Дошка не має розв'язку");
            else {
                System.out.println("Мінімальна кількість кроків = " + solver.moves());
                for (Board board : solver.solution())
                    System.out.println(board);
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
            priority = b.mtn() + moves;
        }

        public int compareTo(Node other) {
            if (this.priority > other.priority) return 1;
            if (this.priority < other.priority) return -1;
            return 0;
        }
    }
}