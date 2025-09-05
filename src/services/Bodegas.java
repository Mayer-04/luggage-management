package services;

import datastructures.list.List;
import domain.Bodega;
import domain.ColaGeneral;
import domain.Equipaje;

public class Bodegas {

    private static List<Equipaje> ordenarEquipajesPorPrioridad(ColaGeneral cola) {

        // Creamos una "lista de equipajes" vacía para almacenar las maletas que tenemos en la "cola".
        List<Equipaje> equipajes = new List<>();

        // Sacamos todos los "equipajes" de la cola y la agregamos a la nueva lista de equipajes
        while (!cola.estaVacia()) {
            equipajes.addLast(cola.sacarEquipaje());
        }

        // Aplicamos el algoritmo de ordenamiento a nuestra "lista de equipajes".
        QuickLuggageSorting.quickSort(equipajes);

        // Devolvemos esa "lista de equipajes" ya ordenada por categoria de tiquete.
        return equipajes;
    }

    public static void procesarEquipaje(ColaGeneral cola, Bodega[] bodegas) {

        // Lista de "equipajes" ordenada por categoria de Tiquete (L, M, S).
        List<Equipaje> equipajesOrdenados = ordenarEquipajesPorPrioridad(cola);

        // Recorremos la "lista de equipajes" para posteriormente agregarla a su respectivo vuelo (pila).
        for (Equipaje maleta : equipajesOrdenados) {
            // Recorremos cada bodega que tenemos
            for (Bodega bodega : bodegas) {

                // Obtenemos el "destino" de cada maleta, por ejemplo (Bogotá, Cali, etc.).
                String destinoMaleta = maleta.destino();
                // Obtenemos el "destino" de la bodega que estamos recorriendo en este momento.
                String destinoBodega = bodega.getDestino();

                // Si el "destino de la maleta" es igual al "destino de la bodega"
                if (destinoMaleta.equals(destinoBodega)) {
                    // Agregamos la maleta al final de la bodega
                    bodega.agregarEquipaje(maleta);
                    // Salimos del bucle que recorre las bodegas.
                    break;
                }
            }
        }
    }
}
