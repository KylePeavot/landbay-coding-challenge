package main.java.entities;

import java.util.List;

public class Funder {

    String funderCodeName;
    List<Product> desiredProducts;

    public Funder(String funderCodeName, List<Product> desiredProducts) {
        this.funderCodeName = funderCodeName;
        this.desiredProducts = desiredProducts;
    }

    public String getFunderCodeName() {
        return funderCodeName;
    }

    public List<Product> getDesiredProducts() {
        return desiredProducts;
    }
}
