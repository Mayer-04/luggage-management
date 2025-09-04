package luggage;

import datastructures.stack.Stack;

public class BodegaAvion {

    private static final int LIMITE_TOTAL = 100;
    private static final int LIMITE_L = 20;
    private static final int LIMITE_M = 30;
    private static final int LIMITE_S = 50;
    private final Stack<Equipaje> pila = new Stack<>();
    private final String destino;
    // Contadores por categoría
    private int contadorL = 0;
    private int contadorM = 0;
    private int contadorS = 0;

    public BodegaAvion(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public int getCantidadTotal() {
        return contadorL + contadorM + contadorS;
    }

    public Iterable<Equipaje> getPasajeros() {
        return pila;
    }

    public boolean agregarEquipaje(Equipaje maleta) {
        if (size() >= LIMITE_TOTAL) return false;

        switch (maleta.categoriaTiquete()) {
            case "L" -> {
                if (contadorL < LIMITE_L) {
                    contadorL++;
                    pila.push(maleta);
                    return true;
                }
            }
            case "M" -> {
                if (contadorM < LIMITE_M) {
                    contadorM++;
                    pila.push(maleta);
                    return true;
                }
            }
            case "S" -> {
                if (contadorS < LIMITE_S) {
                    contadorS++;
                    pila.push(maleta);
                    return true;
                }
            }
            default -> throw new IllegalArgumentException(
                    "Categoría desconocida: " + maleta.categoriaTiquete()
            );
        }
        return false;
    }

    public Equipaje extraerTope() {
        Equipaje maleta = pila.pop();
        switch (maleta.categoriaTiquete()) {
            case "L" -> contadorL--;
            case "M" -> contadorM--;
            case "S" -> contadorS--;
        }
        return maleta;
    }

    public boolean estaVacia() {
        return pila.isEmpty();
    }

    public int size() {
        return pila.size();
    }

    @Override
    public String toString() {
        return pila.toString();
    }
}
