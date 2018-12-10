package eolymp;

import java.util.Scanner;

public class Eolymp4REplacement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] numbers = new int[n+1];
        for (int i = 0; i < n; i++) {
            int current = sc.nextInt();
            if (current<=n) numbers[current] = 1;
        }
        System.out.println(missing(numbers));
    }
    static int missing(int[] numbers){
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i]==0) return i;
        }
        return 0;
    }
}
