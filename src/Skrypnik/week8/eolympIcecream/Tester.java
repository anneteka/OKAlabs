package week8.eolympIcecream;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Tester {

    static int max = 10001;
    static int n, k;
    static int[] m = new int[max];

    static boolean check(int value) {
        int i, stall = 1, len = 0;
        for (i = 1; i < n; i++) {
            len += m[i] - m[i - 1];
            if (len >= value) {
                len = 0;
                stall++;
            }
        }
        return stall >= k;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringTokenizer st = new StringTokenizer(s);
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        s = scanner.nextLine();
        st = new StringTokenizer(s);
        for (int i = 0; i < n; i++)
            m[i] = Integer.parseInt(st.nextToken());
        int left = 0;
        int right = 1000000000;
        while (left <= right) {
            int middle = (left + right) / 2;
            if (check(middle)) left = middle + 1;
            else right = middle - 1;
        }
        System.out.println(left-1);
    }
}
