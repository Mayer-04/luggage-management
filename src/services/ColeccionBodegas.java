package services;

import domain.Bodega;
import domain.BodegaAvion;

public final class ColeccionBodegas {

    private static final String[] CIUDADES = {
            "Bogotá", "Medellín", "Cali", "Bucaramanga", "Barranquilla"
    };

    private ColeccionBodegas() {}

    public static Bodega[] obtenerBodegasDeEntrada() {
        var bodegas = new Bodega[CIUDADES.length];
        for (int i = 0; i < CIUDADES.length; i++) {
            bodegas[i] = new Bodega(CIUDADES[i]);
        }
        return bodegas;
    }

    public static BodegaAvion[] obtenerBodegasDeAviones() {
        var bodegasAvion = new BodegaAvion[CIUDADES.length];
        for (int i = 0; i < CIUDADES.length; i++) {
            bodegasAvion[i] = new BodegaAvion(CIUDADES[i]);
        }
        return bodegasAvion;
    }
}
