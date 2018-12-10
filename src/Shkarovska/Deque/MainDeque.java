import java.util.Iterator;

public class MainDeque {

    public static void main(String[] args){
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(6);
        deque.addFirst(9);
        deque.addFirst(1);
        deque.addFirst(3);
        deque.addLast(44);
        deque.addLast(2);
        deque.addLast(6);
        deque.removeFirst();
        deque.removeFirst();
        deque.removeLast();
        Iterator<Integer> iterator = deque.iterator();
        System.out.println("size: "+deque.size());
        while(iterator.hasNext()){
            System.out.print(iterator.next()+" ");
        }
    }
}
