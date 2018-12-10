import java.io.PrintWriter;
import java.util.Scanner;


public class Index {
    public static void main(String a[]) {

        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.hasNextInt()) {
            int n1 = in.nextInt();
            int n2 = in.nextInt();

            int maxNumber, number;
            maxNumber = 1;
            for (long i = n1; i <= n2; i++) {
                long i1;
                i1 = i;
                number = 1;
                while (i1 != 1) {
                    if (i1 % 2 == 1) i1 = 3 * i1 + 1;
                    else i1 /= 2;
                    number++;
                }
                if (number > maxNumber) maxNumber = number;
            }

            out.printf("%d %d %d\n", n1, n2, maxNumber);
        }
    }
}
