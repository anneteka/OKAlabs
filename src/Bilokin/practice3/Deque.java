import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   
   private  Node first;  
   private Node last; 
   private int n = 0; // the size of the deque
    
   public Deque(){
    // Initialize the first and last node of the deque as null when the Deque is created
    first = null; 
    last  = null;
   }
   
   
   public boolean empty() {
    return n == 0; 
   }
   
   public int size() {
       return n;
   }
  
   public void addforw(Item item)  {
         if (item == null) {
            throw new NullPointerException();
        }
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        if (n == 0) {
        last = first;
        n++;  
        }
        else {
        oldfirst.prev = first;
        n++;  
        } 
   }
   
  
   public void addBack(Item item) {
       // add the item to the end
       if (item == null) {
            throw new NullPointerException();
        }
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.prev = oldlast;
        if (n == 0) {
        first = last;
        n++;  
        }
        else {
        oldlast.next = last;
        n++;    
        }
   }
   
   public Item removeForw() {
       // remove and return the item from the front
       if (empty()) throw new NoSuchElementException();
       
       if (n == 1) {
        Item item = first.item;       
        first = null;   
        last = null;    
        n--;
        return item;
       }
     
        Item item = first.item;       
        first = first.next;           
        n--;
       return item;
   }
   
  
   @Override
   public Iterator<Item> iterator() {
       return new DequeIterator();
   }
   
  
   public static void main(String[] args) {
           Deque<Integer> deque=new Deque<>();
           deque.addforw(123);
           deque.addBack(456);
           deque.removeForw();
           Iterator it=deque.iterator();
           while(it.hasNext()){
        	   Integer number=(Integer) it.next();
        	   System.out.println(number);
           }
    }
   
  
    private class DequeIterator implements Iterator<Item> {

        Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
        if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
        
      
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }
    
    
    private class Node {
        private Item item;
        private Node next; 
        private Node prev; 
    }
}