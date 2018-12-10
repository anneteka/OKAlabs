package practice7;

public class Events {

    Event[] heap;
    int n;

    public Events() {
        n = 0;
        heap = new Event[100];
    }

    public Events(Event[] heap) {
        n = 0;
        this.heap = new Event[heap.length + 1];
        for (int i = 0; i < heap.length; i++) {
            insert(heap[i]);
        }
    }

    public Events(int length) {
        heap = new Event[length + 1];
        n = 0;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void swap(int a, int b) {
        Event tenp = heap[a];
        heap[a] = heap[b];
        heap[b] = tenp;
    }

    private boolean less(int a, int b) {
        if (heap[a] == null) return false;
        return heap[a].compareTo(heap[b]) < 0;
    }

    public void insert(Event x) {
        n++;
        if (n >= heap.length) resize(n);
        heap[n] = x;
        swim(n);
    }

    private void resize(int n) {
        Event[] temp = new Event[n * 4];
        for (int i = 0; i < n; i++) {
            temp[i] = heap[i];
        }
        heap = temp;
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

    //dont need it here
//    public Event delMax() {
//        sort();
//        Event max = heap[1];
//        swap(1, n--);
//        sink(1);
//        heap[n + 1] = null;
//        return max;
//    }

    public Event delMin() {
        Event min = heap[1];
        swap(1, n--);
        sink(1);
        heap[n + 1] = null;
        if (n < heap.length/8) resize(n);
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

    public boolean isEmpty() {
        return n == 0;
    }

}

class Event implements Comparable<Event> {
    public double time; // time of event
    public Particle a, b; // particles involved in event
    private int countA, countB; // collision counts for a and b

    public Event(double t, Particle a, Particle b) {
        this.a = a;
        this.b = b;
        countA = a == null ? 0 : a.getCount();
        countB = b == null ? 0 : b.getCount();
        this.time = t;
    }

    public int compareTo(Event that) {
        if (that == null) return -1;
        return (int) Math.signum(this.time - that.time);
    }

    public boolean isValid() {
        return a == null ? (b == null ? (true) : (countB == b.getCount()))
                : (b == null ? (countA == a.getCount()) : (countA == a.getCount() && countB == b.getCount()));
        //return (countA==a.getCount()&&countB==b.getCount());
    }
}

