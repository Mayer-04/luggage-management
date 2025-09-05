import java.util.Scanner;

public class Main {

    private static final String MENU = """
            Seleccione una opción:
            ----------------------
            1) Registrar equipaje
            2) Registrar 500 equipajes
            3) Procesar equipaje
            4) Abordar vuelo
            5) Desembarcar vuelo
            6) Lista de pasajeros
            7) Mostrar estadísticas
            8) Salir
            """;

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var isRunning = true;

        InterfazGestorEquipaje gestorEquipaje = new InterfazGestorEquipaje(scanner);

        while (isRunning) {
            System.out.println(MENU);
            System.out.print("Ingresa una opción entre 1 y 8: ");

            String opcion = scanner.nextLine().trim();
            try {
                int opcionComoNumero = Integer.parseInt(opcion);
                switch (opcionComoNumero) {
                    case 1 -> gestorEquipaje.registrarEquipaje();
                    case 2 -> gestorEquipaje.registrarMultiplesEquipajes();
                    case 3 -> gestorEquipaje.procesarEquipajes();
                    case 4 -> gestorEquipaje.abordarVuelo();
                    case 5 -> gestorEquipaje.desembarcarVuelo();
                    case 6 -> gestorEquipaje.mostrarListaPasajeros();
                    case 7 -> gestorEquipaje.mostrarEstadisticas();
                    case 8 -> isRunning = false;
                    default -> System.out.println("Opción inválida, intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.err.printf("La entrada '%s' no es válida. Por favor escribe un número entre 1 y 8.%n", opcion);
            }
        }

        scanner.close();
    }
}
