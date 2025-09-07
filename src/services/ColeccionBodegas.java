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
     * Crea un arreglo de {@link Bodega}, una por cada ciudad guardada en
     * {@code Constantes.DESTINOS}.
     * <p>
     * Básicamente: recorre la lista de destinos y arma una bodega para cada uno.
     * </p>
     *
     * @return un arreglo de bodegas, cada una con el nombre de una ciudad destino.
     */
    public static Bodega[] obtenerBodegasDeEntrada() {
        String[] destinos = Constantes.DESTINOS;
        Bodega[] bodegas = new Bodega[destinos.length];

        for (int i = 0; i < destinos.length; i++) {
            bodegas[i] = new Bodega(destinos[i]);
        }

        return bodegas;
    }

    /**
     * Crea un arreglo de {@link BodegaAvion}, una por cada ciudad guardada en
     * {@code Constantes.DESTINOS}.
     * <p>
     * Igual que el método anterior, pero en lugar de bodegas normales,
     * se generan bodegas de avión.
     * </p>
     *
     * @return un arreglo de bodegas de avión, cada una con el nombre de una ciudad destino.
     */
    public static BodegaAvion[] obtenerBodegasDeAviones() {
        String[] destinos = Constantes.DESTINOS;
        BodegaAvion[] bodegasAvion = new BodegaAvion[destinos.length];

        for (int i = 0; i < destinos.length; i++) {
            bodegasAvion[i] = new BodegaAvion(destinos[i]);
        }

        return bodegasAvion;
    }
}
