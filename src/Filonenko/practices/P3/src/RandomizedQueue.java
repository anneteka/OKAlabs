import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import lib.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    int count = 0;
    private Object[] queue;
    public RandomizedQueue() {
        queue = new Object[10];
    }
    public boolean isEmpty()  {
        return count == 0;
    }
    public int size()  {
        return count;
    }
    public void enqueue(Item item)  {
        if(item == null) throw new NullPointerException();
        if(count < queue.length) {
            resize(queue.length*2);
        }
        queue[count] = item;
        count++;
    }
    public Item dequeue()  {
        if(isEmpty()) throw new NoSuchElementException();
        int random = new Random().nextInt(count-1);
        Object item = queue[random];
        queue[random] = queue[count-1];
        queue[count-1]=null;
        count--;
        if(count <queue.length/4) {
            resize(queue.length/2);
        }
        return (Item) item;
    }
    public Item sample()  {
        return (Item) queue[StdRandom.uniform(count)];
    }
    public Iterator<Item> iterator()  {
        return new RandomizedQueueIterator();
    }
    private class RandomizedQueueIterator implements Iterator<Item> {
        int n = count-1;
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return n > 0;
        }

        @Override
        public Item next() {
            return  (Item) queue[n--];
        }

    }
    private void resize(int newSize) {
        Object[] newArray = new Object[newSize];
        for(int i=0, minLength = Math.min(queue.length, newSize);i<minLength;i++) {
            newArray[i] = queue[i];
        }
        queue = newArray;
    }
}
