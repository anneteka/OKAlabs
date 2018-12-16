import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    // implementation of deque based on linked list
    private class Node {
        Node next;
        Node previous;
        Item item;
    }
    private Node first;
    private Node last;
    private int count = 0;

    public boolean isEmpty()  {
        return count == 0;
    }
    public int size()  {
        return count;
    }
    public void addFirst(Item item)  {
        if(item == null) throw new NullPointerException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.previous = null;
        first.next = oldFirst;
        if(!isEmpty()) {
            oldFirst.previous = first;
        } else {
            last = first;
        }
        count++;
    }
    public void addLast(Item item)  {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
            last.previous = oldLast;
        }
        count++;
    }
    public Item removeFirst()  {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = first.item;;
        first = first.next;
        count--;
        if(isEmpty()) last = null;
        return item;
    }
    public Item removeLast()  {
        if(isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.previous;
        last.next = null;
        return item;
    }
    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        private int count = size();
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return count>0;
        }

        @Override
        public Item next() {
            // TODO Auto-generated method stub
            count--;
            Item next = current.item;
            current = current.next;
            return next;
        }
        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }
}
