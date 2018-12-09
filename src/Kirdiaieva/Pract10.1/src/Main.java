import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args0) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger x = sc.nextBigInteger();
        BigInteger y = sc.nextBigInteger();
        BigInteger one = new BigInteger("1");
        BigInteger mone = new BigInteger("-1");
        BigInteger two = new BigInteger("2");
        while (!x.equals(y)) {
            if( x.compareTo(y)>0 && x.compareTo(one)>0) {
                if(x.mod(two).equals(1)) x = x.add(mone);
                x = x.divide(two);
            } else if(y.compareTo(one)>0) {
                if(y.mod(two).equals(1)) y = y.add(mone);
                y = y.divide(two);
            } else break;

        }
        System.out.println(x);
    }

}