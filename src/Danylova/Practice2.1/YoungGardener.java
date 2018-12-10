
import java.io.PrintWriter;
        import java.util.Scanner;


public class Index{



    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.hasNextInt()) {
            int j = in.nextInt();
            int sum;
            sum = 1;
            for(int i = 1; i <= j; i++ ){
                sum+=2*i;
            }

            out.printf("%d\n", sum);
        }
    }
}