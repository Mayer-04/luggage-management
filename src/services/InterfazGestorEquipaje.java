package services;

import datastructures.list.List;
import domain.Bodega;
import domain.BodegaAvion;
import domain.ColaGeneral;
import domain.Equipaje;
import util.Constantes;
import util.LuggageJsonReader;
import util.Validacion;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
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

        public Equipaje obtenerEquipaje() {
        System.out.print("Nombre del pasajero: ");
        String nombrePasajero = scanner.nextLine();

        System.out.print("Destino (Bogotá, Medellín, Cali, Bucaramanga o Barranquilla): ");
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
            System.out.println("Verifica que esté escrito exactamente igual a los destinos dados o que sea correcto.");
            return null;
        }

        System.out.print("Tiquete (L, M, S): ");
        String categoriaTiquete = scanner.nextLine().trim().toUpperCase();

        boolean categoriaValida = Validacion.esCategoriaValida(categoriaTiquete);
        if (!categoriaValida) {
            System.out.println("Categoría de tiquete inválida.");
            return null;
        }

        System.out.print("Peso del equipaje: ");
        int peso;
        try {
            peso = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Peso inválido");
            return null;
        }

        return new Equipaje(nombrePasajero, destino, categoriaTiquete, peso);
    }

    public void registrarEquipaje() {
        Equipaje equipaje = obtenerEquipaje();

        if (equipaje == null) {
            System.out.println("No se pudo registrar el equipaje.");
            return;
        }

        colaGeneral.registrarEquipaje(equipaje);
        System.out.println("El equipaje se registró con éxito.");
    }

    public void registrarMultiplesEquipajes() {
        System.out.print("Elige la cantidad de equipajes a registrar (300, 500, 700, 1000): ");
        String opcion = scanner.nextLine().trim();

        String rutaArchivo = "./src/resources/luggage_" + opcion + ".json";
        Path archivo = Path.of(rutaArchivo);

        if (!Files.exists(archivo)) {
            System.out.printf("El archivo %s no existe. Intenta con 300, 500, 700 o 1000.%n", rutaArchivo);
            return;
        }

        var lector = new LuggageJsonReader(rutaArchivo);
        java.util.List<Equipaje> equipajes = lector.cargarDatos();

        int cantidadAntes = colaGeneral.size();

        for (Equipaje l : equipajes) {
            colaGeneral.registrarEquipaje(l);
        }

        int cantidadDespues = colaGeneral.size();

        System.out.println("Se registraron " + equipajes.size() + " equipajes en la cola general.");
        System.out.println("Cantidad antes: " + cantidadAntes + " | Ahora: " + cantidadDespues + "\n");
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
        if (!Avion.verificarEquipajesParaAbordar(bodegas)) {
            System.out.println("No hay equipajes en las bodegas para abordar el vuelo.");
            return;
        }

        List<Equipaje> noAbordados = Avion.abordarVuelo(bodegas, bodegasAvion);

        if (!Avion.verificarMinimoPorVuelo(bodegasAvion, 50)) {
            System.out.println("No se puede despegar: algún vuelo no cumple con el mínimo de 50 equipajes.");
            return;
        }

        Validacion.mostrarResumenNoAbordadas(noAbordados);

        System.out.println("\nResumen de equipajes abordados por vuelo:");
        for (BodegaAvion vuelo : bodegasAvion) {
            int totalEquipajes = vuelo.size();
            System.out.printf("✈️ Vuelo destino: %s | Equipajes abordados: %d%n",
                    vuelo.getDestino(), totalEquipajes);
        }
    }

    public void mostrarContenidoBodegasAvion() {
        for (BodegaAvion vuelo : bodegasAvion) {
            System.out.println("Vuelo destino: " + vuelo.getDestino());
            int numeroDeMaleta = 1;
            for (Equipaje maleta : vuelo.getPasajeros()) {
                System.out.printf("  %d. Pasajero: %s | Categoría: %s | Peso: %d kg%n",
                        numeroDeMaleta++, maleta.pasajero(), maleta.categoriaTiquete(), maleta.peso());
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
        Estadisticas.mostrarEstadisticas(bodegasAvion);
    }
}
