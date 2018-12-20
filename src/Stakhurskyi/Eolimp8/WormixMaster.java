package Stakhurskyi.Eolimp8;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WormixMaster {
    static int[] points;
    static int[] times;
    static int V;

    public static void main(String[] args) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("input.txt"));
        } catch (FileNotFoundException e) {

        }
        String[] init = in.readLine().split(" ");
        int n = Integer.parseInt(init[0]);
        V = Integer.parseInt(init[1]);
        points = new int[n];
        times = new int[n];
        int maxTime = 0;
        for (int i = 0; i < n; i++) {
            init = in.readLine().split(" ");
            points[i] = Integer.parseInt(init[0]);
            times[i] = Integer.parseInt(init[1]);
            maxTime += times[i];
        }

        int[][] table = new int[n+1][maxTime];

        for (int i = 1; i <=n; i++) {
            for (int j = 0; j < maxTime; j++) {
                if (times[i-1] > j) {
                    table[i][j] = table[i - 1][j];
                } else {
                    table[i][j] = Math.max(table[i - 1][j], table[i - 1][j - times[i-1]] + points[i-1]);
                }
            }
        }
        for(int i = 0; i<maxTime; i++){
            if(table[n][i]>=V){
                System.out.println(i);
                System.exit(0);
            }
        }
        System.out.println("-1");
    }


}