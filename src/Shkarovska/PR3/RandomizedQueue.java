import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>{

    protected Item[] s;
    protected int n =0;

    public RandomizedQueue(){
        //s = new Item[1]; //оголошення generics масивів заборонена
        s = (Item[])new Object[1];
    }

    public void enqueue(Item item) {
        if(item == null) new NullPointerException();
        if (n==s.length) resize(2*s.length);
        s[n++] = item;
    }


    public Item dequeue(){
        int si = StdRandom.uniform(size());
        Item res = s[si];
        //Item[] now = (Item[]) new Object[size()-1];
        for(int i=si; i<size()-1; i++){
            s[i] = s[i+1];
        }
       // s = now;
        n=s.length;
        return res;
    }
    public Item sample() {
        return s[StdRandom.uniform(size())];
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