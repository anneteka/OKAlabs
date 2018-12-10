import java.math.BigInteger;
import java.util.Scanner;

public class Evolution

{

    public static void main(String args[])
    {
        Scanner scan = new Scanner(System.in);
        BigInteger n1, n2, res2;
        BigInteger TWO = BigInteger.valueOf(2);
        res2 = scan.nextBigInteger();
        n1 = scan.nextBigInteger();
        n2 = scan.nextBigInteger();
        while(!n1.equals(n2))
        {
            if(n1.compareTo(n2)>0) n1 = n1.divide(TWO);
            else n2 = n2.divide(TWO);

        }
        System.out.println(n1.toString());
    }
}
