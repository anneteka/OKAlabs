import java.io.PrintWriter;
import java.util.Scanner;

public class Main{


    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.hasNextInt()) {
            int i = in.nextInt();
            int j = in.nextInt();
            //Your code
            int max = rabbits(i,j); //for example

            out.printf("%d\n", max);
        }
    }

    private static int rabbits(int months, int eaten) {
        int rabbitsCount = 1;

        for (int i=0; i<months; i++){
            if (rabbitsCount > eaten){
                rabbitsCount -=eaten;
            }
            rabbitsCount *=2;
        }
        return rabbitsCount;

    }


}