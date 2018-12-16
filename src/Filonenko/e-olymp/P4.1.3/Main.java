import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

            while(in.hasNextInt()) {
                long n = in.nextLong();
                int res = 0;
                for (int j = 1; j <= n; j++) {
                    res += j;
                }
                for (int i = 1; i < n; i++)
                    res -= in.nextLong();

                System.out.println(res);
            }
        
    }

}