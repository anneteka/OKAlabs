import java.util.*;

public class IceCream {
    private static final int MAX_N = 10001;
    private static final int MAX_COORD = 1000000000;


    private static boolean check(int value, int n, int k, int [] m) {
        int i, stall = 1, len = 0;
        for (i = 1; i < n; i++)
        {
            len += m[i] - m[i - 1];
            if (len >= value) {
                len = 0;
                stall++;
            }
        }
        return stall >= k;
    }

    public static void main(String[] args) {
        int k, n, left, right;
        int [] m = new int [MAX_N];
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        k = in.nextInt();
        for (int i = 0; i < n; i++){
            m [i] = in.nextInt();
        }
        left = 0;
        right = MAX_COORD;
        while (left<=right){
            int middle = (left + right) / 2;
            if (check(middle, n, k, m))
                left = middle+1;
            else right = middle-1;
        }
        System.out.println(left-1);
    }
}