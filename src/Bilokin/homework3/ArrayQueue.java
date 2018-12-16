import java.lang.reflect.Array;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;


public class ArrayQueue<E> extends AbstractCollection<E>
                           implements Queue<E>, Cloneable, java.io.Serializable {

    private transient Object[] elements;


    private transient int head;


    private transient int tail;


    private static final int MIN_INITIAL_CAPACITY = 8;


    private void allocateElements(int numElements) {
        int initialCapacity = MIN_INITIAL_CAPACITY;
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)
                initialCapacity >>>= 1;
        }
        elements = new Object[initialCapacity];
    }


    private void doubleCapacity() {
        // assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p; // number of elements to the right of p
        int newCapacity = n << 1;
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, queue too big");
        Object[] a = new Object[newCapacity];
        System.arraycopy(elements, p, a, 0, r);
        System.arraycopy(elements, 0, a, r, p);
        elements = a;
        head = 0;
        tail = n;
    }


    public ArrayQueue() {
        elements = new Object[16];
    }


    public ArrayQueue(int numElements) {
        allocateElements(numElements);
    }


    public ArrayQueue(Collection<? extends E> c) {
        allocateElements(c.size());
        addAll(c);
    }


    @Override
    public boolean add(E e) {
        if (e == null)
            throw new NullPointerException("e == null");
        elements[tail] = e;
        if ((tail = (tail + 1) & (elements.length - 1)) == head)
            doubleCapacity();
        return true;
    }


    @Override
    public boolean offer(E e) {
        return add(e);
    }


    @Override
    public E remove() {
        E x = poll();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }


    @Override
    public E poll() {
        int h = head;
        @SuppressWarnings("unchecked") E result = (E) elements[h];
        // Element is null if queue empty
        if (result == null)
            return null;
        elements[h] = null;     // Must null out slot
        head = (h + 1) & (elements.length - 1);
        return result;
    }


    @Override
    public E element() {
        @SuppressWarnings("unchecked") E result = (E) elements[head];
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }


    @Override
    public E peek() {
        @SuppressWarnings("unchecked") E result = (E) elements[head];
        // elements[head] is null if queue empty
        return result;
    }


    private boolean delete(int i) {
        //checkInvariants();
        final Object[] elements = this.elements;
        final int mask = elements.length - 1;
        final int h = head;
        final int t = tail;
        final int front = (i - h) & mask;
        final int back  = (t - i) & mask;

        if (front >= ((t - h) & mask))
            throw new ConcurrentModificationException();

        if (front < back) {
            if (h <= i) {
                System.arraycopy(elements, h, elements, h + 1, front);
            } else { // Wrap around
                System.arraycopy(elements, 0, elements, 1, i);
                elements[0] = elements[mask];
                System.arraycopy(elements, h, elements, h + 1, mask - h);
            }
            elements[h] = null;
            head = (h + 1) & mask;
            return false;
        } else {
            if (i < t) { // Copy the null tail as well
                System.arraycopy(elements, i + 1, elements, i, back);
                tail = t - 1;
            } else { // Wrap around
                System.arraycopy(elements, i + 1, elements, i, mask - i);
                elements[mask] = elements[0];
                System.arraycopy(elements, 1, elements, 0, t);
                tail = (t - 1) & mask;
            }
            return true;
        }
    }


    @Override
    public int size() {
        return (tail - head) & (elements.length - 1);
    }


    @Override
    public boolean isEmpty() {
        return head == tail;
    }


    @Override
    public Iterator<E> iterator() {
        return new QueueIterator();
    }


    private class QueueIterator implements Iterator<E> {
        private int cursor = head;


        private int fence = tail;


        private int lastRet = -1;

        @Override
        public boolean hasNext() {
            return cursor != fence;
        }

        @Override
        public E next() {
            if (cursor == fence)
                throw new NoSuchElementException();
            @SuppressWarnings("unchecked") E result = (E) elements[cursor];
            // This check doesn't catch all possible comodifications,
            // but does catch the ones that corrupt traversal
            if (tail != fence || result == null)
                throw new ConcurrentModificationException();
            lastRet = cursor;
            cursor = (cursor + 1) & (elements.length - 1);
            return result;
        }

        @Override
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            if (delete(lastRet)) { // if left-shifted, undo increment in next()
                cursor = (cursor - 1) & (elements.length - 1);
                fence = tail;
            }
            lastRet = -1;
        }
    }


    @Override
    public boolean contains(Object o) {
        if (o == null)
            return false;
        int mask = elements.length - 1;
        int i = head;
        Object x;
        while ((x = elements[i]) != null) {
            if (o.equals(x))
                return true;
            i = (i + 1) & mask;
        }
        return false;
    }


    @Override
    public boolean remove(Object o) {
        if (o == null)
            return false;
        int mask = elements.length - 1;
        int i = head;
        Object x;
        while ((x = elements[i]) != null) {
            if (o.equals(x)) {
                delete(i);
                return true;
            }
            i = (i + 1) & mask;
        }
        return false;
    }


    @Override
    public void clear() {
        int h = head;
        int t = tail;
        if (h != t) { // clear all cells
            head = tail = 0;
            int i = h;
            int mask = elements.length - 1;
            do {
                elements[i] = null;
                i = (i + 1) & mask;
            } while (i != t);
        }
    }


    @Override
    public Object[] toArray() {
        return toArray(new Object[size()]);
    }


    @Override
    public <T> T[] toArray(T[] a) {
        int size = size();
        if (a.length < size)
            a = (T[]) java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        if (head < tail) {
            System.arraycopy(elements, head, a, 0, size());
        } else if (head > tail) {
            int headPortionLen = elements.length - head;
            System.arraycopy(elements, head, a, 0, headPortionLen);
            System.arraycopy(elements, 0, a, headPortionLen, tail);
        }
        if (a.length > size)
            a[size] = null;
        return a;
    }


    @Override
    public ArrayQueue<E> clone() {
        try {
            ArrayQueue<E> result = (ArrayQueue<E>) super.clone();
            E[] newElements = (E[]) Array.newInstance(elements.getClass().getComponentType(),
                elements.length);
            System.arraycopy(elements, 0, newElements, 0, elements.length);
            result.elements = newElements;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


    private static final long serialVersionUID = 2340985798034038923L;


    private void writeObject(java.io.ObjectOutputStream s)
            throws java.io.IOException {
        s.defaultWriteObject();

        // Write out size
        s.writeInt(size());

        // Write out elements in order.
        int mask = elements.length - 1;
        for (int i = head; i != tail; i = (i + 1) & mask)
            s.writeObject(elements[i]);
    }

    
    private void readObject(java.io.ObjectInputStream s)
            throws java.io.IOException, ClassNotFoundException {
        s.defaultReadObject();

        // Read in size and allocate array
        int size = s.readInt();
        allocateElements(size);
        head = 0;
        tail = size;

        // Read in all elements in the proper order.
        for (int i = 0; i < size; i++)
            elements[i] = s.readObject();
    }
}