package services;

import datastructures.list.List;
import domain.Bodega;
import domain.BodegaAvion;
import domain.ColaGeneral;
import domain.Equipaje;
import util.ColeccionBodegas;

/**
 * La clase {@code GestorEquipaje} es el servicio central que administra el
 * ciclo de vida del equipaje dentro del sistema.
 *
 * <p>Se encarga de:</p>
 * <ul>
 *   <li>Guardar los equipajes registrados en una {@link domain.ColaGeneral} de espera.</li>
 *   <li>Procesar los equipajes y clasificarlos en las {@link domain.Bodega} según destino.</li>
 *   <li>Transferir equipajes a las {@link domain.BodegaAvion} asociadas a vuelos.</li>
 *   <li>Facilitar tanto el abordaje como el desembarque de los vuelos.</li>
 * </ul>
 *
 * <p>
 * Esta clase no interactúa con el usuario directamente, sino que ofrece
 * métodos para que otras capas (como {@link services.InterfazGestorEquipaje})
 * gestionen el proceso completo de forma ordenada.
 * </p>
 *
 * @see domain.Equipaje
 * @see domain.ColaGeneral
 * @see domain.Bodega
 * @see domain.BodegaAvion
 * @see util.ColeccionBodegas
 * @see services.InterfazGestorEquipaje
 */
public class GestorEquipaje {

    /**
     * Cola general donde se almacenan los equipajes antes de ser procesados.
     */
    private final ColaGeneral colaGeneral;

    /**
     * Bodegas de entrada, una por cada destino soportado.
     */
    private final Bodega[] bodegas;

    /**
     * Bodegas asociadas a vuelos, listas para abordar equipajes.
     */
    private final BodegaAvion[] bodegasAvion;

    /**
     * Construye un nuevo gestor inicializando la cola general y
     * cargando las bodegas de entrada y de avión disponibles.
     */
    public GestorEquipaje() {
        this.colaGeneral = new ColaGeneral();
        this.bodegas = ColeccionBodegas.obtenerBodegasDeEntrada();
        this.bodegasAvion = ColeccionBodegas.obtenerBodegasDeAviones();
    }

    /**
     * Inserta un equipaje en la cola general de espera.
     *
     * @param equipaje el equipaje a registrar
     */
    public void registrarEquipaje(Equipaje equipaje) {
        colaGeneral.registrarEquipaje(equipaje);
    }

    /**
     * Procesa todos los equipajes de la cola general, enviándolos
     * a las bodegas de entrada según su destino.
     */
    public void procesarEquipajes() {
        Bodegas.procesarEquipaje(colaGeneral, bodegas);
    }

    /**
     * Aborda los equipajes desde las bodegas de entrada hacia
     * las bodegas de avión asociadas a vuelos.
     *
     * @return lista con los equipajes que no pudieron abordar
     */
    public List<Equipaje> abordarVuelo() {
        return Avion.abordarVuelo(bodegas, bodegasAvion);
    }

    /**
     * Desembarca todos los vuelos, vaciando sus bodegas y
     * recopilando estadísticas de cada uno.
     *
     * @return lista de arreglos con datos de desembarque por vuelo
     */
    public List<int[]> desembarcarVuelo() {
        return Avion.desembarcarVuelo(bodegasAvion);
    }

    /**
     * Devuelve la cola general de equipajes en espera.
     *
     * @return la cola general
     */
    public ColaGeneral getColaGeneral() {
        return colaGeneral;
    }

    /**
     * Devuelve las bodegas de avión activas.
     *
     * @return arreglo de bodegas de avión
     */
    public BodegaAvion[] getBodegasAvion() {
        return bodegasAvion;
    }

    /**
     * Devuelve las bodegas de entrada clasificadas por destino.
     *
     * @return arreglo de bodegas de entrada
     */
    public Bodega[] getBodegas() {
        return bodegas;
    }
}