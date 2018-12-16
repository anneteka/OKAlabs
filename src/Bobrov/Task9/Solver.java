package Task9;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;

import edu.princeton.cs.algs4.StdOut;


public class Solver {
    private class Move implements Comparable<Move> {
        private Move previous;
        private Board board;
        private int numMoves = 0;

        public Move(Board board) {
            this.board = board;
        }

        public Move(Board board, Move previous) {
            this.board = board;
            this.previous = previous;
            this.numMoves = previous.numMoves + 1;
        }

        public int compareTo(Move move) {
            return (this.board.manhattan() - move.board.manhattan()) + (this.numMoves - move.numMoves);
        }
    }

    private Move lastMove;

    public Solver(Board initial) {
        MinPQ<Move> moves = new MinPQ<Move>();
        moves.insert(new Move(initial));

        MinPQ<Move> twinMoves = new MinPQ<Move>();
        twinMoves.insert(new Move(initial.twin()));

        while(true) {
            lastMove = expand(moves);
            if (lastMove != null || expand(twinMoves) != null) return;
        }
    }

    private Move expand(MinPQ<Move> moves) {
        if(moves.isEmpty()) return null;
        Move bestMove = moves.delMin();
        if (bestMove.board.isGoal()) return bestMove;
        for (Board neighbor : bestMove.board.neighbors()) {
            if (bestMove.previous == null || !neighbor.equals(bestMove.previous.board)) {
                moves.insert(new Move(neighbor, bestMove));
            }
        }
        return null;
    }

    public boolean isSolvable() {
        return (lastMove != null);
    }

    public int moves() {
        return isSolvable() ? lastMove.numMoves : -1;
    }

    public Iterable<Board> solution() {
        if (!isSolvable()) return null;

        Stack<Board> moves = new Stack<Board>();
        while(lastMove != null) {
            moves.push(lastMove.board);
            lastMove = lastMove.previous;
        }

        return moves;
    }
    public static void main(String[] args) throws NumberFormatException, IOException {


        Scanner reader=new Scanner(new FileReader("input3.txt"));

        int N=reader.nextInt();
        int[][] blocks = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = reader.nextInt();
        Board initial = new Board(blocks);

        // ðîçâ’ÿçàòè
        Solver solver = new Solver(initial);

        // íàäðóêóâàòè ð³øåííÿ
        if (!solver.isSolvable())
            StdOut.println("It is impossible to solve.");
        else {
            StdOut.println("Min amount of solvings = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
