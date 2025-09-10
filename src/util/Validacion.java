package util;

import datastructures.list.List;
import domain.Equipaje;

/**
 * La clase {@code Validacion} proporciona métodos utilitarios
 * para validar datos relacionados con equipajes y mostrar resúmenes
 * de aquellos que no pudieron ser abordados en el avión.
 * <p>
 * Esta clase no necesita ser instanciada, ya que todos sus métodos son estáticos.
 * </p>
 */
public class Validacion {

    /**
     * Verifica si la categoría es válida.
     * <p>
     * La verificación se hace comparando la categoría con las
     * categorías "L", "M" y "S". Si coincide con alguna de
     * ellas, se devuelve true, de lo contrario se devuelve
     * false.
     * </p>
     *
     * @param categoria La categoría a verificar
     * @return true si la categoría es válida, false en caso contrario
     */
    public static boolean esCategoriaValida(String categoria) {
        return switch (categoria) {
            case "L", "M", "S" -> true;
            default -> false;
        };
    }

    /**
     * Muestra en consola un resumen de los equipajes que no pudieron ser abordados.
     * <p>
     * El resumen indica la cantidad total de equipajes no abordados y
     * un desglose por destino. Si la lista está vacía, no se muestra nada.
     * </p>
     *
     * @param noAbordadas lista de equipajes que no pudieron ser abordados
     */
    public static void mostrarResumenNoAbordadas(List<Equipaje> noAbordadas) {
        if (noAbordadas.isEmpty()) {
            return;
        }

        System.out.printf("⚠️ %d equipajes no pudieron abordarse.%n", noAbordadas.size());

        int[] conteoPorDestino = new int[Constantes.DESTINOS.length];

        for (Equipaje maleta : noAbordadas) {
            for (int i = 0; i < Constantes.DESTINOS.length; i++) {
                if (Constantes.DESTINOS[i].equals(maleta.destino())) {
                    conteoPorDestino[i]++;
                    break;
                }
            }
        }

        System.out.println("Resumen de equipajes no abordadas por destino:");
        for (int i = 0; i < Constantes.DESTINOS.length; i++) {
            if (conteoPorDestino[i] > 0) {
                System.out.printf("   %s: %d equipajes%n", Constantes.DESTINOS[i], conteoPorDestino[i]);
            }
        }
    }

}
