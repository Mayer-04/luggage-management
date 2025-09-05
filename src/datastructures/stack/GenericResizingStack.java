package datastructures.stack;

@SuppressWarnings("unchecked")
public class GenericResizingStack<T> {

    private T[] elements;
    private int size;

    public GenericResizingStack() {
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

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T peak() {
        return elements[size - 1];
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        
        for (int i = 0; i < size; i++) {
            newArray[i] = elements[i];
        }

        elements = newArray;
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

            if (i < size - 1) {
                salida.append(", ");
            }
        }
        salida.append("]");
        return "genericStack: " + salida;
    }
}
