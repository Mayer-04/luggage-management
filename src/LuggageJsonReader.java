import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import luggage.Equipaje;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

// LectorJsonDeEquipajes
public class LuggageJsonReader {
    private final Gson gson = new Gson();
    private final Path path;

    public LuggageJsonReader(String filePath) {
        this.path = Path.of(filePath);
    }

    public List<Equipaje> cargarDatos() {
        try {
            String json = Files.readString(path);

            // Definir el tipo de dato: Lista de Luggage
            Type listType = new TypeToken<List<Equipaje>>() {
            }.getType();

            // Convertir JSON a lista de objetos Java
            return gson.fromJson(json, listType);

        } catch (IOException e) {
            throw new RuntimeException("Error leyendo el archivo JSON", e);
        }
    }
}
