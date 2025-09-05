/**
 * Contiene implementaciones de pilas (estructuras de datos LIFO: Last In, First Out).
 *
 * <p>Las clases de este paquete permiten manejar colecciones de elementos
 * siguiendo la política de último en entrar, primero en salir. Las implementaciones
 * incluyen pilas de capacidad fija, pilas genéricas redimensionables y pilas
 * con soporte de iteración.</p>
 *
 * <h2>Clases principales</h2>
 * <ul>
 *   <li>{@link datastructures.stack.Stack} - Pila genérica con redimensionamiento dinámico.</li>
 *   <li>{@link datastructures.stack.FixedCapacityStackOfStrings} - Pila de capacidad fija para cadenas.</li>
 *   <li>{@link datastructures.stack.GenericResizingStack} - Pila genérica con arreglo redimensionable.</li>
 *   <li>{@link datastructures.stack.GenericResizingIterableStack} - Pila genérica redimensionable e iterable.</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
 * <pre>{@code
 * Stack<Integer> stack = new Stack<>();
 * stack.push(10);
 * stack.push(20);
 * int top = stack.pop(); // devuelve 20
 * }</pre>
 *
 * @see datastructures.stack.Stack
 * @see datastructures.stack.FixedCapacityStackOfStrings
 * @see datastructures.stack.GenericResizingStack
 * @see datastructures.stack.GenericResizingIterableStack
 */

package datastructures.stack;
