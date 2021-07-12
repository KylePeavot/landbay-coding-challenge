package main.java;

import main.java.services.AllocationService;
import main.java.services.FunderService;
import main.java.services.MortgageService;
import main.java.services.ProductService;

public class Main {

    private final FunderService funderService;
    private final MortgageService mortgageService;
    private final AllocationService allocationService;
    private final ProductService productService;

    public Main() {
        this.funderService = new FunderService();
        this.mortgageService = new MortgageService();
        this.allocationService = new AllocationService();
        this.productService = new ProductService();
    }

    public void run() {
        var products = productService.getFromCsv("src/main/resources/products.csv");
        var funders = funderService.getFromCsv("src/main/resources/funded_products_by_funder.csv");
        var mortgages = mortgageService.getFromCsv("src/main/resources/mortgages.csv");

        var allocations = allocationService.distributeMortgagesAcrossFunders(mortgages, funders);

        allocations.forEach(System.out::println);
    }

    public static void main(String[] args) {
        var main = new Main();
        main.run();
    }
}