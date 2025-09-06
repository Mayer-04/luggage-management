package services;

/**
 * La clase {@code InventarioEquipaje} representa el registro de equipajes
 * en una bodega de avión, controlando las cantidades por categoría (L, M, S)
 * y los límites máximos permitidos.
 * <p>
 * Proporciona métodos para verificar si queda espacio disponible para una
 * categoría específica, incrementar o decrementar los contadores y obtener
 * el total de equipajes almacenados.
 * </p>
 * <p>
 * Esta clase debe instanciarse por cada bodega/avión, ya que mantiene
 * estado interno independiente.
 * </p>
 */
public class InventarioEquipaje {

    /**
     * Cantidad de equipajes categoría L.
     */
    private int contadorL = 0;

    /**
     * Cantidad de equipajes categoría M.
     */
    private int contadorM = 0;

    /**
     * Cantidad de equipajes categoría S.
     */
    private int contadorS = 0;

    /**
     * Límite máximo total de equipajes en la bodega.
     */
    private static final int LIMITE_TOTAL = 100;

    /**
     * Límite máximo de equipajes categoría L.
     */
    private static final int LIMITE_L = 20;

    /**
     * Límite máximo de equipajes categoría M.
     */
    private static final int LIMITE_M = 30;

    /**
     * Límite máximo de equipajes categoría S.
     */
    private static final int LIMITE_S = 50;

    /**
     * Verifica si queda cupo disponible para agregar un equipaje de la categoría dada.
     *
     * @param categoria La categoría del equipaje ("L", "M", "S").
     * @return {@code true} si todavía hay espacio para la categoría y no se supera el límite total;
     * {@code false} en caso contrario.
     * @throws IllegalArgumentException Si la categoría no es "L", "M" ni "S".
     */
    public boolean quedaCupoPara(String categoria) {
        return switch (categoria) {
            case "L" -> contadorL < LIMITE_L && getTotal() < LIMITE_TOTAL;
            case "M" -> contadorM < LIMITE_M && getTotal() < LIMITE_TOTAL;
            case "S" -> contadorS < LIMITE_S && getTotal() < LIMITE_TOTAL;
            default -> throw new IllegalArgumentException("Categoría desconocida: " + categoria);
        };
    }

    /**
     * Incrementa el contador de la categoría especificada en 1.
     *
     * @param categoria La categoría del equipaje ("L", "M", "S").
     * @throws IllegalArgumentException Si la categoría no es "L", "M" ni "S".
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
     * @param categoria La categoría del equipaje ("L", "M", "S").
     * @throws IllegalArgumentException Si la categoría no es "L", "M" ni "S".
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
     * @return La suma de los contadores de las categorías L, M y S.
     */
    public int getTotal() {
        return contadorL + contadorM + contadorS;
    }

    /**
     * Obtiene la cantidad de equipajes de categoría L.
     *
     * @return Cantidad de equipajes categoría L.
     */
    public int getContadorL() {
        return contadorL;
    }

    /**
     * Obtiene la cantidad de equipajes de categoría M.
     *
     * @return Cantidad de equipajes categoría M.
     */
    public int getContadorM() {
        return contadorM;
    }

    /**
     * Obtiene la cantidad de equipajes de categoría S.
     *
     * @return Cantidad de equipajes categoría S.
     */
    public int getContadorS() {
        return contadorS;
    }
}
