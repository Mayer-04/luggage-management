import luggage.ColaGeneral;

import java.util.Scanner;

public class Main {

    private static final String MENU = """
            Seleccione una opción:
            ----------------------
            1) Registrar equipaje
            2) Procesar equipaje
            3) Clasificar equipaje
            4) Abordar vuelo
            5) Desembarcar vuelo
            6) Lista de pasajeros
            7) Mostrar estadísticas
            8) Salir
            """;

    public static void main(String[] args) {

        var colaGeneral = new ColaGeneral();
        var sc = new Scanner(System.in);
        var running = true;
        var interfaz = new Interfaz(colaGeneral, sc);

        while (running) {
            System.out.println(MENU);
            System.out.print("Ingrese su opción: ");

            String line = sc.nextLine();
            int option;
            try {
                option = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Introduce un número entre 1 y 5.");
                continue;
            }

            switch (option) {
                case 1 -> {
                    interfaz.registrarEquipaje();
                    System.out.println("cola general: " + colaGeneral);
                }
                case 8 -> {
                    running = false;
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción inválida, intenta de nuevo.");
            }

        }
    }
}
