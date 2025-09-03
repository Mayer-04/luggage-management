package enums;

public enum Destino {
    BOGOTA("Bogotá"),
    MEDELLIN("Medellín"),
    CALI("Cali"),
    BARRANQUILLA("Barranquilla"),
    BUCARAMANGA("Bucaramanga");

    private final String nombre;

    Destino(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
