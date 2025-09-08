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
     * Verifica si hay cupo disponible para un equipaje de la categoría
     * especificada.
     * <p>
     * La verificación tiene en cuenta dos aspectos:
     * <ul>
     *   <li>La cantidad actual de equipajes en la categoría no debe
     *       superar el límite máximo para esa categoría.</li>
     *   <li>El total de equipajes en la bodega no debe superar el
     *       límite máximo total.</li>
     * </ul>
     * </p>
     * <p>
     * Si la categoría es desconocida, se lanza una excepción de tipo
     * {@link IllegalArgumentException}.
     * </p>
     *
     * @param categoria la categoría del equipaje a verificar
     * @return {@code true} si hay cupo disponible, {@code false} en caso
     *         contrario
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
