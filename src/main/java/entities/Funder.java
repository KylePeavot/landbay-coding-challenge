package main.java.entities;

import java.util.ArrayList;
import java.util.List;

public class Funder {
    private final String codeName;
    private final List<String> desiredProducts;
    private final List<Mortgage> fundedMortgages;

    public Funder(String funderCodeName, List<String> desiredProducts) {
        this.codeName = funderCodeName;
        this.desiredProducts = desiredProducts;
        this.fundedMortgages = new ArrayList<>();
    }

    public String getCodeName() {
        return codeName;
    }

    public List<String> getDesiredProducts() {
        return desiredProducts;
    }

    public void addToDesiredProducts(String productId) {
        this.desiredProducts.add(productId);
    }

    public void addToFundedMortgages(Mortgage mortgage) {
        fundedMortgages.add(mortgage);
    }

    public int totalMoneyDeployed() {
        int total = 0;

        for (Mortgage m : this.fundedMortgages) {
            total += m.getLoanAmount();
        }

        return total;
    }
}
