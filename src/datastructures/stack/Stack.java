package datastructures.stack;

import java.util.Iterator;

/**
 * Implementación genérica de una pila (estructura de datos LIFO: Last In, First Out).
 * <p>
 * La pila crece dinámicamente cuando se llena y se contrae cuando
 * está sobredimensionada para optimizar el uso de memoria.
 *
 * @param <T> el tipo de elementos almacenados en la pila
 */
@SuppressWarnings("unchecked")
public class Stack<T> implements Iterable<T> {
    private T[] elements;
    private int size;

    /**
     * Crea una pila vacía con capacidad inicial de 1.
     */
    public Stack() {
        elements = (T[]) new Object[1];
        size = 0;
    }

    /**
     * Inserta un elemento en la parte superior de la pila.
     *
     * @param item el elemento a agregar
     */
    public void push(T item) {
        if (size == elements.length) {
            resize(2 * elements.length);
        }
        elements[size] = item;
        size++;
    }

    /**
     * Elimina y devuelve el elemento en la parte superior de la pila.
     *
     * @return el elemento en la parte superior de la pila
     */
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


    /**
     * Verifica si la pila está vacía.
     *
     * @return {@code true} si la pila no contiene elementos, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Devuelve el número de elementos almacenados en la pila.
     *
     * @return cantidad de elementos en la pila
     */
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

    /**
     * Elimina todos los elementos de la pila, dejándola vacía.
     */
    public void clear() {
        elements = (T[]) new Object[elements.length];
        size = 0;
    }

    /**
     * Redimensiona el arreglo interno de la pila.
     *
     * @param newCapacity la nueva capacidad del arreglo
     */
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }

        elements = newArray;
    }

    /**
     * Devuelve un iterador que recorre los elementos de la pila
     * en orden inverso (del más reciente al más antiguo).
     *
     * @return un iterador de tipo {@link Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    /**
     * Devuelve una representación en cadena de la pila.
     * <p>
     * Los elementos se muestran desde el tope hasta la base.
     * Las cadenas se muestran entre comillas dobles.
     *
     * @return una cadena que representa la pila
     */
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

    /**
     * Iterador interno que recorre los elementos de la pila
     * en orden inverso (del último al primero).
     */
    private class ReverseArrayIterator implements Iterator<T> {
        private int index = size - 1;

        /**
         * Indica si aún quedan elementos por iterar.
         *
         * @return {@code true} si hay más elementos, {@code false} en caso contrario
         */
        @Override
        public boolean hasNext() {
            return index >= 0;
        }

        /**
         * Devuelve el siguiente elemento en el recorrido.
         *
         * @return el siguiente elemento de la pila
         */
        @Override
        public T next() {
            return elements[index--];
        }
    }
}
