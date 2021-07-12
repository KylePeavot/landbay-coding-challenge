package test.java.services;

import main.java.entities.Funder;
import main.java.entities.Product;
import main.java.services.FunderService;
import main.java.services.ProductService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void setUp(){
        productService = new ProductService();
    }

    @Test()
    public void testGetFromCsvReturnsListOfFunders() {
        List<Product> products = productService.getFromCsv("src/test/testResources/test_products.csv");

        assertTrue(products.size() > 0);
    }

}