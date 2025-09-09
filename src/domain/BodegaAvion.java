package domain;

import datastructures.stack.Stack;
import services.InventarioEquipaje;

public class BodegaAvion {
    private final Stack<Equipaje> pila = new Stack<>();
    private final String destino;
    private final InventarioEquipaje inventario = new InventarioEquipaje();

    public BodegaAvion(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public Iterable<Equipaje> getPasajeros() {
        return pila;
    }

    public boolean agregarEquipaje(Equipaje maleta) {
        String categoriaTiquete = maleta.categoriaTiquete();

        if (inventario.hayCupoPara(categoriaTiquete)) {
            pila.push(maleta);
            inventario.incrementarContadores(maleta.categoriaTiquete());
            return true;
        }
        return false;
    }

    public Equipaje sacarTope() {
        Equipaje maleta = pila.pop();
        inventario.decrementarContadores(maleta.categoriaTiquete());
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
