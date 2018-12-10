package Chernova.week8.olymp;

import java.util.Scanner;
import java.util.StringTokenizer;

public class IceCream {

    static int kiosks;
    static int salers;
    static Long[] distances;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        StringTokenizer tokenizer = new StringTokenizer(s);
        if (tokenizer.hasMoreTokens()) {
            kiosks = Integer.parseInt(tokenizer.nextToken(), 10);
            if (kiosks < 3 || kiosks > 10000) {
                System.out.println("Wrong format of the number!");
                return;
            }
            distances = new Long[kiosks];
            if (tokenizer.hasMoreTokens()) {
                salers = Integer.parseInt(tokenizer.nextToken(), 10);
                if (salers < 2 || salers > kiosks) {
                    System.out.println("Wrong format of the number!");
                    return;
                }
                s = scanner.nextLine();
                tokenizer = new StringTokenizer(s);
                for (int i = 0; i < kiosks; i++) {
                    if (tokenizer.hasMoreTokens()) {
                        Long number = Long.parseLong(tokenizer.nextToken(), 10);
                        if (number < 1 || number > 1000000000) {
                            System.out.println("Wrong format of the number!");
                            return;
                        }
                        distances[i] = number;
                    }
                }
                Long first = 0L;
                Long last = 1000000000L;
                while (first <= last) {
                    Long middle = (first + last) / 2;
                    if (check(middle)) first = middle + 1;
                    else last = middle - 1;
                }
                System.out.println(first - 1);
            }
        }
    }

    static boolean check(Long value) {
        int counter = 1;
        Long sumOfDistances = 0L;
        for (int i = 1; i < kiosks; i++) {
            sumOfDistances += distances[i] - distances[i - 1];
            if (sumOfDistances >= value) {
                sumOfDistances = 0L;
                counter++;
            }
        }
        return counter >= salers;
    }
}
