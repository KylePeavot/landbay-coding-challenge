package main.java.services;

import main.java.spreadsheetClasses.Product;
import main.java.utils.CsvHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * For logic concerning Funder(s)
 */
public class FunderService {

    public static HashMap<String, ArrayList<String>> getFundersWithDesiredProductsFromCsv(String filename) {
        var fundersWithDesiredProducts = new HashMap<String, ArrayList<String>>();

        for (String csvRow : CsvHelper.getAllLinesFromCsvWithoutHeaders(filename)) {
            String[] funderData = csvRow.split(",");
            String funderName = funderData[0];
            String productId = funderData[1];

            if (!fundersWithDesiredProducts.containsKey(funderName)) {
                fundersWithDesiredProducts.put(funderName, new ArrayList<>(List.of(productId)));
            } else {
                var updatedProducts = fundersWithDesiredProducts.get(funderName);
                updatedProducts.add(productId);

                fundersWithDesiredProducts.put(funderName, updatedProducts);
            }
        }


        return fundersWithDesiredProducts;
    }

}
