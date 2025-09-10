package services;

/**
 * La clase {@code InventarioEquipaje} representa el registro de equipajes
 * en una bodega de avión, controlando las cantidades por categoría
 * ({@code "L"}, {@code "M"}, {@code "S"}) y los límites máximos permitidos.
 *
 * <p>
 * Proporciona métodos para:
 * <ul>
 *   <li>Verificar si queda espacio disponible en una categoría.</li>
 *   <li>Incrementar o decrementar los contadores de equipajes por categoría.</li>
 *   <li>Obtener el total de equipajes registrados en la bodega.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Cada instancia de esta clase mantiene un estado interno independiente,
 * por lo que debe crearse una por cada bodega/avión.
 * </p>
 *
 * @see domain.Equipaje
 */
public class InventarioEquipaje {

    /**
     * Cantidad de equipajes categoría L.
     */
    private int contadorL = 0;

    /**
     * Límite máximo total de equipajes en la bodega.
     */
    private static final int LIMITE_TOTAL = 100;
    /** Límite máximo de equipajes categoría L. */
    private static final int LIMITE_L = 20;
    /** Límite máximo de equipajes categoría M. */
    private static final int LIMITE_M = 30;
    /** Límite máximo de equipajes categoría S. */
    private static final int LIMITE_S = 50;
    /**
     * Cantidad de equipajes categoría M.
     */
    private int contadorM = 0;
    /**
     * Cantidad de equipajes categoría S.
     */
    private int contadorS = 0;

    /**
     * Verifica si hay cupo disponible para un equipaje de la categoría
     * especificada.
     *
     * <p>
     * La verificación tiene en cuenta:
     * <ul>
     *   <li>Que la cantidad actual de equipajes de la categoría no supere
     *       su límite máximo.</li>
     *   <li>Que el total de equipajes en la bodega no supere el límite general.</li>
     * </ul>
     * </p>
     *
     * <p>
     * Si la categoría no es válida, se lanza una excepción de tipo
     * {@link IllegalArgumentException}.
     * </p>
     *
     * @param categoria categoría del equipaje ({@code "L"}, {@code "M"}, {@code "S"})
     * @return {@code true} si hay cupo disponible, {@code false} en caso contrario
     */
    public boolean hayCupoPara(String categoria) {
        int limite;
        int contador;

        switch (categoria) {
            case "L" -> {
                limite = LIMITE_L;
                contador = contadorL;
            }
            case "M" -> {
                limite = LIMITE_M;
                contador = contadorM;
            }
            case "S" -> {
                limite = LIMITE_S;
                contador = contadorS;
            }
            default -> throw new IllegalArgumentException("Categoría desconocida: " + categoria);
        }

        return contador < limite && getTotal() < LIMITE_TOTAL;
    }

    /**
     * Incrementa el contador de la categoría especificada en 1.
     *
     * @param categoria categoría del equipaje ({@code "L"}, {@code "M"}, {@code "S"})
     * @throws IllegalArgumentException si la categoría no es válida
     */
    public void incrementarContadores(String categoria) {
        switch (categoria) {
            case "L" -> contadorL++;
            case "M" -> contadorM++;
            case "S" -> contadorS++;
            default -> throw new IllegalArgumentException("Categoría desconocida: " + categoria);
        }
    }

    /**
     * Decrementa el contador de la categoría especificada en 1.
     *
     * @param categoria categoría del equipaje ({@code "L"}, {@code "M"}, {@code "S"})
     * @throws IllegalArgumentException si la categoría no es válida
     */
    public void decrementarContadores(String categoria) {
        switch (categoria) {
            case "L" -> contadorL--;
            case "M" -> contadorM--;
            case "S" -> contadorS--;
            default -> throw new IllegalArgumentException("Categoría desconocida: " + categoria);
        }
    }

    /**
     * Obtiene la cantidad total de equipajes almacenados en la bodega.
     *
     * @return suma de los contadores de las categorías L, M y S
     */
    public int getTotal() {
        return contadorL + contadorM + contadorS;
    }

    /**
     * Obtiene la cantidad de equipajes de categoría L.
     *
     * @return número de equipajes categoría L
     */
    public int getContadorL() {
        return contadorL;
    }

    /**
     * Obtiene la cantidad de equipajes de categoría M.
     *
     * @return número de equipajes categoría M
     */
    public int getContadorM() {
        return contadorM;
    }

    /**
     * Obtiene la cantidad de equipajes de categoría S.
     *
     * @return número de equipajes categoría S
     */
    public int getContadorS() {
        return contadorS;
    }
}
