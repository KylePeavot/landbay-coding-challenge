package main.java.spreadsheetClasses;

public class Mortgage {

    int mortageId;
    int loanAmount;
    Product productForMortgage;
    String postcode;

    public Mortgage(int mortageId, int loanAmount, Product productForMortgage, String postcode) {
        this.mortageId = mortageId;
        this.loanAmount = loanAmount;
        this.productForMortgage = productForMortgage;
        this.postcode = postcode;
    }
}
