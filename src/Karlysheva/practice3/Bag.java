package practice3;

import java.util.Iterator;

public class Bag <Item> implements Iterable<Item>{ //можна скласти не можна витягнути

   private Item[] items;
   private int size;
    public Bag() {
        items=(Item[]) new Object[10];
        size=0;
    }

    public void add(Item item){
        size++;
        if (size>items.length) items= resize(items);
        items[size-1]=item;
    }

    public boolean isEmpty(){
        return size==0;
    }

    private Item[] resize(Item[] items){
        Item[] temp = (Item[]) new Object[items.length*2];
        for (int i = 0; i < items.length; i++) temp[i]=items[i];
        return temp;
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
