import services.InterfazGestorEquipaje;

import java.util.Scanner;

public class Main {

    private static final String MENU = """
            Seleccione una opción:
            ----------------------
            1) Registrar equipaje
            2) Registrar 700 equipajes
            3) Procesar equipaje
            4) Abordar vuelo
            5) Mostrar bodegas de avión
            6) Desembarcar vuelo
            7) Lista de pasajeros
            8) Mostrar estadísticas
            9) Salir
            """;

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var isRunning = true;

        InterfazGestorEquipaje gestorEquipaje = new InterfazGestorEquipaje(scanner);

        while (isRunning) {
            System.out.println(MENU);
            System.out.print("Ingresa una opción entre 1 y 9: ");

            String opcion = scanner.nextLine().trim();
            try {
                int opcionComoNumero = Integer.parseInt(opcion);
                switch (opcionComoNumero) {
                    case 1 -> gestorEquipaje.registrarEquipaje();
                    case 2 -> gestorEquipaje.registrarMultiplesEquipajes();
                    case 3 -> gestorEquipaje.procesarEquipajes();
                    case 4 -> gestorEquipaje.abordarVuelo();
                    case 5 -> gestorEquipaje.mostrarContenidoBodegasAvion();
                    case 6 -> gestorEquipaje.desembarcarVuelo();
                    case 7 -> gestorEquipaje.mostrarListaPasajeros();
                    case 8 -> gestorEquipaje.mostrarEstadisticas();
                    case 9 -> isRunning = false;
                    default -> System.out.println("Opción inválida, intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.err.printf("La entrada '%s' no es válida. Por favor escribe un número entre 1 y 9.%n", opcion);
            }
        }

        scanner.close();
    }
}
