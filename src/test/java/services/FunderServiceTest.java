package test.java.services;

import main.java.entities.Funder;
import main.java.services.FunderService;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.*;

public class FunderServiceTest {

    private FunderService funderService;

    @Before
    public void setUp(){
        funderService = new FunderService();
    }

    @Test()
    public void testGetFromCsvReturnsListOfFunders() {
        List<Funder> funders = funderService.getFromCsv("src/test/testResources/test_funded_products_by_funder.csv");

        assertTrue(funders.size() > 0);
    }

}