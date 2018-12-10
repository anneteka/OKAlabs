package practice3;

import ua.princeton.lib.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedArrayQueue<Item> implements Iterable<Item> {
    int size;
    Item[] items;

    public RandomizedArrayQueue() {
        size=0;
        items=(Item[])new Object[10];
    }

    public void enque(Item item){
        if (item==null) throw new NullPointerException();
        if (size>=items.length)items=resize();
        items[size]=item;
        size++;
    }
    public Item deque(){
        if (size==0) throw new NoSuchElementException();
        int ret = StdRandom.uniform(size);
        Item reti = items[ret];
        items[ret]=items[size-1];
        items[size-1]=null;
        size--;
        return reti;
    }
    private Item[] resize(){
        Item[] temp = (Item[]) new Object[size*2];
        for (int i = 0; i < size; i++) {
            temp[i]=items[i];
        }
        return temp;
    }

    public int size() {
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int current=-1;
            @Override
            public boolean hasNext() {
                return current+1<size;
            }

            @Override
            public Item next() {
                current++;
                return items[current];
            }
        };
    }
}
