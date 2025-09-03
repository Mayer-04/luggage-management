import luggage.ColaGeneral;
import luggage.Equipaje;

import java.util.Scanner;

public class Interfaz {

    private final ColaGeneral colaGeneral;
    private final Scanner scanner;

    public Interfaz(ColaGeneral colaGeneral, Scanner sc) {
        this.colaGeneral = colaGeneral;
        this.scanner = sc;
    }

    public Equipaje pedirMaleta() {
        String[] destinos = {"Bogotá", "Medellín", "Cali", "Bucaramanga", "Barranquilla"};

        System.out.print("Ingresa el nombre del pasajero: ");
        String nombrePasajero = scanner.nextLine();

        System.out.println("Ingresa el destino (Bogotá, Medellín, Cali, Bucaramanga o Barranquilla): ");
        String destino = scanner.nextLine();

        // Validar destino
        boolean destinoValido = false;
        for (String d : destinos) {
            if (d.equals(destino)) {
                destinoValido = true;
                break;
            }
        }

        if (!destinoValido) {
            System.out.println("Destino inválido.");
            return null;
        }

        System.out.println("Ingrese su tiquete (L, M, S): ");
        String categoriaTiquete = scanner.nextLine().trim().toUpperCase();

        System.out.println("Ingrese el peso del equipaje (sin puntos ni comas): ");
        int peso;
        try {
            peso = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Peso inválido.");
            return null;
        }

        return new Equipaje(nombrePasajero, destino, categoriaTiquete, peso);
    }

    public void registrarEquipaje() {
        var maleta = pedirMaleta();
        if (maleta != null) {
            colaGeneral.registrarEquipaje(maleta);
            System.out.println("Equipaje registrado.");
        } else {
            System.out.println("No se registró el equipaje.");
        }
    }

}
