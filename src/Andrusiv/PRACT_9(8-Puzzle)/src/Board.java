import edu.princeton.cs.algs4.Queue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Board {
    private int dimension;
    private int board[];

    int rowZ = 0;
    int colZ = 0;

   
    public Board(int[][] blocks) {
        this.dimension = blocks.length;
        board = new int[dimension * dimension];

        int k = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.board[k++] = blocks[i][j];
                if (blocks[i][j] == 0) {
                    rowZ = i;
                    colZ = j;
                }
            }
        }
    }


    public int dimension() {
        return dimension;
    }


    public int hamming() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) continue;
            if (board[i] != i + 1) counter++;
        }
        return counter;
    }

    public int manhattan() {
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) continue;
            int row = Math.abs((board[i] - 1 - i) / dimension);
            int col = Math.abs((board[i] - 1 - i) % dimension);
            counter += row + col;
        }
        return counter;
    }


    public boolean isGoal() {
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0 && i == board.length - 1) return true;
            if (board[i] != i + 1) return false;
        }
        return true;
    }


    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        return Arrays.equals(this.board, that.board);
    }

    public Iterable<Board> neighbours() {
        Queue<Board> q = new Queue<Board>();
        Board tempBoard;
        // down
        if (rowZ > 0) {
            tempBoard = new Board(swapBlocks(rowZ, colZ, rowZ - 1, colZ));
            q.enqueue(tempBoard);
        }
        // up
        if (rowZ < dimension - 1) {
            tempBoard = new Board(swapBlocks(rowZ, colZ, rowZ + 1, colZ));
            q.enqueue(tempBoard);
        }
        // right
        if (colZ > 0) {
            tempBoard = new Board(swapBlocks(rowZ, colZ, rowZ, colZ - 1));
            q.enqueue(tempBoard);
        }
        // left
        if (colZ < dimension - 1) {
            tempBoard = new Board(swapBlocks(rowZ, colZ, rowZ, colZ + 1));
            q.enqueue(tempBoard);
        }
        return q;
    }

    private int[][] swapBlocks(int rowZ, int colZ, int rowi, int coli) {
        int[][] tempBlocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tempBlocks[i][j] = board[dimension * i + j];
            }
        }
        int temp = tempBlocks[rowZ][colZ];
        tempBlocks[rowZ][colZ] = tempBlocks[rowi][coli];
        tempBlocks[rowi][coli] = temp;

        return tempBlocks;
    }

    public Board twin() {
        int[][] tempBlocks = new int[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                tempBlocks[i][j] = board[dimension * i + j];
            }
        }
        Board twinBoard = new Board(tempBlocks);

        if (board[0] == 0 || board[1] == 0) {
            twinBoard.swapBlocks(1, 0, 1, 1);
        } else {
            twinBoard.swapBlocks(0, 0, 0, 1);
        }
       for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
               if (tempBlocks[i][j] != 0 && tempBlocks[i][j + 1] != 0) {
                  twinBoard.swapBlocks(i, j, i, j + 1);
                    return twinBoard;
               }
            }
       }
        return twinBoard;
    }

  
    public String toString() {
        String result = "";
        for (int i = 0; i < board.length; i++) {
            result += board[i] + " ";
            if ((i + 1) % dimension == 0) result += "\n";
        }
        return result;
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
    	
    	
    	
       // int blocks[][] = {{1,2,3}, {4,6,5},{7,8,0}};
   	//int blocks[][] = {{8, 6, 7}, {2, 5, 4}, {3, 0, 1}};
    int blocks[][] = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        Board b = new Board(blocks);
        System.out.println(b.isGoal());
        System.out.println(b.manhattan());
        System.out.println(b.toString());

      //  System.out.println(b.neighbours());
    }
}
