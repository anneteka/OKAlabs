package practice9;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private static final int[][] GOAL = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    private static final int BLANK = 0;
    public final int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = blocks;
    }

    public int dimension() {
        return blocks.length;
    }

    // кількість блоків не на своєму місці
    public int hamming() {
        int count = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != i * 3 + j + 1)
                    count++;
            }
        }
        return count;
    }

    // сума Манхатенських відстаней між блоками і цільовим станом
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                sum += blocks[i][j] == BLANK ? 0 : Math.abs(i - ((i - 1) / dimension()))
                        + Math.abs(j - ((j - 1) / dimension()));
            }
        }
        return sum;
    }

    // чи є ця дошка цільовим станом
    public boolean isGoal() {
        return Arrays.deepEquals(blocks, GOAL);
    }

    // чи ця дошка рівна y?
    public boolean equals(Board y) {
        return Arrays.deepEquals(blocks, y.blocks);
    }

    // всі сусдні дошки
    public Iterable<Board> neighbors() {
        int blankX = 0, blankY = 0;
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] == BLANK) {
                    blankX = i;
                    blankY = j;
                }
            }
        }

        ArrayList<Board> neighbors = new ArrayList<>();
        if (blankX > 0)
            neighbors.add(new Board(swap(blankX, blankY, blankX - 1, blankY)));
        if (blankY > 0)
            neighbors.add(new Board(swap(blankX, blankY, blankX, blankY - 1)));
        if (blankX < dimension() - 1)
            neighbors.add(new Board(swap(blankX, blankY, blankX  + 1, blankY)));
        if (blankY < dimension() - 1)
            neighbors.add(new Board(swap(blankX, blankY, blankX, blankY + 1)));

        return neighbors;
    }

    private int[][] swap(int blankX, int blankY, int X, int Y) {
        int[][] copy = Arrays.copyOf(blocks, dimension());
        int tmp = copy[blankX][blankY];
        copy[blankX][blankY] = copy[X][Y];
        copy[X][Y] = tmp;

        return copy;
    }

    public String toString() {
        return Arrays.deepToString(blocks);
    }
}

