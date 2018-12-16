import java.io.PrintWriter;
import java.util.Scanner;


public class Main{



    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.hasNextInt()) {
            int N = in.nextInt();
            int[] colors = new int[9];
            int j [] = new int[N];
            for (int o = 0; o < N; o++){
                j[o] = in.nextInt();
                colors[j[o]-1]++;
                }
            int max = 0;
            for (int p=0;p<9;p++) {
                if (colors[p]>max)
                    max = colors[p];
            }
            out.printf("%d\n",N - max);
        }
    }
}