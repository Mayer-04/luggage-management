package datastructures.queue;

import java.util.Iterator;

// FIFO (First in, First Out)
@SuppressWarnings("unchecked")
public class Queue<T> implements Iterable<T> {
    private T[] elements;
    private int size;

    public Queue() {
        elements = (T[]) new Object[1];
        size = 0;
    }

    public void enqueue(T item) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size] = item;
        size++;
    }

    public T dequeue() {
        var item = elements[0];

        for (int i = 1; i < size; i++) {
            elements[i - 1] = elements[i];
        }

        elements[size - 1] = null;

        size--;

        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }

        return item;
    }

    /**
     * Devuelve el elemento al frente de la cola sin eliminarlo.
     *
     * @return el elemento al frente de la cola
     */
    public T peek() {
        return elements[0];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        elements = (T[]) new Object[elements.length];
        size = 0;
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];

        // if (size >= 0) System.arraycopy(elements, 0, newArray, 0, size);
        if (size >= 0) System.arraycopy(elements, 0, newArray, 0, size);

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
        return "queue: " + output;
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
