package PW13_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EODayOfUnion {

    public static void main(String[] args) throws IOException {

	BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	PrintWriter pw = new PrintWriter(System.out);

	int n = Integer.parseInt(bf.readLine());

	// 0 - x; 1 - y; 2 - nearTownX; 3 - newrTownY;
	int[][] towns = new int[n][4];
	double[] minDistanse = new double[n];
	boolean[] isVisited = new boolean[n];

	for (int i = 0; i < n; ++i) {
	    String[] st = bf.readLine().split(" ");
	    towns[i][0] = Integer.parseInt(st[0]);
	    towns[i][1] = Integer.parseInt(st[1]);
	    towns[i][2] = 10001;
	    towns[i][3] = 10001;
	    minDistanse[i] = 999999999;
	}

	double sum = 0;

	for (int i = 0; i < n; ++i) {

	    int[] minDistanceTown = null;
	    int idTown = 0;
	    for (int j = 0; j < n; ++j)
		if (!isVisited[j])
		    if (minDistanceTown == null || minDistanse[j] < minDistanse[idTown]) {
			minDistanceTown = towns[j];
			idTown = j;
		    }
	    isVisited[idTown] = true;

	    if (minDistanceTown[2] != 10001) {
		sum += Math.sqrt((minDistanceTown[0] - minDistanceTown[2]) * (minDistanceTown[0] - minDistanceTown[2])
			+ (minDistanceTown[1] - minDistanceTown[3]) * (minDistanceTown[1] - minDistanceTown[3]));
	    }

	    for (int j = 0; j < n; ++j) {
		double distance = Math.sqrt((minDistanceTown[0] - towns[j][0]) * (minDistanceTown[0] - towns[j][0])
			+ (minDistanceTown[1] - towns[j][1]) * (minDistanceTown[1] - towns[j][1]));
		if (!isVisited[j] && distance < minDistanse[j]) {
		    minDistanse[j] = distance;
		    towns[j][2] = minDistanceTown[0];
		    towns[j][3] = minDistanceTown[1];
		}
	    }

	}
	pw.println(sum);
	pw.flush();
    }

}