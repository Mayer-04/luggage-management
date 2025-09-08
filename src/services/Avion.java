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
     * Verifica si existen equipajes en alguna de las bodegas para abordar un vuelo.
     * <p>
     * Si el arreglo de bodegas es {@code null}, se devuelve {@code false}.
     * </p>
     * <p>
     * De lo contrario, se itera en la lista de bodegas y se verifica si alguna de ellas
     * contiene al menos un equipaje. Si se encuentra al menos una bodega con contenido,
     * se devuelve {@code true}. De lo contrario, se devuelve {@code false}.
     * </p>
     *
     * @param bodegas arreglo de bodegas a verificar
     * @return {@code true} si hay equipajes listos para abordar, {@code false} en caso contrario
     */
    public static boolean verificarEquipajesParaAbordar(Bodega[] bodegas) {
        if (bodegas == null) return false;
        for (Bodega bodega : bodegas) {
            if (!bodega.estaVacia()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Coloca las maletas de una bodega en un vuelo en orden por categoría de tiquete (L > M > S).
     * <p>
     * Primero, se extraen todas las maletas de la bodega y se ordenan ascendentemente
     * por categoría utilizando el algoritmo <strong>QuickSort</strong>. 
     * Luego, se intenta agregar cada maleta en el vuelo en orden. 
     * Si alguna maleta no puede ser agregada (debido a que se ha alcanzado el límite de una categoría), 
     * se agrega a la lista de maletas no abordadas.
     * </p>
     *
     * @param bodega bodega de la que se extraerán las maletas
     * @param vueloDestino vuelo donde se intentarán agregar las maletas
     * @return lista de maletas que no pudieron ser abordadas
     */
    private static List<Equipaje> colocarMaletasEnVueloConOrden(Bodega bodega, BodegaAvion vueloDestino) {
        List<Equipaje> maletasOrdenadas = new List<>();
        List<Equipaje> maletasNoAbordadas = new List<>();

        while (!bodega.estaVacia()) {
            maletasOrdenadas.addLast(bodega.sacarUltimoEquipaje());
        }

        QuickLuggageSorting.quickSort(maletasOrdenadas);

        for (Equipaje maleta : maletasOrdenadas) {
            boolean agregado = vueloDestino.agregarEquipaje(maleta);
            if (!agregado) {
                maletasNoAbordadas.addLast(maleta);
            }
        }

        return maletasNoAbordadas;
    }

    /**
     * Distribuye los equipajes de una bodega en el vuelo que se destine al mismo lugar.
     * <p>
     * Primero, se busca el vuelo correspondiente al destino de la bodega.
     * Luego, se intenta agregar cada maleta en el vuelo en orden por categoría de tiquete.
     * Si alguna maleta no puede ser agregada (debido a que se ha alcanzado el límite de una categoría),
     * se agrega a la lista de maletas no abordadas.
     * </p>
     *
     * @param bodega bodega de la que se extraerán las maletas
     * @param vuelos arreglo de vuelos disponibles
     * @return lista de maletas que no pudieron ser abordadas
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
                Equipaje maleta = vuelo.sacarTope();
                cantidadTotal++;
                pesoTotal += maleta.peso();
            }

            estadisticasVuelos.addLast(new int[]{cantidadTotal, pesoTotal});
        }

        return estadisticasVuelos;
    }
}
