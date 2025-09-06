package services;

import datastructures.list.List;
import domain.Bodega;
import domain.BodegaAvion;
import domain.Equipaje;

public class Avion {

    /**
     * Verifica si hay equipajes en alguna bodega lista para abordar.
     */
    public static boolean hayEquipajesParaAbordar(Bodega[] bodegas) {
        if (bodegas == null) return false;
        for (Bodega bodega : bodegas) {
            if (!bodega.estaVacia()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Coloca las maletas de la bodega en la pila del avión ordenadas por categoría de tiquete.
     * Si alguna categoría está llena, la maleta se omite.
     *
     * @param bodega La bodega origen de las maletas.
     * @param vuelo  La bodega del avión destino.
     * @return Lista de equipajes que no pudieron abordarse.
     */
    private static List<Equipaje> colocarMaletasEnVueloConOrden(Bodega bodega, BodegaAvion vuelo) {
        List<Equipaje> lista = new List<>();
        List<Equipaje> noAbordadas = new List<>();

        // Sacamos todas las maletas de la bodega
        while (!bodega.estaVacia()) {
            lista.addLast(bodega.sacarUltimoEquipaje());
        }

        // Ordenamos según categoría de tiquete (L > M > S)
        QuickLuggageSorting.quickSort(lista);

        // Push en la pila del avión respetando los límites por categoría
        for (Equipaje maleta : lista) {
            boolean agregado = vuelo.agregarEquipaje(maleta);
            if (!agregado) {
                noAbordadas.addLast(maleta);
            }
        }

        return noAbordadas;
    }

    /**
     * Distribuye el equipaje de una bodega hacia la bodega correspondiente del avión.
     *
     * @param bodega Bodega de origen
     * @param vuelos Arreglo de bodega de aviones
     * @return Lista de equipajes que no pudieron abordarse
     */
    private static List<Equipaje> distribuirEquipaje(Bodega bodega, BodegaAvion[] vuelos) {
        List<Equipaje> noAbordadasTotales = new List<>();

        for (BodegaAvion vuelo : vuelos) {
            if (bodega.getDestino().equals(vuelo.getDestino())) {
                List<Equipaje> noAbordadas = colocarMaletasEnVueloConOrden(bodega, vuelo);
                // Guardamos las maletas que no pudieron subir
                for (Equipaje eq : noAbordadas) {
                    noAbordadasTotales.addLast(eq);
                }
                break;
            }
        }

        return noAbordadasTotales;
    }

    /**
     * Función principal para cargar los vuelos.
     * Toma todas las bodegas generales y distribuye su contenido en los vuelos.
     *
     * @param bodegas Bodegas de origen
     * @param vuelos  Bodegas de avión destino
     * @return Lista de equipajes que no pudieron abordarse
     */
    public static List<Equipaje> abordarVuelo(Bodega[] bodegas, BodegaAvion[] vuelos) {
        List<Equipaje> noAbordadasTotales = new List<>();

        for (Bodega bodega : bodegas) {
            List<Equipaje> noAbordadas = distribuirEquipaje(bodega, vuelos);
            for (Equipaje eq : noAbordadas) {
                noAbordadasTotales.addLast(eq);
            }
        }

        return noAbordadasTotales;
    }

    /**
     * Despacha todos los vuelos y genera estadísticas.
     * Por cada vuelo obtiene:
     * - la cantidad total de maletas
     * - el peso total de todas las maletas
     *
     * Retorna una lista donde cada elemento es un arreglo de enteros [cantidad, peso].
     */
    public static List<int[]> desembarcarVuelo(BodegaAvion[] vuelos) {
        var estadisticasVuelos = new List<int[]>();

        for (BodegaAvion vuelo : vuelos) {
            int cantidadTotal = 0;
            int pesoTotal = 0;

            while (!vuelo.estaVacia()) {
                Equipaje maleta = vuelo.extraerTope();
                cantidadTotal++;
                pesoTotal += maleta.peso();
            }

            estadisticasVuelos.addLast(new int[]{cantidadTotal, pesoTotal});
        }

        return estadisticasVuelos;
    }
}
