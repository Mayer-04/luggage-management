package services;

import domain.Bodega;
import domain.ColaGeneral;
import domain.Equipaje;

public class Bodegas {

    /**
     * Procesa el equipaje de la cola general y lo coloca en la bodega correspondiente
     * según su destino.
     */
    public static void procesarEquipaje(ColaGeneral cola, Bodega[] bodegas) {
        while (!cola.estaVacia()) {
            Equipaje maleta = cola.sacarEquipaje();
            String destinoMaleta = maleta.destino();

            for (Bodega bodega : bodegas) {

                String bodegaDestino = bodega.getDestino();

                if (destinoMaleta.equals(bodegaDestino)) {
                    bodega.agregarEquipaje(maleta);
                    break;
                }
            }
        }
    }
}
