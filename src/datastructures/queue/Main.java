package datastructures.queue;

import java.util.Scanner;

public class Main {

    private static final String MENU = """
            Seleccione una opción:
            ----------------------
            1) Agregar un elemento
            2) Eliminar un elemento
            3) Mostrar el primer elemento
            4) Mostrar todos los elementos
            5) Salir
            """;

    public static void main(String[] args) {
        var queue = new Queue<String>();
        var sc = new Scanner(System.in);
        var interactiveQueue = new InteractiveQueue(queue, sc);
        var running = true;

        while (running) {
            System.out.println(MENU);
            System.out.print("Ingrese su opción: ");

            String line = sc.nextLine(); // leemos la línea entera
            int option;
            try {
                option = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número entre 1 y 5.");
                continue;
            }

            switch (option) {
                case 1 -> interactiveQueue.enqueue();
                case 2 -> interactiveQueue.dequeue();
                case 3 -> interactiveQueue.peek();
                case 4 -> interactiveQueue.showElements();
                case 5 -> {
                    running = false;
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción inválida, intenta de nuevo.");
            }
        }

        sc.close();
    }
}
