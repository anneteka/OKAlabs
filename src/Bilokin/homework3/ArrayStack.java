

import java.util.ArrayList;
import java.util.EmptyStackException;


public class ArrayStack<E> extends ArrayList<E> {

    private static final long serialVersionUID = 2130079159931574599L;

    public ArrayStack() {
        super();
    }

    public ArrayStack(final int initialSize) {
        super(initialSize);
    }

  
    public boolean empty() {
        return isEmpty();
    }

    
    public E peek() throws EmptyStackException {
        final int n = size();
        if (n <= 0) {
            throw new EmptyStackException();
        } else {
            return get(n - 1);
        }
    }

   
    public E peek(final int n) throws EmptyStackException {
        final int m = (size() - n) - 1;
        if (m < 0) {
            throw new EmptyStackException();
        } else {
            return get(m);
        }
    }

   
    public E pop() throws EmptyStackException {
        final int n = size();
        if (n <= 0) {
            throw new EmptyStackException();
        } else {
            return remove(n - 1);
        }
    }

   
    public E push(final E item) {
        add(item);
        return item;
    }

    
    public int search(final Object object) {
        int i = size() - 1;        // Current index
        int n = 1;                 // Current distance
        while (i >= 0) {
            final Object current = get(i);
            if ((object == null && current == null) ||
                (object != null && object.equals(current))) {
                return n;
            }
            i--;
            n++;
        }
        return -1;
    }

}