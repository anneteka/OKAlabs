import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
    private Node first,last;
    private int count =0;

    private class Node{
        Item item;
        Node next;
        Node previous;
    }

    public void addLast(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;

        last.previous = oldLast;

        last.next = null;
        count++;
        if (isEmpty())
            first = last;
        else
            oldLast.next=last;
    }

    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        first.previous = null;
        count++;
        if (isEmpty())
            first = last;
        else
            oldFirst.previous=first;
    }

    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        count--;
        if (isEmpty()) last =null;
        return item;
    }

    public Item removeLast() {
        Item item = last.item;
        last = last.previous;
        count--;
        if (isEmpty()) first = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }


    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        @Override
        public boolean hasNext() {
            return current!=null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {

        }

    }
}
