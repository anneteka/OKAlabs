import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.hasNextInt()) {
            long n = in.nextInt();
            long a = in.nextInt();
            long b = in.nextInt();
            long c = in.nextInt();
            long x = in.nextInt();
            long answer = 0;
            Stack<Long> s = new Stack<>();

            for (int i=0; i<n; i++){

                x =(a*x*x + b*x + c) / 100 % 1000000;

                if (x % 5 < 2){
                    if (!s.empty())
                        s.pop();
                    if (!s.empty())
                        answer += s.lastElement();
                }
                else {
                    if (s.isEmpty())
                        s.push(x);
                    else
                        s.push(Math.min(x,s.lastElement()));
                        answer += s.lastElement();
                }
            }
            out.printf("%d\n",answer);
        }

    }
}
