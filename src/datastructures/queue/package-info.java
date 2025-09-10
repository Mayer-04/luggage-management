/**
 * Paquete {@code datastructures.queue}.
 * <p>
 * Este paquete contiene implementaciones y utilidades relacionadas con
 * la estructura de datos <strong>cola</strong>. Una cola sigue el principio
 * <b>FIFO (First In, First Out)</b>, es decir, el primer elemento en entrar
 * es el primero en salir.
 * </p>
 *
 * <h2>Clases principales:</h2>
 * <ul>
 *   <li>
 *     {@link datastructures.queue.Queue}:
 *     <p>
 *     Implementación genérica de una cola usando un arreglo dinámico.
 *     Permite realizar operaciones como:
 *     </p>
 *     <ul>
 *       <li>{@code enqueue()} → insertar un elemento al final.</li>
 *       <li>{@code dequeue()} → eliminar y devolver el primer elemento.</li>
 *       <li>{@code peek()} → consultar el primer elemento sin eliminarlo.</li>
 *       <li>{@code isEmpty()} y {@code size()} → comprobar estado de la cola.</li>
 *       <li>Recorrido mediante un {@link java.util.Iterator} en orden FIFO.</li>
 *     </ul>
 *     <p>
 *     La capacidad de la cola se redimensiona automáticamente al llenarse
 *     o quedarse demasiado vacía, optimizando el uso de memoria.
 *     </p>
 *   </li>
 *
 *   <li>
 *     {@link datastructures.queue.InteractiveQueue}:
 *     <p>
 *     Clase de apoyo para interactuar con la cola de manera dinámica
 *     desde consola. Permite al usuario:
 *     </p>
 *     <ul>
 *       <li>Ingresar elementos.</li>
 *       <li>Eliminar elementos.</li>
 *       <li>Consultar el primer elemento.</li>
 *       <li>Visualizar todos los elementos actuales.</li>
 *     </ul>
 *     <p>
 *     Resulta útil para prácticas educativas y pruebas rápidas de la estructura.
 *     </p>
 *   </li>
 *
 *   <li>
 *     {@link datastructures.queue.Main}:
 *     <p>
 *     Clase principal que ejecuta un menú interactivo en consola,
 *     permitiendo al usuario manipular una {@code Queue<String>} mediante
 *     la clase {@code InteractiveQueue}.
 *     </p>
 *     <p>
 *     Es el punto de entrada de un programa de demostración.
 *     </p>
 *   </li>
 * </ul>
 *
 * <h2>Ejemplo de uso básico:</h2>
 * <pre>{@code
 * Queue<Integer> cola = new Queue<>();
 * cola.enqueue(1);
 * cola.enqueue(2);
 * cola.enqueue(3);
 *
 * System.out.println(cola.dequeue()); // 1
 * System.out.println(cola.peek());    // 2
 * System.out.println(cola);           // queue: [2, 3]
 * }</pre>
 *
 * <h2>Ejemplo de ejecución interactiva:</h2>
 * <pre>
 * Seleccione una opción:
 * ----------------------
 * 1) Agregar un elemento
 * 2) Eliminar un elemento
 * 3) Mostrar el primer elemento
 * 4) Mostrar todos los elementos
 * 5) Salir
 * </pre>
 *
 * <p>
 * De esta manera, el paquete ofrece tanto la implementación de la estructura
 * como herramientas para su uso y experimentación en consola.
 * </p>
 *
 */

package datastructures.queue;
