package main.java;

//TODO Rename spreadsheetClasses folder

import main.java.services.ProductService;
import main.java.spreadsheetClasses.Funder;
import main.java.spreadsheetClasses.Mortgage;
import main.java.spreadsheetClasses.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = ProductService.getProductsFromCsv("products");
        List<Funder> funders = new ArrayList<>();
        List<Mortgage> mortgages = new ArrayList<>();


        /**
         * Load spreadsheet data into POJOs
         * Find fair distribution for mortgages to funders
         *   - Order mortgages by loan amount and distribute among funders going down the list
         *      - If two funders could receive the same mortgage
         *          - Give it to funder with lowest total loaned amount
         *          - If equal, Random.next()
         * Load results into POJO(s) and output
         */


    }
}