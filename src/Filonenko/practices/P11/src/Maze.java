import lib.StdDraw;
import lib.StdRandom;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Maze {
    private int dimension;
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] north;
    private boolean[][] visited;
    private boolean solved = false;

    public Maze(int dimension)  {
        this.dimension = dimension;
        StdDraw.setXscale(0, dimension+2);
        StdDraw.setYscale(0, dimension+2);

        visited = new boolean[dimension+2][dimension+2];
        for(int i=0;i<dimension+2;i++)
        {
            visited[0][i] = true;
            visited[dimension+1][i] = true;
        }
        for (int y = 0; y < dimension+2; y++) {
            visited[0][y] = true;
            visited[dimension+1][y] = true;
        }
        north = new boolean[dimension+2][dimension+2];
        west = new boolean[dimension+2][dimension+2];
        south = new boolean[dimension+2][dimension+2];
        east = new boolean[dimension+2][dimension+2];

        for (int x = 0; x < dimension+2; x++) {
            for (int y = 0; y < dimension+2; y++) {
                north[x][y] = true;
                east[x][y]  = true;
                south[x][y] = true;
                west[x][y]  = true;
            }
        }

        generateMaze(1,1);
    }

    private void generateMaze(int x, int y) {
        visited[x][y] = true;
        if(x < 1 || x > dimension) return;;
        if(y < 1 || y > dimension) return;;
        while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x - 1][y]) {
            while (true) {
                double r = ThreadLocalRandom.current().nextInt(0, 4);
                if (r == 0 && !visited[x][y+1]) {
                    north[x][y] = false;
                    south[x][y+1] = false;
                    generateMaze(x, y + 1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    east[x][y] = false;
                    west[x+1][y] = false;
                    generateMaze(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    south[x][y] = false;
                    north[x][y-1] = false;
                    generateMaze(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    west[x][y] = false;
                    east[x-1][y] = false;
                    generateMaze(x-1, y);
                    break;
                }
            }
        }
    }
    private void clean() {
        for (int x = 1; x <= dimension; x++)
            for (int y = 1; y <= dimension; y++)
                visited[x][y] = false;
        solved = false;
    }
    private void solve(int x, int y) {
        if (x == 0 || y == 0 || x == dimension+1 || y == dimension+1) {
            return;
        }
        if (solved || visited[x][y]) {
            return;
        }
        visited[x][y] = true;

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(e);
        }


        // reached middle
        if (x == dimension/2 && y == dimension/2) solved = true;

        if (!north[x][y]) solve(x, y + 1);
        if (!east[x][y])  solve(x + 1, y);
        if (!south[x][y]) solve(x, y - 1);
        if (!west[x][y])  solve(x - 1, y);

        if (solved) {
            return;
        }

        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show();
    }
    public void draw() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(dimension/2.0 + 0.5, dimension/2.0 + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.line(1,1,dimension+1,1);
        StdDraw.line(1, dimension+1, dimension+1, dimension+1);

        for (int x = 1; x <= dimension; x++) {
            for (int y = 1; y <= dimension; y++) {
                if (south[x][y]) StdDraw.line(x, y, x+1, y);
                if (north[x][y]) StdDraw.line(x, y+1, x+1, y+1);
                if (west[x][y])  StdDraw.line(x, y, x, y+1);
                if (east[x][y])  StdDraw.line(x+1, y, x+1, y+1);
            }
        }
        StdDraw.show();
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    public void solve() {
        clean();
        solve(1,1);
    }
    public static void main(String[] args) {
        int n = 10;
        Maze maze = new Maze(n);
        maze.draw();
        maze.solve();
    }
}
