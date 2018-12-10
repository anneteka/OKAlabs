import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first=null, last=null;
    private int count=0;



    private class Node{
        Item item;
        Node next;
        Node prev;
    }

    public void addFirst(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item=item;
        first.prev=null;
        first.next=oldFirst;
        if(oldFirst==null) last=first;
            else oldFirst.prev = first;
       // if(first.next==null) last = first;
        count++;
    }
    public void addLast(Item item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if(oldLast==null) first = last;
        else oldLast.next = last;
        //if(first.n)
        count++;
    }

    public Item removeFirst() {
        Item item = first.item;
        first = first.next;
        first.prev=null;
        count--;
        return item;
    }
    public Item removeLast(){
        Item item = last.item;
        last = last.prev;
        last.next = null;
        count--;
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
