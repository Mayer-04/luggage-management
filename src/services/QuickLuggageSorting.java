package services;

import datastructures.list.List;
import domain.Equipaje;

/**
 * La clase {@code QuickLuggageSorting} implementa el algoritmo de
 * ordenamiento <strong>QuickSort</strong> para organizar equipajes en función de
 * la categoría del tiquete.
 * Las categorías se convierten en prioridades numéricas:
 * <ul>
 *   <li>{@code "L"} → 3 (alta prioridad)</li>
 *   <li>{@code "M"} → 2 (media prioridad)</li>
 *   <li>{@code "S"} → 1 (baja prioridad)</li>
 *   <li>Cualquier otra → 0</li>
 * </ul>
 * <p>
 * El orden resultante de la {@link List} coloca primero los equipajes
 * con menor prioridad numérica.
 *
 * @see Equipaje
 * @see List
 */
public class QuickLuggageSorting {

    /**
     * Determina la prioridad numérica de un {@link Equipaje} en función
     * de su categoría de tiquete.
     *
     * @param equipaje equipaje a evaluar
     * @return número que representa la prioridad
     */
    private static int prioridad(Equipaje equipaje) {
        String categoriaTiquete = equipaje.categoriaTiquete();
        return switch (categoriaTiquete) {
            case "L" -> 3;
            case "M" -> 2;
            case "S" -> 1;
            default -> 0;
        };
    }

    /**
     * Ordena una {@link List} de {@link Equipaje} utilizando el algoritmo QuickSort.
     * El usuario solo debe pasar la lista, ya que los índices se manejan internamente.
     *
     * @param lista lista de equipajes a ordenar
     * @see #quickSort(List, int, int)
     */
    public static void quickSort(List<Equipaje> lista) {
        if (lista == null || lista.isEmpty()) return;
        quickSort(lista, 0, lista.size() - 1);
    }

    /**
     * Implementación recursiva del algoritmo QuickSort para ordenar
     * una {@link List} de {@link Equipaje}.
     *
     * @param lista  lista de equipajes a ordenar
     * @param inicio índice inicial de la partición
     * @param fin    índice final de la partición
     */
    private static void quickSort(List<Equipaje> lista, int inicio, int fin) {
        if (inicio < fin) {
            Equipaje pivote = lista.get(fin);
            int i = inicio - 1;

            for (int j = inicio; j < fin; j++) {
                int prioridadJ = prioridad(lista.get(j));
                int prioridadPivote = prioridad(pivote);

                if (prioridadJ <= prioridadPivote) {
                    i++;
                    Equipaje temp = lista.get(i);
                    lista.set(i, lista.get(j));
                    lista.set(j, temp);
                }
            }

            Equipaje temp = lista.get(i + 1);
            lista.set(i + 1, lista.get(fin));
            lista.set(fin, temp);

            int indiceParticion = i + 1;

            quickSort(lista, inicio, indiceParticion - 1);
            quickSort(lista, indiceParticion + 1, fin);
        }
    }
}
