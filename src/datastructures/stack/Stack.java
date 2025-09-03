package datastructures.stack;

import java.util.Iterator;

// LIFO (Last in, First out)
@SuppressWarnings("unchecked")
public class Stack<T> implements Iterable<T> {
    private T[] elements;
    private int size;

    public Stack() {
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

//    public T pop() {
//        T item = elements[size - 1];
//        elements[size - 1] = null;
//        size--;
//
//        if (size > 0 && size == elements.length / 4) {
//            resize(elements.length / 2);
//        }
//        return item;
//    }


    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    /**
     * Devuelve el último elemento ingresado en la pila.
     *
     * @return último elemento {@code T} de la pila
     */
    public T peek() {
        return elements[size - 1];
    }

    public void clear() {
        elements = (T[]) new Object[elements.length];
        size = 0;
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];

        if (size >= 0) System.arraycopy(elements, 0, newArray, 0, size);

        elements = newArray;
    }

    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder("[");
        for (int i = size - 1; i >= 0; i--) {
            T element = elements[i];

            if (element instanceof String s) {
                salida.append('"').append(s).append('"');
            } else {
                salida.append(element);
            }

            if (i > 0) {
                salida.append(", ");
            }
        }
        salida.append("]");
        return "stack: " + salida;
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
