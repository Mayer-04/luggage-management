package util;

import datastructures.list.List;
import luggage.BodegaAvion;
import luggage.Equipaje;

public class Estadisticas {

    // [2, 28]
    // [3, 66]
    // [5, 88]
    public static void mostrarEstadistica(BodegaAvion[] vuelos) {
        List<int[]> despachar = Avion.despacharVuelo(vuelos);

        for (int i = 0; i < vuelos.length; i++) {
            BodegaAvion avion = vuelos[i];
            int[] estadisticaAvion = despachar.get(i);

            System.out.println("=================================");
            System.out.println("✈️  Avión destino: " + avion.getDestino());
            System.out.println("👥 Pasajeros: " + estadisticaAvion[0]);
            System.out.println("⚖️  Peso total: " + estadisticaAvion[1] + " kg");
            System.out.println("=================================\n");
        }
    }

    public static void mostrarListaDePasajeros(BodegaAvion[] vuelos) {
        for (BodegaAvion avion : vuelos) {
            System.out.println("=================================");
            System.out.println("✈️  Avión destino: " + avion.getDestino());
            System.out.println("👥 Pasajeros a bordo:");

            if (avion.estaVacia()) {
                System.out.println("   (Sin pasajeros)");
            } else {
                int i = 1;
                for (Equipaje maleta : avion.getPasajeros()) { // ✅ no se altera la pila
                    System.out.printf("   %d. %s (Tiquete: %s, %d kg)%n",
                            i++,
                            maleta.pasajero(),
                            maleta.categoriaTiquete(),
                            maleta.peso());
                }
            }

            System.out.println("=================================\n");
        }
    }
}
