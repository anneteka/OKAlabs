import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Iterable<Item>{

    public LinkedStack(){}

    private Node next,first;
    private int counter=0;

    public void push(Item item) {
        throwIfNull(item);

        Node prev=first;
        first=new Node();
        first.item=item;
        first.next=prev;
        counter++;

    }

    public Item pop() {
        throwIfEmpty();
        Item item=first.item;
        first=first.next;
        counter--;
        return item;
    }

    private void throwIfEmpty(){
        if(first==null) throw new NoSuchElementException();
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