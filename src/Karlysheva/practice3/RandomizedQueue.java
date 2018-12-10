package practice3;

import ua.princeton.lib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size=0;
    private Node first, last;
    public RandomizedQueue(){
        first=last=null;
    }

    // чи порожня?
    public boolean isEmpty(){
        return size==0;
    }

    // кількість елементів
    public int size(){
        return size;
    }

    // додати елемент
    public void enqueue(Item item){
        if (item==null)throw new NullPointerException();
        Node newNode = new Node(item);
        if (isEmpty())
            first=last=newNode;
        else {
            last.setNext(newNode);
            last=last.getNext();
        }
        size++;
    }

    // видалити і повернути випадковий елемент
    public Item dequeue(){
        if (isEmpty()) throw new NoSuchElementException();
        Node ret = sampleNode();
       if (size>1){ ret.getPrevious().setNext(ret.getNext());
        ret.getNext().setPrevious(ret.getPrevious());}
        else {
            first=last=null;
       }
        size--;
        return ret.getItem();}

    // повернути але не видалити випадковий елемент
    public Item sample(){
        if (isEmpty()) throw new NoSuchElementException();
        return sampleNode().getItem();}

private Node sampleNode(){
    int element = StdRandom.uniform(size);
    Node current = first;
    if (element>size/2){
        current=last;
        for (int i = size; i > element; i--)
            current=current.getPrevious();
    }
    else{
        for (int i = 0; i < element; i++)
            current=current.getNext();

    }
        return current;
}

    public Iterator iterator(){return new Iterator() {
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
    };}

    private class Node {
        private Item item;
        private Node next, previous;

        public Node(Item current) {
            this.item = current;
        }

        public Node getNext() {
            return next;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item current) {
            this.item = current;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getThis() {
            return this;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }


}
