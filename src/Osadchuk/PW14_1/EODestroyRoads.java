package PW14_1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EODestroyRoads {

    public static void main(String[] args) throws IOException {
	Scanner sc = new Scanner(System.in);
	PrintWriter pw = new PrintWriter(System.out);

	while (sc.hasNextLine()) {

	    int n = Integer.parseInt(sc.nextLine());
	    int towns[][] = new int[n][n];
	    for (int i = 0; i < n; i++) {
		String str = sc.nextLine();
		String strArray[] = str.split("");
		for (int j = 0; j < n; j++) {
		    towns[i][j] = Integer.parseInt(strArray[j]);
		}
	    }

	    int minRoad = 999999999;
	    for (int i = 1; i < n; i++) {
		minRoad = Math.min(minRoad, findMinRoad(i, towns));
	    }

	    pw.println(minRoad);
	}
	pw.flush();
    }

    private static int findMinRoad(int t, int[][] towns) {
	int size = towns.length;
	int arrayCopy[][] = new int[size][size];

	for (int i = 0; i < size; i++) {
	    for (int j = 0; j < size; j++) {
		arrayCopy[i][j] = towns[i][j];
	    }
	}
	boolean visited[] = new boolean[size];
	int x, road = 0;
	do {
	    for (int i = 0; i < visited.length; ++i) {
		visited[i] = false;
	    }
	    x = findCap(0, t, 999999999, visited, arrayCopy);
	    road += x;
	} while (x != 0 && road != 0);
	return road;
    }

    private static int findCap(int x, int t, int roadCurrent, boolean vis[], int res[][]) {
	if (x == t) {
	    return roadCurrent;
	}
	if (vis[x]) {
	    return 0;
	}
	vis[x] = true;
	for (int road, y = 0; y < res.length; ++y) {
	    if (res[x][y] > 0 && (road = findCap(y, t, Math.min(roadCurrent, res[x][y]), vis, res)) > 0) {
		res[x][y] -= road;
		res[y][x] += road;
		return road;
	    }
	}
	return 0;
    }

}