package pruebas;

import luggage.Bodega;
import luggage.BodegaAvion;
import luggage.ColaGeneral;
import luggage.Equipaje;
import util.Avion;
import util.Bodegas;
import util.Estadisticas;
import util.LuggageJsonReader;

import java.util.List;

public class Pruebas {

    public static void main(String[] args) {

        // Registrar equipajes en la cola general
        var colaGeneral = new ColaGeneral();

        // Instancia de leerJSON
        var lector = new LuggageJsonReader("luggage_500.json");
        List<Equipaje> maletas = lector.cargarDatos();
        // Agregamos cada maleta del JSON a la cola general
        for (Equipaje l : maletas) {
            colaGeneral.registrarEquipaje(l);
        }

        System.out.printf("Cola general inicial: %s\n", colaGeneral);
        System.out.println("Número de elementos en cola general: " + colaGeneral.size());

        // Crear bodegas de entrada dependiendo su destino
        var bogota = new Bodega("Bogotá");
        var medellin = new Bodega("Medellín");
        var cali = new Bodega("Cali");
        var bucaramanga = new Bodega("Bucaramanga");
        var barranquilla = new Bodega("Barranquilla");

        // Creando un arreglo con las diferentes bodegas dependiendo su destino
        Bodega[] bodegasDeEntrada = {bogota, cali, medellin, bucaramanga, barranquilla};

        // Procesar los equipajes desde la cola general hacia las bodegas de entrada
        Bodegas.procesarEquipaje(colaGeneral, bodegasDeEntrada);

        // Crear bodegas de avión con sus diferentes destinos
        var avionBogota = new BodegaAvion("Bogotá");
        var avionMedellin = new BodegaAvion("Medellín");
        var avionCali = new BodegaAvion("Cali");
        var avionBucaramanga = new BodegaAvion("Bucaramanga");
        var avionBarranquilla = new BodegaAvion("Barranquilla");

        // Creando un arreglo con las diferentes bodegas de Aviones dependiendo su destino
        BodegaAvion[] aviones = {
                avionBogota, avionMedellin, avionCali, avionBucaramanga, avionBarranquilla
        };

        // Distribuir equipajes a los aviones según destino
        Avion.abordarVuelo(bodegasDeEntrada, aviones);

        // Imprimir las bodegas de Avión
        System.out.printf("avión Bogotá: %s%n tamaño es: %d%n", avionBogota, avionBogota.size());
        System.out.printf("avión Medellín: %s\n y su tamaño es: %d\n", avionMedellin, avionMedellin.size());
        System.out.printf("avión Cali: %s\n y su tamaño es: %d\n", avionCali, avionCali.size());
        System.out.printf("avión Bucaramanga: %s%n tamaño es: %d%n", avionBucaramanga, avionBucaramanga.size());
        System.out.printf("avión Barranquilla: %s\n y su tamaño es: %d\n", avionBarranquilla, avionBarranquilla.size());


        Estadisticas.mostrarListaDePasajeros(aviones);
        Estadisticas.mostrarEstadistica(aviones);


        System.out.println("avión Bogotá " + avionBogota.size());
        System.out.println("avión Medellín " + avionMedellin.size());
        System.out.println("avión Cali " + avionCali.size());
        System.out.println("avión Bucaramanga " + avionBucaramanga.size());
        System.out.println("avión Barranquilla " + avionBarranquilla.size());
    }


//    // Creando 10 equipajes
//    var maleta1 = new Equipaje("Juan", "cali", "L", 20);
//    var maleta2 = new Equipaje("Ana", "bogota", "M", 18);
//    var maleta3 = new Equipaje("Luis", "cali", "S", 12);
//    var maleta4 = new Equipaje("Marta", "medellin", "L", 25);
//    var maleta5 = new Equipaje("Pedro", "cali", "M", 15);
//    var maleta6 = new Equipaje("Sofía", "bogota", "S", 10);
//    var maleta7 = new Equipaje("Camilo", "medellin", "L", 30);
//    var maleta8 = new Equipaje("Valentina", "cali", "M", 19);
//    var maleta9 = new Equipaje("Andrés", "medellin", "S", 11);
//    var maleta10 = new Equipaje("Laura", "cali", "L", 22);
//
//    // Registrar equipajes en la cola general
//    var colaGeneral = new ColaGeneral();
//        colaGeneral.registrarEquipaje(maleta1);
//        colaGeneral.registrarEquipaje(maleta2);
//        colaGeneral.registrarEquipaje(maleta3);
//        colaGeneral.registrarEquipaje(maleta4);
//        colaGeneral.registrarEquipaje(maleta5);
//        colaGeneral.registrarEquipaje(maleta6);
//        colaGeneral.registrarEquipaje(maleta7);
//        colaGeneral.registrarEquipaje(maleta8);
//        colaGeneral.registrarEquipaje(maleta9);
//        colaGeneral.registrarEquipaje(maleta10);
}
