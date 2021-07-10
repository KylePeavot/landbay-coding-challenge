package main.java.spreadsheetClasses;

public class Product {

    String productId;
    Double rate;
    String name;

    public Product(String productId, Double rate, String name) {
        this.productId = productId;
        this.rate = rate;
        this.name = name;
    }

    public String getProductId() {
        return productId;
    }

    public Double getRate() {
        return rate;
    }

    public String getName() {
        return name;
    }
}
