package main.java.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * For various CSV helper methods
 */
public class CsvHelper {

    public static List<String> getAllLinesFromCsv(String filename) {
        try {
            return new BufferedReader(new FileReader("src/main/resources/" + filename + ".csv"))
                .lines()
                .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
