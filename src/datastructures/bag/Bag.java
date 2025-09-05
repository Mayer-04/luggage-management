package datastructures.bag;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class Bag<T> implements Iterable<T> {

    private T[] elements;
    private int size;

    public Bag() {
        elements = (T[]) new Object[1];
        size = 0;
    }

    public void add(T item) {
        // Objects.requireNonNull(item, "El elemento no puede ser nulo");
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size] = item;
        size++;
    }

    public boolean contains(T item) {
        for (T element : elements) {
            if (element != null && element.equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        elements = (T[]) new Object[elements.length];
        size = 0;
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
        return new ArrayIterator();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            T element = elements[i];
            if (element instanceof String s) {
                output.append('"').append(s).append('"');
            } else {
                output.append(element);
            }

            if (i < size - 1) {
                output.append(", ");
            }
        }
        output.append("]");
        return "bag: " + output;
    }

    private class ArrayIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return elements[index++];
        }
    }
}
