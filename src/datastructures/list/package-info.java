/**
 * Paquete {@code datastructures.list}.
 * <p>
 * Este paquete contiene implementaciones de listas que permiten almacenar,
 * acceder y manipular colecciones de elementos en memoria. Proporciona tanto
 * una versión genérica como una versión especializada para cadenas de texto.
 * </p>
 *
 * <h2>Clases principales:</h2>
 * <ul>
 *   <li>
 *     {@link datastructures.list.List}:
 *     <p>
 *     Una implementación genérica de lista basada en un arreglo dinámico.
 *     Soporta operaciones comunes como:
 *     </p>
 *     <ul>
 *       <li>Agregar elementos en posiciones específicas, al inicio o al final.</li>
 *       <li>Eliminar elementos individuales, en rangos o todos de una vez.</li>
 *       <li>Consultar si un elemento existe, obtenerlo o modificarlo.</li>
 *       <li>Recorrer los elementos mediante un {@link java.util.Iterator}.</li>
 *     </ul>
 *     <p>
 *     Es adecuada cuando se necesita flexibilidad para trabajar con cualquier
 *     tipo de objeto en una lista dinámica que crece y se reduce automáticamente.
 *     </p>
 *   </li>
 *
 *   <li>
 *     {@link datastructures.list.ListOfStrings}:
 *     <p>
 *     Una implementación de lista optimizada exclusivamente para
 *     {@link java.lang.String}. Sus operaciones son equivalentes a la clase
 *     genérica, pero simplificadas para trabajar con cadenas de texto.
 *     </p>
 *     <p>
 *     Es útil cuando se requiere gestionar únicamente listas de cadenas,
 *     ofreciendo una implementación más directa y fácil de entender.
 *     </p>
 *   </li>
 * </ul>
 *
 * <h2>Uso recomendado:</h2>
 * <ul>
 *   <li>Utiliza {@code List<T>} cuando necesites manejar distintos tipos de datos.</li>
 *   <li>Utiliza {@code ListOfStrings} cuando solo trabajes con cadenas de texto y
 *       quieras una implementación sencilla y específica.</li>
 * </ul>
 *
 * <p><strong>Ejemplo rápido:</strong></p>
 * <pre>{@code
 * List<Integer> numeros = new List<>();
 * numeros.addLast(10);
 * numeros.addLast(20);
 * numeros.addFirst(5);
 * System.out.println(numeros); // list: [5, 10, 20]
 *
 * ListOfStrings palabras = new ListOfStrings(10);
 * palabras.adicionarFinal("Hola");
 * palabras.adicionarFinal("Mundo");
 * System.out.println(palabras); // lista: [Hola, Mundo]
 * }</pre>
 *
 */

package datastructures.list;