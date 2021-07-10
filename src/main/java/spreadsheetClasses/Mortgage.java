package main.java.spreadsheetClasses;

public class Mortgage {

    int mortgageId;
    int loanAmount;
    String product;
    String postcode;

    public Mortgage(int mortgageId, int loanAmount, String productForMortgage, String postcode) {
        this.mortgageId = mortgageId;
        this.loanAmount = loanAmount;
        this.product = productForMortgage;
        this.postcode = postcode;
    }

    public int getMortgageId() {
        return mortgageId;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public String getProduct() {
        return product;
    }

    public String getPostcode() {
        return postcode;
    }
}
