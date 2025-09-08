package services;

import datastructures.list.List;
import domain.BodegaAvion;
import domain.Equipaje;

public class Estadisticas {

    /**
     * Muestra por consola las estadísticas de cada vuelo, incluyendo el destino,
     * cantidad de pasajeros y peso total transportado.
     *
     * @param vuelos arreglo de bodegas de avión a mostrar sus estadísticas
     */
    public static void mostrarEstadisticas(BodegaAvion[] vuelos) {
        List<int[]> estadisticas = Avion.desembarcarVuelo(vuelos);

        for (int i = 0; i < vuelos.length; i++) {
            BodegaAvion vuelo = vuelos[i];
            int[] estadistica = estadisticas.get(i);

            System.out.println("=================================");
            System.out.println("✈️ Avión destino: " + vuelo.getDestino());
            System.out.println("  Pasajeros: " + estadistica[0]);
            System.out.println("  Peso total: " + estadistica[1] + " kg");
            System.out.println("=================================\n");
        }
    }

    /**
     * Muestra por consola la lista de pasajeros a bordo para cada vuelo.
     * <p>
     * Para cada vuelo, se muestra el destino, la cantidad de pasajeros y
     * su lista de pasajeros con su tiquete y peso.
     * </p>
     *
     * @param vuelos arreglo de bodegas de avión a mostrar sus pasajeros
     */
    public static void mostrarListaDePasajeros(BodegaAvion[] vuelos) {
        for (BodegaAvion vuelo : vuelos) {
            System.out.println("=================================");
            System.out.println("✈️ Avión destino: " + vuelo.getDestino());
            System.out.println("Pasajeros a bordo:");

            if (vuelo.estaVacia()) {
                System.out.println(" (Sin pasajeros)");
            } else {
                int numeroDeMaleta = 1;
                for (Equipaje maleta : vuelo.getPasajeros()) {
                    System.out.printf("   %d. %s (Tiquete: %s, %d kg)%n",
                            numeroDeMaleta++,
                            maleta.pasajero(),
                            maleta.categoriaTiquete(),
                            maleta.peso());
                }
            }
            System.out.println("=================================\n");
        }
    }
}
