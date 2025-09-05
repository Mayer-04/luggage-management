package datastructures.stack;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class GenericResizingIterableStack<T> implements Iterable<T> {
    private T[] elements;
    private int size;

    public GenericResizingIterableStack() {
        elements = (T[]) new Object[1];
        size = 0;
    }

    public void push(T item) {
        if (size == elements.length) {
            resize(2 * elements.length);
        }
        elements[size] = item;
        size++;
    }

    public T pop() {
        T item = elements[--size];

        elements[size] = null;
        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T peak() {
        return elements[size - 1];
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }

        elements = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int index = size - 1;

        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        @Override
        public T next() {
            return elements[index--];
        }
    }
}
