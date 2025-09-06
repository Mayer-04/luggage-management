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

    public InventarioEquipaje getInventario() {
        return inventario;
    }

    public boolean agregarEquipaje(Equipaje maleta) {
        if (!inventario.quedaCupoPara(maleta.categoriaTiquete())) {
            return false;
        }
        inventario.incrementarContadores(maleta.categoriaTiquete());
        pila.push(maleta);
        return true;
    }

    public Equipaje extraerTope() {
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
