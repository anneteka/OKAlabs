
import java.io.PrintWriter;
import java.util.Scanner;


public class Index{



    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            int k = in.nextInt();
            int sum = 1;
            for(int i = 0; i < n; ++i)
            {
                if(sum > k)
                    sum-=k;
                sum*=2;
            }
            out.printf("%d\n", sum);
        }
    }
}