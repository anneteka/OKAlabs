import java.io.PrintWriter;
import java.util.Scanner;


public class Main{



    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out, true);
        while (in.hasNextInt()) {
            int i = in.nextInt();
            int j = in.nextInt();

            FormulaList fl = new FormulaList(i, j);

            int max = fl.findMaxLength(); //for example
            //Your code
            out.printf("%d %d %d\n", i, j, max);
        }
    }
}