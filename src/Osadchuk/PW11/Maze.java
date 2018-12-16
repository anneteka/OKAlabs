package PW11;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Random;

import PW7.StdDraw;

public class Maze {
    private int n; // size of application

    private int lampX, lampY; // lamp coordinats

    private boolean[][] north, east, south, west;

    private boolean[][] visited;

    private long[][] curWays;

    private Random rand;

    public Maze(int n) {
	this.n = n;

	StdDraw.setXscale(0, n + 2);
	StdDraw.setYscale(0, n + 2);

	rand = new Random();
	this.lampX = rand.nextInt(n) + 1;
	this.lampY = rand.nextInt(n) + 1;

	createArrs();
	generate(1, 1);
	drawMap();
    }

    private void createArrs() {
	this.curWays = new long[n + 2][n + 2];
	this.visited = new boolean[n + 2][n + 2];
	for (int x = 0; x < n + 2; ++x) {
	    visited[x][0] = true;
	    visited[x][n + 1] = true;
	}
	for (int y = 0; y < n + 2; ++y) {
	    visited[0][y] = true;
	    visited[n + 1][y] = true;
	}

	north = new boolean[n + 2][n + 2];
	east = new boolean[n + 2][n + 2];
	south = new boolean[n + 2][n + 2];
	west = new boolean[n + 2][n + 2];
	for (int x = 0; x < n + 2; ++x)
	    for (int y = 0; y < n + 2; ++y) {
		north[x][y] = true;
		east[x][y] = true;
		south[x][y] = true;
		west[x][y] = true;
	    }

    }

    private void generate(int x, int y) {
	visited[x][y] = true;

	while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1] || !visited[x - 1][y]) {

	    while (true) {
		double r = rand.nextInt(4);
		if (r == 0 && !visited[x][y + 1]) {
		    north[x][y] = false;
		    south[x][y + 1] = false;
		    generate(x, y + 1);
		    break;
		} else if (r == 1 && !visited[x + 1][y]) {
		    east[x][y] = false;
		    west[x + 1][y] = false;
		    generate(x + 1, y);
		    break;
		} else if (r == 2 && !visited[x][y - 1]) {
		    south[x][y] = false;
		    north[x][y - 1] = false;
		    generate(x, y - 1);
		    break;
		} else if (r == 3 && !visited[x - 1][y]) {
		    west[x][y] = false;
		    east[x - 1][y] = false;
		    generate(x - 1, y);
		    break;
		}
	    }
	}
    }

    public void drawMap() {
	StdDraw.setPenColor(new Color(255, 144, 0));
	StdDraw.filledCircle(lampX + 0.5, lampY + 0.5, 0.375);

	StdDraw.setPenColor(Color.black);
	for (int x = 1; x < n + 1; ++x)
	    for (int y = 1; y < n + 1; ++y) {
		if (south[x][y])
		    StdDraw.line(x, y, x + 1, y);
		if (north[x][y])
		    StdDraw.line(x, y + 1, x + 1, y + 1);
		if (west[x][y])
		    StdDraw.line(x, y, x, y + 1);
		if (east[x][y])
		    StdDraw.line(x + 1, y, x + 1, y + 1);
	    }

	StdDraw.show();
	StdDraw.pause(1000);
    }

    private boolean done;

    public void solve() {
	for (int x = 1; x < n + 1; ++x)
	    for (int y = 1; y < n + 1; ++y)
		visited[x][y] = false;

	this.done = false;
	// solveDFS(1, 1);
	solveBFS();
    }

    // DFS
    private void solveDFS(int x, int y) {
	if (x == 0 || y == 0 || x == n + 1 || y == n + 1)
	    return;
	if (done || visited[x][y])
	    return;

	visited[x][y] = true;

	StdDraw.setPenColor(Color.blue);
	StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
	StdDraw.show();
	StdDraw.pause(50);

	if (x == lampX && y == lampY)
	    done = true;

	if (!north[x][y]) {
	    StdDraw.setPenColor(Color.white);
	    StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
	    solveDFS(x, y + 1);
	}
	if (!east[x][y]) {
	    StdDraw.setPenColor(Color.white);
	    StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
	    solveDFS(x + 1, y);
	}
	if (!south[x][y]) {
	    StdDraw.setPenColor(Color.white);
	    StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
	    solveDFS(x, y - 1);
	}
	if (!west[x][y]) {
	    StdDraw.setPenColor(Color.white);
	    StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
	    solveDFS(x - 1, y);
	}

	if (done)
	    return;

	StdDraw.setPenColor(Color.gray);
	StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
	StdDraw.show();
	StdDraw.pause(50);
    }

    // BFS
    private void solveBFS() {
	StdDraw.setPenColor(Color.blue);

	ArrayDeque<long[]> q = new ArrayDeque<long[]>();
	q.add(new long[] { 1, 1, 1 });
	long curWay = 1;

	while (!q.isEmpty()) {
	    long[] curCell = q.remove();
	    int curX = (int) curCell[0];
	    int curY = (int) curCell[1];
	    curWay = curCell[2];

	    curWays[curX][curY] = curWay;
	    visited[curX][curY] = true;

	    StdDraw.filledCircle(curX + 0.5, curY + 0.5, 0.25);
	    StdDraw.show();
	    StdDraw.pause(30);

	    if (curX == lampX && curY == lampY)
		break;

	    long[] firstArr = new long[3];
	    long[] secondArr = new long[3];
	    long[] thirdArr = new long[3];
	    byte counter = 0;
	    if (!north[curX][curY] && curY != n) {
		if (!visited[curX][curY + 1]) {
		    firstArr[0] = curX;
		    firstArr[1] = curY + 1;
		    ++counter;
		}
	    }
	    if (!east[curX][curY] && curX != n) {
		if (!visited[curX + 1][curY]) {
		    if (counter == 0) {
			firstArr[0] = curX + 1;
			firstArr[1] = curY;
		    } else {
			secondArr[0] = curX + 1;
			secondArr[1] = curY;
		    }
		    ++counter;
		}
	    }
	    if (!south[curX][curY] && curY != 1) {
		if (!visited[curX][curY - 1]) {
		    if (counter == 0) {
			firstArr[0] = curX;
			firstArr[1] = curY - 1;
		    } else if (counter == 1) {
			secondArr[0] = curX;
			secondArr[1] = curY - 1;
		    } else {
			thirdArr[0] = curX;
			thirdArr[1] = curY - 1;
		    }
		    ++counter;
		}
	    }
	    if (!west[curX][curY] && curX != 1) {
		if (!visited[curX - 1][curY]) {
		    if (counter == 0) {
			firstArr[0] = curX - 1;
			firstArr[1] = curY;
		    } else if (counter == 1) {
			secondArr[0] = curX - 1;
			secondArr[1] = curY;
		    } else {
			thirdArr[0] = curX - 1;
			thirdArr[1] = curY;
		    }
		    ++counter;
		}
	    }

	    if (counter == 1) {
		firstArr[2] = curWay;
		q.add(firstArr);
	    } else if (counter == 2) {
		firstArr[2] = curWay * 3 - 1;
		secondArr[2] = curWay * 3;
		q.add(firstArr);
		q.add(secondArr);
	    } else if (counter == 3) {
		firstArr[2] = curWay * 3 - 1;
		secondArr[2] = curWay * 3;
		thirdArr[2] = curWay * 3 + 1;
		q.add(firstArr);
		q.add(secondArr);
		q.add(thirdArr);
	    }
	}
	findTheShortestWay(lampX, lampY);
    }

    private void findTheShortestWay(int xx, int yy) {
	StdDraw.setPenColor(Color.green);
	StdDraw.filledCircle(xx + 0.5, yy + 0.5, 0.25);
	StdDraw.show();
	visited[xx][yy] = false;

	long num = curWays[xx][yy];

	if (!north[xx][yy]) {
	    if (curWays[xx][yy + 1] != 0 && visited[xx][yy + 1]) {
		if (num == curWays[xx][yy + 1] || ((num + 1) / curWays[xx][yy + 1] == 3)
			|| (num / curWays[xx][yy + 1] == 3) || ((num - 1) / curWays[xx][yy + 1] == 3))
		    findTheShortestWay(xx, yy + 1);
	    }
	}
	if (!east[xx][yy]) {
	    if (curWays[xx + 1][yy] != 0 && visited[xx + 1][yy]) {
		if (num == curWays[xx + 1][yy] || ((num + 1) / curWays[xx + 1][yy] == 3)
			|| (num / curWays[xx + 1][yy] == 3) || ((num - 1) / curWays[xx + 1][yy] == 3))
		    findTheShortestWay(xx + 1, yy);
	    }
	}
	if (!south[xx][yy]) {
	    if (curWays[xx][yy - 1] != 0 && visited[xx][yy - 1]) {
		if (num == curWays[xx][yy - 1] || ((num + 1) / curWays[xx][yy - 1] == 3)
			|| (num / curWays[xx][yy - 1] == 3) || ((num - 1) / curWays[xx][yy - 1] == 3))
		    findTheShortestWay(xx, yy - 1);
	    }
	}
	if (!west[xx][yy]) {
	    if (curWays[xx - 1][yy] != 0 && visited[xx - 1][yy]) {
		if (num == curWays[xx - 1][yy] || ((num + 1) / curWays[xx - 1][yy] == 3)
			|| (num / curWays[xx - 1][yy] == 3) || ((num - 1) / curWays[xx - 1][yy] == 3))
		    findTheShortestWay(xx - 1, yy);
	    }
	}

    }

    public static void main(String[] args) {
	StdDraw.enableDoubleBuffering();

	Maze maze = new Maze(30);

	maze.solve();
    }

}