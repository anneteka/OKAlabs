package week14.EolympRoads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        PrintWriter pw = new PrintWriter(new File("output.txt"));
        while (true) {
            String str = br.readLine();
            if (str == null)
                break;
            int n = Integer.parseInt(str);
            int arrayCity[][] = new int[n][n];
            for (int i = 0; i < n; i++) {
                str = br.readLine();
                String strArray[] = str.split("");
                for (int j = 0; j < n; j++) {
                    arrayCity[i][j] = Integer.parseInt(strArray[j]);
                }
            }

            pw.println(findRoads(n, arrayCity));
        }
        br.close();
        pw.close();
    }

    private int findRoads(int n, int[][] array) {
        int minLenRoads = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++)
            minLenRoads = Math.min(minLenRoads, minRoadBetweenST(0, i, array));
        return minLenRoads;
    }

    private int minRoadBetweenST(int s, int t, int[][] array) {
        int arrayCopy[][] = new int[array.length][array.length];

        for (int i = 0; i < arrayCopy.length; i++) {
            for (int j = 0; j < arrayCopy.length; j++) {
                arrayCopy[i][j] = array[i][j];
            }
        }
        boolean used[] = new boolean[array.length];
        int x, road = 0;
        do {
            for (int i = 0; i < used.length; i++) {
                used[i] = false;
            }
            x = findCapacity(s, t, Integer.MAX_VALUE, used, arrayCopy);
            road += x;
        } while (x != 0 && road != 0);
        return road;
    }

    private int findCapacity(int x, int t, int roadCurrent, boolean used[], int res[][]) {
        if (x == t)
            return roadCurrent;
        if (used[x])
            return 0;
        used[x] = true;
        for (int Road, y = 0; y < res.length; y++) {
            if (res[x][y] > 0 && (Road = findCapacity(y, t, Math.min(roadCurrent, res[x][y]), used, res)) > 0) {
                res[x][y] -= Road;
                res[y][x] += Road;
                return Road;
            }
        }
        return 0;
    }

}
