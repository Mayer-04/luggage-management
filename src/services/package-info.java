/**
 * Este paquete agrupa los servicios principales del sistema de gestión de equipaje.
 *
 * <p>Su objetivo es coordinar el flujo completo del equipaje, desde que el pasajero lo registra
 * hasta que llega a su destino final. Las clases incluidas proporcionan tanto lógica de negocio
 * como utilidades de organización y validación.</p>
 *
 * <p>Entre sus responsabilidades se encuentran:</p>
 * <ul>
 *   <li>Registrar y almacenar equipajes en una {@link domain.ColaGeneral} de espera.</li>
 *   <li>Clasificar los equipajes en {@link domain.Bodega}s según destino.</li>
 *   <li>Gestionar la carga y descarga de equipajes en {@link domain.BodegaAvion}s asociadas a vuelos.</li>
 *   <li>Aplicar algoritmos de ordenamiento (p. ej., {@link services.QuickLuggageSorting}) para priorizar maletas por categoría de tiquete.</li>
 *   <li>Controlar inventarios y restricciones de capacidad mediante {@link services.InventarioEquipaje}.</li>
 *   <li>Ofrecer estadísticas y reportes finales a través de clases auxiliares.</li>
 *   <li>Facilitar la interacción con el usuario mediante la interfaz de consola {@link services.InterfazGestorEquipaje}.</li>
 * </ul>
 *
 * <p>Clases destacadas dentro del paquete:</p>
 * <ul>
 *   <li>{@link services.GestorEquipaje}: servicio central que administra todo el ciclo de vida del equipaje.</li>
 *   <li>{@link services.Bodegas}: gestiona la distribución de equipajes en bodegas de entrada.</li>
 *   <li>{@link services.Avion}: coordina el abordaje y desembarque de los vuelos.</li>
 *   <li>{@link services.InventarioEquipaje}: controla límites y cupos de equipajes en bodegas de avión.</li>
 *   <li>{@link services.QuickLuggageSorting}: implementa el algoritmo QuickSort para ordenar maletas según prioridad.</li>
 *   <li>{@link services.InterfazGestorEquipaje}: capa de interfaz que conecta al usuario con el sistema.</li>
 * </ul>
 *
 * <p>En conjunto, este paquete constituye el núcleo de la lógica del sistema,
 * integrando las estructuras de datos del paquete {@code domain} con reglas
 * de negocio y procesos logísticos.</p>
 */

package services;
