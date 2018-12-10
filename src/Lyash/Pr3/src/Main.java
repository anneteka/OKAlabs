import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        System.out.println("Deque:");
        Deque<Integer> myDeq = new Deque<>();
        myDeq.addFirst(24224);
        myDeq.addLast(24);
        myDeq.addLast(214932);
        myDeq.addFirst(1488);
        System.out.println("Item removed from beginning: "+myDeq.removeFirst());
        System.out.println("Item removed from end: "+myDeq.removeLast());
        Iterator<Integer> iter = myDeq.iterator();

        while (iter.hasNext()){
            System.out.println(iter.next());
        }

        System.out.println("RandomizedQueue:");
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        for (int i = 0; i < 20; i++)
            rq.enqueue(i);

        System.out.println("Sample: "+rq.sample());

        for (int i = 0; i < 3; i++)
            System.out.println("Deleted: "+rq.dequeue());

        iter = rq.iterator();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }
}
