import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item> {

    protected Item[] s;
    protected int n = 0;

    public ArrayQueue() {
        s = (Item[]) new Object[1];
    }

    public void enqueue(Item item) {
        if (n == s.length) resize(2 * s.length);
        if (n <= s.length / 4) resize(s.length / 2 + 1);
        if (item != null) {
            s[n++] = item;
        } else {
            throw new NullPointerException("what the fuck dude stop");
        }
    }


    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("u kidding me");
        Item first = s[0];
        Item[] copy = (Item[]) new Object[s.length];
        for (int i = 0; i < n - 1; i++)
            copy[i] = s[i + 1];
        s = copy;
        n--;
        return first;
    }


    public boolean isEmpty() {
        return n == 0;
    }


    public int size() {
        return n;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = s[i];
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            return s[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
