package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import domain.Equipaje;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class LuggageJsonReader {
    private final Gson gson = new Gson();
    private final Path path;

    public LuggageJsonReader(String filePath) {
        this.path = Path.of(filePath);
    }

    /**
     * Lee un archivo JSON con la ruta especificada en el constructor y devuelve
     * una lista de objetos {@link Equipaje} correspondientes a los datos en el
     * archivo.
     * <p>
     * Si ocurre un error al leer el archivo, se lanza una excepción de tipo
     * {@link IllegalArgumentException} con el mensaje "Error leyendo el archivo
     * JSON" y el objeto {@link IOException} original como causa.
     *
     * @return una lista de objetos {@link Equipaje} con los datos del archivo JSON
     */
    public List<Equipaje> cargarDatos() {
        try {
            String json = Files.readString(path);

            Type tipoDeLista = TypeToken.getParameterized(List.class, Equipaje.class).getType();

            return gson.fromJson(json, tipoDeLista);

        } catch (IOException e) {
            throw new IllegalArgumentException("Error leyendo el archivo JSON", e);
        }
    }
}
