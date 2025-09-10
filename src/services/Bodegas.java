package services;

import domain.Bodega;
import domain.ColaGeneral;
import domain.Equipaje;

/**
 * La clase {@code Bodegas} ofrece métodos para gestionar
 * la distribución de equipajes en distintas {@link Bodega}s, de acuerdo
 * con el destino registrado en cada {@link Equipaje}.
 *
 * <p>
 * Su función principal es procesar los equipajes de una {@link ColaGeneral}
 * e insertarlos en la bodega correspondiente. Esto permite centralizar
 * la lógica de asignación de maletas según el destino.
 * </p>
 *
 * @see Bodega
 * @see ColaGeneral
 * @see Equipaje
 */
public class Bodegas {

    /**
     * Procesa todos los equipajes contenidos en una {@link ColaGeneral}
     * y los distribuye en el arreglo de {@link Bodega} recibido como parámetro.
     *
     * <p>El método realiza los siguientes pasos:</p>
     * <ul>
     *   <li>Extrae cada equipaje de la cola.</li>
     *   <li>Obtiene el destino del equipaje.</li>
     *   <li>Busca en el arreglo de bodegas aquella cuyo destino coincide.</li>
     *   <li>Inserta el equipaje en la bodega correspondiente.</li>
     * </ul>
     *
     * <p>
     * Una vez encontrado el destino correcto e insertado el equipaje,
     * se detiene la búsqueda en el resto de bodegas para optimizar el proceso.
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
                if (destinoEquipaje.equals(bodega.getDestino())) {
                    bodega.agregarEquipaje(equipaje);
                    break;
                }
            }
        }
    }
}
