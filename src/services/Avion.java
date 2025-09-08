package services;

import datastructures.list.List;
import domain.Bodega;
import domain.BodegaAvion;
import domain.Equipaje;

/**
 * La clase {@code Avion} gestiona el proceso de carga y descarga de equipajes en los vuelos.
 * <p>
 * Proporciona métodos para:
 * <ul>
 *   <li>Verificar si existen equipajes listos para ser abordados.</li>
 *   <li>Distribuir y ordenar equipajes en las bodegas de los aviones.</li>
 *   <li>Obtener estadísticas de equipajes transportados al finalizar un vuelo.</li>
 * </ul>
 *
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>{@code
 * Bodega[] bodegas = {...};
 * BodegaAvion[] vuelos = {...};
 *
 * // Abordar todos los vuelos con las bodegas disponibles
 * List<Equipaje> noAbordadas = Avion.abordarVuelo(bodegas, vuelos);
 *
 * // Desembarcar los vuelos y obtener estadísticas
 * List<int[]> estadisticas = Avion.desembarcarVuelo(vuelos);
 * }</pre>
 */
public class Avion {

    /**
     * Verifica si alguna de las bodegas contiene equipajes listos para abordar.
     *
     * @param bodegas arreglo de bodegas a inspeccionar
     * @return {@code true} si existe al menos una bodega con equipajes, {@code false} en caso contrario
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
     * Extrae los equipajes de una bodega, los ordena por categoría de tiquete (L > M > S)
     * utilizando un algoritmo de ordenamiento <strong>Quicksort</strong>
     * y los intenta colocar en el avión.
     * Si una categoría ya alcanzó su límite, los equipajes correspondientes no son abordados.
     *
     * @param bodega bodega origen de los equipajes
     * @param vuelo  bodega del avión destino
     * @return lista de equipajes que no pudieron ser abordados
     */
    private static List<Equipaje> colocarMaletasEnVueloConOrden(Bodega bodega, BodegaAvion vuelo) {
        List<Equipaje> lista = new List<>();
        List<Equipaje> noAbordadas = new List<>();

        while (!bodega.estaVacia()) {
            lista.addLast(bodega.sacarUltimoEquipaje());
        }

        QuickLuggageSorting.quickSort(lista);

        for (Equipaje maleta : lista) {
            boolean agregado = vuelo.agregarEquipaje(maleta);
            if (!agregado) {
                noAbordadas.addLast(maleta);
            }
        }

        return noAbordadas;
    }

    /**
     * Distribuye los equipajes de una bodega hacia la bodega correspondiente en los aviones,
     * determinada por el destino de la bodega.
     *
     * @param bodega bodega de origen
     * @param vuelos arreglo de bodegas de avión disponibles
     * @return lista de equipajes que no pudieron ser abordados en el avión correspondiente
     */
    private static List<Equipaje> distribuirEquipaje(Bodega bodega, BodegaAvion[] vuelos) {
        List<Equipaje> noAbordadasTotales = new List<>();
        String destinoBodega = bodega.getDestino();

        for (BodegaAvion vuelo : vuelos) {
            String destinoVuelo = vuelo.getDestino();

            if (destinoBodega.equals(destinoVuelo)) {
                List<Equipaje> noAbordadas = colocarMaletasEnVueloConOrden(bodega, vuelo);
                for (Equipaje eq : noAbordadas) {
                    noAbordadasTotales.addLast(eq);
                }
                break;
            }
        }

        return noAbordadasTotales;
    }

    /**
     * Carga los vuelos distribuyendo los equipajes de todas las bodegas
     * hacia las bodegas correspondientes de los aviones.
     *
     * @param bodegas bodegas de origen
     * @param vuelos  bodegas de avión destino
     * @return lista de equipajes que no pudieron ser abordados
     *
     * <p><strong>Ejemplo:</strong></p>
     * <pre>{@code
     * List<Equipaje> noCargados = Avion.abordarVuelo(bodegas, vuelos);
     * }</pre>
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
     * Despacha los vuelos y genera estadísticas de cada uno.
     * <p>
     * Para cada vuelo, devuelve un arreglo con:
     * <ul>
     *   <li>[0] cantidad total de maletas transportadas</li>
     *   <li>[1] peso total de todas las maletas</li>
     * </ul>
     *
     * @param vuelos arreglo de bodegas de avión a despachar
     * @return lista donde cada elemento es un arreglo {@code [cantidad, peso]}
     *
     * <p><b>Ejemplo:</b></p>
     * <pre>{@code
     * List<int[]> estadisticas = Avion.desembarcarVuelo(vuelos);
     * for (int[] vueloStats : estadisticas) {
     *     System.out.println("Cantidad: " + vueloStats[0]);
     *     System.out.println("Peso total: " + vueloStats[1]);
     * }
     * }</pre>
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
