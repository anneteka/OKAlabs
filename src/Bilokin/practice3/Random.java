import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Random<T> implements Iterable<T> {

    private int endQue = 0;

    @SuppressWarnings("unchecked")
    private T[] queue = (T[]) new Object[1];    

    private java.util.Random rGen = new java.util.Random();

    private void resize(int newSize) {
        System.out.println("Resizing from " + queue.length + " to " + newSize);
        T[] newArray = Arrays.copyOfRange(queue, 0, newSize);
        queue = newArray;
    }


    public boolean empty() {
        return endQue == 0;
    }

    public int size() {
        return endQue;
    }

    public void Enqueue(T elem) {
        if (elem == null)
            throw new NullPointerException();

        if (endQue == queue.length)
            resize(queue.length*2);

        queue[endQue++] = elem;
    }

    
    public T dequeue() {
        if (endQue == 0)
            throw new UnsupportedOperationException();

        if (endQue <= queue.length/4)
            resize(queue.length/2);

        int ind = rGen.nextInt(endQue);

        T returnValue = queue[ind];

        queue[ind] = queue[--endQue];
                                              
        queue[endQue] = null;

        return returnValue;
    }


    private class RandomIterator implements Iterator<T> {

        private T[] shuffled;

        private int current = 0;

        public RandomIterator() {
            shuffled = queue.clone();
            shuffle(shuffled);
        }

        @Override
        public boolean hasNext() {
            return current < queue.length;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            return shuffled[current++];
        }
        
        public void shuffle(T[] array) {
            int n = array.length;
            for (int i = 0; i < n; i++) {
                int r = i + (int) (Math.random() * (n - i));
                T swap = array[r];
                array[r] = array[i];
                array[i] = swap;
            }
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new RandomIterator();
    }

    public static void main(String[] args) {
        Random<Integer> test = new Random<>();

        for (int i = 0; i < 10; i++) {
            test.Enqueue(i);
            System.out.println("Added element: " + i);
            System.out.println("Current number of elements in queue: " + test.size() + "\n");

        }


        System.out.print("\nIterator test:\n[");
        for (Integer elem: test)
            System.out.print(elem + " ");
        System.out.println("]\n");

        for (int i = 0; i < 10; i++) {
            System.out.println("Removed: " + test.dequeue());
            System.out.println("Current number: " + test.size() + "\n");
        }       

    }   
}