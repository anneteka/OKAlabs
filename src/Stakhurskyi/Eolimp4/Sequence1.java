package Stakhurskyi.Eolimp4;

import java.util.Arrays;
import java.util.Scanner;

public class Sequence1 {

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N-1; i++) {
            int x = in.nextInt();
            arr[x - 1] = 1;
        }
        int res=N;
        for (int j = 0; j < N-1; j++) {
            if (arr[j] == 0) {
                res = j + 1;
                break;
            }
        }
        System.out.println(res);
    }

}

