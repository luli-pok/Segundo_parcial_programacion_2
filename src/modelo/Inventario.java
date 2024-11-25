
package modelo;


import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import servicio.Almacenable;
import servicio.Serializadora;

public class Inventario<T extends CSVSerializable<T>> implements Almacenable<T>{
    private List<T> items = new ArrayList<>();

    @Override
    public void agregar(T item) {
        if (item == null){
            throw new IllegalArgumentException("no deben almacenar nulos");
        }
        System.out.println("producto almacenado");
        items.add(item);
        
    }

    @Override
    public T obtener(int indice) {
        validarIndice(indice);
        return items.get(indice);
        
    }

    @Override
    public void eliminar(int indice) {
       validarIndice(indice);
        items.remove(indice); 
    }

    @Override
    public int tamanio() {
        return items.size();
        
    }
    private void validarIndice(int indice){
        if(indice < 0 || indice >= items.size()){
            throw new IndexOutOfBoundsException("error en indice");
        }
    }


    @Override
    public List<T> filtrar(Predicate<? super T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T item : items) {
            if (criterio.test(item)) {
                resultado.add(item);
            }
        }
        return resultado;
    }

    

    // Guardar inventario en CSV
    public void guardarEnCSV(String path) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            // Escribir encabezado
            writer.write(items.get(0).getCSVHeader());
            writer.newLine();

            // Escribir datos
            for (T item : items) {
                writer.write(item.toCSV());
                writer.newLine();
            }
            System.out.println("Inventario guardado en archivo CSV.");
        } catch (IOException e) {
            System.err.println("Error al guardar el inventario en CSV: " + e.getMessage());
        }
    }

    // Cargar inventario desde CSV
    public void cargarDesdeCSV(String path, Function<String, T> fromCSV) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String linea;
            reader.readLine(); // Saltar encabezado
            while ((linea = reader.readLine()) != null) {
                T item = fromCSV.apply(linea);
                if (item != null) {
                    items.add(item);
                }
            }
            System.out.println("Inventario cargado desde archivo CSV.");
        } catch (IOException e) {
            System.err.println("Error al cargar el inventario desde CSV: " + e.getMessage());
        }
    }

    // Guardar el inventario en un archivo binario
    public void guardarEnBinario(String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(items);
            System.out.println("Inventario guardado en archivo binario.");
        } catch (IOException e) {
            System.err.println("Error al guardar el inventario en binario: " + e.getMessage());
        }
    }

    // Cargar inventario desde archivo binario
    @SuppressWarnings("unchecked")
    public void cargarDesdeBinario(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            List<T> cargados = (List<T>) ois.readObject();
            items.clear();
            items.addAll(cargados);
            System.out.println("Inventario cargado desde archivo binario.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar el inventario desde binario: " + e.getMessage());
        }
    }

    @Override
    public void paraCadaElemento(Consumer<? super T> accion) {
        for (T item : items) {
            accion.accept(item);
        }
    }

    @Override
    public List<T> transformar(Function<? super T, ? extends T> transformacion) {
        List<T> toReturn = new ArrayList<>();
        for(T item: items){
            toReturn.add(transformacion.apply(item));
        }
        
        return toReturn;
    }
    

    public void ordenar() {
        items.sort(null);
    }

    public void ordenar(Comparator<? super T> comparador) {
        items.sort(comparador);
    }
    @Override
    public Iterator<T> iterator() {
        
        if(!items.isEmpty() && items.get(0) instanceof Comparable ){
            return iteratorNatural();
        }
        return iterator((Comparator<? super T>) Comparator.naturalOrder());
        //return items.iterator();
    }
    
    public Iterator<T> iterator(Comparator<? super T> comparador) {
        
        List<T> aux = new ArrayList<>(items);
        aux.sort(comparador);
        return aux.iterator();
    }
    private Iterator<T> iteratorNatural(){
        List<T> aux = new ArrayList<>(items);
        aux.sort(null);
        return aux.iterator();
        
    }
}