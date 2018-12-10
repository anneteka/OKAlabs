import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        BigInteger numberOfGenerations = new BigInteger(line[0]);
        line = reader.readLine().split(" ");
        BigInteger first = new BigInteger(line[0]);
        line = reader.readLine().split(" ");
        BigInteger second = new BigInteger(line[0]);
        System.out.println(commonParent(first, second));
    }

    private static BigInteger commonParent(BigInteger first, BigInteger second) {
        if (first.equals(second)) return first;
        if (first.compareTo(second) < 0) return commonParent(first, second.divide(BigInteger.valueOf(2)));
        return commonParent(first.divide(BigInteger.valueOf(2)), second);
    }
}
