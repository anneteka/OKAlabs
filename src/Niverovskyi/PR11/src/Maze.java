

import java.util.LinkedList;
import java.util.List;

public class Maze {

	String sh = "";
	String sv = "";

	private int N;
	private boolean[][] north;
	private boolean[][] east;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] visited;
	private boolean done = false;
	private int[][] pathNo;
	private String[][] hashPath;
	private int count = 0;

	public Maze(int n) {
		N = n;

		for (int i = 0; i < 2 * n; i++) {
			for (int j = 0; j < n; j++) {
				if (i % 2 == 0) {
					sh = sh + "+-";

					if (j == n - 1) {
						sh = sh + "+";

					}
				} else if (i % 2 == 1) {
					sv = sv + "| ";

					if (j == n - 1) {
						sv = sv + "|";

					}
				}

			}
			if (i % 2 == 0) {
				System.out.println(sh);
				sh = "";
			} else if (i % 2 == 1) {
				System.out.println(sv);
				sv = "";
			}

			if (i == 2 * n - 1) {
				for (int j = 0; j < n; j++) {
					System.out.print("+-");
					if (j == n - 1) {
						System.out.print("+");
					}
				}
			}

		}
		System.out.println();

		init();
		generate();

		solve();
		displayDFSNos();
		System.out.println();
		System.out.println("DFS Path");
		displayPath();

		BFSPath();
		System.out.println();
		System.out.println("BFS Path");
		displayBFSNos();
		displayPath();

	}

	public int checkPath(int x, int y) {

		if (x < 1 || y < 1 || x > N || y > N) {
			return 2;
		}

		if (x == N && y == N) {
			visited[x][y] = false;
			count++;
			pathNo[x - 1][y - 1] = count;
			hashPath[x - 1][y - 1] = "#";
			return 1;
		}

		if (!visited[x][y]) {
			return 2;
		}

		System.out.print(" count " + count + "\n");
		visited[x][y] = false;
		count++;
		pathNo[x - 1][y - 1] = count;
		hashPath[x - 1][y - 1] = "#";

		return 0;
	}

	public void displayBFSNos() {

		for (int k = 0, i = 1; k < 2 * N + 1; k++) {

			if (k % 2 == 0) {
				sh = "";
				for (int j = 1; j < north.length - 1; j++) {
					if (north[i][j] == true) {
						sh = sh + "+-";

					} else {
						sh = sh + "+ ";
					}
					if (j == north.length - 2) {
						sh = sh + "+";
					}
				}

				System.out.println(sh);

			} else {
				sv = "";
				for (int j = 1; j < west.length - 1; j++) {
					if (west[i][j] == true) {
						sv = sv + "|" + pathNo[i - 1][j - 1];

					} else {
						sv = sv + " " + pathNo[i - 1][j - 1];
					}
					if (j == west.length - 2) {
						if (east[i][j] == true) {
							sv = sv + "|";
						} else if (east[i][j] == false) {
							sv = sv + "";
						}
					}

				}
				System.out.println(sv);

				i++;
			}

		}

	}

	public void BFSPath() {

		count = 0;

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				visited[i][j] = true;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				pathNo[i][j] = 0;
			}
		}

		List<Integer> l1x = new LinkedList<Integer>();
		List<Integer> l2y = new LinkedList<Integer>();

		l1x.add(1);
		l2y.add(1);
		visited[1][1] = false;

		count++;
		pathNo[1 - 1][1 - 1] = count;
		hashPath[1 - 1][1 - 1] = "#";

		while (!l1x.isEmpty()) {
			int x = l1x.get(0);
			int y = l2y.get(0);

			l1x.remove(0);
			l2y.remove(0);
			if (checkPath(x, y) == 1) {
				break;
			}

			if (north[x][y] == false) {

				if (checkPath(x - 1, y) == 0) {
					l1x.add(x - 1);
					l2y.add(y);
				} else if (checkPath(x - 1, y) == 1) {
					break;
				}
			}
			if (west[x][y] == false) {

				if (checkPath(x, y - 1) == 0) {
					l1x.add(x);
					l2y.add(y - 1);
				} else if (checkPath(x, y - 1) == 1) {
					break;
				}

			}
			if (east[x][y] == false) {

				if (checkPath(x, y + 1) == 0) {
					l1x.add(x);
					l2y.add(y + 1);

				} else if (checkPath(x, y + 1) == 1) {
					break;
				}

			}
			if (south[x][y] == false) {

				if (checkPath(x + 1, y) == 0) {
					l1x.add(x + 1);
					l2y.add(y);
				} else if (checkPath(x + 1, y) == 1) {
					break;
				}

			}

		}
	}

	public void displayPath() {

		for (int k = 0, i = 1; k < 2 * N + 1; k++) {

			if (k % 2 == 0) {
				sh = "";
				for (int j = 1; j < north.length - 1; j++) {
					if (north[i][j] == true) {
						sh = sh + "+-";

					} else {
						sh = sh + "+ ";
					}
					if (j == north.length - 2) {
						sh = sh + "+";
					}
				}

				System.out.println(sh);

			} else {
				sv = "";
				for (int j = 1; j < west.length - 1; j++) {
					if (west[i][j] == true) {
						sv = sv + "|" + hashPath[i - 1][j - 1];

					} else {
						sv = sv + " " + hashPath[i - 1][j - 1];
					}
					if (j == west.length - 2) {
						if (east[i][j] == true) {
							sv = sv + "|";
						} else if (east[i][j] == false) {
							sv = sv + "";
						}
					}

				}
				System.out.println(sv);

				i++;
			}

		}

	}

	public void displayDFSNos() {

		for (int k = 0, i = 1; k < 2 * N + 1; k++) {

			if (k % 2 == 0) {
				sh = "";
				for (int j = 1; j < north.length - 1; j++) {
					if (north[i][j] == true) {
						sh = sh + "+-";

					} else {
						sh = sh + "+ ";
					}
					if (j == north.length - 2) {
						sh = sh + "+";
					}
				}

				System.out.println(sh);

			} else {
				sv = "";
				for (int j = 1; j < west.length - 1; j++) {
					if (west[i][j] == true) {
						sv = sv + "|" + pathNo[i - 1][j - 1];

					} else {
						sv = sv + " " + pathNo[i - 1][j - 1];
					}
					if (j == west.length - 2) {
						if (east[i][j] == true) {
							sv = sv + "|";
						} else if (east[i][j] == false) {
							sv = sv + "";
						}
					}

				}
				System.out.println(sv);

				i++;
			}

		}

	}

	public void solve() {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				visited[i][j] = false;
			}
		}

		solve(1, 1);
	}

	public void solve(int x, int y) {

		if (x == 0 || y == 0 || x == N + 1 || y == N + 1) {
			return;
		}

		if (done || visited[x][y]) {
			return;
		}

		visited[x][y] = true;
		count++;
		pathNo[x - 1][y - 1] = count;
		hashPath[x - 1][y - 1] = "#";

		if (x == N && y == N) {
			done = true;
		}

		if (!north[x][y]) {
			solve(x - 1, y);
		}
		if (!east[x][y]) {
			solve(x, y + 1);
		}
		if (!south[x][y]) {
			solve(x + 1, y);
		}
		if (!west[x][y]) {
			solve(x, y - 1);
		}

		if (done) {
			return;
		}

		hashPath[x - 1][y - 1] = " ";

	}

	private void init() {
		visited = new boolean[N + 2][N + 2];
		for (int x = 0; x < N + 2; x++) {
			visited[x][0] = visited[x][N + 1] = true;
		}
		for (int y = 0; y < N + 2; y++) {
			visited[0][y] = visited[N + 1][y] = true;
		}
		north = new boolean[N + 2][N + 2];
		east = new boolean[N + 2][N + 2];
		south = new boolean[N + 2][N + 2];
		west = new boolean[N + 2][N + 2];
		for (int x = 0; x < N + 2; x++) {
			for (int y = 0; y < N + 2; y++) {
				north[x][y] = east[x][y] = south[x][y] = west[x][y] = true;
			}
		}

		pathNo = new int[N][N];
		hashPath = new String[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				hashPath[i][j] = " ";
			}
		}

	}

	public void displayArrays() {

		north[1][1] = south[0][1] = false;

		south[N][N] = north[N + 1][N] = false;

		System.out.println("north");
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				System.out.print(north[i][j]);
			}
			System.out.println();
		}

		System.out.println();

		System.out.println("south");
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				System.out.print(south[i][j]);
			}
			System.out.println();
		}

		System.out.println();

		System.out.println("West");
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				System.out.print(west[i][j]);
			}
			System.out.println();
		}

		System.out.println();

		
		System.out.println("East");
		for (int i = 0; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				System.out.print(east[i][j]);
			}
			System.out.println();
		}

	}

	public void displayMaze() {

		for (int k = 0, i = 1; k < 2 * N + 1; k++) {

			if (k % 2 == 0) {
				sh = "";
				for (int j = 1; j < north.length - 1; j++) {
					if (north[i][j] == true) {
						sh = sh + "+-";

					} else {
						sh = sh + "+ ";
					}
					if (j == north.length - 2) {
						sh = sh + "+";
					}
				}

				System.out.println(sh);

			} else {
				sv = "";
				for (int j = 1; j < west.length - 1; j++) {
					if (west[i][j] == true) {
						sv = sv + "| ";

					} else {
						sv = sv + "  ";
					}
					if (j == west.length - 2) {
						if (east[i][j] == true) {
							sv = sv + "|";
						} else if (east[i][j] == false) {
							sv = sv + "";
						}
					}

				}
				System.out.println(sv);

				i++;
			}

		}

	}

	public void generate(int x, int y) {
		visited[x][y] = true;

		while (!visited[x][y + 1] || !visited[x + 1][y] || !visited[x][y - 1]
				|| !visited[x - 1][y]) {

			while (true) {
				double r = Math.random();
				if (r < 0.25 && !visited[x][y + 1]) {
					east[x][y] = west[x][y + 1] = false;
					generate(x, y + 1);
					break;
				} else if (r >= 0.25 && r < 0.50 && !visited[x + 1][y]) {
					south[x][y] = north[x + 1][y] = false;
					generate(x + 1, y);
					break;
				} else if (r >= 0.5 && r < 0.75 && !visited[x][y - 1]) {
					west[x][y] = east[x][y - 1] = false;
					generate(x, y - 1);
					break;
				} else if (r >= 0.75 && r < 1.00 && !visited[x - 1][y]) {
					north[x][y] = south[x - 1][y] = false;
					generate(x - 1, y);
					break;
				}
			}
		}
	}

	public void generate() {
		generate(1, 1);
	}

	public static void main(String[] args) {
		int n = 4;
		Maze maze = new Maze(n);
	}
}