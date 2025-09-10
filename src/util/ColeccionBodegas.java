package util;

import domain.Bodega;
import domain.BodegaAvion;

/**
 * La clase {@code ColeccionBodegas} es una utilidad para generar
 * colecciones de {@link Bodega} y {@link BodegaAvion} a partir de
 * los destinos definidos en {@link Constantes#DESTINOS}.
 *
 * <p>
 * No se puede instanciar, ya que todos sus métodos son estáticos.
 * </p>
 *
 * @see Bodega
 * @see BodegaAvion
 * @see Constantes
 */
public class ColeccionBodegas {

    /**
     * Constructor privado para evitar instanciación.
     */
    private ColeccionBodegas() {
    }

    /**
     * Crea un arreglo de {@link Bodega}, una por cada ciudad destino
     * definida en {@link Constantes#DESTINOS}.
     *
     * <p>
     * Recorre la lista de destinos y genera una {@code Bodega} asociada
     * a cada uno de ellos.
     * </p>
     *
     * @return arreglo de bodegas de entrada, cada una vinculada a una ciudad destino
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
     * Crea un arreglo de {@link BodegaAvion}, una por cada ciudad destino
     * definida en {@link Constantes#DESTINOS}.
     *
     * <p>
     * Similar a {@link #obtenerBodegasDeEntrada()}, pero en lugar de bodegas
     * normales se generan bodegas específicas de avión.
     * </p>
     *
     * @return arreglo de bodegas de avión, cada una vinculada a una ciudad destino
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
