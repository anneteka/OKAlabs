package eolymp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Eolymp10Evolution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        int n = Integer.parseInt(reader.readLine());
        System.out.println(ancestor(reader.readLine(), reader.readLine()));
    }

    static BigInteger ancestor(String ch1, String ch2) {
        BigInteger child1 = new BigInteger(ch1);
        BigInteger child2 = new BigInteger(ch2);
       // int bits1 = countBits(child1);
        //int bits2 = countBits(child2);
        while (!child1.equals(child2)) {
           if (child1.compareTo(child2)>0)child1=child1.shiftRight(1);
           else child2=child2.shiftRight(1);
           // System.out.println(child1+" "+child2);
        }
        return child1;
    }

    static int countBits(BigInteger b) {
        int counter = 0;
        while (!b.equals(BigInteger.ZERO)) {
            b = b.shiftRight(1);
            counter++;
        }
        return counter;
    }
}
