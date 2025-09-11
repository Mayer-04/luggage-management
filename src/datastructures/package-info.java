/**
 * Colección de estructuras de datos genéricas, redimensionables e iterables.
 * <p>
 * Este paquete proporciona implementaciones eficientes de estructuras de datos fundamentales
 * basadas en arreglos dinámicos. Todas las implementaciones son genéricas, lo que permite
 * trabajar con cualquier tipo de objeto de forma type-safe.
 * </p>
 *
 * <h2>Características Principales</h2>
 * <ul>
 *     <li><strong>Genéricas:</strong> Utilizan parámetros de tipo ({@code <T>}) para garantizar type safety</li>
 *     <li><strong>Redimensionables:</strong> Los arreglos internos crecen automáticamente según la demanda</li>
 *     <li><strong>Iterables:</strong> Todas implementan {@link java.lang.Iterable}, compatible con for-each</li>
 *     <li><strong>Basadas en arreglos:</strong> Uso eficiente de memoria con acceso O(1) a elementos</li>
 * </ul>
 *
 * <h2>Estructuras de Datos Disponibles</h2>
 *
 * <h3>{@link datastructures.Bag} - Multiconjunto</h3>
 * <p>
 * Colección que permite elementos duplicados sin mantener un orden específico.
 * Ideal para almacenar elementos donde el orden no importa pero sí la multiplicidad.
 * </p>
 * <pre>{@code
 * Bag<String> bag = new Bag<>();
 * bag.add("apple");
 * bag.add("banana");
 * bag.add("apple"); // Los duplicados están permitidos
 *
 * System.out.println(bag.size()); // 3
 * System.out.println(bag.contains("apple")); // true
 * }</pre>
 *
 * <h3>{@link datastructures.list.List} - Lista Dinámica</h3>
 * <p>
 * Implementación de lista basada en arreglo que permite acceso indexado,
 * inserción y eliminación en cualquier posición. Mantiene el orden de inserción.
 * </p>
 * <pre>{@code
 * List<Integer> list = new List<>();
 * list.addLast(10);
 * list.addFirst(5);
 * list.add(1, 15); // Insertar en posición específica
 *
 * System.out.println(list.get(0)); // 5
 * System.out.println(list); // list: [5, 15, 10]
 * }</pre>
 *
 * <h3>{@link datastructures.queue.Queue} - Cola FIFO</h3>
 * <p>
 * Implementación de cola que sigue el principio "First In, First Out".
 * Los elementos se agregan al final y se eliminan del frente.
 * </p>
 * <pre>{@code
 * Queue<String> queue = new Queue<>();
 * queue.enqueue("first");
 * queue.enqueue("second");
 *
 * String item = queue.dequeue(); // "first"
 * String next = queue.peek();    // "second" (sin eliminar)
 * }</pre>
 *
 * <h3>{@link datastructures.stack.Stack} - Pila LIFO</h3>
 * <p>
 * Implementación de pila que sigue el principio "Last In, First Out".
 * Los elementos se agregan y eliminan desde la parte superior.
 * </p>
 *
 * <h3>{@link datastructures.Deque} - Cola de Doble Extremo</h3>
 * <p>
 * Estructura que permite inserción y eliminación eficiente en ambos extremos,
 * combinando las funcionalidades de pila y cola.
 * </p>
 *
 * <h3>{@link datastructures.LinkedList} - Lista Enlazada</h3>
 * <p>
 * Implementación de lista enlazada que permite inserción y eliminación eficiente
 * en cualquier posición sin necesidad de redimensionar arreglos.
 * </p>
 *
 * <h2>Uso con Iteradores</h2>
 * <p>
 * Todas las estructuras implementan {@link java.lang.Iterable}, lo que permite
 * su uso en bucles for-each y con la API de Streams de Java:
 * </p>
 * <pre>{@code
 * List<String> list = new List<>();
 * list.addLast("A");
 * list.addLast("B");
 * list.addLast("C");
 *
 * // Usando for-each
 * for (String item : list) {
 *     System.out.println(item);
 * }
 *
 * // Usando iterador explícitamente
 * Iterator<String> iter = list.iterator();
 * while (iter.hasNext()) {
 *     System.out.println(iter.next());
 * }
 * }</pre>
 *
 * <h2>Consideraciones de Memoria</h2>
 * <p>
 * Todas las estructuras utilizan arreglos dinámicos que:
 * </p>
 * <ul>
 *     <li>Comienzan con capacidad inicial mínima (generalmente 1)</li>
 *     <li>Duplican su tamaño cuando alcanzan la capacidad máxima</li>
 *     <li>Algunos implementan shrinking cuando el uso es muy bajo</li>
 *     <li>Almacenan referencias a objetos, no los objetos directamente</li>
 * </ul>
 *
 */

package datastructures;