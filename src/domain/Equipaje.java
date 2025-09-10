package domain;

/**
 * La clase {@code Equipaje} representa la información asociada a un equipaje
 * registrado en el sistema. Contiene datos del pasajero, destino,
 * * categoría del tiquete y el peso del equipaje.
 *
 * @param pasajero         nombre del pasajero dueño del equipaje
 * @param destino          ciudad de destino del equipaje
 * @param categoriaTiquete Categoría del tiquete del pasajero (por ejemplo: L, M, S)
 * @param peso             peso del equipaje en kilogramos
 */
public record Equipaje(String pasajero,
                       String destino,
                       String categoriaTiquete,
                       int peso) {
}