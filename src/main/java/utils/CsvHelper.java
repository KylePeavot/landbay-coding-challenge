package main.java.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * For CSV helper methods
 */
public class CsvHelper {

    public static List<String> getAllLinesFromCsvWithHeaders(String filePath) {
        try {
            return new BufferedReader(new FileReader(filePath))
                .lines()
                .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> getAllLinesFromCsvWithoutHeaders(String filename) {
        var allLines = getAllLinesFromCsvWithHeaders(filename);
        allLines.remove(0);
        return allLines;
    }

}
