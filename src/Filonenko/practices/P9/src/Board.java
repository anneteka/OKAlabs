import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    public int[][] blocks;

    public Board(int[][] blocks) {
        this.blocks = blocks;
    }

    public int dimension() {
        return blocks.length;
    }

    public int hamming() {
        int length = dimension();
        int notOnThePlace = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (i * length + j != length * length - 1 && blocks[i][j] != i * dimension() + j + 1) {
                    notOnThePlace++;
                }
            }
        }
        return notOnThePlace;
    }

    public int manHattan() {
        int length = dimension();
        int manhattanDistancesSum = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if(blocks[i][j] != 0) {
                    int moveToIndex = blocks[i][j] - 1;
                    int row = getRowByIndex(moveToIndex);
                    int column = getColumnByIndex(moveToIndex);
                    manhattanDistancesSum += Math.abs(i - row) + Math.abs(j - column);
                }
            }
        }
        return manhattanDistancesSum;
    }

    public boolean isGoal() {
        if (hamming() == 0) return true;
        return false;
    }

    private int getRowByIndex(int index) {
        return index / dimension();
    }

    private int getColumnByIndex(int index) {
        return index % dimension();
    }

    @Override
    public boolean equals(Object o) {
        Board that = (Board) o;
        int length = this.dimension();
        if (length != that.dimension()) return false;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (this.blocks[i][j] != that.blocks[i][j]) return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        int length = dimension();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                result += blocks[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
    public Iterable<Board> neighbors() {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        int rowOfZero = 0;
        int columnOfZero = 0;
        int length = dimension();
        // find zero
        for(int i=0;i<length;i++) {
            for(int j=0;j<length;j++) {
                if(blocks[i][j] == 0) {
                    rowOfZero = i;
                    columnOfZero = j;
                    break;
                }
            }
        }
        // left neighbor
        if(columnOfZero > 0) {
            int[][] left = copy(blocks);
            swap(left, rowOfZero, columnOfZero, rowOfZero, columnOfZero-1);
            neighbors.add(new Board(left));
        }
        // right neighbor
        if(columnOfZero < length-1) {
            int[][] right = copy(blocks);
            swap(right, rowOfZero, columnOfZero, rowOfZero, columnOfZero+1);
            neighbors.add(new Board(right));
        }
        // top neighbor
        if(rowOfZero > 0) {
            int[][] top = copy(blocks);
            swap(top, rowOfZero, columnOfZero, rowOfZero-1, columnOfZero);
            neighbors.add(new Board(top));
        }
        // bottom neighbor
        if(rowOfZero < length-1) {
            int[][] bottom = copy(blocks);
            swap(bottom, rowOfZero, columnOfZero, rowOfZero+1, columnOfZero);
            neighbors.add(new Board(bottom));
        }
        return neighbors;
    }
    private int[][] copy(int[][] array) {
        int[][] copy = new int[array.length][array.length];
        for(int i=0;i<array.length;i++) {
            for(int j=0;j<array.length;j++) {
                copy[i][j] = array[i][j];
            }
        }
        return copy;
    }
    private void swap(int[][] array, int firstRow, int firstColumn, int secondRow, int secondColumn) {
        int tmp = array[firstRow][firstColumn];
        array[firstRow][firstColumn] = array[secondRow][secondColumn];
        array[secondRow][secondColumn] = tmp;
    }
}
