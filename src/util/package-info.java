/**
 * Paquete {@code util}.
 * <p>
 * Este paquete contiene clases de apoyo (utilidades) que facilitan la gestión
 * y validación de datos dentro de la aplicación. Incluye constantes predefinidas,
 * validaciones comunes y un lector especializado para archivos JSON de equipajes.
 * </p>
 *
 * <h2>Clases principales:</h2>
 * <ul>
 *   <li>
 *     {@link util.Constantes}:
 *     <p>
 *     Clase que centraliza valores fijos usados en la aplicación, como la lista
 *     de destinos disponibles: <i>Bogotá, Medellín, Cali, Bucaramanga y
 *     Barranquilla</i>.
 *     </p>
 *     <p>
 *     Al ser final y tener un constructor privado, no puede instanciarse.
 *     Sus datos se acceden directamente como atributos estáticos.
 *     </p>
 *     <pre>{@code
 * String[] destinos = Constantes.DESTINOS;
 * }</pre>
 *   </li>
 *
 *   <li>
 *     {@link util.LuggageJsonReader}:
 *     <p>
 *     Clase que permite leer archivos JSON y convertirlos en listas de objetos
 *     {@link domain.Equipaje}. Internamente utiliza la librería
 *     <a href="https://github.com/google/gson">Gson</a> para el
 *     procesamiento de JSON.
 *     </p>
 *     <p>
 *     Maneja los errores de lectura lanzando una excepción de tipo
 *     {@link IllegalArgumentException}, de forma que el programa pueda
 *     identificar fallas en la carga de datos.
 *     </p>
 *     <pre>{@code
 * LuggageJsonReader reader = new LuggageJsonReader("equipajes.json");
 * List<Equipaje> equipajes = reader.cargarDatos();
 * }</pre>
 *   </li>
 *
 *   <li>
 *     {@link util.Validacion}:
 *     <p>
 *     Clase con métodos estáticos para realizar comprobaciones y mostrar resúmenes.
 *     Incluye:
 *     </p>
 *     <ul>
 *       <li>{@code esCategoriaValida()} → valida si una categoría es correcta
 *           (acepta solo "L", "M" o "S").</li>
 *       <li>{@code mostrarResumenNoAbordadas()} → imprime un informe con el
 *           conteo de equipajes no abordados por destino.</li>
 *     </ul>
 *     <p>
 *     Se usa como herramienta auxiliar para verificar datos y mostrar resultados
 *     al usuario.
 *     </p>
 *     <pre>{@code
 * if (!Validacion.esCategoriaValida("XL")) {
 *     System.out.println("Categoría inválida");
 * }
 *
 * Validacion.mostrarResumenNoAbordadas(listaEquipajes);
 * }</pre>
 *   </li>
 * </ul>
 *
 * <h2>Uso recomendado:</h2>
 * <ul>
 *   <li>Utiliza {@code Constantes} para acceder a valores fijos que no deberían cambiar.</li>
 *   <li>Utiliza {@code LuggageJsonReader} para cargar equipajes desde un archivo JSON externo.</li>
 *   <li>Utiliza {@code Validacion} para verificar categorías y mostrar resúmenes de datos.</li>
 * </ul>
 *
 * <p>
 * En conjunto, este paquete actúa como un conjunto de <b>herramientas
 * auxiliares</b> para mejorar la legibilidad y la reutilización del código en
 * el proyecto.
 * </p>
 *
 */

package util;
