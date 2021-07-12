package main.java.interfaces;

import java.util.List;

public interface CsvService<T> {

    public List<T> getFromCsv(String filename);

}