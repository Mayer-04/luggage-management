package luggage;

import datastructures.queue.Queue;

public class ColaGeneral {

    private final Queue<Equipaje> cola = new Queue<>();

    public void registrarEquipaje(Equipaje maleta) {
        cola.enqueue(maleta);
    }

    public Equipaje sacarEquipaje() {
        return cola.dequeue();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int size() { return cola.size(); }

    @Override
    public String toString() {
        return cola.toString();
    }
}
