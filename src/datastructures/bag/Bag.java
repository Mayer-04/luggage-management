package datastructures.bag;

import java.util.Iterator;

/**
 * Implementación genérica de un {@code Bag}, también conocido como multiconjunto.
 * <p>
 * Un Bag es una colección que permite almacenar elementos sin un orden específico
 * y donde pueden existir duplicados. A diferencia de un {@code Set}, este no evita
 * elementos repetidos, y a diferencia de una {@code List}, no garantiza un orden.
 * <p>
 * Características principales:
 * <ul>
 *     <li>Permite elementos duplicados.</li>
 *     <li>No mantiene un orden fijo de inserción.</li>
 *     <li>Se redimensiona automáticamente cuando alcanza su capacidad.</li>
 *     <li>Es iterable, por lo que puede usarse en un bucle {@code for-each}.</li>
 * </ul>
 *
 * @param <T> tipo de los elementos que contendrá el bag
 */
@SuppressWarnings("unchecked")
public class Bag<T> implements Iterable<T> {

    /**
     * Arreglo interno donde se almacenan los elementos.
     */
    private T[] elements;

    /** Número actual de elementos almacenados en el bag. */
    private int size;

    /**
     * Crea un {@code Bag} vacío con una capacidad inicial mínima.
     */
    public Bag() {
        elements = (T[]) new Object[1];
        size = 0;
    }

    /**
     * Agrega un elemento al bag.
     * <p>
     * Este método no verifica duplicados; por lo tanto, múltiples instancias
     * del mismo objeto o valores iguales pueden coexistir dentro del bag.
     *
     * @param item el elemento a insertar (puede ser repetido)
     */
    public void add(T item) {
        // Objects.requireNonNull(item, "El elemento no puede ser nulo");
        if (size == elements.length) {
            resize(elements.length * 2); // duplicar capacidad cuando está lleno
        }
        elements[size] = item;
        size++;
    }

    /**
     * Verifica si un elemento está presente en el bag.
     *
     * @param item el elemento a buscar
     * @return {@code true} si el bag contiene al menos una ocurrencia de {@code item},
     *         {@code false} en caso contrario
     */
    public boolean contains(T item) {
        for (T element : elements) {
            if (element != null && element.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determina si el bag está vacío.
     *
     * @return {@code true} si no contiene elementos, {@code false} en caso contrario
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Devuelve el número de elementos almacenados en el bag.
     *
     * @return cantidad de elementos
     */
    public int size() {
        return size;
    }

    /**
     * Elimina todos los elementos del bag.
     * <p>
     * Después de esta operación, el bag estará vacío pero conservará la misma capacidad.
     */
    public void clear() {
        elements = (T[]) new Object[elements.length];
        size = 0;
    }

    /**
     * Redimensiona el arreglo interno a una nueva capacidad.
     * <p>
     * Este método es utilizado internamente para manejar el crecimiento dinámico.
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
     * Devuelve un iterador que recorre los elementos del bag.
     * <p>
     * El orden de iteración no está garantizado y puede variar dependiendo
     * de cómo fueron insertados los elementos.
     *
     * @return un iterador de tipo {@link Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    /**
     * Devuelve una representación en cadena de los elementos del bag.
     * <p>
     * Ejemplo: {@code bag: [1, 2, "hola", 3]}
     *
     * @return cadena representando el contenido del bag
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
        return "bag: " + output;
    }

    /**
     * Clase interna que implementa un iterador simple para recorrer los elementos del bag.
     */
    private class ArrayIterator implements Iterator<T> {
        /** Índice actual de iteración. */
        private int index = 0;

        /**
         * Indica si aún quedan elementos por iterar.
         *
         * @return {@code true} si hay más elementos, {@code false} en caso contrario
         */
        @Override
        public boolean hasNext() {
            return index < size;
        }

        /**
         * Devuelve el siguiente elemento del bag en la iteración.
         *
         * @return el siguiente elemento
         */
        @Override
        public T next() {
            return elements[index++];
        }
    }
}
