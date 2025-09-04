package util;

import datastructures.list.List;
import luggage.Equipaje;

public class QuickLuggageSorting {

    private static int prioridad(Equipaje maleta) {
        // Convertimos a mayúscula la "categoria del tiquete".
        String categoriaTiquete = maleta.categoriaTiquete();
        return switch (categoriaTiquete) {
            case "L" -> 1;
            case "M" -> 2;
            case "S" -> 3;
            default -> 0;
        };
    }

    /**
     * Método público para ordenar una lista de equipajes usando QuickSort.
     * El usuario solo pasa la lista; los índices se manejan internamente.
     */
    public static void quickSort(List<Equipaje> lista) {
        if (lista == null || lista.isEmpty()) return;
        quickSort(lista, 0, lista.size() - 1);
    }

    /**
     * Implementación recursiva de QuickSort.
     */
    private static void quickSort(List<Equipaje> lista, int inicio, int fin) {
        if (inicio < fin) {
            Equipaje pivote = lista.get(fin);
            int i = inicio - 1;

            for (int j = inicio; j < fin; j++) {
                int prioridadJ = prioridad(lista.get(j));
                int prioridadPivote = prioridad(pivote);

                if (prioridadJ >= prioridadPivote) {
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

            // Recursividad
            quickSort(lista, inicio, indiceParticion - 1);
            quickSort(lista, indiceParticion + 1, fin);
        }
    }
}
