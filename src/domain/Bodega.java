package domain;

import datastructures.list.List;

/**
 * La clase {@code Bodega} representa un almacenamiento de equipajes
 * asociado a un destino, utilizando una {@link datastructures.list.List}
 * para su gestión. Posee métodos que permiten agregar, acceder y remover
 * datos dentro de la lista.
 *
 * @see Equipaje
 */
public class Bodega {

    /**
     * Lista que contiene los equipajes.
     */
    private final List<Equipaje> lista = new List<>();

    /**
     * Destino asociado a la bodega.
     */
    private final String destino;

    /**
     * Construye una nueva {@code Bodega} para un destino específico.
     *
     * @param destino ciudad destinó asociada a la bodega
     */
    public Bodega(String destino) {
        this.destino = destino;
    }

    /**
     * Agrega el equipaje en la última posición de la {@link List}.
     *
     * @param equipaje equipaje que se va a almacenar
     * @see List#addLast(Object)
     */
    public void agregarEquipaje(Equipaje equipaje) {
        lista.addLast(equipaje);
    }

    /**
     * Remueve el equipaje ubicado en la última posición de la {@link List}.
     *
     * @return el último equipaje
     * @see List#removeLast()
     */
    public Equipaje sacarUltimoEquipaje() {
        return lista.removeLast();
    }

    /**
     * Devuelve el destino asociado como cadena de texto.
     *
     * @return el destino como {@link String}
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Verifica si la {@link List} está vacía.
     *
     * @return {@code true} si no hay ningún dato guardado en la lista,
     * {@code false} si hay al menos un dato guardado
     * @see List#isEmpty()
     */
    public boolean estaVacia() {
        return lista.isEmpty();
    }

    /**
     * Devuelve la cantidad de datos guardados en la {@link List}.
     *
     * @return número de datos guardados en la lista
     * @see List#size()
     */
    public int size() {
        return lista.size();
    }

    /**
     * Representación de tipo cadena de texto de la bodega.
     *
     * @return cadena de texto con los equipajes almacenados
     * @see List#toString()
     */
    @Override
    public String toString() {
        return lista.toString();
    }
}
