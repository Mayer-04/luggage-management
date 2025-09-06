package domain;

import datastructures.list.List;

public class Bodega {
    private final List<Equipaje> lista = new List<>();
    private final String destino;

    public Bodega(String destino) {
        this.destino = destino;
    }

    public void agregarEquipaje(Equipaje maleta) {
        lista.addLast(maleta);
    }

    public Equipaje sacarUltimoEquipaje() {
        return lista.removeLast();
    }

    public String getDestino() {
        return destino;
    }

    public boolean estaVacia() {
        return lista.isEmpty();
    }

    public int size() {
        return lista.size();
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
