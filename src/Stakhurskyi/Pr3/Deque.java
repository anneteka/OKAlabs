package Stakhurskyi.Pr3;

import java.util.NoSuchElementException;

public class Deque<Item> {
    private Node header;
    private int count;

    public Deque() {
        header = new Node();
        header.next = header;
        header.prev = header;
    }
    public int size(){
        return count;
    }

    public void addFirst(Item item) throws NullPointerException {

        Node newComer = new Node();
        newComer.next = header.next;
        header.next.prev = newComer;
        newComer.prev = header;
        newComer.item = item;
        header.next = newComer;
        if (count == 0)
            header.prev = newComer;
        count++;
    }

    public Item removeFirst() throws NoSuchElementException {
        if(count == 0)
            throw new NoSuchElementException();

        Node toDelete = header.next;
        header.next = header.next.next;
        count--;
        return toDelete.item;

    }

    public void addLast(Item item) throws NullPointerException {

        Node newComer = new Node();
        newComer.next = header;
        newComer.prev = header.prev;
        header.prev.next = newComer;
        newComer.item = item;
        header.prev = newComer;
        if (count == 0)
            header.next = newComer;
        count++;
    }

    public Item removeLast() throws NoSuchElementException {
        if(count == 0)
            throw new NoSuchElementException();
        Node toDelete = header.prev;
        header.prev = header.prev.prev;
        count--;
        return toDelete.item;
    }

    private class Node {
        Item item;
        Node prev;
        Node next;
    }
}
