package practice3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<Item> implements Iterable<Item>{

   private Node first, last;
   private int size;

    LinkedStack(){
        first=last=null;
        size=0;
    }

    public Item pop(){
        return null;
    }

    public void push(Item item){
        Node newLast = new Node(item);
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException();
        return last.getItem();
    }

    public boolean isEmpty(){
        return size==0;
    }
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node current=first;
            int currentNode=-1;
            @Override
            public boolean hasNext() {
                return currentNode+1<size;
            }

            @Override
            public Item next() {
                currentNode++;
                if (currentNode==0) return first.getItem();
                current=current.getNext();
                return current.getItem();
            }
        };
    }

    private class Node{
        Item item;
        Node next, current;

        public Node(Item item) {
            this.item = item;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getCurrent() {
            return current;
        }

        public void setCurrent(Node current) {
            this.current = current;
        }
    }
}
