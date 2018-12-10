package practice5.competition;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Palindrome {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        for (int i = 0; i < 10; i++) {
            System.out.println(palindrome(reader.readLine()));
        }
        reader.close();
    }

    static boolean palindrome(String n) {
        char[] numbers = n.toCharArray();
        int length = numbers.length;
        for (int i = 0; i < (length + 1) / 2; i++)
            if (numbers[i] != numbers[length - 1 - i]) return false;
        return true;
    }
}
