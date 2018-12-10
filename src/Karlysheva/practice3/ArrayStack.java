package practice3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<Item> implements Iterable<Item> {

    Item[] items;
    int size;

    public ArrayStack() {
        items = (Item[]) new Object[10];
        size = 0;
    }

    public void push(Item item) {
        if (item == null) throw new NullPointerException();
        size++;
        if (size > items.length) items = resize();
    }

    public Item pop() {
        if (size == 0) throw new NoSuchElementException();

        if (size < items.length / 4) items = resize();
        size--;
        Item ret = items[size];
        items[size]=null;
        return ret;
    }

    public Item peek() {
        if (size == 0) throw new NoSuchElementException();
        return items[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Item[] resize() {
        Item[] temp = (Item[]) new Object[size * 2];
        for (int i = 0; i < size; i++)
            temp[i] = items[i];

        return temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            int current = -1;

            @Override
            public boolean hasNext() {
                return current + 1 < size;
            }

            @Override
            public Item next() {
                current++;
                return items[current];
            }
        };
    }
}
