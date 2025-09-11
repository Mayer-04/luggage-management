package datastructures.list;

/**
 * Implementación especializada de una lista que almacena exclusivamente
 * cadenas de texto ({@link String}), basada en un arreglo dinámico.
 * <p>
 * La lista permite insertar, modificar, buscar, obtener y eliminar elementos
 * en posiciones específicas. Al eliminar elementos, la estructura se ajusta
 * automáticamente para mantener la continuidad de los datos.
 * </p>
 *
 * <p>
 * A diferencia de {@link datastructures.list.List}, esta implementación
 * no es genérica y está optimizada para trabajar solo con cadenas.
 * </p>
 */
public class ListOfStrings {

    private String[] elementos;
    private int contador;

    /**
     * Crea una lista vacía con la capacidad inicial indicada.
     *
     * @param capacidad el tamaño inicial del arreglo interno
     */
    public ListOfStrings(int capacidad) {
        elementos = new String[capacidad];
        contador = 0;
    }

    /**
     * Agregar un elemento en una posición indicada.
     *
     * @param indice   el índice donde se insertará el elemento
     * @param elemento el elemento a insertar
     */
    public void adicionar(int indice, String elemento) {
        elementos[indice] = elemento.trim();
        contador++;
    }

    /**
     * Agregar un elemento al inicio de la lista.
     *
     * @param elemento el elemento a insertar
     */
    public void adicionarInicio(String elemento) {
        adicionar(0, elemento);
    }

    /**
     * Agregar un elemento al final de la lista.
     *
     * @param elemento el elemento a insertar
     */
    public void adicionarFinal(String elemento) {
        adicionar(contador, elemento);
    }

    /**
     * Eliminar todos los elementos de la lista.
     */
    public void vaciar() {
        elementos = new String[elementos.length];
        contador = 0;
    }

    /**
     * Verifica si la lista contiene un elemento.
     *
     * @param elemento el elemento a buscar
     * @return true si el elemento se encuentra en la lista, false si no
     */
    public boolean contiene(String elemento) {
        for (String valor : elementos) {
            if (valor != null && valor.equals(elemento)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene el elemento de la lista en la posición indicada.
     *
     * @param indice el índice del elemento a obtener
     * @return el elemento en la posición indicada
     */
    public String obtener(int indice) {
        return elementos[indice];
    }

    /**
     * Obtiene el primer elemento de la lista.
     *
     * @return el primer elemento de la lista
     */
    public String obtenerPrimero() {
        return obtener(0);
    }

    /**
     * Obtiene el último elemento de la lista.
     *
     * @return el último elemento de la lista
     */
    public String obtenerUltimo() {
        return obtener(contador - 1);
    }

    /**
     * Busca el elemento en la lista y devuelve su posición.
     *
     * @param elemento el elemento a buscar
     * @return la posición del elemento si lo encuentra, -1 si no lo encuentra
     */
    public int buscar(String elemento) {
        for (int i = 0; i < contador; i++) {
            if (elementos[i].equals(elemento)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return true si la lista no contiene elementos, false si contiene al
     * menos uno
     */
    public boolean estaVacia() {
        return contador == 0;
    }

    /**
     * Remueve y devuelve el elemento de la posición indicada.
     *
     * @param indice el índice del elemento a remover
     * @return el elemento que fue removido
     */
    public String remover(int indice) {
        // 1. Guardamos el elemento que se encuentra en la posición indicada.
        var elemento = elementos[indice];

        // 2. Creamos un nuevo arreglo con un espacio menos, ya que vamos a eliminar un elemento.
        String[] nuevoArreglo = new String[contador - 1];

        // 3. Copiamos todos los elementos del arreglo original al nuevo,
        // excepto el que está en la posición que queremos eliminar.
        var nuevoIndice = 0;
        for (int i = 0; i < contador; i++) {
            if (i == indice) {
                continue;
            }
            nuevoArreglo[nuevoIndice] = elementos[i];
            nuevoIndice++;
        }

        // 4. Actualizamos el contador para reflejar que hay un elemento menos.
        contador--;

        // 5. Reemplazamos el arreglo original por el nuevo.
        elementos = nuevoArreglo;

        // 6. Devolvemos el elemento que fue eliminado.
        return elemento;
    }

    /**
     * Remueve un elemento específico de la lista.
     *
     * @param elemento el elemento a remover
     * @return true si el elemento fue removido, false si no se encontró
     */
    public boolean remover(String elemento) {
        int indice = buscar(elemento);
        if (indice != -1) {
            remover(indice);
            return true;
        }
        return false;
    }

    /**
     * Remueve el primer elemento de la lista y lo devuelve.
     *
     * @return el elemento removido
     */
    public String removerPrimero() {
        return remover(0);
    }

    /**
     * Remueve el último elemento de la lista y lo devuelve.
     *
     * @return el elemento removido
     */
    public String removerUltimo() {
        return remover(contador - 1);
    }

    /**
     * Remueve un rango de elementos y devuelve una nueva lista con ellos.
     *
     * @param inicio índice inicial (inclusive)
     * @param fin    índice final (exclusive)
     * @return nueva lista con los elementos removidos
     */
    public ListOfStrings removerIntervalo(int inicio, int fin) {
        int cantidad = fin - inicio;
        var removidos = new ListOfStrings(cantidad);

        // Copiar los elementos que se eliminan
        System.arraycopy(elementos, inicio, removidos.elementos, 0, cantidad);
        removidos.contador = cantidad;

        // Desplazar a la izquierda los elementos posteriores a 'fin'
        for (int i = fin; i < contador; i++) {
            elementos[i - cantidad] = elementos[i];
        }

        // Limpiar el resto
        for (int i = contador - cantidad; i < contador; i++) {
            elementos[i] = null;
        }

        contador -= cantidad;
        return removidos;
    }

    /**
     * Modifica el valor de un elemento en la posición indicada.
     *
     * @param indice   el índice del elemento a modificar
     * @param elemento el nuevo valor del elemento
     */
    public void modificar(int indice, String elemento) {
        elementos[indice] = elemento;
    }

    /**
     * Devuelve el número de elementos en la lista.
     *
     * @return la cantidad de elementos que contiene la lista
     */
    public int tamaño() {
        return contador;
    }

    /**
     * Devuelve una representación en cadena de la lista.
     * <p>
     * Las cadenas se muestran en orden de inserción, separadas por comas.
     * </p>
     *
     * @return una cadena que representa la lista
     */
    @Override
    public String toString() {
        final StringBuilder salida = new StringBuilder("lista: [");
        for (int i = 0; i < contador; i++) {
            salida.append(elementos[i]);
            if (i < contador - 1) {
                salida.append(", ");
            }
        }
        salida.append("]");
        return salida.toString();
    }
}
