import java.util.*;

public class ArrayQueue<Item> implements  Iterable<Item>
{
    private static final int DEFAULT_CAPACITY = 10;
    private int cap,
            cur,
            front,
            back;
    private Item[] A;


    public ArrayQueue ()
    {
        cap = DEFAULT_CAPACITY;
        A = (Item[]) new Object[DEFAULT_CAPACITY];
        back = -1; front = 0;
    }


    public boolean isEmpty()
    {
        return cur == 0;
    }


    public void enqueue (Item value)
    {
        if (isFull()) doubleSize();

        back++;
        A[back%cap] = value;
        cur++;
    }


    public Item getFront()
    {
        if (!isEmpty())
            return A[front%cap];
        else
            return null;
    }


    public Item dequeue()
    {
        Item e = getFront();
        A[front%cap] = null;
        front++;
        cur--;
        return e;
    }


    public void clear()
    {
        for(int i = 0; i < cap; i++) A[i] = null;

        cur = 0; back = -1; front = 0;
    }


    public boolean isFull()
    {
        return cur == cap;
    }


    private void doubleSize()
    {
        Item[] newArray = (Item[]) new Object[2*cap];

        //copy items
        for(int i = front; i <= back; i ++)
            newArray[i-front] = A[i%cap];

        A = newArray;
        front = 0;
        back = cur-1;
        cap *= 2;
    }


    public Iterator<Item> iterator( )
    {
        return new QueueIterator( );
    }

    private class QueueIterator implements Iterator<Item>
    {
        private int index;

        public QueueIterator()
        {
            index = front;
        }


        public boolean hasNext( )
        {
            return index <= back;
        }


        public Item next( )
        {
            return A[(index++)%cap];
        }


        public void remove( )
        {
            throw new java.lang.UnsupportedOperationException();
        }
    }

}

