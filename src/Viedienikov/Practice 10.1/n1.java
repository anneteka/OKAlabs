import java.math.BigInteger;
import java.util.Scanner;

public class n1 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        do {
            int n = scan.nextInt();
            BigInteger el1 = scan.nextBigInteger();
            BigInteger el2 = scan.nextBigInteger();

            while (!el1.equals(el2)) {
                if (el1.compareTo(el2)==1)  el1=el1.divide(BigInteger.valueOf(2));
                else  el2=el2.divide(BigInteger.valueOf(2)); ;
            }

            System.out.println(el1);
        }while(scan.hasNextInt());
        scan.close();
    }
}