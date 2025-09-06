package services;

import domain.Bodega;
import domain.BodegaAvion;
import util.Constantes;

/**
 * Clase utilitaria para crear colecciones de bodegas y bodegas de aviones.
 */
public final class ColeccionBodegas {

    // Constructor privado para evitar instanciación
    private ColeccionBodegas() {
    }

    /**
     * Construye un arreglo de objetos {@link Bodega}, uno por cada ciudad en {@code Constantes.DESTINOS}.
     *
     * @return un arreglo de {@link Bodega}, donde cada elemento representa una ciudad.
     */
    public static Bodega[] obtenerBodegasDeEntrada() {
        String[] destinos = Constantes.DESTINOS;
        Bodega[] bodegas = new Bodega[destinos.length];

        // Crear una bodega por cada ciudad
        for (int i = 0; i < destinos.length; i++) {
            bodegas[i] = new Bodega(destinos[i]);
        }

        return bodegas;
    }

    /**
     * Construye un arreglo de objetos {@link BodegaAvion}, uno por cada ciudad en {@code Constantes.DESTINOS}.
     *
     * @return un arreglo de {@link BodegaAvion}, donde cada elemento representa una ciudad.
     */
    public static BodegaAvion[] obtenerBodegasDeAviones() {
        String[] destinos = Constantes.DESTINOS;
        BodegaAvion[] bodegasAvion = new BodegaAvion[destinos.length];

        // Crear una bodega de avión por cada ciudad
        for (int i = 0; i < destinos.length; i++) {
            bodegasAvion[i] = new BodegaAvion(destinos[i]);
        }

        return bodegasAvion;
    }
}
