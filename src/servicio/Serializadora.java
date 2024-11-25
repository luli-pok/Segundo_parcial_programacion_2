
package servicio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serializadora {
    // Método genérico para serializar listas
    public static <T extends Serializable> void serializarLista(List<T> lista, String path) {
        try (FileOutputStream archivo = new FileOutputStream(path);
             ObjectOutputStream salida = new ObjectOutputStream(archivo)) {
            salida.writeObject(lista);
            System.out.println("Lista serializada y guardada en: " + path);
        } catch (IOException ex) {
            System.err.println("Error al serializar la lista: " + ex.getMessage());
        }
    }

    // Método genérico para deserializar listas
    public static <T extends Serializable> List<T> deserializarLista(String path) {
        List<T> lista = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(path))) {
            lista = (List<T>) entrada.readObject();
            System.out.println("Lista deserializada desde: " + path);
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Error al deserializar la lista: " + ex.getMessage());
        }
        return lista;
    }
    
    
}
