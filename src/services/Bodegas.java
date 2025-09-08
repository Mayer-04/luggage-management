package services;

import domain.Bodega;
import domain.ColaGeneral;
import domain.Equipaje;

/**
 * La clase {@code Bodegas} proporciona métodos utilitarios para gestionar
 * el almacenamiento de equipajes en distintas bodegas, de acuerdo con su destino.
 * <p>
 * Su propósito principal es procesar las maletas de una cola general y guardarlas en la bodega
 * que corresponda según el destino de cada una.
 * </p>
 *
 * <p><strong>Ejemplo de uso:</strong></p>
 * <pre>{@code
 * ColaGeneral cola = new ColaGeneral();
 * // se agregan maletas a la cola
 *
 * Bodega[] bodegas = { new Bodega("Bogotá"), new Bodega("Cali") };
 *
 * Bodegas.procesarEquipaje(cola, bodegas);
 * }</pre>
 *
 * @see domain.Bodega
 * @see domain.ColaGeneral
 * @see domain.Equipaje
 */
public class Bodegas {

    /**
     * Procesa todos los equipajes contenidos en una {@link ColaGeneral} y los distribuye
     * en el arreglo de {@link Bodega} recibido como parámetro.
     * <p>
     * El método se encarga de:
     * <ul>
     *   <li>Extraer cada equipaje de la cola.</li>
     *   <li>Obtener el destino del equipaje.</li>
     *   <li>Comparar el destino del equipaje con el destino de cada bodega.</li>
     *   <li>Colocar la maleta en la bodega correspondiente cuando hay coincidencia.</li>
     * </ul>
     * Una vez insertado el equipaje en la bodega adecuada, se detiene la búsqueda
     * en el resto de bodegas para optimizar el proceso.
     * </p>
     *
     * @param cola la cola general que contiene los equipajes pendientes de procesar
     * @param bodegas el arreglo de bodegas disponibles donde se almacenarán los equipajes
     */
    public static void procesarEquipaje(ColaGeneral cola, Bodega[] bodegas) {
        while (!cola.estaVacia()) {
            Equipaje equipaje = cola.sacarEquipaje();
            String destinoEquipaje = equipaje.destino();

            for (Bodega bodega : bodegas) {
                String bodegaDestino = bodega.getDestino();

                if (destinoEquipaje.equals(bodegaDestino)) {
                    bodega.agregarEquipaje(equipaje);
                    break;
                }
            }
        }
    }
}
