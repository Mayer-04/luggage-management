import domain.Bodega;
import domain.BodegaAvion;
import domain.ColaGeneral;
import domain.Equipaje;
import services.Avion;
import services.Bodegas;
import services.ColeccionBodegas;
import services.Estadisticas;
import util.LuggageJsonReader;

import java.util.List;
import java.util.Scanner;

public class InterfazGestorEquipaje {

    private final ColaGeneral colaGeneral;
    private final Scanner scanner;
    private final Bodega[] bodegas;
    private final BodegaAvion[] bodegasAvion;

    public InterfazGestorEquipaje(Scanner sc) {
        this.colaGeneral = new ColaGeneral();
        this.scanner = sc;
        this.bodegas = ColeccionBodegas.obtenerBodegasDeEntrada();
        this.bodegasAvion = ColeccionBodegas.obtenerBodegasDeAviones();
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

    public void registrarMultiplesEquipajes() {
        var lector = new LuggageJsonReader("./src/resources/luggage_500.json");
        List<Equipaje> maletas = lector.cargarDatos();

        int antes = colaGeneral.size();

        // Agregamos cada maleta del JSON a la cola general
        for (Equipaje l : maletas) {
            colaGeneral.registrarEquipaje(l);
        }

        int despues = colaGeneral.size();

        System.out.println("Se registraron " + maletas.size() + " equipajes en la cola general.");
        System.out.println("Cantidad antes: " + antes + " | Ahora: " + despues);
    }

    public void procesarEquipajes() {
        if (colaGeneral.estaVacia()) {
            System.out.println("⚠️ No hay equipajes en la cola general.");
            System.out.println("Primero debes registrar equipajes usando la opción 1 o 2 del menú.");
            return;
        }

        // Procesamos los equipajes de la cola general hacia sus bodegas
        Bodegas.procesarEquipaje(colaGeneral, bodegas);

        System.out.println("Equipajes procesados y enviados a sus bodegas exitosamente.");
    }


    public void abordarVuelo() {
        // Validación 1: nunca se procesaron equipajes
        if (bodegas == null) {
            System.out.println("⚠️ Debes procesar los equipajes (opción 3) antes de abordar el vuelo.");
            return;
        }

        // Validación 2: bodegas procesadas, pero vacías
        if (!Avion.hayEquipajesParaAbordar(bodegas)) {
            System.out.println("⚠️ No hay equipajes en las bodegas para abordar el vuelo.");
            System.out.println("Usa primero la opción 1 o 2 para registrar equipajes y luego la opción 3 para procesarlos.");
            return;
        }

        try {
            Avion.abordarVuelo(bodegas, bodegasAvion);
            System.out.println("✈️ Los pasajeros y su equipaje han sido abordados exitosamente.");
        } catch (IllegalStateException e) {
            System.out.println("Error al abordar vuelo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado al abordar vuelo: " + e.getMessage());
        }
    }

    public void desembarcarVuelo() {
        // Validación 1: no hay vuelos creados
        if (bodegasAvion == null) {
            System.out.println("⚠️ No hay vuelos abordados para desembarcar. Use la opción 4 primero.");
            return;
        }

        // Validación 2: vuelos creados pero vacíos
        boolean vuelosVacios = true;
        for (BodegaAvion vuelo : bodegasAvion) {
            if (!vuelo.estaVacia()) {
                vuelosVacios = false;
                break;
            }
        }

        if (vuelosVacios) {
            System.out.println("⚠️ No hay equipajes en los vuelos para desembarcar.");
            System.out.println("Usa la opción 1 o 2 para registrar equipajes, la opción 3 para procesarlos y la opción 4 para abordar.");
            return;
        }

        // Si pasa las validaciones, desembarcamos
        try {
            Avion.desembarcarVuelo(bodegasAvion);
            System.out.println("Los pasajeros llegaron a su respectivo destino...");
        } catch (Exception e) {
            System.out.println("Error al desembarcar vuelo: " + e.getMessage());
        }
    }


    public void mostrarListaPasajeros() {
        if (bodegasAvion == null) {
            System.out.println("⚠️ No hay vuelos abordados. Use la opción 4 primero.");
            return;
        }

        Estadisticas.mostrarListaDePasajeros(bodegasAvion);
    }

    public void mostrarEstadisticas() {
        if (bodegasAvion == null) {
            System.out.println("⚠️ No hay vuelos abordados. Use la opción 4 primero.");
            return;
        }

        Estadisticas.mostrarEstadistica(bodegasAvion);

        System.out.println("📊 Se han mostrado las estadísticas. Los vuelos quedaron vacíos.");
    }
}
