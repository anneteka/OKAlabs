package who;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;

public class Main {


    private MaxPQ maxPQ;

    public Main() {
        maxPQ = new MaxPQ();
    }

    public void add(int element) {
        maxPQ.insert(element);
    }

    public boolean areHappy() {
        Stack<Integer> temp = new Stack<>();
        while (!maxPQ.isEmty()) {
            int max = maxPQ.delMax();
            for (int i = 0; i < max; i++) {
                if (maxPQ.isEmty()) return false;
                int newMax = maxPQ.delMax();
                if (newMax != 1) temp.push(newMax - 1);
            }
            while (!temp.empty()) {
                maxPQ.insert(temp.pop());
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] split = s.split(" ");
            for (String k : split) {
                main.add(Integer.valueOf(k));
            }
            System.out.println((main.areHappy() ? "ok\n" : "fail\n"));
        }
    }

    class MaxPQ {

        private int[] pq;
        private int n;

        public MaxPQ(int capacity) {
            pq = new int[capacity + 1];
        }

        public MaxPQ() {
            this(10);
        }

        public boolean isEmty() {
            return n == 0;
        }

        public void insert(int key) {
            if (n == pq.length - 1) resize(2 * pq.length);
            pq[++n] = key;
            swim(n);
        }


        private void swim(int k) {
            while (k > 1 && less(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && less(j, j + 1)) j++;
                if (!less(k, j)) break;
                exch(k, j);
                k = j;
            }
        }

        private boolean less(int i, int j) {
            return pq[i] < pq[j];
        }

        private void exch(int i, int j) {
            int t = pq[i];
            pq[i] = pq[j];
            pq[j] = t;
        }


        public int delMax() {
            int max = pq[1];
            exch(1, n--);
            sink(1);
            pq[n + 1] = Integer.MIN_VALUE;
            return max;
        }

        private void resize(int capacity) {
            assert capacity > n;
            int[] temp = new int[capacity];
            for (int i = 1; i <= n; i++) {
                temp[i] = pq[i];
            }
            pq = temp;
        }
    }
}
