package practice3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<Item> implements Iterable<Item> {
   private Node first, last;
    private int size;
   LinkedQueue(){
       first=last=null;
       size=0;
   }
    public void enqueue(Item item){//додає елемент в кінець
       if (item==null) throw new NullPointerException();
       if (isEmpty()) first=last=new Node(item);
       else {
           first.setPrevious(new Node(item));
           first.getPrevious().setNext(first);
           first=first.getPrevious();
       }
       size++;
    }

    public Item dequeue(){//повертає перший елемент
       if (isEmpty()) throw new NoSuchElementException();
       Item ret = first.getItem();
       if (size==1) first=last=null;
       else{
           first=first.getNext();
           first.setPrevious(null);
       }
       size--;
       return ret;
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
                return size>0&&current.getNext()!=null;
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

        private Item item;
        private Node next, previous;

        Node(Item i){
            item=i;
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

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }
}
