package main.java.entities;

public class Mortgage implements Comparable<Mortgage> {

    private final int mortgageId;
    private final int loanAmount;
    private final String productId;
    private final String postcode;

    public Mortgage(int mortgageId, int loanAmount, String productForMortgage, String postcode) {
        this.mortgageId = mortgageId;
        this.loanAmount = loanAmount;
        this.productId = productForMortgage;
        this.postcode = postcode;
    }

    public int getMortgageId() {
        return mortgageId;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public String getProductId() {
        return productId;
    }

    public String getPostcode() {
        return postcode;
    }

    @Override
    public String toString() {
        return "id: " + mortgageId + ", loan amount: " + loanAmount + ", product: " + productId + ", postcode: " + postcode;
    }

    @Override
    public int compareTo(Mortgage o) {
        return Integer.compare(this.loanAmount, o.loanAmount);
    }
}
