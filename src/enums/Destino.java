package enums;

import util.Validacion;

public enum Destino {
    BOGOTA("Bogotá"),
    MEDELLIN("Medellín"),
    CALI("Cali"),
    BARRANQUILLA("Barranquilla"),
    BUCARAMANGA("Bucaramanga");

    private final String destino;
    private final String destinoNormalizado;

    Destino(String destino) {
        this.destino = destino;
        this.destinoNormalizado = Validacion.normalizarDestino(destino);
    }

    public String getDestino() {
        return destino;
    }

    public String getDestinoNormalizado() {
        return destinoNormalizado;
    }

    /**
     * Convierte un String a un Destino válido usando el nombre normalizado.
     */
    public static Destino fromString(String valor) {
        String normalizado = Validacion.normalizarDestino(valor);
        for (Destino d : values()) {
            if (d.destinoNormalizado.equals(normalizado)) {
                return d;
            }
        }
        throw new IllegalArgumentException("Destino no válido: " + valor);
    }
}
