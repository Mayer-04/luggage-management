package util;

import domain.BodegaAvion;
import domain.Equipaje;

/**
 * La clase {@code Estadisticas} proporciona métodos utilitarios
 * para mostrar por consola información relevante sobre los vuelos
 * y sus bodegas de equipajes.
 *
 * <p>Ofrece funcionalidades para:</p>
 * <ul>
 *   <li>Mostrar estadísticas de cada vuelo (destino, cantidad de pasajeros y peso total).</li>
 *   <li>Listar los pasajeros a bordo junto con los datos de su equipaje.</li>
 * </ul>
 *
 * @see BodegaAvion
 * @see Equipaje
 */
public class Estadisticas {

    /**
     * Muestra por consola las estadísticas de cada vuelo, incluyendo:
     * <ul>
     *   <li>El destino del vuelo.</li>
     *   <li>La cantidad de pasajeros (equipajes registrados).</li>
     *   <li>El peso total de los equipajes transportados.</li>
     * </ul>
     *
     * @param vuelos arreglo de {@link BodegaAvion} cuyas estadísticas se van a mostrar
     */
    public static void mostrarEstadisticas(BodegaAvion[] vuelos) {
        for (BodegaAvion vuelo : vuelos) {
            int cantidad = vuelo.size();
            int pesoTotal = 0;

            for (Equipaje maleta : vuelo.getPasajeros()) {
                pesoTotal += maleta.peso();
            }

            System.out.println("✈️ Avión destino: " + vuelo.getDestino());
            System.out.println("   Pasajeros a bordo: " + cantidad);
            System.out.println("   Peso total equipaje: " + pesoTotal + " kg");
            System.out.println("---------------------------------\n");
        }
    }

    /**
     * Muestra por consola la lista de pasajeros a bordo para cada vuelo.
     *
     * <p>
     * Para cada vuelo se imprime:
     * <ul>
     *   <li>El destino del vuelo.</li>
     *   <li>La lista de pasajeros con su categoría de tiquete y peso de equipaje.</li>
     *   <li>El número total de equipajes transportados.</li>
     * </ul>
     * Si un vuelo no tiene equipajes registrados, se indica explícitamente.
     * </p>
     *
     * @param vuelos arreglo de {@link BodegaAvion} de los que se mostrará la lista de pasajeros
     */
    public static void mostrarListaDePasajeros(BodegaAvion[] vuelos) {
        for (BodegaAvion vuelo : vuelos) {
            System.out.println("✈️ Vuelo destino: " + vuelo.getDestino());

            if (vuelo.estaVacia()) {
                System.out.println("   (Sin pasajeros ni equipajes)");
            } else {
                int numeroDeMaleta = 1;
                for (Equipaje maleta : vuelo.getPasajeros()) {
                    System.out.printf("   %d. Pasajero: %s | Tiquete: %s | Peso: %d kg%n",
                            numeroDeMaleta++,
                            maleta.pasajero(),
                            maleta.categoriaTiquete(),
                            maleta.peso());
                }
                System.out.println("   Total equipajes: " + vuelo.size());
            }

            System.out.println("=================================\n");
        }
    }
}
