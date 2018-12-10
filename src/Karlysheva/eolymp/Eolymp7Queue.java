package eolymp;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Eolymp7Queue {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                new FileInputStream("input.txt"), StandardCharsets.UTF_8));
        String line;
        while ((line=reader.readLine())!=null) {
            System.out.println(findTGime(line, reader.readLine()));
        }
    }

    static long findTGime(String data, String people) {
        long answer = 0;
        int k = Integer.parseInt(data.split(" ")[1]);
        int n = Integer.parseInt(data.split(" ")[0]);
        int[] queue = new int[n];
        String[] line = people.split(" ");
        for (int i = 0; i < n; i++) {
            queue[i] = Integer.parseInt(line[i]);
        }
        if (k > n) return queue[findMax(queue)];

        Heap cashboxes = new Heap(k);
        for (int i = 0; i < k; i++) {
            cashboxes.insert(queue[i]);
        }
        int minIndex, min = 0;
        int counter;
        int[] heap = cashboxes.heap;
        for (int i = k; i < n; i++) {
            min = cashboxes.delMin();
            answer += min;
            cashboxes.insert(queue[i]);
        }
        answer += cashboxes.delMax();
        return answer;
    }

    static int findMin(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[index] > array[i]) index = i;
        }
        return index;
    }

    static int findMax(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[index] < array[i]) index = i;
        }
        return index;
    }
}

class Heap {
    int[] heap;
    int n;

    public Heap(int[] heap) {
        n = 0;
        this.heap = new int[heap.length + 1];
        for (int i = 0; i < heap.length; i++) {
            insert(heap[i]);
        }
    }

    public Heap(int length) {
        heap = new int[length + 1];
        n = 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void swap(int a, int b) {
        int tenp = heap[a];
        heap[a] = heap[b];
        heap[b] = tenp;
    }

    private boolean less(int a, int b) {
        return heap[a] < heap[b];
    }

    public void insert(int x) {
        heap[++n] = x;
        swim(n);
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j + 1, j)) j++;
            if (!less(j, k)) break;
            swap(k, j);
            k = j;
        }
    }

    private void sink(int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j + 1, j)) j++;
            if (!less(j, k)) break;
            swap(k, j);
            k = j;
        }
    }

    //ai <= 1000
    public int delMax() {
        sort();
        int max = heap[1];
        swap(1, n--);
        sink(1);
        heap[n + 1] = 0;
        return max;
    }

    public int delMin() {
        int min = heap[1];
        swap(1, n--);
        sink(1);
        for (int i = 1; i < n + 1; i++) {
            heap[i] -= min;
        }
        heap[n + 1] = 0;
        return min;

    }

    void sort() {
        for (int k = n / 2; k >= 1; k--)
            sink(k, n);
        int tempn = n;
        while (tempn > 1) {
            swap(1, tempn--);
            sink(1, tempn);
        }

    }

}