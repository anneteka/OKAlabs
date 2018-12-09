import java.util.Arrays;

public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int n;

    public MinPQ() {
        this(1);
    }

    public MinPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void insert(Key key) {
        if (key != null) {
            if ((n + 1) == pq.length) resize(2 * pq.length);
            pq[++n] = key;
            swim(n);
        }
    }

    public Key delMin() { //look
        Key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        if (n > 0 && n <= pq.length / 4) resize(pq.length / 2);
        return max;
    }

    private void swim(int k) {
        while (k > 1 && more(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && more(j, j + 1)) j++;
            if (!more(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean more(int i, int j) {
        if (pq[j] == null || pq[i] == null) return false;
        return pq[j].compareTo(pq[i]) < 0;
    }

    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private void resize(int capacity) {
        Key[] copy = (Key[]) new Comparable[capacity];
        for (int i = 0; i < Math.min(pq.length, capacity); i++)
            copy[i] = pq[i];
        pq = copy;
    }

    @Override
    public String toString() {
        return Arrays.toString(pq);
    }

}
