package eolymp;

import java.util.Scanner;
import java.util.Stack;

public class Eolymp3Stack {
    static int a, b, c;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        int n, x0;
        n = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();
        x0 = sc.nextInt();


        Stack<Long> numbers = new Stack<>();
        Stack<Long> minimums = new Stack<>();

        long current = (x0);
        long answer = 0;
        long currentMinimum=0;//TODO

        for (int i = 0; i < n; i++) {
            current=next(current);
            if (current % 5 > 1){
                numbers.push(current);
                if (current<=currentMinimum){
                    minimums.push(current);
                }
                if (numbers.size()==1){
                    minimums.removeAllElements();
                    minimums.push(current);
                }
            }
            else if (numbers.size() > 0){
                if( numbers.pop()==currentMinimum&&minimums.size()>0){
                    minimums.pop();
                }
            }
            if (minimums.size()>0) currentMinimum=minimums.peek();
            else currentMinimum=0;

            if (numbers.size()>0) answer += currentMinimum;


        }

        System.out.println(answer);

    }

    private static long next(long x) {
        return ((a * x * x + b * x + c) / 100) % 1000000;
    }

    private static long minimum(Stack<Long> st) {
        if (st.size() == 0) return 0;
        long minimum = st.get(0);
        for (int i = 0; i < st.size(); i++) {
            minimum = Math.min(minimum, st.get(i));
        }
        return minimum;
    }
}