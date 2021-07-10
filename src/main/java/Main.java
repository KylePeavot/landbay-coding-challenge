package main.java;

//TODO Rename spreadsheetClasses folder

import main.java.services.FunderService;
import main.java.services.MortgageService;
import main.java.services.ProductService;
import main.java.entities.Mortgage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        var products = ProductService.getProductsFromCsv("products");
        var funders = FunderService.getFundersWithDesiredProductsFromCsv("funded_products_by_funder");
        var mortgages = MortgageService.sortMortgagesByLoanAmount(MortgageService.getMortgagesFromCsv("mortgages"));

        List<String> boughtProducts = new ArrayList<>();
        Map<String, ArrayList<Mortgage>> funderToMortgages = new HashMap<>(); //funder to product mapping

        for (var mortgage : mortgages) {
            if (!boughtProducts.contains(mortgage.getProduct())) {

            }
        }


        //If not yet "bought", find funders willing to buy product
        //Give mortgage and product to the funder
        //If decision to be made between multiple funders, either random (temporarily just choose first), or choose funder with least total so far


        //This is fair because all funders are being distributed the best mortgages together.
        //Could do a final distribution checker after where I see if the funder with the most total lent can give to the least total (do they have any swappable products)


        System.out.println("done");

        /**
         * Load spreadsheet data into POJOs (done)
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