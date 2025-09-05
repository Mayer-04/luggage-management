package datastructures.list;

import java.util.Iterator;

/**
 * Implementación genérica de una lista basada en un arreglo dinámico.
 * <p>
 * La lista crece automáticamente cuando alcanza su capacidad máxima y
 * permite acceder, insertar, modificar y eliminar elementos en posiciones específicas.
 * </p>
 *
 * @param <T> el tipo de elementos que contendrá la lista
 */
@SuppressWarnings("unchecked")
public class List<T> implements Iterable<T> {

    private T[] elements;
    private int size;

    /**
     * Crea una lista vacía con capacidad inicial de 1.
     */
    public List() {
        elements = (T[]) new Object[1];
        size = 0;
    }

    /**
     * Agregar un elemento en una posición indicada.
     *
     * @param index   el índice donde se insertará el elemento
     * @param element el elemento a insertar
     */
    public void add(int index, T element) {
        if (size == elements.length) {
            resize(elements.length * 2);
        }
        elements[index] = element;
        size++;
    }

    /**
     * Agregar un elemento al inicio de la lista.
     *
     * @param element el elemento a insertar
     */
    public void addFirst(T element) {
        add(0, element);
    }

    /**
     * Agregar un elemento al final de la lista.
     *
     * @param element el elemento a insertar
     */
    public void addLast(T element) {
        add(size, element);
    }

    /**
     * Eliminar todos los elementos de la lista.
     */
    public void clear() {
        elements = (T[]) new Object[elements.length];
        size = 0;
    }

    /**
     * Verifica si la lista contiene un elemento.
     *
     * @param element el elemento a buscar
     * @return true si el elemento se encuentra en la lista, false si no
     */
    public boolean contains(T element) {
        for (T value : elements) {
            if (value != null && value.equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el elemento de la lista en la posición indicada.
     *
     * @param index el índice del elemento a obtener
     * @return el elemento en la posición indicada
     */
    public T get(int index) {
        return elements[index];
    }

    /**
     * Obtiene el primer elemento de la lista.
     *
     * @return el primer elemento de la lista
     */
    public T getFirst() {
        return get(0);
    }

    /**
     * Obtiene el último elemento de la lista.
     *
     * @return el último elemento de la lista
     */
    public T getLast() {
        return get(size - 1);
    }

    /**
     * Busca el elemento en la lista y devuelve su posición.
     *
     * @param element el elemento a buscar
     * @return la posición del elemento si lo encuentra, -1 si no lo encuentra
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista no contiene elementos, false si contiene al
     * menos uno
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Remueve y devuelve el elemento de la posición indicada.
     *
     * @param index el índice del elemento a remover
     * @return el elemento que fue removido
     */
    public T removeAt(int index) {
        // 1. Guardamos el elemento que se encuentra en la posición indicada.
        var element = elements[index];

        // 2. Creamos un nuevo arreglo con un espacio menos, ya que vamos a eliminar un elemento.
        T[] newArray = (T[]) new Object[size - 1];

        // 3. Copiamos todos los elementos del arreglo original al nuevo,
        // excepto el que está en la posición que queremos eliminar.
        var newIndex = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                continue;
            }
            newArray[newIndex] = elements[i];
            newIndex++;
        }

        // 4. Actualizamos el contador para reflejar que hay un elemento menos.
        size--;

        // 5. Reemplazamos el arreglo original por el nuevo.
        elements = newArray;

        // 6. Devolvemos el elemento que fue eliminado.
        return element;
    }

    /**
     * Remueve un elemento específico de la lista.
     *
     * @param element el elemento a remover
     * @return true si el elemento fue removido, false si no se encontró
     */
    public boolean remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            removeAt(index);
            return true;
        }
        return false;
    }

    /**
     * Remueve el primer elemento de la lista y lo devuelve.
     *
     * @return el elemento removido
     */
    public T removeFirst() {
        return removeAt(0);
    }

    /**
     * Remueve el último elemento de la lista y lo devuelve.
     *
     * @return el elemento removido
     */
    public T removeLast() {
        return removeAt(size - 1);
    }

    /**
     * Remueve un rango de elementos y devuelve una nueva lista con ellos.
     *
     * @param start índice inicial (inclusive)
     * @param end   índice final (exclusive)
     * @return nueva lista con los elementos removidos
     */
    public List<T> removeRange(int start, int end) {
        int count = end - start;
        var removed = new List<T>();

        // Copiar los elementos que se eliminan
        System.arraycopy(elements, start, removed.elements, 0, count);
        removed.size = count;

        // Desplazar a la izquierda los elementos posteriores a 'end'
        for (int i = end; i < size; i++) {
            elements[i - count] = elements[i];
        }

        // Limpiar el resto
        for (int i = size - count; i < size; i++) {
            elements[i] = null;
        }

        size -= count;
        return removed;
    }

    /**
     * Modifica un elemento de la lista en la posición indicada.
     *
     * @param index   el índice del elemento a modificar
     * @param element el nuevo valor del elemento
     */
    public void set(int index, T element) {
        elements[index] = element;
    }

    /**
     * Devuelve el número de elementos en la lista.
     *
     * @return la cantidad de elementos que contiene la lista
     */
    public int size() {
        return size;
    }

    /**
     * Redimensiona la capacidad interna de la lista.
     *
     * @param newCapacity la nueva capacidad del arreglo interno
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
     * Devuelve un <strong>iterador</strong> para recorrer los elementos de la lista
     * en orden natural (de izquierda a derecha).
     *
     * @return un iterador de tipo {@link Iterator}
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    /**
     * Devuelve una representación en cadena de la lista.
     * <p>
     * Las cadenas se muestran entre comillas dobles.
     *
     * @return una cadena que representa la lista
     */
    @Override
    public String toString() {
        final StringBuilder output = new StringBuilder("list: [");
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
        return output.toString();
    }

    /**
     * Iterador interno que recorre los elementos de la lista
     * en orden secuencial.
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
         * @return el siguiente elemento de la lista
         */
        @Override
        public T next() {
            return elements[index++];
        }
    }
}
