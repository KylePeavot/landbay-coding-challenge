package main.java.services;

import main.java.entities.Mortgage;
import main.java.interfaces.CsvService;
import main.java.utils.CsvHelper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * For logic concerning Mortgage
 */
public class MortgageService implements CsvService<Mortgage> {

    @Override
    public List<Mortgage> getFromCsv(String filename) {
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
