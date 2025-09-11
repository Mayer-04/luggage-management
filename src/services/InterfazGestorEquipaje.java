package services;

import datastructures.list.List;
import domain.Bodega;
import domain.BodegaAvion;
import domain.ColaGeneral;
import domain.Equipaje;
import util.Constantes;
import util.Estadisticas;
import util.LuggageJsonReader;
import util.Validacion;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Clase que gestiona la interacción con el usuario a través de la consola
 * para registrar, procesar y administrar equipajes.
 * <p>
 * Esta clase actúa como capa de interfaz: recibe entradas del usuario
 * (mediante {@link Scanner}), muestra mensajes en consola y delega la
 * lógica de negocio al servicio {@link GestorEquipaje}.
 * </p>
 */
public class InterfazGestorEquipaje {

    private final Scanner scanner;
    private final GestorEquipaje gestor;

    /**
     * Constructor de la interfaz de gestión de equipaje.
     *
     * @param sc objeto {@link Scanner} para leer entradas desde consola
     */
    public InterfazGestorEquipaje(Scanner sc) {
        this.scanner = sc;
        this.gestor = new GestorEquipaje();
    }

    /**
     * Solicita al usuario los datos de un equipaje y válida su información.
     * <p>
     * El proceso incluye:
     * <ul>
     *   <li>Lectura del nombre del pasajero.</li>
     *   <li>Validación del destino frente a los destinos soportados en {@link Constantes#DESTINOS}.</li>
     *   <li>Validación de la categoría del tiquete mediante {@link Validacion}.</li>
     *   <li>Conversión y validación del peso como número entero.</li>
     * </ul>
     * Si alguno de los datos es inválido, se devuelve {@code null}.
     *
     * @return un nuevo objeto {@link Equipaje} si los datos son válidos; {@code null} en caso contrario
     */
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
            System.out.println("Verifica que esté escrito exactamente igual a los destinos dados.");
            return null;
        }

        System.out.print("Tiquete (L, M, S): ");
        String categoriaTiquete = scanner.nextLine().trim().toUpperCase();

        if (!Validacion.esCategoriaValida(categoriaTiquete)) {
            System.out.println("Categoría de tiquete inválida.");
            return null;
        }

        System.out.print("Ingresa el peso del equipaje (sin puntos ni comas): ");
        int peso;
        try {
            peso = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Peso inválido.");
            return null;
        }

        return new Equipaje(nombrePasajero, destino, categoriaTiquete, peso);
    }

    /**
     * Registra un equipaje en la cola general a partir de los datos ingresados por el usuario.
     * <p>
     * Si la validación falla, se informa al usuario que no fue posible registrar el equipaje.
     * </p>
     */
    public void registrarEquipaje() {
        Equipaje equipaje = obtenerEquipaje();

        if (equipaje == null) {
            System.out.println("No se pudo registrar el equipaje.");
            return;
        }

        gestor.registrarEquipaje(equipaje);
        System.out.println("El equipaje se registró con éxito.");
    }

    /**
     * Registra múltiples equipajes leyendo un archivo JSON de recursos.
     * <p>
     * Los archivos disponibles son {@code luggage_300.json}, {@code luggage_500.json},
     * {@code luggage_700.json} y {@code luggage_1000.json}, ubicados en la carpeta
     * {@code ./src/resources/}.
     * </p>
     * <p>
     * Cada equipaje cargado se inserta en la {@link ColaGeneral}, mostrando
     * al final la cantidad registrada.
     * </p>
     */
    public void registrarMultiplesEquipajes() {
        System.out.print("Elige la cantidad de equipajes a registrar (300, 500, 700, 1000): ");
        String opcion = scanner.nextLine().trim();

        String rutaArchivo = "./src/resources/luggage_" + opcion + ".json";
        Path archivo = Path.of(rutaArchivo);

        if (!Files.exists(archivo)) {
            System.out.printf("El archivo %s no existe. Intenta con 300, 500, 700 o 1000.%n%n", rutaArchivo);
            return;
        }

        var lector = new LuggageJsonReader(rutaArchivo);
        java.util.List<Equipaje> equipajes = lector.cargarDatos();

        int cantidadAntes = gestor.getColaGeneral().size();

        for (Equipaje l : equipajes) {
            gestor.registrarEquipaje(l);
        }

        int cantidadDespues = gestor.getColaGeneral().size();

        System.out.println("Se registraron " + equipajes.size() + " equipajes en la cola general.");
        System.out.println("Cantidad antes: " + cantidadAntes + " | Ahora: " + cantidadDespues + "\n");
    }

    /**
     * Procesa todos los equipajes de la cola general, distribuyéndolos
     * en las bodegas correspondientes según su destino.
     * <p>
     * Internamente utiliza el método {@link Bodegas#procesarEquipaje(ColaGeneral, Bodega[])}.
     * </p>
     */
    public void procesarEquipajes() {
        ColaGeneral cola = gestor.getColaGeneral();

        if (cola.estaVacia()) {
            System.out.println("No hay equipajes en la cola general.");
            System.out.println("Primero debes registrar equipajes usando la opción 1 o 2 del menú.\n");
            return;
        }

        gestor.procesarEquipajes();
        System.out.println("Los equipajes fueron procesados y enviados a sus bodegas correctamente.");
    }

    /**
     * Intenta abordar los vuelos con los equipajes disponibles en las bodegas.
     * <p>
     * El proceso incluye:
     * <ul>
     *   <li>Verificar si existen equipajes listos con {@link Avion#verificarEquipajesParaAbordar(Bodega[])}.</li>
     *   <li>Distribuir los equipajes con {@link Avion#abordarVuelo(Bodega[], BodegaAvion[])}.</li>
     *   <li>Verificar que cada vuelo cumpla un mínimo de 50 equipajes.</li>
     *   <li>Mostrar un resumen de los equipajes no abordados y los que fueron cargados en cada vuelo.</li>
     * </ul>
     */
    public void abordarVuelo() {
        if (!Avion.verificarEquipajesParaAbordar(gestor.getBodegas())) {
            System.out.println("No hay equipajes en las bodegas para abordar el vuelo.\n");
            return;
        }

        List<Equipaje> noAbordados = gestor.abordarVuelo();

        if (!Avion.verificarMinimoPorVuelo(gestor.getBodegasAvion(), 50)) {
            System.out.println("No se puede despegar: algún vuelo no cumple con el mínimo de 50 equipajes.\n");
            return;
        }

        Validacion.mostrarResumenNoAbordadas(noAbordados);

        System.out.println("Resumen de equipajes abordados por vuelo:");
        for (BodegaAvion vuelo : gestor.getBodegasAvion()) {
            int totalEquipajes = vuelo.size();
            System.out.printf("✈️ Vuelo destino: %s | Equipajes abordados: %d%n",
                    vuelo.getDestino(), totalEquipajes);
        }
    }

    /**
     * Desembarca todos los vuelos, mostrando las estadísticas finales
     * de cada uno.
     * <p>
     * Para cada vuelo se indica:
     * <ul>
     *   <li>Cantidad total de equipajes desembarcados.</li>
     *   <li>Peso total transportado.</li>
     * </ul>
     * Al finalizar, se confirma que todos los pasajeros llegaron a su destino.
     * </p>
     */
    public void desembarcarVuelo() {
        boolean vuelosVacios = true;
        for (BodegaAvion vuelo : gestor.getBodegasAvion()) {
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

        var estadisticasFinales = gestor.desembarcarVuelo();

        int i = 0;
        for (BodegaAvion vuelo : gestor.getBodegasAvion()) {
            int[] stats = estadisticasFinales.get(i++);
            System.out.printf("✈️ Vuelo destino: %s | Equipajes desembarcados: %d | Peso total: %d kg%n",
                    vuelo.getDestino(), stats[0], stats[1]);
        }

        System.out.println("✅ Todos los vuelos han desembarcado. Los pasajeros han llegado a su destino correspondiente.\n");
    }

    /**
     * Muestra la lista completa de pasajeros registrados en todos los vuelos.
     * <p>
     * Internamente utiliza {@link Estadisticas#mostrarListaDePasajeros(BodegaAvion[])}.
     * </p>
     */
    public void mostrarListaPasajeros() {
        Estadisticas.mostrarListaDePasajeros(gestor.getBodegasAvion());
    }

    /**
     * Muestra estadísticas generales de los vuelos y sus equipajes.
     * <p>
     * Internamente utiliza {@link Estadisticas#mostrarEstadisticas(BodegaAvion[])}.
     * </p>
     */
    public void mostrarEstadisticas() {
        Estadisticas.mostrarEstadisticas(gestor.getBodegasAvion());
    }
}
