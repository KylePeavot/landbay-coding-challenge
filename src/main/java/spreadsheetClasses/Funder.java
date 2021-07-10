package main.java.spreadsheetClasses;

import java.util.ArrayList;

public class Funder {

    String funderCodeName;
    ArrayList<Product> productsToBeFunded;

    public Funder(String funderCodeName, ArrayList<Product> productsToBeFunded) {
        this.funderCodeName = funderCodeName;
        this.productsToBeFunded = productsToBeFunded;
    }
}
