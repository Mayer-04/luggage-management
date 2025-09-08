package util;

import datastructures.list.List;
import domain.Equipaje;

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
