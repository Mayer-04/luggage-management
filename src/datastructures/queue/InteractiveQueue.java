package datastructures.queue;

import java.util.Scanner;

@SuppressWarnings("ClassCanBeRecord")
public class InteractiveQueue {
    private final Queue<String> queue;
    private final Scanner scanner;

    public InteractiveQueue(Queue<String> queue, Scanner sc) {
        this.queue = queue;
        this.scanner = sc;
    }

    /**
     * Solicita al usuario un elemento y lo agrega a la cola.
     * Válida que el elemento no esté vacío ni sea solo espacios.
     */
    public void enqueue() {
        System.out.print("Ingrese un elemento: ");
        String element = scanner.nextLine();

        if (element == null || element.trim().isEmpty()) {
            System.out.println("El elemento no puede estar vacío");
            return;
        }

        queue.enqueue(element);
        System.out.println("Elemento agregado: " + element);
    }

    /**
     * Elimina el primer elemento de la cola y lo muestra.
     * Si la cola está vacía, informa al usuario.
     */
    public void dequeue() {
        if (queue.isEmpty()) {
            System.out.println("La cola está vacía.");
            return;
        }

        String removed = queue.dequeue();
        System.out.println("Elemento eliminado: " + removed);
    }

    /**
     * Muestra el primer elemento de la cola sin eliminarlo.
     * Si la cola está vacía, informa al usuario.
     */
    public void peek() {
        // Verifica si la cola está vacía antes de intentar acceder al primer elemento
        if (queue.isEmpty()) {
            System.out.println("La cola está vacía. No hay elementos para mostrar.");
            return;
        }

        var firstElement = queue.peek();
        if (firstElement == null) {
            System.out.println("Error: No se pudo obtener el primer elemento.");
            return;
        }

        System.out.println("Primer elemento en la cola: " + firstElement);
    }

    /**
     * Muestra todos los elementos presentes en la cola en orden.
     * Si la cola está vacía, informa al usuario.
     */
    public void showElements() {
        if (queue.isEmpty()) {
            System.out.println("La cola está vacía. No hay elementos para mostrar.");
            return;
        }

        System.out.println("Elementos en la cola:");

        StringBuilder sb = new StringBuilder("[ ");
        int size = queue.size();
        int index = 0;

        // Agrega un separador entre elementos, excepto después del último
        for (String element : queue) {
            sb.append(element);
            if (index < size - 1) {
                sb.append(" | ");
            }
            index++;
        }

        sb.append(" ]");
        System.out.println(sb);
    }
}