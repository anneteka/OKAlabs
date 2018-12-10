import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>{

    protected Item[] s;
    protected int n =0;

    public RandomizedQueue(){
        //s = new Item[1]; //оголошення generics масивів заборонена
        s = (Item[])new Object[1];
    }

    public void enqueue(Item item) {
        if (n==s.length) resize(2*s.length);
        s[n++] = item;
    }


    public Item dequeue() {
        if(n<=0) return null;
        int x = StdRandom.uniform(n);
        Item res = s[x];
        //  Item[] now = (Item[])new Object[s.length];
        for(int i=x; i<s.length && s[i]!=null; i++){
            //if(i!=x) now[i] = s[i];
            //else i++;
            s[i] = s[i+1];
        }
        n--;
        // s = now;
        return res;
    }
    public Item sample(){
        if(n<=0) return null;
        int x = StdRandom.uniform(n);
        return s[x];
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
