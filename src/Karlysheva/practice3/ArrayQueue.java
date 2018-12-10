package practice3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size=0, first, last;

    public ArrayQueue(){
        items=(Item[]) new Object[5];
        first=last=5;
    }

    public void addFirst(Item item){
        if (item==null) throw new NullPointerException();
        if (size==items.length) items= resize();
        if (isEmpty()) addEmpty(item);
        else{
            first=(first+items.length-1)%items.length;
            items[first]=item;
        }
        size++;
        System.out.println(first);
    }

    public void addLast(Item item){
        if (item==null) throw new NullPointerException();
        if (size==items.length)items= resize();
        if (isEmpty()) addEmpty(item);
        else {
            last = (last + 1) % items.length;
            items[last]=item;
        }
        System.out.println(last);

        size++;
    }

    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException();
        if (size<=items.length/4&&items.length>5)items= resize();
        size--;
        Item ret = items[last];
        items[last]=null;
        last=(last+items.length-1)%items.length;
        return ret;
    }
    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException();
        if (size<=items.length/4&&items.length>5)items= resize();
        size--;
        Item ret = items[first];
        items[first]=null;
        first=(first+1)%items.length;
        return ret;
    }

    private Item[] resize(){
        System.out.println("resize");
        Item[] temp = (Item[]) new Object[size*2];
       int newFirst=temp.length/4;
        for (int i = 0; i < size; i++) {
            temp[newFirst+i]=items[(first+i)%items.length];
        }
        first=newFirst;
        last=first+size-1;
        return temp;
    }
    private void addEmpty(Item item) {
        first=last=items.length/2;
        items[first]=item;
    }

    public int size(){
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

    public static void main(String[] args) {
        ArrayQueue<String> kek = new ArrayQueue<>();
        kek.addFirst("1");
        kek.addFirst("kesek");
        kek.addFirst("2");
        kek.addLast("kesa");
        kek.addLast("kesek2");
        kek.addLast("kesek3");
        kek.addLast("kesek4");
        kek.addLast("kesek5");
    }
}