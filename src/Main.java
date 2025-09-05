import domain.Bodega;
import domain.BodegaAvion;
import domain.ColaGeneral;
import services.ColeccionBodegas;

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

        var colaGeneral = new ColaGeneral();
        var sc = new Scanner(System.in);
        var running = true;
        var interfaz = new Interfaz(colaGeneral, sc);


        Bodega[] bodegas = ColeccionBodegas.obtenerBodegasDeEntrada();
        BodegaAvion[] bodegasAvion = ColeccionBodegas.obtenerBodegasDeAviones();

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
                case 1 -> interfaz.registrarEquipaje();
                case 2 -> interfaz.registrarMultiplesEquipajes(colaGeneral);
                case 3 -> interfaz.procesarEquipajes(bodegas);
                case 4 -> interfaz.abordarVuelo(bodegas, bodegasAvion);
                case 5 -> interfaz.desembarcarVuelo(bodegasAvion);
                case 6 -> interfaz.mostrarListaPasajeros(bodegasAvion);
                case 7 -> interfaz.mostrarEstadisticas(bodegasAvion);
                case 8 -> {
                    running = false;
                    System.out.println("Saliendo del programa...");
                }
                default -> System.out.println("Opción inválida, intenta de nuevo.");
            }

        }
    }
}
