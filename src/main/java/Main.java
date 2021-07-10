package main.java;

//TODO Rename spreadsheetClasses folder

import main.java.services.FunderService;
import main.java.services.ProductService;


public class Main {
    public static void main(String[] args) {
        var products = ProductService.getProductsFromCsv("products");
        var funders = FunderService.getFundersWithDesiredProductsFromCsv("funded_products_by_funder");
//        var mortgages = MortgageService.getMortgagesFromCsv("mortgages");

        System.out.println("done");

        /**
         * Load spreadsheet data into POJOs
         * Find fair distribution for mortgages to funders
         *   - Order mortgages by loan amount and distribute among funders going down the list
         *      - If two funders could receive the same mortgage
         *          - Give it to funder with lowest total loaned amount
         *          - If equal, Random.next()
         *
         * Load results into POJO(s) and output
         */


    }
}