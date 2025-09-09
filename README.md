# ✈️ Luggage Management (Gestor de Equipajes)

Proyecto académico en Java 24 que simula la gestión de equipajes para vuelos con distintos destinos, utilizando
estructuras de datos clásicas (colas, listas, pilas y bolsas).

El sistema permite registrar, procesar, clasificar y abordar equipajes, así como generar estadísticas y mostrar la lista
de pasajeros de cada vuelo.

## Funcionalidades

- **Ingresar equipaje**

Se generan maletas con datos aleatorios (pasajero, destino, categoría del tiquete y peso).

- **Procesar equipaje (Counter → Bodegas)**
  Las maletas se encolan y luego se envían a la lista del vuelo correspondiente.

- **Clasificación de equipajes**
  Cada maleta va a la lista de su vuelo según el destino.

    - Condición mínima: 50 equipajes por vuelo para despegar.

- **Confirmación de destino**
  Se puede cancelar un vuelo; los pasajeros entran a una lista de espera.

- **Abordaje de vuelos (Bodegas → Aviones)**
  Se cargan las maletas a los stacks de los aviones, con prioridad según categoría:

- L (1–20 / L01–L20, hasta 30kg)
- M (21–50 / M01–M30, hasta 20kg)
- S (51–100 / S01–S50, hasta 10kg)

- **Despacho de vuelos**
  Al despegar un vuelo, se reinicia el stack para el próximo vuelo con el mismo destino.

- **Estadísticas de vuelos**
  Se calculan cantidad de maletas y peso total por avión.

- **Lista de pasajeros**
  Muestra todos los pasajeros y equipajes abordados en cada vuelo.

## Tecnologías utilizadas

- **Lenguaje:** Java 24
- **IDE:** IntelliJ IDEA (JetBrains)
- **Dependencias externas:**
    - gson-2.13.1.jar → para serialización y deserialización de datos
- **Gestión de dependencias:** manual (no se usa Maven/Gradle)

## Estructura del proyecto

```shell
luggage-management/
├── src/                       # Código fuente
│   ├── datastructures/        # Estructuras de datos personalizadas
│   ├── domain/                # Clases principales (Avion, Bodega, Equipaje, etc.)
│   ├── resources/             # Recursos (archivos JSON)
│   ├── services/              # Lógica de negocio
│   ├── util/                  # Utilidades y validaciones
│   └── Main.java              # Punto de entrada al programa
├── gson-2.13.1.jar            # Librería externa
├── .gitignore                 # Archivos ignorados por Git
└── README.md                  # Documentación del proyecto
```

## Ejecución del proyecto

### Desde IntelliJ IDEA

1. Clona el repositorio.
2. Abre la carpeta del proyecto en IntelliJ IDEA.
3. Asegúrate de que el SDK de Java 24 esté configurado.
4. Añade `gson-2.13.1.jar` al **Project Structure → Libraries**.
5. Ejecuta la clase `Main`.

### Desde terminal (sin IntelliJ)

1. **Compila el código:**

```shell
javac -cp gson-2.13.1.jar src/**/*.java
```

2. **Ejecuta el programa:**

```shell
java -cp gson-2.13.1.jar;src Main
```