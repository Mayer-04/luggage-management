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

        /**
         * Punto de entrada principal del programa.
         *
         * <p>En este método se instancia una clase {@link InterfazGestorEquipaje}
         * y se crea un bucle principal que se encarga de mostrar el menú principal
         * al usuario y de realizar la acción correspondiente según la opción
         * seleccionada por el usuario.</p>
         *
         * @param args argumentos pasados al programa desde la línea de comandos
         */
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var gestorEquipaje = new InterfazGestorEquipaje(scanner);
        var estaCorriendo = true;

        while (estaCorriendo) {
            System.out.println(MENU);
            System.out.print("Ingrese una opción entre 1 y 9: ");

            var opcion = scanner.nextLine().trim();
            try {
                var opcionComoNumero = Integer.parseInt(opcion);
                switch (opcionComoNumero) {
                    case 1 -> gestorEquipaje.registrarEquipaje();
                    case 2 -> gestorEquipaje.registrarMultiplesEquipajes();
                    case 3 -> gestorEquipaje.procesarEquipajes();
                    case 4 -> gestorEquipaje.abordarVuelo();
                    case 5 -> gestorEquipaje.mostrarContenidoBodegasAvion();
                    case 6 -> gestorEquipaje.desembarcarVuelo();
                    case 7 -> gestorEquipaje.mostrarListaPasajeros();
                    case 8 -> gestorEquipaje.mostrarEstadisticas();
                    case 9 -> {
                        System.out.println("Saliendo del programa...");
                        estaCorriendo = false;
                        scanner.close();
                    }
                    default -> System.out.println("Opción inválida, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.err.printf("La entrada '%s' no es válida. Por favor escribe un número entre 1 y 9.%n", opcion);
            }
        }
    }
}
