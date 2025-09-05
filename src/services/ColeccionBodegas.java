package services;

import domain.Bodega;
import domain.BodegaAvion;

import java.util.stream.Stream;

public final class ColeccionBodegas {

    private static final String[] CIUDADES = {
            "Bogotá", "Medellín", "Cali", "Bucaramanga", "Barranquilla"
    };

    private ColeccionBodegas() {}

    /**
     * Obtiene una lista de bodegas de entrada, una para cada ciudad en CIUDADES.
     *
     * @return lista de bodegas de entrada
     */
    public static Bodega[] obtenerBodegasDeEntrada() {
        return Stream.of(CIUDADES)
                .map(Bodega::new)
                .toArray(Bodega[]::new);
    }

    /**
     * Obtiene una lista de bodegas de aviones, una para cada ciudad en CIUDADES.
     * @return lista de bodegas de aviones
     */
    public static BodegaAvion[] obtenerBodegasDeAviones() {
        return Stream.of(CIUDADES)
                .map(BodegaAvion::new)
                .toArray(BodegaAvion[]::new);
    }
}
