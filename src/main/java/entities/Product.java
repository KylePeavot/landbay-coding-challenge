package main.java.entities;

public class Product {

    private final String productId;
    private final Double rate;
    private final String name;

    public Product(String productId, Double rate, String name) {
        this.productId = productId;
        this.rate = rate;
        this.name = name;
    }
}
