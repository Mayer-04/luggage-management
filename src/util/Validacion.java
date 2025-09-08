package util;

import datastructures.list.List;
import domain.Equipaje;

import java.text.Normalizer;

public class Validacion {

    private static final String NORMALIZAR_PATRON = "[^a-z\\s]";

    /**
     * Normaliza un nombre de destino de viaje para que solo contenga
     * caracteres alfanumericos y espacios. El nombre se convierte a
     * minusculas y se eliminan los diacriticos y otros caracteres no
     * deseados.
     *
     * @param destino El nombre del destino a normalizar.
     * @return El nombre del destino normalizado, o null si el nombre
     * original era null o vacio.
     */
    public static String normalizarDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            return null;
        }

        String destinoMinusculas = destino.toLowerCase();

        return Normalizer.normalize(destinoMinusculas, Normalizer.Form.NFD)
                .replaceAll(NORMALIZAR_PATRON, "");
    }

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
