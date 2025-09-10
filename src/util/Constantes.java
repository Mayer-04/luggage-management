package util;

/**
 * La clase {@code Constantes} contiene valores constantes utilizados
 * en el sistema de gestión de equipajes.
 * <p>
 * Incluye principalmente los destinos disponibles para los vuelos.
 * </p>
 * <p>
 * Es una clase de solo utilidades, por lo que no debe instanciarse.
 * </p>
 */
public class Constantes {

    /**
     * Lista de destinos disponibles en el sistema.
     * <p>
     * Los destinos definidos son:
     * <ul>
     *     <li>Bogotá</li>
     *     <li>Medellín</li>
     *     <li>Cali</li>
     *     <li>Bucaramanga</li>
     *     <li>Barranquilla</li>
     * </ul>
     * </p>
     */
    public static final String[] DESTINOS = {"Bogotá", "Medellín", "Cali", "Bucaramanga", "Barranquilla"};

    /**
     * Constructor privado para evitar la instanciación de la clase.
     */
    private Constantes() {
    }
}
