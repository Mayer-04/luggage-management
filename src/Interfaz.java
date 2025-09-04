import luggage.Bodega;
import luggage.BodegaAvion;
import luggage.ColaGeneral;
import luggage.Equipaje;
import util.Avion;
import util.Bodegas;
import util.Estadisticas;
import util.LuggageJsonReader;

import java.util.List;
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

        System.out.print("Ingresa el destino (Bogotá, Medellín, Cali, Bucaramanga o Barranquilla): ");
        String destino = scanner.nextLine();

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

        System.out.print("Ingrese su tiquete (L, M, S): ");
        String categoriaTiquete = scanner.nextLine().trim().toUpperCase();

        System.out.print("Ingrese el peso del equipaje (sin puntos ni comas): ");
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
            System.out.println("Equipaje registrado con éxito.");
            System.out.println("Maleta registrada: " + maleta);
        } else {
            System.out.println("No se pudo registrar el equipaje.");
        }
    }

    public void registrarMultiplesEquipajes(ColaGeneral colaGeneral) {
        var lector = new LuggageJsonReader("luggage_500.json");
        List<Equipaje> maletas = lector.cargarDatos();
        // Agregamos cada maleta del JSON a la cola general
        for (Equipaje l : maletas) {
            colaGeneral.registrarEquipaje(l);
        }

        System.out.println("Agregadas los 500 equipajes con éxito");
    }

    public void procesarEquipajes(Bodega[] bodegas) {
        // Procesamos los equipajes de la cola general hacia sus bodegas
        Bodegas.procesarEquipaje(colaGeneral, bodegas);

        System.out.println("✅ Equipajes procesados y enviados a sus bodegas exitosamente.");
    }


    public void abordarVuelo(Bodega[] bodegas, BodegaAvion[] vuelos) {
        if (bodegas == null) {
            System.out.println("Debes procesar los equipajes (opción 3) antes de abordar el vuelo.");
            return;
        }

        try {
            Avion.abordarVuelo(bodegas, vuelos);
            System.out.println("Los pasajeros han sido abordados exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al abordar vuelo: " + e.getMessage());
        }
    }

    public void desembarcarVuelo(BodegaAvion[] vuelos) {
        if (vuelos == null) {
            System.out.println("⚠️ No hay vuelos abordados para desembarcar. Use la opción 4 primero.");
            return;
        }

        try {
            Avion.desembarcarVuelo(vuelos);
            System.out.println("🛄 Los pasajeros llegaron a su respectivo destino...");
            // Reiniciamos los vuelos porque ya fueron despachados
            vuelos = null;
        } catch (Exception e) {
            System.out.println("❌ Error al desembarcar vuelo: " + e.getMessage());
        }
    }

    public void mostrarListaPasajeros(BodegaAvion[] vuelos) {
        if (vuelos == null) {
            System.out.println("⚠️ No hay vuelos abordados. Use la opción 4 primero.");
            return;
        }

        Estadisticas.mostrarListaDePasajeros(vuelos);
    }

    public void mostrarEstadisticas(BodegaAvion[] vuelos) {
        if (vuelos == null) {
            System.out.println("⚠️ No hay vuelos abordados. Use la opción 4 primero.");
            return;
        }

        Estadisticas.mostrarEstadistica(vuelos);

        // Después de mostrar estadísticas los vuelos ya fueron despachados
        vuelos = null;
        System.out.println("📊 Se han mostrado las estadísticas. Los vuelos quedaron vacíos.");
    }
}
