import java.util.Iterator;

public class ArrayQueue<Item> implements Iterable<Item>{

    protected Item[] s;
    protected int n =0;

    public ArrayQueue(){
        //s = new Item[1]; //оголошення generics масивів заборонена
        s = (Item[])new Object[1];
    }

    public void enqueue(Item item) {
        if (n==s.length) resize(2*s.length);
        s[n++] = item;
    }


    public Item dequeue() {
        Item res = s[0];
        for(int i=0; i<s.length && s[i]!=null; i++)
            s[i] = s[i+1];
        return res;
    }


    public boolean isEmpty() {
        return n==0;
    }


    public int size() {
        return n;
    }

    private void resize(int capacity){
        Item[] copy = (Item[])new Object[capacity];
        for (int i=0;i<n;i++)
            copy[i]=s[i];
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i=n;
        @Override
        public boolean hasNext() {
            return i>0;
        }

        @Override
        public Item next() {
            return s[--i];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
