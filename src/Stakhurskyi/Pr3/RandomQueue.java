package Stakhurskyi.Pr3;

import java.util.Iterator;
import java.util.Random;


public class RandomQueue<T> implements Iterable<T> {
    private T[] queue = (T[]) new Object[2];
    public int head = 0;
    public int tail = 0;
    private Random rand = new Random();


    public boolean isEmpty() {
        return head==tail;
    }

    public void enqueue(T item) {
        queue[tail++]=item;
        if(tail==queue.length){
            int len = tail-head;
            T[] buffer = (T[]) new Object[len*2];
            System.arraycopy(queue, 0, buffer, head, len);
            queue = buffer;
            head = 0;
            tail = len;
        }
    }

    public T dequeue() {
        if(tail-head==queue.length/4){
            int len = tail-head;
            T[] buffer = (T[]) new Object[len*2];
            System.arraycopy(queue, head, buffer, 0, len);
            queue = buffer;
            head = 0;
            tail = len;
        }
        int pos = rand.nextInt(tail-head);
        T res = queue[head+pos];
        System.arraycopy(queue, head+pos+1, queue, head+pos, tail-head-pos);
        tail--;
        return res;
    }
    public String toString(){
        String res = "";
        for(T item : queue){
            res+=item+ " ";
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            int cur = head;
            @Override
            public boolean hasNext() {
                return cur!=tail;
            }

            @Override
            public T next() {
                return queue[cur++];
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub

            }

        };
    }
}