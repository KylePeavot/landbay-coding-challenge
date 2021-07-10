package main.java.services;

import main.java.spreadsheetClasses.Product;
import main.java.utils.CsvHelper;

import java.util.List;
import java.util.stream.Collectors;


/**
 * For logic concerning Product(s)
 */
public class ProductService {

    public static List<Product> getProductsFromCsv(String filename) {
        List<String> csvData = CsvHelper.getAllLinesFromCsv(filename);
        csvData.remove(0); //remove labels

        return csvData.stream().map(line -> {
            String[] productData = line.split(",");
            return new Product(productData[0], Double.parseDouble(productData[1].replace('%', ' ')), productData[2]);
        }).collect(Collectors.toList());
    }

}
