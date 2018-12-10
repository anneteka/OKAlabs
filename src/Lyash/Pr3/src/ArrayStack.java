

import java.util.*;

public class ArrayStack<E> extends Stack<E> {
    private E[] elements;
    private int size;

    public ArrayStack() {
        elements = (E[]) (new Object[5]);
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Iterator<E> iterator() {
        return new ArrayStackIterator();
    }

    public E peek() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        return elements[size - 1];
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        E top = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return top;
    }

    public E push(E value) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = value;
        size++;
        return value;
    }

    public int size() {
        return size;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("bottom ");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            sb.append(' ');
        }
        sb.append("top");
        return sb.toString();
    }

    private void resize() {
        E[] newElements = (E[]) (new Object[2 * size]);
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }


    private class ArrayStackIterator implements Iterator<E> {
        private int index;

        public ArrayStackIterator() {
            index = size - 1;
        }

        public boolean hasNext() {
            return index >= 0;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elements[index];
            index--;
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}