import java.util.Iterator;

public class MainRandom {

    public static void main(String[] args){
        RandomizedQueue<Integer> deque = new RandomizedQueue<>();
        for(int i=0; i<12; i++)
            deque.enqueue(i*3);
        deque.dequeue();
        deque.dequeue();
        deque.dequeue();
        System.out.print(deque.sample()+" ");
        System.out.print(deque.sample()+" ");
        System.out.print(deque.sample()+" ");
        System.out.println();
        Iterator<Integer> iterator = deque.iterator();
        System.out.println("size: "+deque.size());
        while(iterator.hasNext()){
            System.out.print(iterator.next()+ " ");
        }
    }
}