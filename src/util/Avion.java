package util;

import datastructures.list.List;
import luggage.Bodega;
import luggage.BodegaAvion;
import luggage.Equipaje;

public class Avion {

    private static void colocarMaletaEnVuelo(Equipaje maleta, BodegaAvion[] vuelos) {
        for (BodegaAvion vuelo : vuelos) {
            String destinoMaleta = maleta.destino();
            String destinoVuelo = vuelo.getDestino();

            if (!destinoMaleta.equals(destinoVuelo)) {
                continue;
            }

            boolean agregado = vuelo.agregarEquipaje(maleta);
            if (agregado) {
                break; // se logró asignar
            }

            // Si no se pudo agregar (por límites)
            System.out.printf(
                    "No se pudo asignar maleta de %s al vuelo con destino %s (límite alcanzado).%n",
                    maleta.pasajero(), vuelo.getDestino()
            );
        }
    }


    private static void distribuirEquipaje(Bodega bodega, BodegaAvion[] vuelos) {
        while (!bodega.estaVacia()) {
            Equipaje maleta = bodega.sacarUltimoEquipaje();
            colocarMaletaEnVuelo(maleta, vuelos);
        }
    }

    /**
     * Función principal para cargar los vuelos.
     * Toma todas las bodegas generales y distribuye su contenido en los vuelos.
     */
    public static void abordarVuelo(Bodega[] bodegas, BodegaAvion[] vuelos) {
        for (Bodega bodega : bodegas) {
            distribuirEquipaje(bodega, vuelos);
        }
    }

    /**
     * Despacha todos los vuelos y genera estadísticas.
     * Por cada vuelo obtiene:
     * - la cantidad total de maletas
     * - el peso total de todas las maletas
     * <p>
     * Retorna una lista donde cada elemento es un arreglo de enteros [cantidad, peso].
     */
    public static List<int[]> despacharVuelo(BodegaAvion[] vuelos) {
        // Lista de estadísticas de cada vuelo (cada estadística es un arreglo de 2 enteros).
        var estadisticasVuelos = new List<int[]>();

        // Recorremos cada vuelo (bodega de avión).
        for (BodegaAvion vuelo : vuelos) {
            int cantidadTotal = 0;
            int pesoTotal = 0;

            // Vacía la bodega del avión contando maletas y sumando su peso.
            while (!vuelo.estaVacia()) {
                // Eliminamos la "última" maleta que entro a la bodega del avión (pila).
                Equipaje maleta = vuelo.extraerTope();
                cantidadTotal++;
                pesoTotal += maleta.peso();
            }

            // Guardamos los resultados en un arreglo: [cantidad de maletas, peso total].
            var estadisticas = new int[]{cantidadTotal, pesoTotal};
            estadisticasVuelos.addLast(estadisticas);
        }

        return estadisticasVuelos;
    }
}

    /*
    public static void abordarVuelo(Bodega[] bodegas, BodegaAvion[] vuelos) {
        for (Bodega bodega : bodegas) {
            while (!bodega.estaVacia()) {
                // Sacamos el último elemento de la lista
                Equipaje maleta = bodega.sacarUltimoEquipaje();
                // Recorremos cada bodega
                for (BodegaAvion vuelo : vuelos) {
                    String destinoMaleta = maleta.destino();
                    String destinoVuelo = vuelo.getDestino();
                    if (destinoMaleta.equals(destinoVuelo)) {
                        // Agregamos la maleta al final de la bodega
                        vuelo.agregarEquipaje(maleta);
                        break;
                    }
                }
            }
        }
    }
    */
