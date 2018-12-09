import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {


    public LinkedQueue() {}

    private Node first,next,last;
    private int counter=0;


    public void enqueue(Item item) {
        throwIfNull(item);
        Node prev=last;
        last=new Node();
        last.item=item;
        last.next=null;
        counter++;
        if (isEmpty())
            first = last;
        else
            prev.next=last;
    }


    public Item dequeue() {
        Item item=first.item;
        first=first.next;
        counter--;
        if (isEmpty()) last =null;
        return item;
    }


    private void throwIfNull(Item item) {
        if(item==null) throw new NullPointerException();
    }

    private void throwIfEmpty() {
        if(first==null) throw new NoSuchElementException();
    }

    public boolean isEmpty() {
        return first==null;
    }

    public int size() {
        return counter;
    }


    @Override
    public Iterator<Item> iterator() {
        // TODO Auto-generated method stub
        return new ItemsIterator();
    }


    private class ItemsIterator implements Iterator<Item> {

        private Node current;

        public ItemsIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null)
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        Item item;
        Node next;
    }


}
