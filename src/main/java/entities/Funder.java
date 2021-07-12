package main.java.entities;

import java.util.ArrayList;
import java.util.List;

public class Funder {
    private final String codeName;
    private final List<String> desiredProducts;

    public Funder(String funderCodeName, List<String> desiredProducts) {
        this.codeName = funderCodeName;
        this.desiredProducts = desiredProducts;
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
}
