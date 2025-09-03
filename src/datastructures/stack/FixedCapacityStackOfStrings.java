package datastructures.stack;

public class FixedCapacityStackOfStrings {

    private final String[] elements;
    private int count;

    public FixedCapacityStackOfStrings(int capacity) {
        elements = new String[capacity];
        count = 0;
    }

    public void push(String item) {
        elements[count] = item;
        count++;
    }

    public String pop(String item) {
        return elements[--count];
    }

    public boolean isEmpty(String item) {
        return count == 0;
    }

    public int size() {
        return count;
    }

    public String peek() {
        return elements[count - 1];
    }

    @Override
    public String toString() {
        String salida = "[";
        for (int i = count - 1; i >= 0; i--) {
            salida += elements[i];
            if (i < count - 1) {
                salida += ", ";
            }
        }
        salida += "]";
        return "stackOfString: " + salida;
    }
}
