import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Main{



    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (sc.hasNextInt()) {
            int N = sc.nextInt();
            int m[] = new int[N+1];
            Arrays.fill(m, 0);
            for (int i = 1; i < N; i++) {
                int a = sc.nextInt();
                if (a <= N) m[a]++;
            }

            int answer = 0;

            for (int i = 1; i <= N; i++)
                if (m[i] == 0) {
                    answer = i;
                }

            out.printf("%d\n", answer);
        }
    }
}