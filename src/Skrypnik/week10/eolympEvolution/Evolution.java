package week10.eolympEvolution;

import java.math.BigInteger;
import java.util.Scanner;

public class Evolution {

    public static void main(String[] args) {

        int n;
        BigInteger number1;
        BigInteger number2;

        try {
            Scanner scanner = new Scanner(System.in);
            n = Integer.parseInt(scanner.nextLine());
            if (n < 1 || n > 100) {
                System.out.println("Wrong format of the number!");
                return;
            }
            number1 = new BigInteger(scanner.nextLine());
            if (number1.compareTo(BigInteger.valueOf(0)) < 0) {
                System.out.println("Wrong format of the number!");
                return;
            }

            number2 = new BigInteger(scanner.nextLine());
            if (number2.compareTo(BigInteger.valueOf(0)) < 0) {
                System.out.println("Wrong format of the number!");
                return;
            }
            while (!number1.equals(number2)) {
                if (number1.compareTo(number2) > 0) number1 = number1.divide(BigInteger.valueOf(2));
                else number2 = number2.divide(BigInteger.valueOf(2));
            }
            System.out.println(number1);
        } catch (NumberFormatException e) {
            System.out.println("Wrong format of the number!");
        }
    }
}