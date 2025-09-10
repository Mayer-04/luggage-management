package datastructures.queue;

import java.util.Iterator;

/**
 * La clase {@code Queue} representa una cola genérica basada en un
 * arreglo dinámico.
 * <p>
 * Una cola sigue el principio <strong>FIFO (First In, First Out)</strong>:
 * El primer elemento en entrar es el primero en salir.
 * </p>
 *
 * Ejemplo de uso:
 * <pre>{@code
 * Queue<String> queue = new Queue<>();
 * queue.enqueue("A");
 * queue.enqueue("B");
 * queue.enqueue("C");
 *
 * System.out.println(queue.dequeue()); // "A"
 * System.out.println(queue.peek());    // "B"
 * }</pre>
 *
 * @param <T> tipo de los elementos almacenados en la cola
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
     * Inserta un elemento al final de la cola.
     * <p>
     * Si el arreglo interno está lleno (es decir, {@code size == elements.length}),
     * la capacidad se duplica automáticamente antes de insertar el nuevo elemento.
     * </p>
     *
     * @param item el elemento a agregar al final de la cola
     */
    public void enqueue(T item) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[size] = item;
        size++;
    }

    /**
     * Elimina y devuelve el primer elemento de la cola.
     *
     * <h4>Detalles de implementación</h4>
     * <ol>
     *   <li>Guarda el <strong>primer elemento</strong> de la cola en una variable para devolverlo al final.</li>
     *   <li>Mueve todos los demás elementos una posición hacia la izquierda,
     *       de forma que el segundo elemento pase a ser el primero, el tercer pase a ser el segundo,
     *       y así hasta el final.</li>
     *   <li>Deja vacía la última posición (poniéndola en {@code null}) para que no quede ocupando memoria.</li>
     *   <li>Resta 1 al contador de elementos.</li>
     *   <li>Si después de quitar un elemento la cola queda usando solo el <em>25%</em> de su espacio,
     *       reduce el tamaño del arreglo a la mitad.</li>
     * </ol>
     *
     * @return el elemento que estaba en el frente de la cola
     */
    public T dequeue() {
        T item = elements[0];

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
     * Devuelve el elemento al <strong>frente</strong> de la cola sin eliminarlo.
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
     * @param newCapacity la nueva capacidad que tendra el arreglo
     */
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
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
     * Devuelve una representación en <strong>cadena</strong> de la cola.
     * <p>
     * Los elementos se muestran desde el frente hasta el final.
     * </p>
     * <p>
     * Si un elemento es una cadena, se mostrará entre comillas dobles.
     * Todos los elementos aparecen separados por comas y encerrados entre corchetes.
     * </p>
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
         * Devuelve el siguiente elemento en la iteración.
         *
         * @return el siguiente elemento en la iteración <em>(queue)</em>
         */
        @Override
        public T next() {
            return elements[index++];
        }
    }
}
