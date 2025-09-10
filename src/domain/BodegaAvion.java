package domain;

import datastructures.stack.Stack;
import services.InventarioEquipaje;

/**
 * La clase {@code BodegaAvion} representa la bodega de equipajes
 * de un avión, asociada a un destino específico. Utiliza una
 * {@link datastructures.stack.Stack} para almacenar los equipajes
 * de forma ordenada y un {@link InventarioEquipaje} para controlar
 * la disponibilidad de cupos según la categoría del tiquete.
 * Permite agregar y extraer equipajes respetando las restricciones
 * de capacidad del inventario.
 *
 * @see Equipaje
 * @see InventarioEquipaje
 */
public class BodegaAvion {

    /**
     * Pila que contiene los equipajes almacenados.
     */
    private final Stack<Equipaje> pila = new Stack<>();

    /**
     * Destino asociado a la bodega.
     */
    private final String destino;

    /**
     * Inventario que controla la capacidad disponible por categoría de tiquete.
     */
    private final InventarioEquipaje inventario = new InventarioEquipaje();

    /**
     * Construye una nueva {@code BodegaAvion} para un destino específico.
     *
     * @param destino ciudad destinó asociada a la bodega del avión
     */
    public BodegaAvion(String destino) {
        this.destino = destino;
    }

    /**
     * Devuelve el destino asociado a la bodega del avión.
     *
     * @return el destino como {@link String}
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Devuelve un <strong>iterable</strong> con los equipajes almacenados en la {@link Stack}.
     *
     * @return iterable de equipajes
     */
    public Iterable<Equipaje> getPasajeros() {
        return pila;
    }

    /**
     * Agrega un equipaje en el tope de la {@link Stack}, siempre que exista
     * disponibilidad en él {@link InventarioEquipaje} para la categoría del tiquete.
     *
     * @param equipaje equipaje a almacenar
     * @return {@code true} si el equipaje se agregó correctamente,
     * {@code false} si no hay cupo en la categoría
     * @see Stack#push(Object)
     * @see InventarioEquipaje#hayCupoPara(String)
     * @see InventarioEquipaje#incrementarContadores(String)
     */
    public boolean agregarEquipaje(Equipaje equipaje) {
        String categoriaTiquete = equipaje.categoriaTiquete();

        if (inventario.hayCupoPara(categoriaTiquete)) {
            pila.push(equipaje);
            inventario.incrementarContadores(equipaje.categoriaTiquete());
            return true;
        }

        return false;
    }

    /**
     * Extrae el equipaje ubicado en el tope de la {@link Stack} y actualiza
     * los contadores del {@link InventarioEquipaje}.
     *
     * @return equipaje extraído
     * @see Stack#pop()
     * @see InventarioEquipaje#decrementarContadores(String)
     */
    public Equipaje sacarTope() {
        Equipaje maleta = pila.pop();
        inventario.decrementarContadores(maleta.categoriaTiquete());
        return maleta;
    }

    /**
     * Verifica si la {@link Stack} está vacía.
     *
     * @return {@code true} si no hay ningún equipaje almacenado,
     * {@code false} si hay al menos un equipaje
     * @see Stack#isEmpty()
     */
    public boolean estaVacia() {
        return pila.isEmpty();
    }

    /**
     * Devuelve la cantidad de equipajes almacenados en la {@link Stack}.
     *
     * @return número de equipajes en la bodega
     * @see Stack#size()
     */
    public int size() {
        return pila.size();
    }

    /**
     * Representación de tipo cadena de texto de la bodega del avión.
     *
     * @return cadena de texto con los equipajes almacenados
     * @see Stack#toString()
     */
    @Override
    public String toString() {
        return pila.toString();
    }
}
