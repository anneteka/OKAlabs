package eolymp;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Eolymp7DoctorWho {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        PrintWriter output = new PrintWriter(new File("output.txt"));
        String line;
        Scanner sc = new Scanner(System.in);
        while (true)
            System.out.println(findOkFail(sc.nextLine()));
//        while ((line = reader.readLine()) != null) {
//            System.out.println(findOkFail(line));
//        }
    }

    static String findOkFail(String line) {
        line = "0 " + line;
        int[] heap = Arrays.stream(line.split(" "))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(heap);
        int n = heap.length - 1;
        while (heap[1] > 0 && heap[1] < 1001) {

            int del = delMax(heap, n);
            n--;
            if (del > n) return "fail";
            int counter = 0;
            for (int i = n; i > n - del; i--) {
                heap[i]--;
                if (heap[i] == 0) {
                    heap[i] = 1001;
                    counter++;
                }
            }
            Arrays.sort(heap);
            n -= counter;
        }
        return "ok";
    }

    private static int delMax(int[] heap, int n) {
        int ans = heap[n];
        heap[n] = 1002;
        return ans;
    }
}
//    static String findOkFail(String line) {
//        Heap current = new Heap(
//                Arrays.stream(line.split(" "))
//                        .map(String::trim)
//                        .mapToInt(Integer::parseInt)
//                        .toArray()
//        );
//        int[] heap = current.heap;
//        current.sort();
//        while (heap[1] > 0 && heap[1] < 1001) {
//
//            int del = current.delMax();
//            if (del > current.n) return "fail";
//            int counter = 0;
//            for (int i = current.n; i > current.n - del; i--) {
//                heap[i]--;
//                if (heap[i] == 0) {
//                    heap[i] = 1001;
//                    counter++;
//                }
//            }
//            current.sort();
//            current.n -= counter;
//        }
//        return "ok";
//    }
//
//}
