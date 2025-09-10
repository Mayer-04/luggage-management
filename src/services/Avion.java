package services;

import datastructures.list.List;
import domain.Bodega;
import domain.BodegaAvion;
import domain.Equipaje;

/**
 * La clase {@code Avion} centraliza la lógica de carga y descarga de equipajes en los vuelos.
 *
 * <p>Sus responsabilidades principales son:</p>
 * <ul>
 *   <li>Verificar si existen equipajes listos para ser abordados en las bodegas.</li>
 *   <li>Distribuir los equipajes hacia las bodegas de los aviones de acuerdo con el destino.</li>
 *   <li>Ordenar los equipajes por categoría de tiquete ({@code L > M > S}) antes de cargarlos.</li>
 *   <li>Obtener estadísticas de cantidad y peso de equipajes transportados al finalizar un vuelo.</li>
 * </ul>
 *
 * @see Bodega
 * @see BodegaAvion
 * @see Equipaje
 */
public class Avion {

    /**
     * Verifica si existe al menos un equipaje en las bodegas para ser abordado.
     *
     * @param bodegas arreglo de bodegas a revisar
     * @return {@code true} si alguna bodega contiene equipajes, {@code false} en caso contrario
     */
    public static boolean verificarEquipajesParaAbordar(Bodega[] bodegas) {
        if (bodegas == null) return false;
        for (Bodega bodega : bodegas) {
            if (!bodega.estaVacia()) return true;
        }
        return false;
    }

    /**
     * Verifica si cada vuelo cumple con un mínimo de equipajes cargados.
     * <p>
     * Si algún vuelo no cumple con el mínimo requerido, se muestra un mensaje de advertencia
     * indicando el destino y la cantidad actual.
     * </p>
     *
     * @param vuelos arreglo de vuelos a verificar
     * @param minimo número mínimo de equipajes requeridos por vuelo
     * @return {@code true} si todos cumplen el mínimo, {@code false} si alguno no lo cumple
     */
    public static boolean verificarMinimoPorVuelo(BodegaAvion[] vuelos, int minimo) {
        if (vuelos == null) return false;
        for (BodegaAvion vuelo : vuelos) {
            if (vuelo.size() < minimo) {
                System.out.printf(
                        "⚠️ El vuelo con destino %s no cumple el mínimo de %d equipajes (actual: %d)%n",
                        vuelo.getDestino(), minimo, vuelo.size()
                );
                return false;
            }
        }
        return true;
    }

    /**
     * Extrae todas las maletas de una {@link Bodega}, las ordena por categoría de tiquete
     * y las intenta cargar en un {@link BodegaAvion}.
     *
     * <p>Si alguna maleta no puede ser cargada por falta de cupo en su categoría,
     * se agrega a la lista de maletas no abordadas.</p>
     *
     * @param bodega bodega de origen
     * @param vueloDestino vuelo de destino
     * @return lista de maletas que no pudieron ser cargadas
     */
    private static List<Equipaje> colocarMaletasEnVueloConOrden(Bodega bodega, BodegaAvion vueloDestino) {
        List<Equipaje> maletasOrdenadas = new List<>();
        List<Equipaje> maletasNoAbordadas = new List<>();

        while (!bodega.estaVacia()) {
            maletasOrdenadas.addLast(bodega.sacarUltimoEquipaje());
        }

        QuickLuggageSorting.quickSort(maletasOrdenadas);

        for (Equipaje maleta : maletasOrdenadas) {
            if (!vueloDestino.agregarEquipaje(maleta)) {
                maletasNoAbordadas.addLast(maleta);
            }
        }
        return maletasNoAbordadas;
    }

    /**
     * Distribuye los equipajes de una {@link Bodega} en el vuelo con el mismo destino.
     *
     * @param bodega bodega de origen
     * @param vuelos arreglo de vuelos disponibles
     * @return lista de maletas que no pudieron ser cargadas
     */
    private static List<Equipaje> distribuirEquipaje(Bodega bodega, BodegaAvion[] vuelos) {
        List<Equipaje> noAbordadasTotales = new List<>();
        String destinoBodega = bodega.getDestino();

        for (BodegaAvion vuelo : vuelos) {
            if (destinoBodega.equals(vuelo.getDestino())) {
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
     * Carga los vuelos distribuyendo los equipajes de todas las bodegas hacia
     * las bodegas de los aviones correspondientes.
     *
     * @param bodegas bodegas de origen
     * @param vuelos  bodegas de avión de destino
     * @return lista de equipajes que no pudieron ser abordados
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
     * Descarga los vuelos y genera estadísticas de cada uno.
     *
     * <p>Cada vuelo genera un arreglo {@code [cantidad, peso]}, donde:</p>
     * <ul>
     *   <li>[0] cantidad de maletas transportadas</li>
     *   <li>[1] peso total en kilogramos</li>
     * </ul>
     *
     * @param vuelos arreglo de vuelos a descargar
     * @return lista de arreglos {@code [cantidad, peso]} por cada vuelo
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
