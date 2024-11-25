
package modelo;

public interface CSVSerializable<T> {
    String toCSV();
    T fromCSV(String csv);
    String getCSVHeader();
}


