package datastructures.queue;

import java.util.Iterator;

/**
 * Implementación genérica de una cola usando un arreglo dinámico.
 * <p>
 * Una cola sigue el principio <b>FIFO (First In, First Out)</b>:
 * el primer elemento en entrar es el primero en salir.
 * </p>
 *
 * <h2>Ejemplo de uso</h2>
 * <pre>{@code
 * Queue<String> queue = new Queue<>();
 * queue.enqueue("A");
 * queue.enqueue("B");
 * queue.enqueue("C");
 *
 * System.out.println(queue.dequeue()); // Imprime "A"
 * System.out.println(queue.peek());    // Imprime "B"
 * }</pre>
 *
 * @param <T> tipo de elementos que contendrá la cola
 */
@SuppressWarnings("unchecked")
public class Queue<T> implements Iterable<T> {
    private T[] elements;
    private int size;

    /**
     * Crea una cola vacía con capacidad inicial de 1.
     */
    public Queue() {
        elements = (T[]) new Object[1];
        size = 0;
    }

    /**
     * Inserta un elemento al final de la cola (operación de encolado).
     *
     * @param item el elemento a agregar
     */
    public void enqueue(T item) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size] = item;
        size++;
    }

    /**
     * Elimina y devuelve el primer elemento de la cola (operación de desencolado).
     *
     * @return el elemento que estaba en el frente de la cola
     */
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

    /**
     * Devuelve el número de elementos actualmente en la cola.
     *
     * @return cantidad de elementos en la cola
     */
    public int size() {
        return size;
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return {@code true} si no contiene elementos, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Elimina todos los elementos de la cola, dejándola vacía.
     */
    public void clear() {
        elements = (T[]) new Object[elements.length];
        size = 0;
    }

    /**
     * Redimensiona el arreglo interno de la cola a una nueva capacidad.
     * <p>
     * Se utiliza automáticamente al encolar cuando el arreglo está lleno
     * o al desencolar cuando el arreglo queda demasiado vacío.
     * </p>
     *
     * @param newCapacity la nueva capacidad del arreglo
     */
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];

        // if (size >= 0) System.arraycopy(elements, 0, newArray, 0, size);
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }

        elements = newArray;
    }

    /**
     * Devuelve un <strong>iterador</strong> que recorre los elementos de la cola en orden FIFO
     * (del frente al final).
     *
     * @return un iterador de tipo {@link Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    /**
     * Devuelve una representación en cadena de la cola.
     * <p>
     * Los elementos se muestran desde el frente hasta el final.
     * </p>
     * Las cadenas se muestran entre comillas dobles.
     *
     * @return una cadena con el contenido de la cola
     */
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

    /**
     * Iterador interno que recorre los elementos de la cola en orden FIFO.
     */
    private class ArrayIterator implements Iterator<T> {
        private int index = 0;

        /**
         * Indica si aún quedan elementos por recorrer.
         *
         * @return {@code true} si hay más elementos, {@code false} en caso contrario
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Devuelve el siguiente elemento en el recorrido.
         *
         * @return el siguiente elemento de la cola
         */
        @Override
        public T next() {
            return elements[index++];
        }
    }
}
