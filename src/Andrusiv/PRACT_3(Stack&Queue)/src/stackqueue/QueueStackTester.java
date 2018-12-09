package stackqueue;
import java.util.Iterator;

public class QueueStackTester {

    public static void main(String[] args){
    	System.out.println("-----Deque-----");
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(6);
        deque.addFirst(9);
        deque.addFirst(1);
        deque.addFirst(3);
        deque.addLast(44);
        deque.addLast(2);
        deque.addLast(6);
        Iterator<Integer> iterator0 = deque.iterator();
        while(iterator0.hasNext()){
            System.out.print(iterator0.next()+" ");
        }
        System.out.println("\nsize: "+deque.size());
       deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();
        Iterator<Integer> iterator = deque.iterator();
        
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
        System.out.println("\nsize: "+deque.size());
    	
        
        System.out.println("-----Randomized queue-----");
    	RandomizedLinkedQueue<Integer> rque=new RandomizedLinkedQueue<Integer>();
    	rque.enqueue(0);
    	rque.enqueue(1);
    	rque.enqueue(2);
    	rque.enqueue(3);
    	rque.enqueue(4);
    	rque.enqueue(5);
    	rque.enqueue(6);
    	rque.enqueue(7);
    	rque.enqueue(8);
    	System.out.println("Size "+rque.size());
    	
    	Iterator<Integer> iterator4 = rque.iterator();
   	 while(iterator4.hasNext())
            System.out.print(iterator4.next()+" ");
    	 
   	 System.out.println("\nrandom items "+rque.rsample()+" "+ rque.rsample());
   	 
    	System.out.println("-"+ rque.rdequeue());    
    	 System.out.println("Size "+rque.size());
    	Iterator<Integer> iterator2 = rque.iterator(); 
    	 while(iterator2.hasNext())
             System.out.print(iterator2.next()+" ");
    		
  
    	 System.out.println("\n-"+ rque.rdequeue());    
    	 System.out.println("Size "+rque.size());
    	Iterator<Integer> iterator3 = rque.iterator(); 
    	 while(iterator3.hasNext())
             System.out.print(iterator3.next()+" ");
    }
}
