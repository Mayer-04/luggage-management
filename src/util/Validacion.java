package util;

import java.text.Normalizer;

public class Validacion {

    public static String normalizarDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            return null;
        }

        String destinoMinusculas = destino.toLowerCase();

        // Quitar acentos y diacríticos
        return Normalizer.normalize(destinoMinusculas, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }

    public static String convertirAMayusculas(String texto) {
        if (texto == null || texto.isBlank()) {
            return null;
        }

        return texto.toUpperCase();
    }
}
