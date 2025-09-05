package services;

import datastructures.list.List;
import domain.Bodega;
import domain.BodegaAvion;
import domain.Equipaje;

public class Avion {

    public static boolean hayEquipajesParaAbordar(Bodega[] bodegas) {
        if (bodegas == null) return false;
        for (Bodega bodega : bodegas) {
            if (!bodega.estaVacia()) {
                return true;
            }
        }
        return false;
    }

//    private static void colocarMaletaEnVuelo(Equipaje maleta, BodegaAvion[] vuelos) {
//        for (BodegaAvion vuelo : vuelos) {
//            String destinoMaleta = maleta.destino();
//            String destinoVuelo = vuelo.getDestino();
//
//            if (!destinoMaleta.equals(destinoVuelo)) {
//                continue;
//            }
//
//            boolean agregado = vuelo.agregarEquipaje(maleta);
//            if (agregado) {
//                break;
//            }
//        }
//    }

    private static void colocarMaletaEnVuelo(Equipaje maleta, BodegaAvion[] vuelos) {
        for (BodegaAvion vuelo : vuelos) {

            String destinoMaleta = maleta.destino();
            String destinoVuelo = vuelo.getDestino();

            if (destinoMaleta.equals(destinoVuelo)) {
                boolean agregado = vuelo.agregarEquipaje(maleta);

                if (!agregado) {
                    throw new IllegalStateException("No se pudo agregar la maleta al vuelo " + vuelo.getDestino() +
                            ": se alcanzó el límite de 100 equipajes por bodega.");
                }

                break;
            }
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
    public static List<int[]> desembarcarVuelo(BodegaAvion[] vuelos) {
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