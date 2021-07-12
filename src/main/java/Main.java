package main.java;

//TODO Rename spreadsheetClasses folder

import main.java.entities.Allocation;
import main.java.entities.Mortgage;
import main.java.services.AllocationService;
import main.java.services.FunderService;
import main.java.services.MortgageService;
import main.java.services.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var products = ProductService.getProductsFromCsv("products");
        var funders = FunderService.getFundersFromCsv("funded_products_by_funder");
        var mortgages = MortgageService.sortMortgagesByLoanAmount(MortgageService.getMortgagesFromCsv("mortgages"));

        List<String> boughtProducts = new ArrayList<>();
        List<Allocation> allocations = funders.stream().map(Allocation::new).collect(Collectors.toList());

        //for each mortgage
        for (var mortgage : mortgages) {
            //if the product for a mortgage hasn't been allocated yet
            if (!boughtProducts.contains(mortgage.getProduct())) {
                //find the funders willing to buy it
                List<Allocation> potentialAllocationsForMortgage = allocations.stream()
                    .filter(allocation -> allocation.getFunder().getDesiredProducts().contains(mortgage.getProduct()))
                    .collect(Collectors.toList());

                //if there exists a funder willing to buy the product on the current mortgage
                if (potentialAllocationsForMortgage.size() > 0) {
                    //Give the mortgage to the funder with the least money deployed
                    Allocation leastDeployedMoney = potentialAllocationsForMortgage.stream()
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

        allocations = AllocationService.distributeMortgagesFairly(allocations, 0.8, 20);

        //TODO SOLID-ify the project
        //TODO add comments
        //TODO add tests
        //TODO finish up all TODOs
        //TODO final pass

        for (Allocation allocation : allocations) {
            System.out.println(allocation.toString());
        }
    }
}