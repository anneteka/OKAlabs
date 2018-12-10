package eolymp;

import java.util.*;

public class Eolymp8Wormix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] info = sc.nextLine().split(" ");
        int n = Integer.parseInt(info[0]);
        int k = Integer.parseInt(info[0]);
        int[][] data = new int[n][2];
        System.out.println(findTime(n, k, data));
    }

    static int findTime(int n, int k, int[][] data) {
        LinkedHashMap<Integer, Integer> current = new LinkedHashMap<>();
        LinkedHashMap<Integer, Integer> other = new LinkedHashMap<>();
        for (int i = 0; i < n; i++) {
            other.put(data[i][0], data[i][1]);
        }

        int sumK = 0; //сумма очков
        int sumT = 0; //сумма времени
        while (current.size()<n){
            if (sumK<k) {
//                other.
            }
        }
        return sumK<k?-1:sumT;
    }
}


//5 12
//6 2
//5 5
//1 5
//10 10
//4 4


/*
8 10
1 5
11 11
2 8
7 3
4 15
4 10
10 12
1 1
--------
1 1
1 5
2 8
4 10
4 15
7 3
10 12
11 11
--------
1 1
1 5
2 8
4 10
4 15  -> 12 39
----
7 3
10 11
11 11
--------
1 1
2 8
4 10
4 15
7 3 ->
----
1 5
10 11
11 11

*/