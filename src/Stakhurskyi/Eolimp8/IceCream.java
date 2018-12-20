package Stakhurskyi.Eolimp8;

import java.io.IOException;
import java.util.Scanner;

public class IceCream {


    public static final int MAX = 10001;
    public static final int INF = 1000000000;

    private static int k, n;
    private static int points[] = new int[MAX];

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();

        for(int i=0;i<n;i++){
            points[i] = sc.nextInt();
        }

        int left = 0;
        int right = INF;
        while (left <= right)
        {
            int Middle = (left + right) / 2;
            if (check(Middle))
                left = Middle + 1;
            else
                right = Middle - 1;
        }
        System.out.println(left - 1);
    }

    private static boolean check(int Value)
    {
        int stall = 1;
        int len = 0;
        for (int i = 1; i < n; i++)
        {
            len += points[i] - points[i - 1];
            if (len >= Value) {
                len = 0;
                stall++;
            }
        }
        return stall >= k;
    }
}
