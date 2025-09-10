package domain;

import datastructures.queue.Queue;

/**
 * La clase {@code ColaGeneral} representa una cola de equipajes
 * utilizando una {@link datastructures.queue.Queue} para su gestión.
 * Permite registrar, extraer y consultar el estado de los equipajes
 * almacenados en la cola.
 *
 * @see Equipaje
 */
public class ColaGeneral {

    /**
     * Cola que contiene los equipajes.
     */
    private final Queue<Equipaje> cola = new Queue<>();

    /**
     * Registra un equipaje en la última posición de la {@link Queue}.
     *
     * @param equipaje equipaje que se va a almacenar
     * @see Queue#enqueue(Object)
     */
    public void registrarEquipaje(Equipaje equipaje) {
        cola.enqueue(equipaje);
    }

    /**
     * Extrae el equipaje ubicado en la primera posición de la {@link Queue}.
     *
     * @return equipaje extraído
     * @see Queue#dequeue()
     */
    public Equipaje sacarEquipaje() {
        return cola.dequeue();
    }

    /**
     * Verifica si la {@link Queue} está vacía.
     *
     * @return {@code true} si no hay ningún dato guardado en la cola,
     * {@code false} si hay al menos un dato guardado
     * @see Queue#isEmpty()
     */
    public boolean estaVacia() {
        return cola.isEmpty();
    }

    /**
     * Devuelve la cantidad de datos guardados en la {@link Queue}.
     *
     * @return número de datos guardados en la cola
     * @see Queue#size()
     */
    public int size() {
        return cola.size();
    }

    /**
     * Representación de tipo cadena de texto de la cola.
     *
     * @return cadena de texto con los equipajes almacenados
     * @see Queue#toString()
     */
    @Override
    public String toString() {
        return cola.toString();
    }
}
