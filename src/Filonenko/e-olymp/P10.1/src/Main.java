import java.math.BigInteger;
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            int stage = scanner.nextInt();
            BigInteger first = scanner.nextBigInteger();
            BigInteger second = scanner.nextBigInteger();
            BigInteger[] parents = new BigInteger[stage];
            for (int i = 0; i < stage; i++) {
                parents[i] = first;
                first = first.divide(BigInteger.valueOf(2));
            }
            for (int i = 0; i < stage; i++) {
                for (BigInteger parent : parents) {
                    if(second.equals(parent)) {
                        System.out.println(second);
                        return;
                    }
                }
                second = second.divide(BigInteger.valueOf(2));
            }
        }
        catch(InputMismatchException e) {
            System.out.println(0);
        }
    }
}

