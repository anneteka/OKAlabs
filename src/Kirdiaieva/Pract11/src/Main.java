public class Main {

    private static final int SIZE = 20;

    public static void main(String[] args) {
        Maze maze = new Maze(SIZE);
        StdDraw.enableDoubleBuffering();
        maze.draw();
        maze.solve();
    }
}
