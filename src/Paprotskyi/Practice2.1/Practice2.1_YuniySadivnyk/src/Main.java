import java.io.PrintWriter;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.hasNextInt()) {
            int i = in.nextInt();
            //Your code
            int max = water(i); //for example

            out.printf("%d\n", max);
        }
    }

    private static int water(int levels) {
        int liters = 1;
        int previous = 0;

        for (int i = 0; i < levels; i++) {
            liters += previous + 2;
            previous +=2;
        }
        return liters;

    }

}