package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private MinPQ queue;

    public Main(int capacity) {
        queue = new MinPQ(capacity);
    }

    public void addElement(int element) {
        if (!queue.isFool())
            queue.insert(element);
        else
            queue.increaseSmallest(element);
    }

    private long getTime() {
        return queue.getMax();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        int people = Integer.parseInt(line[0]);
        int kases = Integer.parseInt(line[1]);
        Main main = new Main(kases);
        line = reader.readLine().split(" ");
        for (int i = 0; i < people; i++) {
            main.addElement(Integer.parseInt(line[i]));
        }
        System.out.println(main.getTime());
    }

    class MinPQ {

        private long[] pq;
        private int n;

        public MinPQ(int capacity) {
            pq = new long[capacity + 1];
        }

        public MinPQ() {
            this(10);
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public boolean isFool() {
            return n == pq.length - 1;
        }

        public void insert(int key) {
            pq[++n] = key;
            swim(n);
        }

        public void increaseSmallest(int difference) {
            pq[1] += difference;
            sink(1);
        }

        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && greater(j, j + 1)) j++;
                if (!greater(k, j)) break;
                exch(k, j);
                k = j;
            }
        }

        private boolean greater(int i, int j) {
            return pq[i] > pq[j];
        }

        private void exch(int i, int j) {
            long t = pq[i];
            pq[i] = pq[j];
            pq[j] = t;
        }

        private long getMax() {
            long max = pq[1];
            for (int i = 1; i <= n; i++) {
                if (pq[i] > max) max = pq[i];
            }
            return max;
        }
    }


}
