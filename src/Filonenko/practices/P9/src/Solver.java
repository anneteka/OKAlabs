import java.util.ArrayList;
import java.util.PriorityQueue;

public class Solver {
    public static void main(String[] args) {
        int[][] array = new int[3][3];
        array[0][0]=8;
        array[0][1]=1;
        array[0][2]=3;
        array[1][0]=4;
        array[1][1]=0;
        array[1][2]=2;
        array[2][0]=7;
        array[2][1]=6;
        array[2][2]=5;
        Board board = new Board(array);

        Solver solver = new Solver(board);
        System.out.println(solver.moves());
    }
    private Board initialBoard;
    public Solver(Board initial) {
        initialBoard = initial;
        PriorityQueue<SearchNode> queue = new PriorityQueue<SearchNode>();
        solutionArray = new ArrayList<Board>();
        SearchNode current = new SearchNode(initial, null, 0);
        queue.offer(current);
        while(!current.currentBoard.isGoal()) {
            SearchNode node = queue.poll();
            for(Board b: node.currentBoard.neighbors()) {
                queue.offer(new SearchNode(b, node, node.moves+1));
            }
            current = node;
        }
        while(current.previousBoard != null) {
            solutionArray.add(current.currentBoard);
            current = current.previousBoard;
        }
    }
     private ArrayList<Board> solutionArray;

    public Iterable<Board> solution() {
        return  solutionArray;
    }

    public int moves() {
        return  solutionArray.size();
    }

    class SearchNode implements Comparable {
        public Board currentBoard;
        public SearchNode previousBoard;
        public int moves = 0;
        public SearchNode(Board current, SearchNode previous, int moves) {
            this.currentBoard = current;
            this.previousBoard = previous;
            this.moves = moves;
        }

        @Override
        public int compareTo(Object o) {
            SearchNode that = (SearchNode) o;
            return (this.moves + this.currentBoard.hamming()) - (that.moves + that.currentBoard.hamming());
        }
    }

    private void swap(int[][] array, int firstRow, int firstColumn, int secondRow, int secondColumn) {
        int tmp = array[firstRow][firstColumn];
        array[firstRow][firstColumn] = array[secondRow][secondColumn];
        array[secondRow][secondColumn] = tmp;
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
}
