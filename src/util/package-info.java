/**
 * Proporciona un conjunto de utilidades para la gestión de equipajes y vuelos,
 * incluyendo validación de categorías, estadísticas de vuelos, generación de colecciones
 * de bodegas y lectura de datos desde archivos JSON.
 * <p>
 * Este paquete centraliza las funciones de soporte para el dominio de equipajes,
 * garantizando operaciones consistentes y reutilizables que facilitan el manejo
 * de datos en diferentes etapas del sistema.
 * </p>
 *
 * <h2>Clases públicas</h2>
 * <ul>
 *   <li>{@link util.Constantes} – Define los destinos disponibles en el sistema.</li>
 *   <li>{@link util.ColeccionBodegas} – Genera arreglos de bodegas y bodegas de avión según los destinos.</li>
 *   <li>{@link util.Validacion} – Ofrece métodos de validación y resúmenes de equipajes no abordados.</li>
 *   <li>{@link util.LuggageJsonReader} – Permite leer datos de equipajes desde un archivo JSON.</li>
 *   <li>{@link util.Estadisticas} – Muestra estadísticas de vuelos y listas de pasajeros.</li>
 * </ul>
 *
 * <h2>Buenas prácticas de uso</h2>
 * <ul>
 *   <li>No instanciar las clases de utilidades con constructores privados.</li>
 *   <li>Reutilizar las constantes definidas en {@link util.Constantes} en lugar de redefinir valores.</li>
 *   <li>Manejar excepciones al leer archivos JSON con {@link util.LuggageJsonReader}.</li>
 *   <li>Verificar que las listas de equipajes no sean nulas antes de invocar métodos de {@link util.Validacion}.</li>
 *   <li>Usar los métodos de {@link util.ColeccionBodegas} para garantizar consistencia en la creación de bodegas.</li>
 * </ul>
 *
 * @since 1.0
 * @author TuNombre
 * @version 1.0
 */

package util;
