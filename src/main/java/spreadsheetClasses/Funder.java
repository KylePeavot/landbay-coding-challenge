package main.java.spreadsheetClasses;

import java.util.List;

public class Funder {

    String funderCodeName;
    List<Product> desiredProducts;

    public Funder(String funderCodeName, List<Product> desiredProducts) {
        this.funderCodeName = funderCodeName;
        this.desiredProducts = desiredProducts;
    }
}
