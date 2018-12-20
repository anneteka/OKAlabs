package Stakhurskyi.Eolimp2;

import java.util.*;
import java.io.*;

public class RabbitOne {
    private int calculation(int n, int k) {
        int z = 1;
        for (int i = 1; i <= n; i++) {
            if (z > k) {
                z = z - k;
            }
            z = z * 2;

        }
        return z;
    }

    public static void main(String[] argv) throws IOException {
        Scanner in = new Scanner(System.in);
        RabbitOne one = new RabbitOne();
        int n = in.nextInt();
        int k = in.nextInt();
        n = one.calculation(n, k);

        System.out.println(n);
    }

}