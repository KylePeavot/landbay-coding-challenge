package main.java.services;

import main.java.entities.Funder;
import main.java.interfaces.CsvService;
import main.java.utils.CsvHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * For logic concerning Funder(s)
 */
public class FunderService implements CsvService<Funder> {

    @Override
    public List<Funder> getFromCsv(String filename) {
        var funders = new HashSet<Funder>();

        for (String csvRow : CsvHelper.getAllLinesFromCsvWithoutHeaders(filename)) {
            String[] funderData = csvRow.split(",");
            String funderName = funderData[0];
            String productId = funderData[1];

            var funderTemp = funders.stream()
                .filter(funder -> funder.getCodeName().equals(funderName))
                .findFirst()
                .orElse(new Funder(funderName, new ArrayList<>()));

            funderTemp.addToDesiredProducts(productId);

            funders.add(funderTemp);
        }
        return new ArrayList<>(funders);
    }
}
