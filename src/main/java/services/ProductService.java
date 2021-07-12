package main.java.services;

import main.java.entities.Product;
import main.java.interfaces.CsvService;
import main.java.utils.CsvHelper;

import java.util.List;
import java.util.stream.Collectors;


/**
 * For logic concerning Product(s)
 */
public class ProductService implements CsvService<Product> {

    @Override
    public List<Product> getFromCsv(String filename) {
        return CsvHelper.getAllLinesFromCsvWithoutHeaders(filename).stream()
            .map(line -> {
                String[] productData = line.split(",");
                return new Product(productData[0], Double.parseDouble(productData[1].replace('%', ' ')), productData[2]);
            }).collect(Collectors.toList());
    }

}
