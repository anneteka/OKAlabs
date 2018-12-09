import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<Item> implements Iterable<Item> {

    private int counter=0;
    private Node first;

    public Bag() {

    }


    public void add(Item item) {
        throwIfNull(item);
        Node next=first;
        first=new Node();
        first.item=item;
        first.next=next;
        counter++;
    }


    private void throwIfNull(Item item) {
        if(item==null) throw new NullPointerException();
    }

    public boolean isEmpty() {
        return first==null;
    }

    public int size() {
        return counter;
    }

    @Override
    public Iterator<Item> iterator() {
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

