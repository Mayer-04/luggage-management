import datastructures.list.List;
import domain.Bodega;
import domain.BodegaAvion;
import domain.ColaGeneral;
import domain.Equipaje;
import services.Avion;
import services.Bodegas;
import services.ColeccionBodegas;
import services.Estadisticas;
import util.Constantes;
import util.LuggageJsonReader;

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

        System.out.print("Ingresa el nombre del pasajero: ");
        String nombrePasajero = scanner.nextLine();

        System.out.print("Ingresa el destino (Bogotá, Medellín, Cali, Bucaramanga o Barranquilla): ");
        String destino = scanner.nextLine();

        boolean destinoValido = false;
        for (String d : Constantes.DESTINOS) {
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
            System.err.println("Peso inválido.");
            return null;
        }

        return new Equipaje(nombrePasajero, destino, categoriaTiquete, peso);
    }

    public void registrarEquipaje() {
        var equipaje = pedirMaleta();

        if (equipaje != null) {
            colaGeneral.registrarEquipaje(equipaje);
            System.out.println("Equipaje registrado con éxito.");
            System.out.println("Equipaje: " + equipaje);
        } else {
            System.out.println("No fue posible registrar el equipaje.");
        }
    }

    public void registrarMultiplesEquipajes() {
        var lector = new LuggageJsonReader("./src/resources/luggage_700.json");
        java.util.List<Equipaje> maletas = lector.cargarDatos();

        int antes = colaGeneral.size();

        for (Equipaje l : maletas) {
            colaGeneral.registrarEquipaje(l);
        }

        int despues = colaGeneral.size();

        System.out.println("Se registraron " + maletas.size() + " equipajes en la cola general.");
        System.out.println("Cantidad antes: " + antes + " | Ahora: " + despues + "\n");
    }

    public void procesarEquipajes() {
        if (colaGeneral.estaVacia()) {
            System.out.println("No hay equipajes en la cola general.");
            System.out.println("Primero debes registrar equipajes usando la opción 1 o 2 del menú.\n");
            return;
        }

        Bodegas.procesarEquipaje(colaGeneral, bodegas);
        System.out.println("Los equipajes fueron procesados y enviados a sus bodegas correctamente.");
    }

    public void abordarVuelo() {
        if (!Avion.hayEquipajesParaAbordar(bodegas)) {
            System.out.println("No hay equipajes en las bodegas para abordar el vuelo.");
            return;
        }

        // Intentamos abordar los vuelos, obtenemos las maletas que no pudieron subirse
        List<Equipaje> noAbordadas = Avion.abordarVuelo(bodegas, bodegasAvion);

        // Mostrar mensaje general de maletas omitidas
        if (!noAbordadas.isEmpty()) {
            System.out.println("⚠️ Algunas maletas no pudieron abordarse por límite de categoría y serán omitidas.");
        }

        // Mostrar resumen por bodega de avión
        System.out.println("\nResumen de equipajes abordados por vuelo:");
        for (BodegaAvion vuelo : bodegasAvion) {
            int totalEquipajes = vuelo.size();
            System.out.printf("✈️ Vuelo destino: %s | Equipajes abordados: %d%n",
                    vuelo.getDestino(), totalEquipajes);
        }
    }

    public void mostrarContenidoBodegasAvion() {
        for (BodegaAvion vuelo : bodegasAvion) {
            System.out.println("✈️ Vuelo destino: " + vuelo.getDestino());
            int i = 1;
            for (Equipaje maleta : vuelo.getPasajeros()) {
                System.out.printf("  %d. Pasajero: %s | Categoría: %s | Peso: %d%n",
                        i++, maleta.pasajero(), maleta.categoriaTiquete(), maleta.peso());
            }
            System.out.println("Total equipajes: " + vuelo.size());
            System.out.println("-----------------------------");
        }
    }

    public void desembarcarVuelo() {
        boolean vuelosVacios = true;
        for (BodegaAvion vuelo : bodegasAvion) {
            if (!vuelo.estaVacia()) {
                vuelosVacios = false;
                break;
            }
        }

        if (vuelosVacios) {
            System.out.println("No hay equipajes en los vuelos para desembarcar.");
            System.out.println("Usa la opción 1 o 2 para registrar equipajes, luego la opción 3 para procesarlos y finalmente la opción 4 para abordar.\n");
            return;
        }

        Avion.desembarcarVuelo(bodegasAvion);
        System.out.println("Los pasajeros han llegado a su destino correspondiente.");
    }

    public void mostrarListaPasajeros() {
        Estadisticas.mostrarListaDePasajeros(bodegasAvion);
    }

    public void mostrarEstadisticas() {
        Estadisticas.mostrarEstadistica(bodegasAvion);
    }
}
