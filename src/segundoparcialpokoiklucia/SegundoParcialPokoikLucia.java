
package segundoparcialpokoiklucia;

import java.io.FileNotFoundException;
import java.io.IOException;
import modelo.Inventario;
import modelo.Libro;
import modelo.Categoria;

/**
 *
 * @author Usuario
 */
public class SegundoParcialPokoikLucia {

    
    public static void main(String[] args) {
        
            // Crear un inventario de libros
            Inventario<Libro> inventarioLibros = new Inventario<>();
            inventarioLibros.agregar(new Libro(1, "1984", "George Orwell", Categoria.ENTRETENIMIENTO));
            inventarioLibros.agregar(new Libro(2, "El señor de los anillos", "J.R.R. Tolkien", Categoria.LITERATURA));
            inventarioLibros.agregar(new Libro(3, "Cien años de soledad", "Gabriel García Márquez", Categoria.LITERATURA));
            inventarioLibros.agregar(new Libro(4, "El origen de las especies", "Charles Darwin", Categoria.CIENCIA));
            inventarioLibros.agregar(new Libro(5, "La guerra de los mundos", "H.G. Wells", Categoria.ENTRETENIMIENTO));

            // Mostrar todos los libros en el inventario
            System.out.println("Inventario de libros:");
            inventarioLibros.paraCadaElemento(libro -> System.out.println(libro));

            // Filtrar libros por categoría LITERATURA
            System.out.println("\nLibros de la categoría LITERATURA:");
            inventarioLibros.filtrar(libro -> libro.getCategoria() == Categoria.LITERATURA)
                            .forEach(libro -> System.out.println(libro));

            // Filtrar libros cuyo título contiene "1984"
            System.out.println("\nLibros cuyo título contiene '1984':");
            inventarioLibros.filtrar(libro -> libro.getTitulo().contains("1984"))
                            .forEach(libro -> System.out.println(libro));

            // Ordenar libros de manera natural (por id)
            System.out.println("\nLibros ordenados de manera natural (por id):");
            inventarioLibros.ordenar();
            inventarioLibros.paraCadaElemento(libro -> System.out.println(libro));

            // Ordenar libros por título utilizando un Comparator
            System.out.println("\nLibros ordenados por título:");
            inventarioLibros.ordenar((libro1, libro2) -> libro1.getTitulo().compareTo(libro2.getTitulo()));
            inventarioLibros.paraCadaElemento(libro -> System.out.println(libro));

            // Guardar el inventario en un archivo binario
            inventarioLibros.guardarEnBinario("src/data/libros.dat");

            // Cargar el inventario desde el archivo binario
            Inventario<Libro> inventarioCargado = new Inventario<>();
            inventarioCargado.cargarDesdeBinario("src/data/libros.dat");
            System.out.println("\nLibros cargados desde archivo binario:");
            inventarioCargado.paraCadaElemento(libro -> System.out.println(libro));

            // Guardar el inventario en un archivo CSV
            inventarioLibros.guardarEnCSV("src/data/libros.csv");
            

            

            
    
    }
    
}
