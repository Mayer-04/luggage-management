package datastructures.deque;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class Deque<T> implements Iterable<T> {

    private T[] elements;
    private int front;
    private int rear;
    private int size;

    public Deque() {
        elements = (T[]) new Object[1];
        front = 0; // el frente
        rear = -1; // trasero
        size = 0;
    }

    public void addFirst(T element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        front = (front - 1 + elements.length) % elements.length;
        elements[front] = element;
        size++;
    }

    public void addLast(T element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }

    public T removeFirst() {
        T element = elements[front];
        elements[front] = null;
        front = (front + 1) % elements.length;
        size--;

        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        return element;
    }

    public T removeLast() {
        T element = elements[rear];
        elements[rear] = null;
        rear = (rear - 1 + elements.length) % elements.length;
        size--;

        if (size > 0 && size == elements.length / 4) {
            resize(elements.length / 2);
        }
        return element;
    }

    public T getFirst() {
        return elements[front];
    }

    public T getLast() {
        return elements[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[(front + i) % elements.length];
        }

        elements = newArray;
        front = 0;
        rear = size - 1;
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
        return "deque: " + output;
    }

    private class ArrayIterator implements Iterator<T> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return elements[(front + index++) % elements.length];
        }
    }
}
