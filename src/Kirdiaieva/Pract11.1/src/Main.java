import java.util.*;

public class Main {

    public static void main(String[] args) {
        int i, j, n;
        int[][] m = new int[101][101];
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
            {
                m[i][j] = in.nextInt();
                if (i == j && m[i][j]==1)
                {
                    System.out.println("NO");
                    return;
                }
            }
        }
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                if (m[i][j] != m[j][i])
                {
                    System.out.println("NO");
                    return;
                }
        }
        System.out.println("YES");
    }

}
