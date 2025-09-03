package datastructures.linkedlist;

public class Main {
    public static void main(String[] args) {
        var linkedList = new LinkedList<Integer>();

        // Caso 1: linkedList vacía
        System.out.println("Inicial: " + linkedList);

        // Caso 2: append
        linkedList.append(10);
        System.out.println("Append(10): " + linkedList);

        linkedList.append(20);
        System.out.println("Append(20): " + linkedList);

        linkedList.append(30);
        System.out.println("Append(30): " + linkedList);

        // Caso 3: prepend
        linkedList.prepend(5);
        System.out.println("Prepend(5): " + linkedList);

        linkedList.prepend(2);
        System.out.println("Prepend(2): " + linkedList);

        linkedList.prepend(1);
        System.out.println("Prepend(1): " + linkedList);
    }
}

