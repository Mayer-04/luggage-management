package datastructures.list;

public final class Validations {

    private Validations() {
    }

    /**
     * Verifica que el índice de acceso esté dentro del rango válido del arreglo.
     * <p>
     * El índice debe estar entre 0 (inclusive) y contador (exclusive).
     * Si no cumple esta condición, se lanza una excepción.
     *
     * @param indice   Índice que se desea acceder
     * @param contador Número total de elementos en el arreglo
     * @throws IndexOutOfBoundsException Si el índice está fuera del rango permitido
     */
    public static void indiceAcceso(int indice, int contador) {
        if (indice < 0 || indice >= contador) {
            throw new IndexOutOfBoundsException(Constants.INDICE_FUERA_DE_RANGO);
        }
    }

    /**
     * Verifica que el índice de inserción esté dentro del rango válido para agregar un elemento.
     * <p>
     * El índice debe estar entre 0 (inclusive) y contador (inclusive).
     * Si no cumple esta condición, se lanza una excepción.
     *
     * @param indice   Posición en la que se desea insertar el nuevo elemento
     * @param contador Número total de elementos actuales en el arreglo
     * @throws IndexOutOfBoundsException Si el índice está fuera del rango permitido
     */
    public static void indiceInsercion(int indice, int contador) {
        if (indice < 0 || indice > contador) {
            throw new IndexOutOfBoundsException(Constants.INDICE_FUERA_DE_RANGO);
        }
    }

    /**
     * Verifica que el elemento proporcionado no sea nulo.
     * <p>
     * Si el elemento es {@code null}, se lanza una excepción.
     *
     * @param elemento El valor que se desea validar
     * @throws NullPointerException Si el elemento es nulo
     */
    public static void elementoNoNulo(String elemento) {
        if (elemento == null) {
            throw new NullPointerException(Constants.ELEMENTO_NULO);
        }
    }
}
