package datastructures;

import java.util.Iterator;

/**
 * Implementación genérica de una {@code Deque} (doble cola) usando un arreglo circular dinámico.
 * <p>
 * Una Deque permite insertar y eliminar elementos tanto desde el frente como desde el final.
 * Internamente, utiliza un arreglo que se expande y contrae automáticamente
 * para optimizar el uso de memoria.
 * <p>
 * Características principales:
 * <ul>
 *     <li>Permite operaciones en ambos extremos en tiempo constante amortizado.</li>
 *     <li>Se redimensiona automáticamente cuando se llena o cuando queda demasiado vacía.</li>
 *     <li>Es iterable: puede usarse en bucles {@code for-each}.</li>
 * </ul>
 *
 * @param <T> tipo de los elementos almacenados en la deque
 */
@SuppressWarnings("unchecked")
public class Deque<T> implements Iterable<T> {

    /**
     * Arreglo interno donde se almacenan los elementos.
     */
    private T[] elements;

    /** Índice del frente de la deque. */
    private int front;

    /** Índice del final de la deque. */
    private int rear;

    /** Número actual de elementos en la deque. */
    private int size;

    /**
     * Crea una {@code Deque} vacía con capacidad inicial mínima.
     */
    public Deque() {
        elements = (T[]) new Object[1];
        front = 0;
        rear = -1;
        size = 0;
    }

    /**
     * Inserta un elemento al inicio de la deque.
     *
     * @param element el elemento a insertar
     */
    public void addFirst(T element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        front = (front - 1 + elements.length) % elements.length;
        elements[front] = element;
        size++;
    }

    /**
     * Inserta un elemento al final de la deque.
     *
     * @param element el elemento a insertar
     */
    public void addLast(T element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        rear = (rear + 1) % elements.length;
        elements[rear] = element;
        size++;
    }

    /**
     * Elimina y devuelve el primer elemento de la deque.
     *
     * @return el primer elemento almacenado
     */
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

    /**
     * Elimina y devuelve el último elemento de la deque.
     *
     * @return el último elemento almacenado
     */
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

    /**
     * Devuelve el primer elemento sin eliminarlo.
     *
     * @return el primer elemento de la deque
     */
    public T getFirst() {
        return elements[front];
    }

    /**
     * Devuelve el último elemento sin eliminarlo.
     *
     * @return el último elemento de la deque
     */
    public T getLast() {
        return elements[rear];
    }

    /**
     * Verifica si la deque está vacía.
     *
     * @return {@code true} si no contiene elementos, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Devuelve el número actual de elementos en la deque.
     *
     * @return número de elementos
     */
    public int size() {
        return size;
    }

    /**
     * Redimensiona el arreglo interno a una nueva capacidad.
     * <p>
     * Se utiliza tanto para crecer como para reducir el espacio disponible.
     *
     * @param newCapacity la nueva capacidad del arreglo
     */
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[(front + i) % elements.length];
        }

        elements = newArray;
        front = 0;
        rear = size - 1;
    }

    /**
     * Devuelve un iterador que recorre los elementos de la deque
     * desde el frente hasta el final.
     *
     * @return un iterador de tipo {@link Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    /**
     * Devuelve una representación en cadena de la deque.
     * <p>
     * Ejemplo: {@code deque: [1, 2, 3]}
     *
     * @return cadena representando el contenido de la deque
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            T element = elements[(front + i) % elements.length];
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

    /**
     * Clase interna que implementa un iterador para recorrer la deque.
     */
    private class ArrayIterator implements Iterator<T> {
        /** Índice actual en la iteración. */
        private int index = 0;

        /**
         * Indica si quedan elementos por recorrer.
         *
         * @return {@code true} si hay más elementos, {@code false} en caso contrario
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Devuelve el siguiente elemento en la iteración.
         *
         * @return el siguiente elemento
         */
        @Override
        public T next() {
            return elements[(front + index++) % elements.length];
        }
    }
}
