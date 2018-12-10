package eolymp;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Eolymp11Graphs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[][] matrix = new int[n][n];
        String[] line;
        for (int i = 0; i < n; i++) {
            line=sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j]=Integer.parseInt(line[j]);
            }
        }

        System.out.println(check(matrix, n));
    }

    static String check(int[][] matrix, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]!=matrix[j][i]||matrix[i][j]>1) return "NO";
                if (i==j&&matrix[i][j]>0) return "NO";
            }
        }
        return "YES";
    }
}
