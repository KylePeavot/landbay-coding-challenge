package main.java.services;

import main.java.spreadsheetClasses.Mortgage;
import main.java.utils.CsvHelper;

import java.util.List;
import java.util.stream.Collectors;

public class MortgageService {

    public static List<Mortgage> getMortgagesFromCsv(String filename) {
        return CsvHelper.getAllLinesFromCsvWithoutHeaders(filename).stream()
            .map(lines -> {
                String[] mortgageData = lines.split(",");
                return new Mortgage(
                    Integer.parseInt(mortgageData[0]),
                    Integer.parseInt(mortgageData[1]),
                    mortgageData[2],
                    mortgageData[3]
                );
            }).collect(Collectors.toList());
    }
}
