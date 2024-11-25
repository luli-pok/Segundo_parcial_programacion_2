
package modelo;


import java.io.Serializable;

public class Libro implements Comparable<Libro>, CSVSerializable<Libro>, Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String titulo;
    private String autor;
    private Categoria categoria;

    public Libro(int id, String titulo, String autor, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    

    @Override
    public int compareTo(Libro other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Libro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", categoria=" + categoria + '}';
    }

    

    @Override
    public String toCSV() {
        return id + "," + titulo + "," + autor + "," + categoria;
    }
    @Override
    public String getCSVHeader() {
        return "id,titulo,autor,categoria";
    }
    
    @Override
    public  Libro fromCSV(String linea) {
        String[] datos = linea.split(",");
        if (datos.length == 4) {
            int id = Integer.parseInt(datos[0]);
            String titulo = datos[1];
            String autor = datos[2];
            Categoria categoria = Categoria.valueOf(datos[3]);
            return new Libro(id, titulo, autor, categoria);
        }
        return null;
    }

    

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if(o instanceof Libro l){
           return Integer.compare(id, l.id) == 0;
        }
        if(o instanceof Categoria c){
             return categoria.equals(c);
        }
        return false;
    }
}

