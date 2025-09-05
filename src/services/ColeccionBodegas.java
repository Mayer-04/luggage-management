package services;

import domain.Bodega;
import domain.BodegaAvion;
import util.Constantes;

import java.util.stream.Stream;

public final class ColeccionBodegas {

    private ColeccionBodegas() {
    }

    /**
     * Construye un arreglo de objetos {@link Bodega}, uno por cada ciudad listada en {@code CIUDADES}.
     * <p>
     * Internamente este método:
     * <ol>
     *   <li>Toma los nombres de las ciudades definidos en {@code CIUDADES}.</li>
     *   <li>Por cada ciudad crea una nueva instancia de {@link Bodega} usando el nombre como parámetro de su constructor.</li>
     *   <li>Agrupa todas las instancias resultantes en un arreglo de {@code Bodega}.</li>
     * </ol>
     *
     * El resultado final es un arreglo con la misma cantidad de elementos que el arreglo de ciudades,
     * en el que cada posición corresponde a una bodega asociada a una ciudad.
     *
     * @return un arreglo de {@link Bodega}, donde cada elemento representa una ciudad de {@code CIUDADES}
     */
    public static Bodega[] obtenerBodegasDeEntrada() {
        return Stream.of(Constantes.DESTINOS)
                .map(Bodega::new)
                .toArray(Bodega[]::new);
    }


    /**
     * Obtiene una lista de bodegas de aviones, una para cada ciudad en CIUDADES.
     *
     * @return lista de bodegas de aviones
     */
    public static BodegaAvion[] obtenerBodegasDeAviones() {
        return Stream.of(Constantes.DESTINOS)
                .map(BodegaAvion::new)
                .toArray(BodegaAvion[]::new);
    }
}
