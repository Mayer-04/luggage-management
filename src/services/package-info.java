/**
 * Este paquete contiene las clases de servicio que gestionan la lógica operativa,
 * el procesamiento de datos, las utilidades y la interacción del sistema de transporte de equipaje.
 * <p>
 * Las funcionalidades clave incluyen:
 * <ul>
 *   <li>{@link services.Bodegas} Distribuye equipajes desde una {@link domain.ColaGeneral} hacia las {@link domain.Bodega} correspondientes según destino.
 *       <br>Método principal: {@code procesarEquipaje(ColaGeneral, Bodega[])}
 *       <br><pre>{@code
 * Bodegas.procesarEquipaje(colaGeneral, bodegas);
 * }</pre></li>
 *
 *   <li>{@link services.Avion}  Organiza y carga equipajes en vuelos, respetando restricciones por categoría de tiquete.
 *       <br>Métodos principales: {@code abordarVuelo(Bodega[], BodegaAvion[])}, {@code desembarcarVuelo(BodegaAvion[])}
 *       <br><pre>{@code
 * List<Equipaje> noAbordados = Avion.abordarVuelo(bodegas, vuelos);
 * List<int[]> stats = Avion.desembarcarVuelo(vuelos);
 * }</pre></li>
 *
 *   <li>{@link services.Estadisticas} Genera estadísticas y muestra la lista de pasajeros por vuelo.
 *       <br>Métodos principales: {@code mostrarEstadisticas(BodegaAvion[])}, {@code mostrarListaDePasajeros(BodegaAvion[])}
 *       <br><pre>{@code
 * Estadisticas.mostrarEstadisticas(vuelos);
 * Estadisticas.mostrarListaDePasajeros(vuelos);
 * }</pre></li>
 *
 *   <li>{@link services.InventarioEquipaje}  Controla el inventario por categoría de equipaje en cada vuelo.
 *       <br>Métodos principales: {@code hayCupoPara(String)}, {@code incrementarContadores(String)}, {@code getTotal()}
 *       <br><pre>{@code
 * if (inventario.hayCupoPara("L")) {
 *     inventario.incrementarContadores("L");
 * }
 * }</pre></li>
 *
 *   <li>{@link services.QuickLuggageSorting}  Ordena listas de equipajes por prioridad de tiquete usando el algoritmo QuickSort.
 *       <br>Método principal: {@code quickSort(List<Equipaje>)}
 *       <br><pre>{@code
 * QuickLuggageSorting.quickSort(listaEquipajes);
 * }</pre></li>
 *
 *   <li>{@link services.ColeccionBodegas}  Inicializa colecciones de bodegas y bodegas de avión a partir de los destinos definidos en {@link util.Constantes}.
 *       <br>Métodos principales: {@code obtenerBodegasDeEntrada()}, {@code obtenerBodegasDeAviones()}
 *       <br><pre>{@code
 * Bodega[] bodegas = ColeccionBodegas.obtenerBodegasDeEntrada();
 * BodegaAvion[] vuelos = ColeccionBodegas.obtenerBodegasDeAviones();
 * }</pre></li>
 *
 *   <li>{@link services.InterfazGestorEquipaje}  Proporciona una interfaz de consola para registrar, procesar, abordar y desembarcar equipajes.
 *       <br>Métodos principales: {@code registrarEquipaje()}, {@code procesarEquipajes()}, {@code abordarVuelo()}, {@code desembarcarVuelo()}
 *       <br><pre>{@code
 * interfaz.registrarEquipaje();
 * interfaz.procesarEquipajes();
 * interfaz.abordarVuelo();
 * interfaz.desembarcarVuelo();
 * }</pre></li>
 * </ul>
 * <p>
 * Estas clases interactúan con el modelo del paquete {@code domain}, estructuras de datos personalizadas en {@code datastructures},
 * y constantes definidas en {@code util}, promoviendo una arquitectura modular, clara y extensible.
 */

package services;