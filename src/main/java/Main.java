package main.java;

//TODO Rename spreadsheetClasses folder

import main.java.entities.Funder;
import main.java.entities.Mortgage;
import main.java.services.FunderService;
import main.java.services.MortgageService;
import main.java.services.ProductService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var products = ProductService.getProductsFromCsv("products");
        var funders = FunderService.getFundersFromCsv("funded_products_by_funder");
        var mortgages = MortgageService.sortMortgagesByLoanAmount(MortgageService.getMortgagesFromCsv("mortgages"));

        List<String> boughtProducts = new ArrayList<>();

        //for each mortgage
        for (var mortgage : mortgages) {
            String productId = mortgage.getProduct();

            //if the product for a mortgage hasn't been allocated yet
            if (!boughtProducts.contains(productId)) {
                List<Funder> fundersForCurrentMortgage = funders.stream()
                    .filter(funder -> funder.getDesiredProducts().contains(productId))
                    .collect(Collectors.toList());


                //if there exists a funder willing to fund the current mortgage
                if (fundersForCurrentMortgage.size() > 0) {
                    //Give the mortgage to the funder with the least money deployed
                    Funder leastDeployedMoney = fundersForCurrentMortgage.stream()
                        .min((o1, o2) -> {
                            if (o1.totalMoneyDeployed() < o2.totalMoneyDeployed()) return -1;
                            else if (o1.totalMoneyDeployed() < o2.totalMoneyDeployed()) return 1;
                            else return 0;
                        })
                        .get();

                    leastDeployedMoney.addToFundedMortgages(mortgage);

                    boughtProducts.add(mortgage.getProduct());
                }
            }
        }

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