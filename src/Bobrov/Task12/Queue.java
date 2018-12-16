package Task12;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private int N;
    private Node first;
    private Node last;

    private class Node {
        private Item item;
        private Node next;
    }

    public Queue() {
        first = null;
        last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public int length() {
        return N;
    }

    public Item peek() {
        if (isEmpty())
            throw new RuntimeException("Queue underflow");
        return first.item;
    }

    public void enqueue(Item item) {
        Node x = new Node();
        x.item = item;
        if (isEmpty()) {
            first = x;
            last = x;
        } else {
            last.next = x;
            last = x;
        }
        N++;
    }

    public Item dequeue() {
        if (isEmpty())
            throw new RuntimeException("Queue underflow");
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty())
            last = null; // to avoid loitering
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}