package util;

import java.text.Normalizer;

public class Validacion {

    public static String normalizarDestino(String destino) {
        if (destino == null || destino.isBlank()) {
            return null;
        }

        String destinoMinusculas = destino.toLowerCase();

        return Normalizer.normalize(destinoMinusculas, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
    }
}
