import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class Rand<T> implements Iterable<T> {

    private int queueEnd = 0;   

    @SuppressWarnings("unchecked")
    private T[] queue = (T[]) new Object[1];    

    private Random rGen = new Random();    

    private void resize(int newSize) {
        System.out.println("Resizing from " + queue.length + " to " + newSize);
        T[] newArray = Arrays.copyOfRange(queue, 0, newSize);
        queue = newArray;
    }


    public boolean isEmpty() {
        return queueEnd == 0;
    }

    public int size() {
        return queueEnd;
    }

    public void enqueue(T elem) {
        if (elem == null)
            throw new NullPointerException();

        if (queueEnd == queue.length) 
            resize(queue.length*2);

        queue[queueEnd++] = elem;
    }

    
    public T dequeue() {    
        if (queueEnd == 0)  // can't remove element from empty queue
            throw new UnsupportedOperationException();

        if (queueEnd <= queue.length/4)     
            resize(queue.length/2);

        int index = rGen.nextInt(queueEnd);     

        T returnValue = queue[index];   

        queue[index] = queue[--queueEnd];    
                                              
        queue[queueEnd] = null;         

        return returnValue;
    }

    
    public T sample() {
        int index = rGen.nextInt(queueEnd);     // selects a random index
        return queue[index];
    }

    /*
     * Every iteration will (should) return entries in a different order.
     */
    private class RanQueueIterator implements Iterator<T> {

        private T[] shuffledArray;

        private int current = 0;

        public RanQueueIterator() {
            shuffledArray = queue.clone();
            shuffle(shuffledArray);
        }

        @Override
        public boolean hasNext() {
            return current < queue.length;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return shuffledArray[current++];
        }
        
        public void shuffle(T[] array) {
            int n = array.length;
            for (int i = 0; i < n; i++) {
                // choose index uniformly in [i, n-1]
                int r = i + (int) (Math.random() * (n - i));
                T swap = array[r];
                array[r] = array[i];
                array[i] = swap;
            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new RanQueueIterator();
    }

    public static void main(String[] args) {
        Rand<Integer> test = new Rand<>();

        // adding 10 elements
        for (int i = 0; i < 10; i++) {
            test.enqueue(i);
            System.out.println("Added element: " + i);
            System.out.println("Current number of elements in queue: " + test.size() + "\n");

        }


        System.out.print("\nIterator test:\n[");
        for (Integer elem: test)
            System.out.print(elem + " ");
        System.out.println("]\n");

        // removing 10 elements
        for (int i = 0; i < 10; i++) {
            System.out.println("Removed element: " + test.dequeue());
            System.out.println("Current number of elements in queue: " + test.size() + "\n");
        }       

    }   
}